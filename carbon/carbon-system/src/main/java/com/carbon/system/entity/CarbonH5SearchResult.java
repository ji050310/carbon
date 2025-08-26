package com.carbon.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "es_carbon_h5_search_result")
public class CarbonH5SearchResult {

    @ApiModelProperty(value = "表的id")
    @Id
    private Long id;

    @ApiModelProperty(value = "主键，如果分类类型是文章资讯则为文章资讯id，如果分类类型为方法学，则为方法学的id")
    private Long primaryKey;

    @ApiModelProperty(value = "分类类型")
    @Field(type = FieldType.Keyword)
    private String  categoryType;

    @ApiModelProperty(value = "搜索结果显示的标题")
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String title;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    @ApiModelProperty(value = "方法学内容")
    private String content;

    @ApiModelProperty(value = "对应的类型")
    @Transient //默认情况下，所有字段在存储或检索时都映射到文档，此注释不包括该字段。
    private Object data;
}
