package com.carbon.assets.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 碳资讯 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-01-11
 */
@Data
@ApiModel(value="CarbonInformationQueryVo对象", description="碳资讯查询参数")
public class CarbonInformationQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "链接")
    private String url;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

}
