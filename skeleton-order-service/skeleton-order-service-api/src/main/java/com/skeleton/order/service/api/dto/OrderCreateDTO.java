package com.skeleton.order.service.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yxhsea
 */
@ApiModel(value = "创建订单参数")
@Data
@Accessors(chain = true)
public class OrderCreateDTO implements Serializable {

    private static final long serialVersionUID = 1504821319144415129L;

    /**
     * 用户 ID
     */
    @ApiModelProperty(value = "用户 ID")
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * 商品 ID
     */
    @ApiModelProperty(value = "商品 ID")
    @JsonProperty("good_id")
    private Integer goodId;
}
