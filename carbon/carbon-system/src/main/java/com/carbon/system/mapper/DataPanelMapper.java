package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.DataPanel;
import com.carbon.system.vo.StatCarbonProject;
import com.carbon.system.vo.StatCarbonProjectVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 数据面板 Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
@Repository
public interface DataPanelMapper extends BaseMapper<DataPanel> {

    BigDecimal getCarbonCreditTotal(@Param("statDate") Date statDate);

    BigDecimal getCarbonQuotaTotal(@Param("statDate") Date statDate);

    StatCarbonProjectVo getProjectCountStat();

    List<StatCarbonProject> getProjectList();

}
