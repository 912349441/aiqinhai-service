package com.tor;

import com.tor.common.entity.Config;
import com.tor.common.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AqhcomsApplicationTests {

    @Autowired
    private ConfigService configService;

	@Test
	public void contextLoads() {
        Condition condition = new Condition(Config.class,false,false);
        Example.Criteria criteria = condition.createCriteria();
        criteria.orEqualTo("kvType", 4400);
        List<Config> list = configService.findByCondition(condition);
        List<Config> collect = list.stream().filter(a -> a.getKvtype()!=null && ( a.getKvtype().equals(4400) || a.getKvtype().equals(4401))).collect(Collectors.toList());
        Map<String, String> config = new HashMap<>();
        collect.forEach(kv -> config.put(kv.getK(), kv.getV()));
        System.out.println(config);
    }

}
