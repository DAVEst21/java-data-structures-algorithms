# Data Structures and Algorithms in Java

An educational Java project that builds data structures by hand and uses them in small applications. Arrays, linked nodes, stacks, queues, matrices, sorting, recursion, and numerical examples show how storage choices affect program behavior.

Developed by **David Ignacio Becerra Morán** for the Data Structures course in the Software Engineering program at Universidad Autónoma de Zacatecas in 2025. The original project identifier is `ed_4b_25`; the suggested repository name is `java-data-structures-algorithms`. Original Spanish identifiers and comments are preserved.

The custom structures and algorithms are academic implementations. Implementing them directly makes index management, links, traversal, capacity, recursion, and composition visible. This repository also retains third-party support code, identified below. Some modules remain incomplete: the verified scope is explicitly separated from experimental work.

## Data structures and algorithms

| Area | Implementations | Current scope |
| --- | --- | --- |
| Arrays | `Arreglo`, `ArregloNumerico`, `ArregloOrdenado` | Basic fixed-capacity operations verified; ascending ordered insertion rejects duplicates. Several inherited methods remain incomplete. |
| Linked lists | `ListaDin`, `Nodo`, `ListaDinClave`, `NodoClaveValor` | Basic insertion, lookup, indexed removal, traversal, and key replacement verified; advanced methods have limitations. |
| Stacks | `PilaFija`, `PilaDin` | Fixed stack verified; dynamic stack incomplete. |
| Queues | `ColaFija`, `ColaDin`, `ColaPrioridad` | Circular FIFO, linked FIFO, and array-backed priority selection verified for selected operations. Fixed queue has two pending methods. |
| Multidimensional arrays | `Arreglo2D`, `Arreglo2DNumerico`, `Arreglo3D` | Bounds, cell access, transpose, and a known matrix product verified. |
| Sorting | `MergeSort` | Recursive split and merge using `Arreglo`; empty, singleton, sorted, reversed, negative, and duplicate inputs checked. |
| Recursion | Binary/hexadecimal conversion, binary-digit validation, `EncriptadorRecursivo` | Known results and boundary cases checked, including examples from the academic report. |
| Applications | Calculator, housing statistics, regression, image processing, sales, farming, race records, airport simulation, KNN, audio | Selected applications are reproducible; others are retained as experiments. See [verification](docs/VERIFICATION.md) and [limitations](docs/LIMITATIONS.md). |

## Requirements and existing dependencies

- **JDK 21** (`java` and `javac`), matching the existing Maven configuration.
- **Bash** for the provided scripts on Linux/macOS. They also accept `JAVA_HOME` when the JDK is outside `PATH`. Native Windows commands are shown below.
- The existing bundled `libs/jfreechart-demo-1.5.2-jar-with-dependencies.jar`, used only by `GraficadorDispersion`. It already contains JFreeChart 1.5.2, JFreeSVG 3.4, and OrsonPDF 1.9; its usage has not been expanded.
- `utils/audio/wavfile` is bundled source credited to **A. Greensted** in its original header. It supports the audio exercise.

No new application or test dependencies are required. The JDK scripts work without Maven or dependency downloads. Maven is optional; its POM now declares the existing local JAR using `systemPath`. No project license has been added; existing third-party credits are preserved.

## Build and verify

From the repository root:

```bash
java -version
javac -version
bash scripts/build.sh
bash scripts/verify.sh
```

`build.sh` compiles all production Java sources into `target/jdk/classes`. `verify.sh` additionally compiles and runs the small JDK-only checking class. It exits with an error if a check fails; Java assertion flags are unnecessary. The verified run ends with:

```text
Groups passed: 14/14
```

These are fourteen groups of selected behavior checks, not a coverage measurement or a claim that every module is complete. [Verification details](docs/VERIFICATION.md) distinguish source inspection, compilation, executed examples, checked behavior, and pending work.

To compile directly on Windows with PowerShell and JDK 21:

```powershell
New-Item -ItemType Directory -Force target/jdk/classes | Out-Null
$sources = Get-ChildItem src/main/java -Recurse -Filter *.java |
    ForEach-Object { '"' + $_.FullName.Replace('\', '/') + '"' }
[System.IO.File]::WriteAllLines("$PWD/target/jdk/sources.txt", [string[]]$sources, [System.Text.UTF8Encoding]::new($false))
javac --release 21 -encoding UTF-8 -cp "libs/*" -d target/jdk/classes '@target/jdk/sources.txt'
java -cp "target/jdk/classes;libs/*" principales.utiles.ordenamiento.PrincipalMergeSort
```

