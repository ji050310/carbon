package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonProject;
import com.carbon.assets.entity.CarbonProjectContent;
import com.carbon.assets.param.CarbonDataSubmissionQueryParam;
import com.carbon.assets.param.CarbonProjectQueryParam;
import com.carbon.assets.vo.CarbonDetectionDataVo;
import com.carbon.assets.vo.CarbonProjectListVo;
import com.carbon.assets.vo.CarbonProjectQueryVo;
import com.carbon.common.api.Paging;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 碳减排项目 Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Mapper
public interface CarbonProjectMapper extends BaseMapper<CarbonProject> {


//    public List<String>getIndexType();

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonProjectQueryVo
     */
    CarbonProjectQueryVo getCarbonProjectById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonProjectQueryVo>
     */
    IPage<CarbonProjectListVo> getCarbonProjectPageList(@Param("page") Page<?> page, @Param("param") CarbonProjectQueryParam param);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonProjectQueryVo>
     */
    IPage<CarbonProjectListVo> getDataSubmissionPage(@Param("page") Page<?> page, @Param("param") CarbonProjectQueryParam param);

    List<CarbonDetectionDataVo> getDataSubmissionPageById(String projectId);

    boolean insertSubmissionToFactorTable(@Param("param") CarbonDataSubmissionQueryParam param);

    boolean insertSubmissionToFactorProjectTable(@Param("param") CarbonDataSubmissionQueryParam param);

    List<String> updateDataSubmissionPage(String id);

    Boolean delDataSubmissionPage(String id);

    CarbonProjectContent getCarbonProjectByName(String name);

    Long getIdByMethodologyId(Long id);

    IPage<CarbonProjectListVo> getNoWaitExaminePageList(Page<?> page, CarbonProjectQueryParam queryParam);
}