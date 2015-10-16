package com.natint.site.Ebay;


import com.natint.site.Site;
import com.natint.data.BaseData;
import com.natint.data.Data;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ivaa on 10/6/2015.
 */
public class Ebay extends Site {

    private String ebayLink;
    @FindBy(css = "table[class='gh-tbl']")
    private SearchBlock searchBlock;

    public Ebay(String ebayLink){
        this.ebayLink = ebayLink;
    }

    @Override
    protected List<Data> getResults(int resultsAmount)
    {
        SearchResults searchResults = new SearchResults(getDriver());
        List<SearchResultElement> results = searchResults.getSearchItems();
        if (resultsAmount < results.size())
            results = results.subList(0, resultsAmount);

        LinkedList<Data> list = new LinkedList<>();
        for (SearchResultElement element : results)
            list.add(new BaseData(element.getLink().getReference(), element.getPrice().getText()));

        return list;
    }

    @Override
    protected void doSearch(String searchCriteria)
    {
        searchBlock.performSearch(searchCriteria);
    }

    @Override
    protected void open()
    {
        PageFactory.initElements(new HtmlElementDecorator(getDriver()), this);
        getDriver().get(ebayLink);
    }
}
