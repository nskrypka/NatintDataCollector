package com.natint.task;

import com.natint.exec.Status;
import com.natint.site.Site;
import com.natint.site.SiteFactory;

import java.util.Map;

/**
 * Created by skn on 08/10/2015.
 */
public class UiTask extends Task {

    public UiTask() {
        super();
    }

    @Override
    public void init(Map<String, String> params) {
        taskStatus.setStatus(getId(), Status.INITIALIZED);
        this.params = params;
        this.endpoint = new SiteFactory().getSite(params.get("siteName"));
        ((Site) endpoint).withParams(params.get("searchCriteria"), Integer.parseInt(params.get("resultsAmount")));
    }
}
