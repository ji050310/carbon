package com.carbon.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.CarbonProject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 碳减排项目 Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Repository
public interface CarbonProjectMapper extends BaseMapper<CarbonProject> {

    void updateInitiationDate(@Param("date") String date, @Param("id") Long id);
}
