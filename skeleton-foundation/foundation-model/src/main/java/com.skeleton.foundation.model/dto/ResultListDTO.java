package com.skeleton.foundation.model.dto;

import com.skeleton.foundation.model.error.ErrorObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 集合封装类
 *
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultListDTO<T> extends AbstractResult {

    private static final long serialVersionUID = -910503860069933801L;

    /**
     * 集合数据
     */
    private List<T> data;


    ResultListDTO(Status status) {
        this.status = status.getFlag();
    }

    /**
     * 有数据成功结果返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultListDTO<T> success(List<T> data) {
        ResultListDTO<T> result = new ResultListDTO<>(Status.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 失败结果返回
     *
     * @param error
     * @param <T>
     * @return
     */
    public static <T> ResultListDTO<T> failure(ErrorObject error) {
        ResultListDTO<T> result = new ResultListDTO<>(Status.FAILURE);
        result.setError(error);
        return result;
    }

}