package com.example;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class DomainPage<T> {
    private DomainPageDetails domainPageDetails;
    private List<T> content;

    public static <T> DomainPage<T> toPageable(List<T> content, DomainPageDetails page) {
        return new DomainPage<>(page, content);
    }
}
