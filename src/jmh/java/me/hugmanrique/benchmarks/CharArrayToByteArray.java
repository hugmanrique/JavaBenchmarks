package me.hugmanrique.benchmarks;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Results on my machine:
 * Benchmark                              Mode  Cnt     Score    Error  Units
 * CharArrayToByteArray.stringAndToBytes  avgt    5  2326,950    3,361  ns/op
 * CharArrayToByteArray.manual            avgt    5   622,081   11,655  ns/op
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class CharArrayToByteArray {

    private static final char[] charArray = new char[1024];

    static {
        Random random = new Random(1);

        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) random.nextInt();
        }
    }

    @Benchmark
    public byte[] stringAndToBytes() {
        return new String(charArray).getBytes();
    }

    @Benchmark
    public byte[] manual() {
        byte[] result = new byte[charArray.length * 2];
        int index = 0;

        for (char value : charArray) {
            result[index++] = (byte) (value & 0xFF);
            result[index++] = (byte) ((value & 0xFF00) >> 8);
        }

        return result;
    }

    public static void main(final String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(CharArrayToByteArray.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }
}
