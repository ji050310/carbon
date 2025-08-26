package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonProject;
import com.carbon.assets.entity.CarbonProjectContent;
import com.carbon.assets.param.CarbonDataSubmissionQueryParam;
import com.carbon.assets.param.CarbonProjectAddParam;
import com.carbon.assets.param.CarbonProjectOwnerDataParam;
import com.carbon.assets.vo.CarbonDetectionDataVo;
import com.carbon.assets.vo.CarbonProjectListVo;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonProjectQueryParam;
import com.carbon.assets.vo.CarbonProjectQueryVo;
import com.carbon.common.api.Paging;
import com.carbon.domain.common.ApiResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 碳减排项目 服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
public interface CarbonProjectService extends BaseService<CarbonProject> {

    Boolean delDataSubmissionPage(String id);

    List<String>updateDataSubmissionPage(String id);

    List<CarbonDetectionDataVo> getDataSubmissionPageById(String id);

    boolean insertSubmissionToDB(CarbonDataSubmissionQueryParam param);

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonProjectQueryVo
     */
    CarbonProjectQueryVo getCarbonProjectById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonProjectQueryParam
     * @return Paging<CarbonProjectListVo>
     */
    Paging<CarbonProjectListVo> getCarbonProjectPageList(CarbonProjectQueryParam param);
    /**
     * 检测数据报送
     * @param param CarbonProjectQueryParam
     * @return Paging<CarbonProjectListVo>
     */
    Paging<CarbonProjectListVo> getDataSubmissionPage(CarbonProjectQueryParam param);

    /**
     * 添加减排项目
     * @param param 参数
     */
    CarbonProjectQueryVo addCarbonProject(CarbonProjectAddParam param);

    /**
     * 修改减排项目
     * @param param 参数
     * @return boolean
     */
    boolean updateCarbonProject(CarbonProjectAddParam param);

    /**
     * 上传业主资料
     * @param param 参数
     */
    void uploadOwnerData(CarbonProjectOwnerDataParam param);

    CarbonProjectContent getCarbonProjectByName(String name);

    ApiResult insertMqForm(CarbonProjectAddParam param);

    ApiResult addFeishuProject(CarbonProjectAddParam param);
}