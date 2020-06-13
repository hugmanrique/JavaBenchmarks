package me.hugmanrique.benchmarks;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Results on my machine:
 * Benchmark                              (bitsPerValue)  Mode  Cnt     Score    Error  Units
 * CompareVsEqualsCompactedArray.compare               7  avgt    5  4517.080   18.746  ns/op
 * CompareVsEqualsCompactedArray.compare               9  avgt    5  4667.507   32.675  ns/op
 * CompareVsEqualsCompactedArray.compare              14  avgt    5  4901.558   17.921  ns/op
 * CompareVsEqualsCompactedArray.equals                7  avgt    5  8081.748   42.750  ns/op
 * CompareVsEqualsCompactedArray.equals                9  avgt    5  8097.779   62.892  ns/op
 * CompareVsEqualsCompactedArray.equals               14  avgt    5  8103.681   89.402  ns/op
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class CompareVsEqualsCompactedArray {

    private static final int MAX_INDEX = 1000;
    private static final int LONG_MASK = 0b111111;

    @Param({"7", "9", "14"})
    private int bitsPerValue;

    @Benchmark
    public void compare(Blackhole bh) {
        for (int i = 0; i < MAX_INDEX; i++) {
            int bitOffset = i * bitsPerValue;
            int startBit = bitOffset & LONG_MASK;

            if (startBit + bitsPerValue > 64) {
                int shiftBy = 64 - startBit;
                bh.consume(shiftBy + 1);
            }

            bh.consume(bitOffset);
        }
    }

    @Benchmark
    public void equals(Blackhole bh) {
        for (int i = 0; i < MAX_INDEX; i++) {
            int bitOffset = i * bitsPerValue;
            int startBit = bitOffset & LONG_MASK;
            int nextOffset = (bitOffset + bitsPerValue) >> 6;

            if (bitOffset != nextOffset) {
                int shiftBy = 64 - startBit;
                bh.consume(shiftBy);
            }

            bh.consume(bitOffset);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(CompareVsEqualsCompactedArray.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }
}
