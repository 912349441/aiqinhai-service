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
        return HANG_ZHOU.equalsIgnoreCase(version);
    }

    public static boolean isJh() {
        return JIN_HUA.equalsIgnoreCase(version);
    }

    public static boolean isHy() {
        return HAI_YAN.equalsIgnoreCase(version);
    }

    public static boolean isJy() {
        return JIANG_YIN.equalsIgnoreCase(version);
    }

    public static boolean isJa() {
        return JI_AN.equalsIgnoreCase(version);
    }

    public static boolean isSiHong() {
        return SI_HONG.equalsIgnoreCase(version);
    }

    public static boolean isQingHai() {
        return QING_HAI.equalsIgnoreCase(version);
    }
}
