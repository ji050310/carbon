package com.carbon.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bae
 * @version 1.0
 * @date 2022/4/29 21:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    String fileToken;

    String text;

    String replaceText;
}
