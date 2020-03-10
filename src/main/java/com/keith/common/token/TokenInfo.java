package com.keith.common.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * token信息
 * @author keith
 * @date 2019-04-16
 */
@Data
@ApiModel(value = "token信息")
public class TokenInfo {

    @ApiModelProperty(value ="用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "token信息")
    private String token;

}
