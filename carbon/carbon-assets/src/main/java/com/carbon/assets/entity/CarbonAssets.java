package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * <p>
 * 碳中和资产
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CarbonAssets对象", description = "碳中和资产")
public class CarbonAssets extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "中和资产核证记录id，核证成功会存储")
    private String certifiedTxId;

    @ApiModelProperty(value = "碳源数据ID(链上-中和资产tokenID)")
    private String carbonSourceDataId;

    @ApiModelProperty(value = "买方(持有者租户ID)")
    private Long receivingId;

    @ApiModelProperty(value = "发行方(发行者租户ID)")
    private Long publishId;

    @ApiModelProperty(value = "资产状态，字典：41")
    private Integer status;

    @ApiModelProperty(value = "资产类型,字典：6")
    private Integer type;

    @ApiModelProperty(value = "资产单位类型，字典：25")
    private Integer uomType;

    @ApiModelProperty(value = "资产数量")
    private BigDecimal amount;

    @ApiModelProperty(value = "元数据")
    private String metaData;

    @ApiModelProperty(value = "清单")
    private String mainList;

    @ApiModelProperty(value = "从/到日期时间戳记")
    private String timeScope;

}
