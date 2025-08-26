package com.carbon.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bae
 * @version 1.0
 * @date 2022/5/18 20:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Folder {

    @ApiModelProperty(value = "用户上传文件夹url")
    public String url;

    @ApiModelProperty(value = "刷新token")
    public String refreshToken;

}
