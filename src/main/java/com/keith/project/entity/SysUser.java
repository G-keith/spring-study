package com.keith.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author keith
 * @version 1.0
 * @date 2020-02-22
 **/
@Data
@ApiModel(value = "用户实体")
public class SysUser {

    @ApiModelProperty(value = "主键id",hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "用户名")
    private String userName;
}
