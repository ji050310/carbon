package com.carbon.assets.vo;

import com.carbon.domain.common.annotation.Dict;
import com.carbon.assets.common.enums.AssetsStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 碳中和资产 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Data
@ApiModel(value="CarbonAssetsQueryVo对象", description="碳中和资产查询参数")
public class CarbonAssetsQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资产ID")
    private Long id;

    @ApiModelProperty(value = "中和资产核证记录id，核证成功会存储")
    private String certifiedTxId;

    @ApiModelProperty(value = "碳源数据ID,中和资产链上tokenId")
    private String carbonSourceDataId;

    @ApiModelProperty(value = "买方(持有者租户ID)")
    private String receivingId;
    @ApiModelProperty(value = "买方")
    private String receivingName;

    @ApiModelProperty(value = "发行方(发行者租户ID)")
    private String publishId;
    @ApiModelProperty(value = "发行方")
    private String publishName;

    @Dict(enumType = AssetsStatus.class)
    @ApiModelProperty(value = "资产状态")
    private Integer status;

    @Dict(dictCode = "assets_type")
    @ApiModelProperty(value = "资产类型")
    private Long type;

    @Dict(dictCode = "assets_unit")
    @ApiModelProperty(value = "资产单位类型")
    private Long uomType;

    @ApiModelProperty(value = "资产数量")
    private BigDecimal amount;

    @ApiModelProperty(value = "元数据")
    private String metaData;

    @ApiModelProperty(value = "清单")
    private String mainList;

    @ApiModelProperty(value = "从/到日期时间戳记")
    private String timeScope;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;


    @ApiModelProperty(value = "碳减排项目")
    private String carbonProjectName;
    @ApiModelProperty(value = "碳减排方法学")
    private String carbonMethodologyName;

    @JsonIgnore
    @ApiModelProperty(value = "资产来源渠道",hidden = true)
    private Integer sourceChannel;
    @ApiModelProperty(value = "资产来源渠道")
    private String sourceChannelName;

}
