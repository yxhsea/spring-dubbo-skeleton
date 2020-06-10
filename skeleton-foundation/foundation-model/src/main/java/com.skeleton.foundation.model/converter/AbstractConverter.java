package com.skeleton.foundation.model.converter;

import com.skeleton.foundation.model.dto.AbstractResult;
import com.skeleton.foundation.model.dto.PageData;
import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.foundation.model.dto.ResultListDTO;
import com.skeleton.foundation.model.dto.ResultPageDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO 和 BO 转换类
 */
public abstract class AbstractConverter<DTO, BO> {

    /**
     * 将DTO转换为BO
     *
     * @param dto
     * @return
     */
    public abstract BO toBO(final DTO dto);

    /**
     * 将BO转换为DTO
     * @param bo
     * @return
     */
    public abstract DTO toDTO(final BO bo);

    /**
     * 将 BO参数封装并返回
     * @param bo
     * @return
     */
    public final ResultDTO<DTO> toResultDTO(final BO bo) {
        final DTO dto = (bo == null) ? null : this.toDTO(bo);
        final ResultDTO<DTO> resultDTO = ResultDTO.success(dto);
        return resultDTO;
    }

    /**
     * 将 resultBO 封装并返回
     * @param resultBO
     * @return
     */
    public final ResultDTO<DTO> toResultDTO(final ResultDTO<BO> resultBO) {
        if(AbstractResult.Status.FAILURE.getFlag().equals(resultBO.getStatus())){
            return (ResultDTO<DTO>)ResultDTO.failure(resultBO.getError());
        }
        final DTO dto = (resultBO.getData() == null) ? null : this.toDTO(resultBO.getData());
        return ResultDTO.success(dto);
    }

    /**
     * 将resultList参数封装并返回
     *
     * @param resultList
     * @return
     */
    public final ResultListDTO<DTO> toResultListDTO(final ResultListDTO<BO> resultList) {
        if(AbstractResult.Status.FAILURE.getFlag().equals(resultList.getStatus())){
            return (ResultListDTO<DTO>)ResultListDTO.failure(resultList.getError());
        }
        final List<DTO> dtoList = resultList.getData() == null ? null : this.toListDTO(resultList.getData());
        return ResultListDTO.success(dtoList);
    }

    /**
     * 将resultPage封装并返回
     *
     * @param resultPage
     * @return
     */
    public final ResultPageDTO<DTO> toPageDataDTO(final ResultPageDTO<BO> resultPage) {
        if(AbstractResult.Status.FAILURE.getFlag().equals(resultPage.getStatus())){
            return (ResultPageDTO<DTO>)ResultPageDTO.failure(resultPage.getError());
        }
        final PageData pageData = resultPage.getPageData() == null ? null
                : new PageData().setData(this.toListDTO(resultPage.getPageData().getData()))
                .setPageSize(resultPage.getPageData().getPageSize())
                .setTotal(resultPage.getPageData().getTotal());
        return ResultPageDTO.success(pageData);
    }

    /**
     * 将DTO集合转换为BO集合
     *
     * @param dtoList
     * @return
     */
    public final List<BO> toListBO(final List<DTO> dtoList) {
        final List<BO> boList = new ArrayList();
        for (DTO dto : dtoList) {
            boList.add(this.toBO(dto));
        }
        return boList;
    }

    /**
     * 将BO集合转换为DTO集合
     *
     * @param boList
     * @return
     */
    public final List<DTO> toListDTO(final List<BO> boList) {
        final List<DTO> dtoList = new ArrayList();
        for (BO bo : boList) {
            dtoList.add(this.toDTO(bo));
        }
        return dtoList;
    }

}
