package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.CrmExchangeRecord;
import com.carbon.system.param.CrmExchangeRecordQueryParam;
import com.carbon.system.vo.CrmExchangeRecordVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 兑换记录 Mapper 接口
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Repository
public interface CrmExchangeRecordMapper extends BaseMapper<CrmExchangeRecord> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CrmExchangeRecordQueryVo
     */
    CrmExchangeRecordVo getCrmExchangeRecordById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CrmExchangeRecordQueryVo>
     */
    IPage<CrmExchangeRecordVo> getCrmExchangeRecordPageList(@Param("page") Page<?> page, @Param("param") CrmExchangeRecordQueryParam param);

}
