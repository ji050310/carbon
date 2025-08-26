package com.carbon.domain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询参数
 * @author Li Jun
 * @since 2021-06-11
 */
@Data
@ApiModel("查询参数对象")
public abstract class QueryParam implements Serializable{
    private static final long serialVersionUID = -3263921252635611410L;

    private static Integer DEFAULT_PAGE_INDEX = 1;
    private static Integer DEFAULT_PAGE_SIZE = 10;

    @ApiModelProperty(value = "页码,默认为1")
	private Integer current = DEFAULT_PAGE_INDEX;
	@ApiModelProperty(value = "页大小,默认为10")
	private Integer size = DEFAULT_PAGE_SIZE;
    @ApiModelProperty(value = "排序字段")
	private String sortField;
    @ApiModelProperty(value = "是否升序")
    private Boolean asc = true;


    public void setCurrent(Integer current) {
	    if (current != null && current > 0){
            this.current = current;
        }
    }

    public void setSize(Integer size) {
	    if (size != null && size > 0){
            this.size = size;
        }
    }

    public void setAsc(Boolean asc) {
        if (size != null){
            this.asc = asc;
        }
    }
}
