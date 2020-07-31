package com.tor;

import org.junit.Test;

import java.util.HashSet;

public class StringUtils {

    @Test
    public void test(){
        String a = "";
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
        Object o = new Object();
        HashSet<Object> objects = new HashSet<>();
        boolean b = test1(1);
        System.out.println(b);
    }

    public boolean test1(int i){
        switch (i){
            case 1:
            case 2:return true;
            case 3:
            default:return false;
        }
    }
}
