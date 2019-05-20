package com.qijianguo.ad.constant;

import lombok.Getter;

/**
 * 物料类型
 */
@Getter
public enum CreativeMaterialType {

    JPG(1, "jpg"),

    BMP(2, "bmp"),

    MP4(3, "mp4"),

    AVI(4, "avi"),

    TET(4, "txt ");

    private Integer type;

    private String desc;

    CreativeMaterialType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
