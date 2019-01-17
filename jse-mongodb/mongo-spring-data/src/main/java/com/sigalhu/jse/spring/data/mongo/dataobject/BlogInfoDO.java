package com.sigalhu.jse.spring.data.mongo.dataobject;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 插入时不持久化，但可以更新
     */
    @ReadOnlyProperty
    private String title;

    /**
     * 为文档域指定自定义的元数据，value有内容时有效
     */
    @Field(value = "nickname")
    private String author;

    /**
     * 除非将属性设置为瞬时态的，否则java对象中的所有域都会持久化为文档中的域，持久化该字段会抛异常
     */
    @Transient
    private String name;

    @CreatedDate
    private Long createTime;

    //todo
    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
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

    public static void main(String[] args) {
        List<BlogInfoDO> blogInfoDOS = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            BlogInfoDO blogInfoDO = new BlogInfoDO();
            blogInfoDO.setId(String.valueOf(i % 10));
            blogInfoDO.setTitle(String.valueOf(i));
            blogInfoDOS.add(blogInfoDO);
        }
        System.err.println(
                blogInfoDOS.stream().collect(Collectors.toMap(BlogInfoDO::getId, blogInfo -> blogInfo))
        );
    }
}
