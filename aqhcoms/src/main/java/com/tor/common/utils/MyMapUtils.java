package com.tor.common.utils;

import org.apache.commons.lang.WordUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyMapUtils {
    /**
     * 大写带下划线的属性列,转化为驼峰命名风格的 MAP
     * @param stringObjectMap
     * @return
     */
    public Map<String,Object> columnUpperCaseToMap(Map<String, Object> stringObjectMap){
        Map<String, Object> map = new HashMap<>();
        // 选用entrySet是因为比keySet效率要高
        Set<Map.Entry<String, Object>> entrySet = stringObjectMap.entrySet();
        for (Map.Entry<String, Object> stringObjectEntry : entrySet) {
            String key = stringObjectEntry.getKey();
            Object value = stringObjectEntry.getValue();
            if(value instanceof Date){
                value = ((Date) value).getTime();
            }
            String replace = WordUtils.capitalizeFully(key, new char[]{'_'}).replace("_", "");
            String newKey = replace.substring(0, 1).toLowerCase() + replace.substring(1);
            map.put(newKey, value);
        }
        return map;
    }

    /**
     * 大写带下划线的属性列,转化为驼峰命名风格的 BEAN
     * @param clazz
     * @param map
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public <T> T columnUpperCaseToBean(Class<T> clazz,Map<String,Object> map) throws IllegalAccessException {
        T bean = null;
        try {
            bean = clazz.newInstance();
            Map<String, Object> stringObjectMap = columnUpperCaseToMap(map);
            bean = MyBeanUtils.MapToBean(clazz, stringObjectMap);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
