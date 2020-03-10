package com.keith.common.token;

import com.keith.common.security.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * security工具类
 * @author ziv
 * @date 2019-04-18
 */
public class SecurityUserUtil {

    /**
     * 获取当前系统用户给
     * @return MyUserDetails
     */
    public static MyUserDetails getUser() {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
