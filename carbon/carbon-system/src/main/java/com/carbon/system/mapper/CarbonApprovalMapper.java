package com.carbon.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.system.entity.CarbonApproval;
import com.carbon.system.param.CarbonProjectInstanceQueryParam;
import com.carbon.system.vo.CarbonApprovalQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JinRui Zhang
 * @since 2022-06-21
 */
@Repository
public interface CarbonApprovalMapper extends BaseMapper<CarbonApproval> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonProjectInstanceQueryVo
     */
    CarbonApprovalQueryVo getCarbonProjectInstanceById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonProjectInstanceQueryVo>
     */
    IPage<CarbonApprovalQueryVo> getCarbonProjectInstancePageList(@Param("page") Page<?> page, @Param("param") CarbonProjectInstanceQueryParam param);


    void updateProjectStatus(@Param("id") Long id, @Param("projectStatus") String projectStatus);

    void updateExchangeAccountStatus(@Param("id") Long id, @Param("accountStatus") String accountStatus);

    boolean updateExchangeDate(@Param("id") Long id,@Param("newDate") String newDate);

    boolean updateQuotaStatus(@Param("id") Long id, @Param("assetsStatus") String assetsStatus);

    void deleteExchangeAccount(@Param("id") Long id);

    void updateAssetsStatus(@Param("id") Long id, @Param("projectStatus") String assetsStatus);
}
