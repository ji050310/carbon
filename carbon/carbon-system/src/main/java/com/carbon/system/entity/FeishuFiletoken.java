package com.carbon.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.carbon.domain.common.BaseEntity;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-06-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="FeishuFiletoken对象", description="")
public class FeishuFiletoken extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文档编号")
    private String code;

    @ApiModelProperty(value = "文档名称")
    private String fileName;

    @ApiModelProperty(value = "文档token ")
    private String fileToken;

    @ApiModelProperty(value = "文档url")
    private String fileUrl;

    @ApiModelProperty(value = "文档所属应用；1-碳信使中台；2-碳信使工作台")
    private String app;

    @ApiModelProperty(value = "文档url")
    private String webRedirect;

}
