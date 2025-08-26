package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Li Jun
 **/
@Data
@ApiModel(value="FeiShuEventVo", description="FeiShuEventVo")
public class FeiShuEventVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String challenge;

}
