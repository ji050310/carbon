package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 碳资源文件
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonResourceFile对象", description="碳资源文件")
public class CarbonResourceFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "关联业务ID")
    private Long businessId;

    @ApiModelProperty(value = "文件类型字典code")
    private String fileDictCode;

    @ApiModelProperty(value = "文件字典value")
    private String fileDictValue;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件url")
    private String url;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

}
