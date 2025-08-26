package com.carbon.system.vo;

import com.carbon.domain.common.annotation.Dict;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 碳文章 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-01
 */
@Data
@ApiModel(value = "CarbonArticleQueryVo对象", description = "碳文章查询参数")
public class CarbonArticleQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章简介")
    private String description;

    @ApiModelProperty(value = "文章封面图")
    private String imageUrl;

    @ApiModelProperty(value = "跳转url")
    private String url;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "作者头像链接")
    private String authorHeadPortrait;

    @ApiModelProperty(value = "评论数量")
    private Integer commentNumber;

    @ApiModelProperty(value = "评论列表")
    private List<CarbonArticleCommentQueryVo> commentList;

    @Dict(dictCode = "018")
    @ApiModelProperty(value = "数据字典 分类id对应分类name")
    private String categoryId;

    @Dict(dictCode = "018")
    @ApiModelProperty(value = "数据字典 type对应分类name")
    private String type;

    @ApiModelProperty(value = "浏览数量")
    private Integer browseNum;

    @Dict(dictCode = "026")
    @ApiModelProperty(value = "状态")
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间")
    private Date createdTime;
}
