package com.sigalhu.jse.lombok.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author huxujun
 * @date 2018/8/24
 */
//相当于@Getter/@Setter/@RequiredArgsConstructor/@ToString/@EqualsAndHashCode
@Data
public class Example1 {
    private final String name;

    @NonNull
    @Setter(AccessLevel.PRIVATE)
    private Integer age;

    private Double score;

    private String[] tags;
}
