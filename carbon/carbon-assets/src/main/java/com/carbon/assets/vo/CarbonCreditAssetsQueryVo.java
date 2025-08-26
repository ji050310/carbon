package com.carbon.assets.vo;

import com.carbon.assets.common.enums.AssetsStatus;
import com.carbon.assets.common.enums.AssetsTradeStatus;
import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 碳信用资产 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Data
@ApiModel(value="CarbonCreditAssetsQueryVo对象", description="碳信用资产查询参数")
public class CarbonCreditAssetsQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private Long carbonProjectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目说明")
    private String projectIntroduction;

    @ApiModelProperty(value = "方法学字典编码")
    private String carbonMethodology;

    @ApiModelProperty(value = "方法学名称")
    private String carbonMethodologyName;

    @Dict(dictCode = "007")
    @ApiModelProperty(value = "核证标准")
    private String certificationCriteria;

    @ApiModelProperty(value = "项目领域编码")
    private String projectScopeCode;

    @ApiModelProperty(value = "项目类型编码")
    @Dict(dictCode = "004")
    private String projectScopeTypeCode;

    @ApiModelProperty(value = "项目类型")
    private String projectScopeType;


    @Dict(dictCode = "002")
    @ApiModelProperty(value = "行业编码（字典：002）")
    private String industryCode;

    @ApiModelProperty(value = "交易所ID")
    private Long carbonExchangeId;

    @ApiModelProperty(value = "交易所名称")
    private String carbonExchangeName="--";

    @ApiModelProperty(value = "交易所官网")
    private String carbonExchangeWebsite="--";

    @Dict(dictCode = "052")
    @ApiModelProperty(value = "核证机构")
    private String certifiedAgency;

    @ApiModelProperty(value = "签发机构")
    private String issuingAgency;

    @ApiModelProperty(value = "签发凭证")
    private String issuingCertificates;

    @ApiModelProperty(value = "签发凭证文件名")
    private String issuingCertificatesFileName;

    @ApiModelProperty(value = "签发日期")
    private Date issuingDate;

    @ApiModelProperty(value = "有效期")
    private Date expiryDate;

    @Dict(dictCode = "013")
    @ApiModelProperty(value = "资产状态（字典：013）")
    private String assetsStatus;

    @Dict(dictCode = "016")
    @ApiModelProperty(value = "交易状态（字典：016）")
    private String transactionStatus;

    @ApiModelProperty(value = "购入总价")
    private BigDecimal buyTotalPrice;

    @ApiModelProperty(value = "购入单价")
    private BigDecimal buyUnitPrice;

    @ApiModelProperty(value = "购入日期")
    private Date buyDate;

    @ApiModelProperty(value = "购入凭证")
    private String buyCertificate;

    @ApiModelProperty(value = "购入凭证")
    private String buyCertificateFileName;

    @ApiModelProperty(value = "持仓总额")
    private BigDecimal total;

    @ApiModelProperty(value = "可用数量")
    private BigDecimal availableAmount;

    @ApiModelProperty(value = "冻结数量")
    private BigDecimal frozenAmount;

    @ApiModelProperty(value = "锁定数量")
    private BigDecimal lockedAmount;

    @ApiModelProperty(value = "资产估值")
    private BigDecimal valuation;

    @ApiModelProperty(value = "一级市场持有机构")
    private String tenantName;

    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    @ApiModelProperty(value = "领域编码")
    private String ProjectScopeCode;


}