The argument file uses quoted paths and UTF-8 without a byte-order mark. The Bash workflow was executed on Linux; these PowerShell instructions have not been executed on Windows.

For an existing Maven setup, `mvn -DskipTests package` uses the same bundled JAR. This build also passed with Maven 3.9.9 and JDK 21. The resulting JAR has no executable main manifest and does not bundle the example resources or JFreeChart. Run examples from the checkout. `mvn test` does not run the checking class; use `scripts/verify.sh`.

## Selected examples and verified results

The runner builds the project and sets its working directory to the repository root, so resource paths resolve consistently:

```bash
bash scripts/run.sh principales.utiles.ordenamiento.PrincipalMergeSort
bash scripts/run.sh principales.colas.PrincipalColaPrioridad
bash scripts/run.sh principales.utiles.conversores.PrincipalConvertidorBinario
bash scripts/run.sh principales.utiles.conversores.PrincipalConvertidorHexadecimal
bash scripts/run.sh principales.recursivas.PrincipalEncriptadorRecursivo
bash scripts/run.sh principales.registros.casas.PrincipalPearsonCasas
bash scripts/run.sh principales.registros.casas.PrincipalGCasas
```

The original programs print Spanish messages. The table describes results observed during the current verification:

| Example | Input | Verified result |
| --- | --- | --- |
| Merge Sort | `5 2 4 6 1 3 2 6` | Numeric order `1 2 2 3 4 5 6 6`; duplicates retained. |
| Priority queue | Priorities `10, 5, 8, 2, 6`, highest first | Removal priorities `10, 8, 6, 5, 2`. Tests also check lowest first and insertion order for tied priorities. |
| Decimal to binary | `150`, `0`, `1024` | `10010110`, `0`, `10000000000`. |
| Decimal to hexadecimal | `65029`, `255`, `0` | `FE05`, `FF`, `0`. |
| Recursive text transformation | Supplied text file; positions `3, 10, 16`, lengths `2, 4, 3` | `Cur(os) de I(rofn)má(cit)a`; decoding restores `Curso de Informática`. This is a reversible exercise, not secure encryption. |
| Housing Pearson correlation | Two supplied files, 498 values each | `0.7964096556804487`. |
| Scatter plot | Same housing data | `target/grafico_casas.png`, 800 × 600 pixels. |

The exact sorted-value line currently contains mixed numeric representations:

```text
1.0 2.0 2.0 3.0 4.0 5.0 6.0 6
```

The merge writes compared values as `double`, while remaining values can retain their original type. This behavior is preserved.

For the image application:

```bash
bash scripts/run.sh principales.utiles.imagenes.PrincipalImagen
```

Choose `1` for grayscale. The included original image is read from `resultados/imagenes/`; the result is written to `target/grises.jpg`. Menu options `10` and `11` select the frame and negative operations. Only grayscale was exercised through this menu; the checking class also checks color components and rotated dimensions using a tiny generated image.

The calculator entry point is `principales.registros.calculadora.PrincipalCalculadora`. A directly checked expression, `(1-2.0)*3/2^(12.0-8)`, produces `-0.1875`. The core conversion and evaluation were checked programmatically; the multi-prompt variable-entry flow was not verified.

Generated outputs from the adjusted examples go to ignored `target/`. The original academic output folders remain locally available and are not overwritten by these examples.

## Selected screenshots

**Historical academic capture — priority queue.** Extracted from the original report, PDF page 6, figure 1. It is a historical illustration, not evidence of the current build. The current queue behavior was checked separately.

![Historical priority queue example](docs/images/priority-queue-historical.png)

**Historical academic capture — recursive binary conversion.** Extracted from PDF page 15, figure 15 of the recursion report. The pictured inputs were checked again with the current source.

![Historical recursive binary conversion example](docs/images/binary-conversion-historical.png)

**Current generated output — housing scatter plot.** Generated from the supplied files by `PrincipalGCasas` during this review, using the existing chart dependency.

