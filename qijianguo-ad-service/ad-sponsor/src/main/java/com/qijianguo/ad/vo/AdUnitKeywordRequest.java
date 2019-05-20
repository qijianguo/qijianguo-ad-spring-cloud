package com.qijianguo.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AdUnitKeywordRequest {

    private List<UnitKeyword> unitKeywords;

    @Data
    public static class UnitKeyword {
        private Long unitId;

        private String keyword;
    }
}
