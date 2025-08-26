package com.carbon.domain.mq.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资产上传审批表单MQ实体类
 *
 * @author Bae
 * @version 1.0
 * @date 2022/6/5 22:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="资产上传审批表单MQ实体类", description="资产上传审批表单MQ实体类")
public class AssetUploadApproval {

    @ApiModelProperty(value = "资产ID")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "机构名称")
    private String agenciesName;

    @ApiModelProperty(value = "联系电话")
    private String contactNumber;

    @ApiModelProperty(value = "资产类型")
    private String assetType;

    @ApiModelProperty(value = "一级市场持有机构")
    private String primaryMarketHoldingInstitutions;

    @ApiModelProperty(value = "持仓总量（tCO2e）")
    private String shareholding;

    @ApiModelProperty(value = "持仓凭证")
    private String proofOfPosition;

    @ApiModelProperty(value = "签发机构")
    private String issuingAgency;

    @ApiModelProperty(value = "审批人")
    private String approver;

    @ApiModelProperty(value = "审批状态")
    private String approvalState;

    @ApiModelProperty(value = "备注")
    private String remark;

}
