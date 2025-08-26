package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 * 兑换记录 查询参数对象
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CrmExchangeRecordQueryParam对象", description="兑换记录查询参数")
public class CrmExchangeRecordQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
