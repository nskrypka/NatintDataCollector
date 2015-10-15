package com.natint.site;

import com.natint.data.IData;

import java.util.List;

/**
 * Created by skn on 12/10/2015.
 */
public abstract class Endpoint {
     public abstract List<IData> collectData();
}
