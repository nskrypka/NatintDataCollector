package com.natint.site.Ebay;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.List;

/**
 * Created by ivaa on 10/7/2015.
 */
public class SearchResults{
    @FindBy(css = "li[class*='sresult lvresult']")
    private List<SearchResultElement> searchItems;

    public SearchResults(WebDriver driver)
    {
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public List<SearchResultElement> getSearchItems() {
        return searchItems;
    }
}
