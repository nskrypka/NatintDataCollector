package com.natint.site.Ebay;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextBlock;

/**
 * Created by ivaa on 10/7/2015.
 */

public class SearchResultElement extends HtmlElement {
    public TextBlock getPrice() {
        return price;
    }

    public Link getLink() {
        return link;
    }

    @Name("Llink")
    @FindBy(xpath = "//h3[@class='lvtitle']/a")
    private Link link;

    @Name("Price")
    @FindBy(className = "lvprice")
    private TextBlock price;
}
