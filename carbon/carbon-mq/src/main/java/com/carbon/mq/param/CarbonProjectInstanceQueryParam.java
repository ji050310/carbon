package com.carbon.mq.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 *  查询参数对象
 * </p>
 *
 * @author JinRui Zhang
 * @since 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonProjectInstanceQueryParam对象", description="查询参数")
public class CarbonProjectInstanceQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
