package com.natint.site;

import com.natint.site.Ebay.Ebay;

/**
 * Created by skn on 07/10/2015.
 */
public class SiteFactory {

    public Site getSite (String siteName) {
        switch (siteName.toUpperCase()) {
            case "EBAY" :
                return new Ebay();
            case "AMAZON" :
                return new Amazon();
            default :
                throw new IllegalArgumentException("Possible values for property 'siteName' are : EBAY, AMAZON");
        }
    }
}
