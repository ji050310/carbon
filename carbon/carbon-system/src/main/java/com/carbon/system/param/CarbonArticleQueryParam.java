package com.carbon.system.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 碳文章 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonArticleQueryParam对象", description="碳文章查询参数")
public class CarbonArticleQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类ID: 字典表里面的值")
    private String categoryId;

    @ApiModelProperty(value = "文章状态: 1.未发布 2.已发布 3.已下线 4.编辑中 5.已推送")
    private Integer status;

    @ApiModelProperty(value = "搜索标题关键字")
    private String searchKeywords;


}
