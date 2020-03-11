package com.keith.project.service;

import com.keith.common.statuscode.ServerResponse;
import com.keith.project.entity.SysUser;
import com.keith.project.mapper.database1.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author keith
 * @version 1.0
 * @date 2020-03-10
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Resource
    private UserMapper userMapper;

    public ServerResponse<Integer> insertUser(SysUser sysUser){
        return ServerResponse.createBySuccess(userMapper.insertUser(sysUser));
    }

    public void testOptional(Integer userId){
        Optional<SysUser> sysUser=userMapper.find(userId);
        System.out.println(sysUser);
        sysUser.ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        SysUser sysUser=new SysUser();
        Optional<String> optCompany = Optional.ofNullable(sysUser.getPassword());
        optCompany.ifPresent(System.out::println);
    }

}
