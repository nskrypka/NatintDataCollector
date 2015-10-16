package com.natint.task;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by skn on 08/10/2015.
 */
@Component
public class TaskExecutor {

    private Logger logger = Logger.getLogger(TaskExecutor.class);

    ExecutorService executor;

    @Autowired
    ObjectFactory<UiTask> uiTaskObjectFactory;
    @Autowired
    ObjectFactory<ApiTask> apiTaskObjectFactory;
    int post;
    static int startIndex = 1;
    Task task;

    public TaskExecutor(ExecutorService executor) {
        this.executor = executor;
        this.post = startIndex++;
    }

    public TaskExecutor() {
    }

    public int execute(final Task task) throws ExecutionException, InterruptedException {
        this.task = task;
        executor.execute(task);
        return task.getId();
    }

    public void execute(String resourcePath) throws IOException, ExecutionException, InterruptedException {
        List<Task> taskList = getTasks(resourcePath);
        for (Task task : taskList) {
            executor.execute(task);
        }
    }

    public List<Task> getTasks(String resourcePath) throws IOException {
        List<Task> taskList = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(resourcePath)));
        HSSFSheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            Task task;
            Map<String, String> params = new HashMap<>();
            Cell firstCell = row.getCell(0);
            switch (firstCell.getStringCellValue().toLowerCase()) {
                case "ui":
                    params.put("siteName", row.getCell(1).getStringCellValue());
                    params.put("searchCriteria", row.getCell(2).getStringCellValue());
                    Double resultAmount = row.getCell(3).getNumericCellValue();
                    params.put("resultsAmount", String.valueOf(resultAmount.intValue()));
                    task = uiTaskObjectFactory.getObject();
                    task.init(params);
                    break;
                default:
                    params.put("siteName", row.getCell(1).getStringCellValue());
                    Double postId = row.getCell(2).getNumericCellValue();
                    params.put("postId", String.valueOf(postId.intValue()));
                    task = apiTaskObjectFactory.getObject();
                    task.init(params);
            }
            taskList.add(task);
        }
        return taskList;
    }

    public void shutdown() throws InterruptedException {
        executor.shutdown();
        while (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            logger.info("Wait for execution of task # " + task.getId());
        }
    }
}
