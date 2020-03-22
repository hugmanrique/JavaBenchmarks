package me.hugmanrique.benchmarks;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Benchmarks the different options on <a href="https://stackoverflow.com/a/21830188/4514467">this
 * great post</a>.
 *
 * Benchmark                    Mode  Cnt  Score   Error  Units
 * CeilIntegerDivision.option1  avgt   25  3,792   0,027  ns/op
 * CeilIntegerDivision.option2  avgt   25  3,851   0,013  ns/op
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class CeilIntegerDivision {

    private static final Random random = new Random(1);

    private int a;
    private int b;

    @Setup
    public void setup() {
        a = random.nextInt();
        b = random.nextInt();
    }

    @Benchmark
    public int option1() {
        if (b == 0) {
            // The actual implementation should throw ArithmeticException
            return a;
        }

        return a / b + ((a % b == 0) ? 0 : 1);
    }

    @Benchmark
    public int option2() {
        if (a == 0) {
            return b;
        } else if (b == 0) {
            // The actual implementation should throw ArithmeticException
            return a;
        }

        return (a - 1) / b + 1;
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(CeilIntegerDivision.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }
}
