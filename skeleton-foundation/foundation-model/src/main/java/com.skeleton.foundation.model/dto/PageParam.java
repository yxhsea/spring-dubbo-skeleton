package com.skeleton.foundation.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 分页条件类
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PageParam implements Serializable{

    private static final long serialVersionUID = 4441702795077671385L;

    /**
     * 页码
     */
    @JsonProperty("page_no")
    private Integer pageNo;

    /**
     * 每页显示数量
     */
    @JsonProperty("page_size")
    private Integer pageSize;
}
