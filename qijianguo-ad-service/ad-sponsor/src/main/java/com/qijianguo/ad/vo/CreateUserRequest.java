package com.qijianguo.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String name;

    public boolean validate() {
        return !StringUtils.isEmpty(this.name);
    }

}
