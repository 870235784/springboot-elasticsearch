package com.tca.elasticsearch.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author zhoua
 * @Date 2019/10/29
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@Document(indexName = "item", type = "docs")
public class Item {

    /**
     * 主键id
     */
    @Id
    private Long id;

    /**
     * 标题
     */
    @Field(type = FieldType.Text)
    private String title;

    /**
     * 分类
     */
    @Field(type = FieldType.Keyword)
    private String category;

    /**
     * 品牌
     */
    @Field(type = FieldType.Keyword)
    private String brand;

    /**
     * 价格
     */
    @Field(type = FieldType.Double)
    private Double price;

    /**
     * 图片地址
     */
    @Field(type = FieldType.Keyword, index = false)
    private String images;

}
