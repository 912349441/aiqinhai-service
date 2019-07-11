package com.tor.project.service.impl;

import com.tor.project.mapper.EssayMapper;
import com.tor.project.entity.Essay;
import com.tor.project.service.EssayService;
import com.tor.generator.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Tzx on 2019/02/21.
 */
@Service
@Transactional
public class EssayServiceImpl extends AbstractService<Essay> implements EssayService {
    @Resource
    private EssayMapper essayMapper;

}
