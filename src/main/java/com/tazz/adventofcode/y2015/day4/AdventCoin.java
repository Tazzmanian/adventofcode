package com.tazz.adventofcode.y2015.day4;

import com.tazz.adventofcode.common.Parser;
import com.tazz.adventofcode.common.ParserContext;
import com.tazz.adventofcode.common.readers.FileReadStrategy;
import com.tazz.adventofcode.common.readers.WholeFileReadStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AdventCoin {

    private final MessageDigest md;
    private final ParserContext<String> parserContext;

    public AdventCoin() throws NoSuchAlgorithmException {
        FileReadStrategy fileReader = new WholeFileReadStrategy();
        Parser<String> parser = new SecretKeyParser();
        parserContext = new ParserContext<>(parser, fileReader);
        md = MessageDigest.getInstance("MD5");
    }

    public Mono<Integer> mineFiveZeros(String classpathFile) {
        return mineFiveZeros(classpathFile, "00000");
    }

    public Mono<Integer> mineSixZeros(String classpathFile) {
        return mineFiveZeros(classpathFile, "000000");
    }

    private Mono<Integer> mineFiveZeros(String classpathFile, String leadingString) {
        return parserContext.parseClasspathFile(classpathFile)
                .single()                                   // Mono<String> secret
                .flatMap(secret ->
                        Flux.range(1, Integer.MAX_VALUE - 1)   // sequential candidates 1..MAX-1
                                .publishOn(Schedulers.boundedElastic()) // offload CPU work from main thread
                                .map(i -> {
                                    try {
                                        MessageDigest md = MessageDigest.getInstance("MD5");
                                        byte[] digest = md.digest((secret + i).getBytes(StandardCharsets.UTF_8));
                                        String hex = bytesToHex(digest);
                                        return Tuples.of(i, hex);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                                .filter(t -> t.getT2().startsWith(leadingString)) // five leading zeros
                                .map(Tuple2::getT1)                         // keep the integer
                                .next()                                     // Mono<Integer> first match
                );
    }

    // small helper - fast bytes -> hex
    private static String bytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
