package com.example;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PageDto<T> {
    private int pageSize;
    private int currentPage;
    private int total;
    private int totalPages;
    private List<T> content;

    public static <T> PageDto<T> toPageableDto(List<T> content, DomainPageDetails domainPageableDetails) {
        return new PageDto<>(domainPageableDetails.getSize(), domainPageableDetails.getNumber(),
                domainPageableDetails.getNumberOfElements(), domainPageableDetails.getNumberOfElements(), content);
    }
}
