package com.keith.common.security;

import com.keith.project.entity.SysUser;
import com.keith.project.mapper.database1.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 用户业务逻辑实现接口
 *
 * @author ziv
 * @date 2019-03-06
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    /**
     * 通过登录名加载用户
     *
     * @param loginName dengluming
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        SysUser user = null;
        user = userMapper.findByUserName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new MyUserDetails(user);
    }
}
