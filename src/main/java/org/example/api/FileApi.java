package org.example.api;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.valueobject.Candle;
import org.example.valueobject.Symbol;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class FileApi implements DataStreamingApi {

    private MappingIterator<Candle> candleIterator;

    public FileApi(final Symbol symbol) {
        initialise(symbol);
    }

    @Override
    public Optional<Candle> getNext() {
        if (candleIterator.hasNext()) {
            return Optional.of(candleIterator.next());
        }
        return Optional.empty();
    }

    private void initialise(final Symbol symbol) {
        final Map<String, String> parameters = symbol.getParameters();

        if(!parameters.containsKey("filepath")) {
            throw new IllegalArgumentException("Missing filePath parameter");
        }

        final String filePath = parameters.get("filepath");
        final File csvFile = new File(filePath);

        try {
            final CsvMapper csvMapper = new CsvMapper();
            csvMapper.registerModule(new JavaTimeModule());

            this.candleIterator = csvMapper
                    .readerWithTypedSchemaFor(Candle.class)
                    .readValues(csvFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
