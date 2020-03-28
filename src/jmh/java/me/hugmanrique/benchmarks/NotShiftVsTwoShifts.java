package me.hugmanrique.benchmarks;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Results on my machine:
 * Benchmark                      (shiftBy)  Mode  Cnt  Score   Error  Units
 * NotShiftVsTwoShifts.notShift           7  avgt    5  3,791   0,013  ns/op
 * NotShiftVsTwoShifts.notShift           9  avgt    5  3,783   0,020  ns/op
 * NotShiftVsTwoShifts.notShift          14  avgt    5  3,791   0,067  ns/op
 * NotShiftVsTwoShifts.twoShifts          7  avgt    5  3,561   0,013  ns/op
 * NotShiftVsTwoShifts.twoShifts          9  avgt    5  3,559   0,054  ns/op
 * NotShiftVsTwoShifts.twoShifts         14  avgt    5  3,575   0,010  ns/op
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class NotShiftVsTwoShifts {

    private static final long VALUE = 0x1234567890ABCDEFL;

    @Param({"7", "9", "14"})
    private int shiftBy;
    private long bitmask;

    @Setup
    public void setup() {
        bitmask = (1L << shiftBy) - 1;
    }

    @Benchmark
    public long notShift() {
        return (VALUE & ~(bitmask << shiftBy));
    }

    @Benchmark
    public long twoShifts() {
        return (VALUE << shiftBy >>> shiftBy);
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(NotShiftVsTwoShifts.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }
}
