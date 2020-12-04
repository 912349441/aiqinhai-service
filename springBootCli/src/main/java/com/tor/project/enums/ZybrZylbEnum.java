package com.tor.project.enums;

import lombok.Getter;

@Getter
public enum ZybrZylbEnum {
    type1(1,"普通住院"),
    type2(2,"外伤住院"),
    type3(3,"生育住院"),
    type4(4,"日间小手术"),
    type5(5,"单病种住院");

    private Integer zylb;
    private String lbmc;

    private ZybrZylbEnum(Integer zylb,String lbmc){
        this.zylb = zylb;
        this.lbmc = lbmc;
    }

    public static Integer getCodeByName(String lbmc){
        if(null != lbmc){
            for (ZybrZylbEnum anEnum : values()) {
                if(lbmc.equals(anEnum.getLbmc())){
                    return anEnum.getZylb();
                }
            }
        }
        return 0;
    }
}
