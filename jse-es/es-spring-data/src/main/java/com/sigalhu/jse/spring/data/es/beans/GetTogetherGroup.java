package com.sigalhu.jse.spring.data.es.beans;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author huxujun
 * @date 2020/1/25
 */
@Data
@Document(indexName = "get-together", type = "group")
public class GetTogetherGroup {

    @Id
    private long id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String organizer;
}
