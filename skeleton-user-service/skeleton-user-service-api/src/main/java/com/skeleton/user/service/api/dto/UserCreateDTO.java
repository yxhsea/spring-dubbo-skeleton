package com.skeleton.user.service.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel(value = "创建用户参数")
@Data
@Accessors(chain = true)
public class UserCreateDTO implements Serializable {

    private static final long serialVersionUID = 2685358116482174403L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @JsonProperty("user_name")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @JsonProperty("password")
    private String password;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @JsonProperty("phone")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @JsonProperty("email")
    private String email;
}
