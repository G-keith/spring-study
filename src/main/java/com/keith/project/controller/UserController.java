package com.keith.project.controller;

import com.keith.common.security.MyUserDetails;
import com.keith.common.statuscode.ServerResponse;
import com.keith.common.token.JwtTokenUtil;
import com.keith.common.token.TokenInfo;
import com.keith.project.entity.SysUser;
import com.keith.project.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author keith
 * @version 1.0
 * @date 2020-03-10
 **/
@Api(tags = "系统后台登录接口")
@RestController
@RequestMapping(value = "test")
@Slf4j
public class UserController {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private UserService userService;

    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "登录名", dataType = "string", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", required = true),
    })
    @GetMapping(value = "login")
    public ServerResponse<TokenInfo> getToken(String loginName, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginName, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        MyUserDetails userDetails = (MyUserDetails)userDetailsService.loadUserByUsername(loginName);
        SysUser user = userDetails.getUserInfo();
        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setToken(token);
        tokenInfo.setUserName(user.getUserName());
        tokenInfo.setUserId(user.getUserId());
        return ServerResponse.createBySuccess(tokenInfo);
    }

    @ApiOperation(value = "注册")
    @PostMapping(value = "register")
    public ServerResponse<Integer> register(@RequestBody SysUser sysUser){
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        return userService.insertUser(sysUser);
    }
}
