package com.skeleton.foundation.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageData<T> implements Serializable {

    /**
     * 数据集合
     */
    private List<T> data;

    /**
     * 总数
     */
    private Integer total;

    /**
     * 每页显示数量
     */
    @JsonProperty("page_size")
    private Integer pageSize;

    /**
     * 创建 PageData 对象
     *
     * @param pageInfo
     * @return
     */
    public static <T> PageData<T> build(PageInfo<T> pageInfo) {
        return new PageData().setData(pageInfo.getList()).setPageSize(pageInfo.getSize()).setTotal((int) pageInfo.getTotal());
    }

}
