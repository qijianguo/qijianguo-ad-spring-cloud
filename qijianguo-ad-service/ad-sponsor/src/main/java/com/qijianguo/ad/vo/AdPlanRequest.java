package com.qijianguo.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

@Data
@AllArgsConstructor
public class AdPlanRequest {

    private Long id;

    private Long userId;

    private String planName;

    private String startDate;

    private String endDate;

    public boolean createValidate() {
        return this.userId != null
                && !StringUtils.isEmpty(this.planName)
                && !StringUtils.isEmpty(this.startDate)
                && !StringUtils.isEmpty(this.endDate);
    }

    public boolean updateValidate() {
        return this.id != null && this.userId != null;
    }

    public boolean deleteValidate() {
        return this.id != null && this.userId != null;
    }
}
