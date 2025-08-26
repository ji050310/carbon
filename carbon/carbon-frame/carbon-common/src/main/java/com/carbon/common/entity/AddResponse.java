package com.carbon.common.entity;

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
public class AddResponse {
    String objToken;
    String url;
}
