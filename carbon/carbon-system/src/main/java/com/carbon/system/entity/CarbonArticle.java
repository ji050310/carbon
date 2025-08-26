package com.carbon.system.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * <p>
 * 碳文章
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-01
 */
@Data
@Document(indexName = "es_carbon_article")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonArticle对象", description="碳文章")
public class CarbonArticle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    @Id
    private Long id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章简介")
    private String description;

    @ApiModelProperty(value = "文章封面图")
    private String imageUrl;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "分类id")
    private String categoryId;

    @ApiModelProperty(value = "文章类型")
    private String type;

    @ApiModelProperty(value = "浏览数量")
    private Integer browseNum;

    @ApiModelProperty(value = "文章状态: 1.未发布 2.已发布 3.已下线 4.编辑中 5.已推送")
    private String status;

    @ApiModelProperty(value = "来源")
    private String copyFrom;

    @ApiModelProperty(value = "跳转url")
    private String url;

}
