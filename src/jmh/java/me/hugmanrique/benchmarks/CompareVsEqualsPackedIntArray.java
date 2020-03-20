package me.hugmanrique.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Benchmark                               Mode  Cnt        Score       Error  Units
 * CompareVsEqualsPackedIntArray.compare  thrpt   25  4201497,734   13713,921  ops/s
 * CompareVsEqualsPackedIntArray.equals   thrpt   25  4081149,596   13109,555  ops/s
 */
public class CompareVsEqualsPackedIntArray {

    private static final byte LONG_BITS = Long.BYTES * 8;
    private static final byte BITS_PER_INT = 9;

    @Benchmark
    public void compare(Blackhole bh) {
        for (int i = 0; i < 64; i++) {
            int bitPosition = i * BITS_PER_INT;
            int offset = bitPosition / LONG_BITS;
            int startBit = bitPosition % LONG_BITS;

            if ((startBit + BITS_PER_INT) < LONG_BITS) {
                bh.consume(startBit + offset);
            }
        }
    }

    @Benchmark
    public void equals(Blackhole bh) {
        for (int i = 0; i < 64; i++) {
            int bitPosition = i * BITS_PER_INT;
            int firstOffset = bitPosition / LONG_BITS;
            int startBit = bitPosition % LONG_BITS;
            int secondOffset = ((i + 1) * BITS_PER_INT - 1) / LONG_BITS;

            if (firstOffset == secondOffset) {
                bh.consume(startBit + firstOffset);
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(CompareVsEqualsPackedIntArray.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }
}
