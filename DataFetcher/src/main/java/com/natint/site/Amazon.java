package com.natint.site;

import com.natint.data.BaseData;
import com.natint.data.Data;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skn on 07/10/2015.
 */
public class Amazon extends Site {

    private Logger logger = Logger.getLogger(this.getClass());

    private static final String AMAZON_LINK = System.getProperty("NATINT_AMAZON_LINK");

    private static final String SEARCH_INPUT = "//input[@id='twotabsearchtextbox']";
    private static final String SEARCH_BUTTON = "//input[@value='Go']";
    private static final String PRODUCT_LINK_TEMPLATE = "//li[starts-with(@id,'result_')]//a[contains(@class,'s-access-detail-page')]";
    private static final String PRODUCT_PRICE_TEMPLATE = "//div[@class='a-row']//div[contains(@class,'a-span7')]/div[1]//a[1]/span[1]";
    private static final String NEXT_PAGE_LINK = "//a[@id='pagnNextLink']";

    @Override
    protected List<Data> getResults(int resultsAmount) {
        logger.info("Method getResults started...");
        List<Data> result = new ArrayList<>();

        while (resultsAmount > 0) {
            List<WebElement> linkElements = getDriver().findElementsByXPath(PRODUCT_LINK_TEMPLATE);
            List<WebElement> priceElements = getDriver().findElementsByXPath(PRODUCT_PRICE_TEMPLATE);
            int elementsAmount = linkElements.size();
            if (resultsAmount < elementsAmount) {
                extractResult(resultsAmount, result, linkElements, priceElements);
            } else {
                extractResult(elementsAmount, result, linkElements, priceElements);
            }
            resultsAmount = resultsAmount - elementsAmount;
            clickNextPage();
        }
        logger.info("Search results : " + result.toString());
        return result;
    }

    private void clickNextPage() {
        if (!getDriver().findElementsByXPath(NEXT_PAGE_LINK).isEmpty()) {
            getDriver().findElementByXPath(NEXT_PAGE_LINK).click();
        }
    }

    private void extractResult(int amount, List<Data> result, List<WebElement> linkElements, List<WebElement> priceElements) {
        for (int i = 0; i < amount; i++) {
            WebElement productLinkElement = linkElements.get(i);
            WebElement productPriceElement = priceElements.get(i);
            result.add(new BaseData(productLinkElement.getAttribute("href"), (productPriceElement.getText())));
        }
    }

    @Override
    protected void doSearch(String searchCriteria) {
        logger.info("Method doSearch started...");
        getDriver().findElementByXPath(SEARCH_INPUT).sendKeys(searchCriteria);
        getDriver().findElementByXPath(SEARCH_BUTTON).click();
        logger.info("Search performed");
    }

    @Override
    protected void open() {
        logger.info("Method open() started...");
        getDriver().get(AMAZON_LINK);
        logger.info("Amazon site is opened");
    }
}
