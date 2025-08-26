package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.assets.entity.CarbonAssets;
import com.carbon.assets.param.CarbonAssetsQueryParam;
import com.carbon.assets.vo.CarbonAssetsQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 字典编码
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Repository
public interface DictMapper{
    String getDictCh(@Param("dictCode") String dictCode);

}
