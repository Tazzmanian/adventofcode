package com.tazz.adventofcode.common;

import com.tazz.adventofcode.common.readers.FileReadStrategy;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Path;

//@RequiredArgsConstructor
@AllArgsConstructor
@Setter
public class ParserContext<T> {
    private Parser<T> parser;
    private FileReadStrategy readStrategy;

    public Flux<T> parseClasspathFile(String classpathLocation) {
        try {
            Path path = new ClassPathResource(classpathLocation)
                    .getFile()
                    .toPath();

            return readStrategy
                    .read(path)
                    .transform(parser::parse);
        } catch (IOException e) {
            return Flux.error(e);
        }
    }
}
