package com.carbon.assets.param;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 碳减排项目 上传业主资料附件
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Data
@ApiModel(value="CarbonProjectOwnerDataAnnex", description="上传业主资料附件")
public class CarbonProjectOwnerDataAnnex implements Serializable {

    @Dict(dictCode = "023")
    @ApiModelProperty(value = "文件字典value")
    private String fileDictValue;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件url")
    private String url;
}
