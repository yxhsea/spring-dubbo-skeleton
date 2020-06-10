package com.skeleton.outside.service.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yxhsea
 */
@Data
@Accessors(chain = true)
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 2808070390294760936L;
    private String message;
}
