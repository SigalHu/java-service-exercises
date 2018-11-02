package com.sigalhu.jse.spring.data.mongo.dataobject;

import lombok.*;
import org.springframework.data.annotation.Id;
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
@Document(collection = "blog_info")
public class BlogInfoDO {

    @Id
    private String id;

    private String title;

    @Field(value = "nickname")
    private String author;

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
