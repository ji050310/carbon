/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.carbon.domain.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Li Jun
 * @since 2021-06-11
 */
@Data
@ApiModel("BaseEntity")
public abstract class BaseEntity implements Serializable{

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人ID")
    private Long creatorId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
//    @Field(type = FieldType.Date,format = DateFormat.custom,pattern ="uuuu-MM-dd HH:mm:ss")
    private Date createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新人ID")
    private Long updatedId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
//    @Field(type = FieldType.Date,format = DateFormat.custom,pattern ="uuuu-MM-dd HH:mm:ss")
    private Date updatedTime;
}
