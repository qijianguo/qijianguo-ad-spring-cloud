package com.qijianguo.ad.vo;

import com.qijianguo.ad.entity.Creative;
import lombok.Data;

import java.util.Date;

@Data
public class CreativeRequest {

    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer weight;
    private Long size;
    private Long duration;
    private Integer auditStatus;
    private Long userId;
    private String url;

    public Creative convertToEntity() {
        Creative creative = new Creative();
        creative.setName(name);
        creative.setType(type);
        creative.setMaterialType(materialType);
        creative.setHeight(height);
        creative.setWeight(weight);
        creative.setSize(size);
        creative.setDuration(duration);
        creative.setAuditStatus(auditStatus);
        creative.setUserId(userId);
        creative.setUrl(url);
        creative.setCreateDate(new Date());
        creative.setUpdateDate(creative.getCreateDate());

        return creative;
    }
}
