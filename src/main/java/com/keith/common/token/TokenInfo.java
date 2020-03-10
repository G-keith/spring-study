package com.keith.common.token;

import lombok.Data;

/**
 * token信息
 * @author ziv
 * @date 2019-04-16
 */
@Data
public class TokenInfo {

    private Integer userId;

    private String userName;

    private String token;

}
