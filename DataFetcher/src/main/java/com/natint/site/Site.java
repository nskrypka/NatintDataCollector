package com.natint.site;

import com.natint.data.IData;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by ivaa on 10/6/2015.
 */
public abstract class Site extends Endpoint{

    private PhantomJSDriver driver;
    private String searchCriteria;
    private int resultsAmount;

    public Site(PhantomJSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Site() {
    }

    protected PhantomJSDriver getDriver() {
        return driver;
    }

    public Site withParams(String _searchCriteria, int _resultsAmount) {
        searchCriteria = _searchCriteria;
        resultsAmount = _resultsAmount;
        return this;
    }

    public List<IData> collectData() {
        prepare();
        open();
        doSearch(searchCriteria);
        List<IData> results = getResults(resultsAmount);
        System.out.println("Result of ui task execution : " + results);
        finish();
        return results;
    }

    private void finish() {
        driver.quit();
    }

    protected abstract List<IData> getResults(int resultsAmount);

    protected abstract void doSearch(String searchCriteria);

    protected abstract void open();

    private void prepare() {
        URL url = Site.class.getClassLoader().getResource("phantomjs.exe");
        String phantomjsPath = null;
        try {
            phantomjsPath = URLDecoder.decode(url.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true);
        ((DesiredCapabilities) caps).setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                phantomjsPath
        );

        driver = new PhantomJSDriver(caps);
        driver.setLogLevel(Level.OFF);
    }

}
