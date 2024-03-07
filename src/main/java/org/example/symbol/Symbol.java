package org.example.symbol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Symbol {

    private String name;
    private String strategyName;
    private Map<String, String> parameters;
}