![Housing scatter plot generated during verification](docs/images/housing-scatter.png)

Only these two historical captures were selected after visual inspection for personal information. Original documents remain untouched. [Academic report mapping and image provenance](docs/ACADEMIC_NOTES.md) explain the correspondence with the current classes.

## Project structure

```text
src/main/java/
  colecciones/         Custom structures, nodes, and interfaces
  utils/               Sorting, recursion, I/O, numerical and media utilities
  registros/           Application models and operations
  principales/         Individual runnable examples
  resources/           Original text datasets and audio input
src/test/java/
  comprobaciones/      Executable checks using the JDK
libs/                  Existing chart JAR
resultados/imagenes/   Original image input retained for the image example
scripts/               Build, run, and verification scripts
docs/                  Verification, report mapping, limitations, selected images
target/                Ignored compilation products and new example outputs
```

The original resource layout and Maven coordinates are preserved. There is no single application entry point. Each `Principal...` class represents an individual exercise.

## Implementation notes and learning outcomes

- `Arreglo` uses an object array and an upper occupied index. It distinguishes capacity from element count, and shifts elements for removal. Most text comparisons use case-insensitive `toString()` equality.
- `ListaDin` maintains first/last links and an explicit iterator. Its `quitar(Object)` removes **all** matching values; `quitar(int)` removes one position. `ColaDin` now uses the latter to preserve FIFO duplicates.
- `ArregloOrdenado.buscar` returns **one-based** positive positions when found and negative insertion positions otherwise. Ordinary array indexing remains zero-based. Ascending search/insertion is the verified path.
- `ColaPrioridad` appends to a custom array and scans when selecting an element. It is not implemented as a heap.
- Recursive conversion exposes quotient/remainder steps and base cases. Merge Sort exposes splitting, recursive calls, and merging through the custom array API.
- The applications reuse these structures to represent pixels, numeric samples, expression operands, records, and data columns. Their limitations show why individual operation checks matter beyond a successful demonstration.

For fixed-cost element operations, the implementation supports these time bounds; they are derived from loops and storage access, not benchmarks:

| Operation | Time | Reason |
| --- | --- | --- |
| `Arreglo.poner`, `obtener`, parameterless `quitar` | O(1) | One array position and index update/access; no automatic growth. |
| `Arreglo.buscar` / removal by value | O(n) | Linear scan, followed by shifting when removing. String comparison cost is additional. |
| `ListaDin.poner`, `ColaDin.poner` / `quitar` | O(1) | Tail append or first-node removal. |
| `PilaFija` push/pop and circular queue enqueue/dequeue | O(1) | Direct array operations and endpoint updates. |
| `ColaPrioridad` insertion / peek or removal | O(1) / O(n) | Append versus scan and possible shift; printing the full priority order is O(n²). |
| Merge Sort | O(n log n) | Two half-size recursive calls and linear copying/merging per level; O(n) peak auxiliary storage plus O(log n) call depth. |
| Base conversion for positive n | O(log n) | Repeated division by the fixed base; output and recursive depth scale with the digit count. |

## Known limitations and incomplete modules

- The broad collection interfaces contain stubs and inconsistent contracts. Verified operations do not imply complete interface support.
- `PilaDin` removal and printing are incomplete through `ListaDin`. `ColaFija.vaciar` and `verTope` throw `UnsupportedOperationException`; zero-capacity insertion is unsafe.
- Descending ordered-array insertion/search, some ordered mutations, oversized fills, negative linked-list indices, and fixed-size list conversion helpers have known defects or constraints.
- KNN is experimental: distance/class pairs are compared as text rather than numeric distances, and discretization/counting is inconsistent. Its historical predictions are not presented as validated results.
- The calculator assumes valid syntax and sufficient stack capacity. Chained exponentiation is left-associative; unary minus is unsupported. `TratamientoExpresiones` has a separate unfinished infix converter.
- Audio, farming, race aggregation, and airport simulation need additional validation. The audio main currently contains only commented experiments; the race demonstration returns `null` for its aggregate query.
- File-error handling, null values, mismatched data lengths, and several advanced numerical methods require further work. Example resources are filesystem inputs; the packaged JAR is not standalone.

See [the detailed limitations and separate follow-up proposals](docs/LIMITATIONS.md). No pending modules were filled in to imply completion.
