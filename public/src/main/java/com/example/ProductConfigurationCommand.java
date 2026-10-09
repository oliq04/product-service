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
    private List<Long> productOptionIds;
}
