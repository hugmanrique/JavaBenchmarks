package me.hugmanrique.benchmarks;

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

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class Template {

    @Setup
    public void setup() {

    }

    @Benchmark
    public void firstCase() {

    }

    @Benchmark
    public void secondCase() {

    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(Template.class.getSimpleName())
                .build();

        new Runner(opts).run();
    }
}
