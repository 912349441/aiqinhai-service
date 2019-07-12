/**
 * 
 */
package com.tor.common.generator.service;

import com.tor.sys.entity.Config;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 1992lcg@163.com
 * @Time 2017年9月6日
 * @description
 * 
 */
@Service
public interface GeneratorService {
	List<Map<String, Object>> list();

	byte[] generatorCode(String[] tableNames);

    List<Config> findListByKvType(int kvType);
}
