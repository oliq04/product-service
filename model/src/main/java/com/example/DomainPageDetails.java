package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DomainPageDetails {
    private int size;
    private int number;
    private int numberOfElements;
    private int totalPages;
}
