package com.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductConfigurationCommand {
    private Long productId;
    private Long processorId;
    private Long ramId;
    private Long batteryId;
    private Long colorId;
    private List<Long> accessoriesList;
}
