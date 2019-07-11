package com.tor.common.service;

import com.tor.common.entity.Config;
import com.tor.generator.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by Tzx on 2019/07/11.
 */
public interface ConfigService extends Service<Config> {
    List<Map<String, Object>> list();

    byte[] generatorCode(String[] tableNames);

    List<Config> findListByKvType(int kvType);
}
