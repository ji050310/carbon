package com.carbon.system.vo;

import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文档日期
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-01
 */
@Data
@ApiModel(value="文档日期对象", description="文档日期")
public class FileDate implements Serializable {

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;

}
