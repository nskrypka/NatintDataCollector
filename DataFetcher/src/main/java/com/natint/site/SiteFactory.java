package com.natint.site;

import com.natint.site.Ebay.Ebay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by skn on 07/10/2015.
 */
@Component
public class SiteFactory {
    private String ebayLink;
    private String amazonLink;

    @Autowired
    public SiteFactory(String ebayLink, String amazonLink) {
        this.amazonLink = amazonLink;
        this.ebayLink = ebayLink;
    }

    public Site getSite (String siteName) {
        switch (siteName.toUpperCase()) {
            case "EBAY" :
                return new Ebay(ebayLink);
            case "AMAZON" :
                return new Amazon(amazonLink);
            default :
                throw new IllegalArgumentException("Possible values for property 'siteName' are : EBAY, AMAZON");
        }
    }
}
