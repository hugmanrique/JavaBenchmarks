package me.hugmanrique.benchmarks;

import java.util.Random;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Benchmarks the different options on <a href="https://stackoverflow.com/a/21830188/4514467">this
 * great post</a>.
 *
 * Benchmark                     Mode  Cnt         Score        Error  Units
 * CeilIntegerDivision.option1  thrpt   25  94149045,475   761963,028  ops/s
 * CeilIntegerDivision.option2  thrpt   25  94328653,910   782928,022  ops/s
 */
public class CeilIntegerDivision {

    private static final Random random = new Random(1);

    @Benchmark
    public int option1() {
        int a = random.nextInt();
        int b = random.nextInt();

        if (b == 0) {
            // The actual implementation should throw ArithmeticException
            return 0;
        }

        return a / b + ((a % b == 0) ? 0 : 1);
    }

    @Benchmark
    public int option2() {
        int a = random.nextInt();
        int b = random.nextInt();

        if (a == 0) {
            return 0;
        } else if (b == 0) {
            // The actual implementation should throw ArithmeticException
            return 0;
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
