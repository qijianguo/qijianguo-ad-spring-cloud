package com.qijianguo.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

@Data
@AllArgsConstructor
public class AdUnitRequest {

    private Long planId;
    private String unitName;

    private Integer positionType;

    private Long budget;

    public boolean validate() {
        return null != planId && !StringUtils.isEmpty(unitName)
                && positionType != null && positionType > 0 && budget != null && budget > 0;
    }
}
