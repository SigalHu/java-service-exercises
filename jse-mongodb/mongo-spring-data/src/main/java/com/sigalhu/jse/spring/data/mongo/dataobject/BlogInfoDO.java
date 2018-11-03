package com.sigalhu.jse.spring.data.mongo.dataobject;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * @author huxujun
 * @date 2018/11/3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//标示映射到mongodb文档上的领域对象
@Document(collection = "blog_info")
public class BlogInfoDO {

    /**
     * 标示某个域为id域
     */
    @Id
    private String id;

    private String title;

    /**
     * 为文档域指定自定义的元数据
     */
    @Field(value = "nickname")
    private String author;

    /**
     * 除非将属性设置为瞬时态的，否则java对象中的所有域都会持久化为文档中的域
     */
    @Transient
    private String name;

    private Date createTime;

    private Date updateTime;

    private List<Comment> comments;

    @Data
    public class Comment {

        private String id;

        private String nickname;

        private Integer score;

        private String content;

        private Date date;
    }
}
