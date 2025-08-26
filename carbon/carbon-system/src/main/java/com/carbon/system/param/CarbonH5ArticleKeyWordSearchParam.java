package com.carbon.system.param;

import com.carbon.domain.common.ApiCode;
import com.carbon.domain.common.QueryParam;
import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  资讯页面顶部搜索框 查询参数对象
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonH5ArticleKeyWordSearchParam对象", description="查询参数")
public class CarbonH5ArticleKeyWordSearchParam extends QueryParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "搜索关键字")
    private String keyword;

    @ApiModelProperty(value = "资讯的筛选项")
    private String categoryId;


//    @ApiModelProperty(value = "排序依据 0为默认的 综合排序,1为最新发布")
//    private Integer sortType;
//
//    @ApiModelProperty(value = "发布时间 默认是0（不限）, ")
//    private Integer pushTime;




    public static enum PushTime {
        DEFAULT(0, "不限"),
        DAY(0, "一天内"),
        WEEK(0, "一周内"),
        MONTH(0, "一个月内"),
        HALF_YEAR(0, "半年内"),
        YEAR(0, "一年内");

        private final int pushCode;
        private final String pushName;


        PushTime(int pushCode, String pushName) {
            this.pushCode = pushCode;
            this.pushName = pushName;
        }
        public static PushTime getSearchType(int pushCode) {
            PushTime[] ecs = PushTime.values();
            for (PushTime ec : ecs) {
                if (ec.getPushCode() == pushCode) {
                    return ec;
                }
            }
            return DEFAULT;
        }


        public int getPushCode() {
            return pushCode;
        }

        public String getPushName() {
            return pushName;
        }


    }



}
