package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 碳减排项目 上传业主资料参数
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Data
@ApiModel(value="CarbonProjectOwnerDataParam", description="上传业主资料参数")
public class CarbonProjectOwnerDataParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private Long id;

    @ApiModelProperty(value = "开发负责人姓名")
    private String principalName;

    @ApiModelProperty(value = "开发负责人电话")
    private String principalPhone;

    @ApiModelProperty(value = "法人姓名")
    private String legalPersonName;

    @ApiModelProperty(value = "法人联系方式")
    private String legalPersonPhone;

    @ApiModelProperty(value = "附件列表")
    private List<CarbonProjectOwnerDataAnnex> annexList;

}
