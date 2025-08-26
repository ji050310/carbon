package com.carbon.system.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * H5碳文章 查询参数对象
 * @author: code534
 * @time: 2022/7/7 9:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonH5ArticleQueryParam对象", description="H5碳文章查询参数")
public class CarbonH5ArticleQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类ID: 1.行业资讯 2.行业知识 3.常见问题 4.公告")
    private String categoryId;

    @ApiModelProperty(value = "搜索标题关键字")
    private String searchKeywords;

    @ApiModelProperty(value = "文章状态: 1.未发布 2.已发布 3.已下线 4.编辑中 5.已推送")
    private String status;
}
