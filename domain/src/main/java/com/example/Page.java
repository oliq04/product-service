package com.example;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Page<T> {
    private DomainPageDetails domainPageDetails;
    private List<T> content;

    public static <T> Page<T> toPage(List<T> content, DomainPageDetails page) {
        return new Page<>(page, content);
    }

}
