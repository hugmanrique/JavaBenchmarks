package me.hugmanrique.benchmarks;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class Template {

    @Benchmark
    public void firstCase() {
        throw new AssertionError(
                "Cannot run Template benchmark. Did you forget to replace the include in the main method?");
    }

    @Benchmark
    public void secondCase() {

    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                // TODO REPLACE ME
                .include(Template.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }
}
