package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 碳减排项目文档
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonProjectDoc对象", description="碳减排项目文档")
public class CarbonProjectDoc extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private Long carbonProjectId;

    @ApiModelProperty(value = "文档类型")
    private String type;

    @ApiModelProperty(value = "文档名称")
    private String title;

    @ApiModelProperty(value = "文档url")
    private String url;

    @ApiModelProperty(value = "完成日期")
    private Date completionDate;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

}
