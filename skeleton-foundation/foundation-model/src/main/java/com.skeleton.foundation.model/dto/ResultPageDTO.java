package com.skeleton.foundation.model.dto;

import com.skeleton.foundation.model.error.ErrorObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 分页封装类
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultPageDTO<T> extends AbstractResult {

    private static final long serialVersionUID = 4441702795077671385L;

    /**
     * 分页数据
     */
    @JsonProperty("page_data")
    private PageData<T> pageData;

    ResultPageDTO(Status status) {
        this.status = status.getFlag();
    }

    /**
     * 有数据成功结果返回
     * @param pageData
     * @param <T>
     * @return
     */
    public static <T> ResultPageDTO<T> success(PageData<T> pageData) {
        ResultPageDTO<T> result = new ResultPageDTO<>(Status.SUCCESS);
        result.setPageData(pageData);
        return result;
    }

    /**
     * 失败结果返回
     * @param error
     * @param <T>
     * @return
     */
    public static <T> ResultPageDTO<T> failure(ErrorObject error) {
        ResultPageDTO<T> result = new ResultPageDTO<>(Status.FAILURE);
        result.setError(error);
        return result;
    }

}
