# cybermonday graal cli example

This is a simple program that parses a markdown file and prints the HTML hiccup to stdout, but using graal native image.
It is quite fast, about 10x faster than pandoc producing HTML.

Here is a benchmarking example comparing pandoc to this, using [this](https://github.com/mxstbr/markdown-test-file) markdown test file (~10K).

```sh
❯ hyperfine --warmup 3 './target/cm-cli -f TEST.md'
Benchmark 1: ./target/cm-cli -f TEST.md
  Time (mean ± σ):      51.4 ms ±   3.9 ms    [User: 39.5 ms, System: 11.7 ms]
  Range (min … max):    40.1 ms …  56.8 ms    49 runs

❯ hyperfine --warmup 3 'pandoc --from gfm --to html --standalone TEST.md'
Benchmark 1: pandoc --from gfm --to html --standalone TEST.md
  Time (mean ± σ):     396.1 ms ±  21.7 ms    [User: 322.9 ms, System: 72.3 ms]
  Range (min … max):   363.8 ms … 425.5 ms    10 runs
```
