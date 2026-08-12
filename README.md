# Game of Life — Java/Swing Prototype

A historical Java/Swing implementation of Conway's Game of Life and configurable Life-like cellular-automaton rules.

The application provides a desktop UI for randomizing a board, iterating generations, clearing state and changing birth/survival rules. The implementation also contains early experiments with parallel population counting and concurrent execution.

## Status

**Legacy predecessor project.** This repository is kept as a record of earlier Java desktop and cellular-automata work. The substantially more developed current version of this line of experimentation is [`clife`](https://github.com/lisu188/clife), which uses modern C++, an optimized packed simulation backend, X11/XShm rendering, correctness tests and reproducible performance benchmarks.

## Structure

- `src/com/lis/mgr/logic` — cellular-automaton state and rules
- `src/com/lis/mgr/gui` — Swing UI
- `src/com/lis/mgr/Main.java` — application wiring and entry point

## Repository hygiene

IDE-specific project files are intentionally ignored; the repository contains source and project-relevant resources rather than local IntelliJ configuration.
