# :dash: Java Performance Benchmarks

Various benchmarks illustrating the performance benefits or pitfalls of different approaches.

I normally run benchmarks through IntelliJ IDEA. However, you can also use the `./gradlew jmh` task.

My PC is in Spanish, so numbers use commas to indicate decimal places (e.g. `0,612 ns/ops`) instead
of periods. I replace `(\d+),(\d+)` by `$1.$2` prior to pasting my machine results in the javadoc.

## License

[MIT](LICENSE) &copy; [Hugo Manrique](https://hugmanrique.me)
