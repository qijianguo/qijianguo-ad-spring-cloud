package com.qijianguo.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AdUnitDistrictRequest {

    private List<UnitDistrict> unitDistricts;

    @Data
    public static class UnitDistrict {
        private Long unitId;

        private String province;

        private String city;
    }
}
