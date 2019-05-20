package com.qijianguo.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CreateUserResponse {
    private Long userId;

    private String username;

    private String token;

    private Date createDate;

    private Date updateDate;
}
