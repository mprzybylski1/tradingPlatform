package org.example.config;

import lombok.Data;
import org.example.symbol.Symbol;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "application")
@Data
public class Symbols {

    private List<Symbol> symbols;
}