package com.skeleton.foundation.model.dto;

import com.skeleton.foundation.model.error.ErrorObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 返回结果抽象类
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractResult implements Serializable{

	private static final long serialVersionUID = -6204542340640355634L;


	/**
     * 请求的处理结果状态
     */
    public enum Status {
    	SUCCESS(1), FAILURE(0);
    	
    	private Integer flag;
    	
    	private Status(Integer flag){
    		this.flag = flag;
    	}

		public Integer getFlag() {
			return flag;
		}

		public void setFlag(Integer flag) {
			this.flag = flag;
		}
    	
    }

	/**
	 * 返回状态  1:成功   0：失败
	 */
	@NonNull
	protected Integer status;

	/**
	 * 错误信息
	 */
	protected ErrorObject error;
}
