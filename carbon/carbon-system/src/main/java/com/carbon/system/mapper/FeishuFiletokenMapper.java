package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.FeishuFiletoken;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-06-16
 */
@Repository
public interface FeishuFiletokenMapper extends BaseMapper<FeishuFiletoken>
{
    List<Map<String,Object>> getDocToken();

}
