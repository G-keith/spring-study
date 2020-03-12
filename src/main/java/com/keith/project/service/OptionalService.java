package com.keith.project.service;

import com.keith.common.statuscode.ServerResponse;
import com.keith.project.entity.SysUser;
import com.keith.project.mapper.database1.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author keith
 * @version 1.0
 * @date 2020-03-11
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class OptionalService {

    private final UserMapper userMapper;

    public OptionalService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void testOptional(Integer userId) {
        Optional<SysUser> sysUser = userMapper.find(userId);
        sysUser.ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        SysUser sysUser = null;
        //ofNullable 如果对象为 null,将会创建不包含值的 empty Optional 对象实例
        Optional<SysUser> optCompany = Optional.ofNullable(sysUser);
/*        由于容器内没有任何对象实例，使用 get 方法将会抛出 NoSuchElementException 异常。
        System.out.println(optCompany.get());*/
        //isPresent 。这个方法将会判断内部是否存在对象实例，若存在则返回 true。
        optCompany.ifPresent(System.out::println);
        // 为空时设置默认值
        //String name=optCompany.orElse(new SysUser()).getUserName();
        // 为空时抛出异常
        //String name2=optCompany.orElseThrow(RuntimeException::new).getUserName();
        //map 方法将String 转变成 Optional<String> ，flatMap返回值必须是Optional<T>
        // 此时 Optional 内部对象变成 String 类型。如果转化之前 Optional 对象为空，则什么也不会发生。
        //orElse方法，无论Optional的值是否为空，它都会去获得传入的值
        //orElseGet方法，只有Optional的值为空，它会去获得传入的值
        System.out.println(optCompany.map(SysUser::getUserName));
        System.out.println(optCompany.map(SysUser::getUserName).orElse("Unknown1"));
        System.out.println(optCompany.map(SysUser::getUserName).orElseGet(()->"Unknown2"));
        System.out.println(optCompany.map(SysUser::getUserName));
        System.out.println(optCompany.map(SysUser::getUserName).orElse("Unknown3"));
        System.out.println(optCompany.map(SysUser::getUserName).orElseGet(()->"Unknown4"));
    }
}
