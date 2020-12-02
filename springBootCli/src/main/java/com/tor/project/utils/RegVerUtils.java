/**
 * 当前项目为哪一个版本
 */
package com.tor.project.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class RegVerUtils {

    private final static String HANG_ZHOU = "Hangzhou";
    private final static String JIN_HUA = "Jinhua";
    private final static String HAI_YAN = "Haiyan";
    private final static String JIANG_YIN = "Jiangyin";
    private final static String JI_AN = "JiAn";
    private final static String QING_HAI = "QingHai";
    private final static String SI_HONG = "SiHong";

    private static String version;

    @Value("${regVer}")
    public synchronized void setRegVer(String regVer) {
        version = regVer;
    }

    public static boolean isHz() {
        return HANG_ZHOU.equals(version);
    }

    public static boolean isJh() {
        return JIN_HUA.equals(version);
    }

    public static boolean isHy() {
        return HAI_YAN.equals(version);
    }

    public static boolean isJy() {
        return JIANG_YIN.equals(version);
    }

    public static boolean isJa() {
        return JI_AN.equals(version);
    }

    public static boolean isSiHong() {
        return SI_HONG.equals(version);
    }

    public static boolean isQingHai() {
        return QING_HAI.equals(version);
    }
}
