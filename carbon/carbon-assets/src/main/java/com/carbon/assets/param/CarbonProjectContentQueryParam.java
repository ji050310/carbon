package com.carbon.assets.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 * 碳减排项目文档内容 查询参数对象
 * </p>
 *
 * @author Cbd
 * @since 2022-08-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonProjectContentQueryParam对象", description="碳减排项目文档内容查询参数")
public class CarbonProjectContentQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("搜索关键词")
    private String searchKey;
}
