package com.keith.project.mapper.database1;

import com.keith.project.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @author keith
 * @version 1.0
 * @date 2020-02-22
 **/
@Repository
public interface UserMapper {

    /**
     * 通过登录名查询用户
     * @param loginName 登录名
     * @return
     */
    SysUser findByUserName(String loginName);

    /**
     * 插入用户信息
     * @param sysUser 用户信息
     * @return
     */
    int insertUser(SysUser sysUser);
}
