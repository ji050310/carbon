package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 *  查询结果对象
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-09-15
 */
@Data
@ApiModel(value="CarbonArticleCommentQueryVo对象", description="查询参数")
public class CarbonArticleCommentQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "文章表id")
    private Long articleId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "回复评论者id")
    private Long replyUserId;

    @ApiModelProperty(value = "点赞数")
    private Integer likeNum;

    @ApiModelProperty(value = "父评论id")
    private Long parentId;

    @ApiModelProperty(value = "是否有子评论")
    private Boolean havaChild;
}
