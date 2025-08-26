package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 *  查询参数对象
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-06-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="FeishuFiletokenQueryParam对象", description="查询参数")
public class FeishuFiletokenQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
