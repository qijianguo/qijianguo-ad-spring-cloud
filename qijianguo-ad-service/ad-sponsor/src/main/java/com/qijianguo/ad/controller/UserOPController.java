package com.qijianguo.ad.controller;

import com.qijianguo.ad.exception.AdException;
import com.qijianguo.ad.service.IUserService;
import com.qijianguo.ad.vo.CreateUserRequest;
import com.qijianguo.ad.vo.CreateUserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api("用户管理系统")
public class UserOPController {

    private final IUserService iUserService;


    @Autowired
    public UserOPController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @ApiOperation(value = "创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "string", paramType = "query"),
    })
    @PostMapping("/create/user") // 请求路径（在yml文件中的context-path）：/ad-sponsor/create/user
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        log.info("ad-sponsor -> createUser: {}", request);
        return iUserService.createUser(request);
    }

    @GetMapping("/hello")
    public void hello(String name) {
        log.info("============={}", name);
    }
}
