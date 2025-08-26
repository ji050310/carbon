package com.carbon.assets.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 碳减排项目文档 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Data
@ApiModel(value="CarbonProjectDocQueryVo对象", description="碳减排项目文档查询参数")
public class CarbonProjectDocQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private Long carbonProjectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @Dict(dictCode = "021")
    @ApiModelProperty(value = "项目类型")
    private String type;

    @ApiModelProperty(value = "文档名称")
    private String title;

    @ApiModelProperty(value = "文档url")
    private String url;

    @ApiModelProperty(value = "完成日期")
    private Date completionDate;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

}
