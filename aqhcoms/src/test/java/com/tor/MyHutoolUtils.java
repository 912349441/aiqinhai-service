package com.tor;

import cn.hutool.http.HttpUtil;
import org.junit.Test;

public class MyHutoolUtils {

    @Test
    public void hutoolTest(){
        String s = HttpUtil.get("http://120.78.154.0:8000/shop/product/list");
        System.out.println(s);
    }
}
