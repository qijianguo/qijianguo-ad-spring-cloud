package com.qijianguo.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreativeUnitRequest {

    private List<CreativeUnitItem> creativeUnitItems;

    @Data
    public static class CreativeUnitItem {
        private Long creativeId;
        private Long unitId;
    }
}
