package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DomainPageable {
    private int page;
    private int size;
}
