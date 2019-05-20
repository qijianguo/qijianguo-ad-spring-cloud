package com.qijianguo.ad.vo;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
public class AdPlanGetRequest {

    private Long userId;

    private List<Long> adPlanIds;

    public boolean validate() {
        return userId != null && !CollectionUtils.isEmpty(adPlanIds);
    }

}
