package org.example.config;

import org.example.config.Symbols;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties(value = Symbols.class)
class SymbolsTest {

    @Autowired
    private Symbols symbols;

    @Test
    public void whenYamlList_thenLoadSimpleList() {
        assertThat(symbols.getSymbols()).isNotEmpty();
        assertThat(symbols.getSymbols().get(0)).isNotNull();
        assertThat(symbols.getSymbols().get(0)).hasFieldOrPropertyWithValue("name", "EURUSD");
        assertThat(symbols.getSymbols().get(0)).hasFieldOrPropertyWithValue("strategy", "Standard");
        assertThat(symbols.getSymbols().get(0).getParameters()).isNotEmpty();
        assertThat(symbols.getSymbols().get(0).getParameters().get("timeframe")).isEqualTo("1m");
    }
}
