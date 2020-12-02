package com.tor.project.dto;

import cn.com.itsea.face.v2.client.entity.PersonImageCollected;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Fn35DTO {

    private boolean succ = false;
    List<PersonImageCollected> imgs;

    public PersonImageCollected latest(){
        if(CollectionUtils.isEmpty(imgs)){
            return null;
        }
        if(imgs.size() == 1){
            return imgs.get(0);
        }
        imgs.sort((o1,o2)-> o1.getCrtTime() > o2.getCrtTime() ? -1 : 1);
        return imgs.get(0);
    }
}
