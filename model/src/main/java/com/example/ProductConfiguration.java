package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductConfiguration {
    private Long productId;
    private Long processorId;
    private Long ramId;
    private Long batteryId;
    private Long colorId;
    private List<Long> accessoriesList;
}
