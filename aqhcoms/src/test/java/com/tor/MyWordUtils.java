package com.tor;

import org.apache.commons.lang.WordUtils;
import org.junit.Test;

public class MyWordUtils {

    @Test
    public void wordUtils(){
        String s = WordUtils.capitalizeFully("ACTIVITY_GROUP_ID",new char[]{'_'}).replace("_","");
        String s1 = s.substring(0, 1).toLowerCase() + s.substring(1);
        System.out.println(s);
        System.out.println(s1);
    }
}
