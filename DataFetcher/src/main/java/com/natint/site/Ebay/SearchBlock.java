package com.natint.site.Ebay;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by ivaa on 10/7/2015.
 */
public class SearchBlock extends HtmlElement {
    @Name("Search box")
    @FindBy(css = "input.gh-tb")
    private TextInput searchBox;

    @Name("Search button")
    @FindBy(id = "gh-btn")
    private Button searchButton;

    public void performSearch(String searchCriteria)
    {
        searchBox.sendKeys(searchCriteria);
        searchButton.click();
    }
}
