package com.carbon.assets.param;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

import java.util.Date;

/**
 * <p>
 * 碳减排项目文档 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonProjectDocQueryParam对象", description="碳减排项目文档查询参数")
public class CarbonProjectDocQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目名称,文档名称")
    private Long projectId;

    @ApiModelProperty(value = "项目名称,文档名称")
    private String searchKey;

    @Dict(dictCode = "021")
    @ApiModelProperty(value = "项目类型")
    private String type;

    @ApiModelProperty(value = "完成日期-开始")
    private Date completionDateStart;

    @ApiModelProperty(value = "完成日期-结束")
    private Date completionDateEnd;
}
