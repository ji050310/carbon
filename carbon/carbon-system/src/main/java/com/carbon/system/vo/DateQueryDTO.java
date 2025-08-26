package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Bae
 * @version 1.0
 * @date 2022/5/27 21:38
 */
@Data
@ApiModel(value="日期查询对象", description="日期格式（YYYY-MM-DD）")
public class DateQueryDTO {
    @ApiModelProperty(value = "用户ID")
    String userId;

    @ApiModelProperty(value = "查询类型（碳信分：0；兑换记录：1；提现记录：2）")
    String type;

    @ApiModelProperty(value = "开始时间")
    String startDate;

    @ApiModelProperty(value = "结束时间")
    String endDate;

}
