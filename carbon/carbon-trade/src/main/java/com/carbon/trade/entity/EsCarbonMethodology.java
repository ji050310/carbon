package com.carbon.trade.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * <p>
 * 碳减排方法学
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "methodology")
@ApiModel(value="CarbonMethodology对象", description="碳减排方法学")
public class EsCarbonMethodology extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "引用的CDM方法学编号")
    private String cdmCode;

    @ApiModelProperty(value = "方法学名称")
    private String name;

    @ApiModelProperty(value = "行业名称")
    private String industryName;

    @ApiModelProperty(value = "核证标准")
    private String certificationCriteria;

    @ApiModelProperty(value = "状态编码")
    private String StatusCode;

    @ApiModelProperty(value = "方法学文档地址")
    private String sourceFileUrl;

    @ApiModelProperty(value = "方法学文档内容")
    private String content;
}
