package com.natint.data;

/**
 * Created by ivaa on 10/6/2015.
 */
public class BaseData implements IData{

    public String getLink() {
        return link;
    }

    public String getPrice() {
        return price;
    }

    public BaseData(String link, String price) {
        this.link = link;
        this.price = price;
    }

    private String link;
    private String price;

    @Override
    public String toString() {
        return "BaseData{" +
                "link='" + link + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
