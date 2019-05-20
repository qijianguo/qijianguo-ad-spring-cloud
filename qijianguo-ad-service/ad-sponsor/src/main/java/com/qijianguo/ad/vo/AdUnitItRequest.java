package com.qijianguo.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AdUnitItRequest {

    private List<UnitIt> unitIts;

    @Data
    public static class UnitIt {
        private Long unitId;

        private String itTag;
    }
}
