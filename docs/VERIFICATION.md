# Verification record

This record separates historical reports, source inspection, compilation, example execution, and actual behavior checks. It does not provide a coverage percentage, benchmark, or guarantee for the full project.

## Source inspected

Detailed inspection covered the array, linked-list, stack, queue, priority, and matrix implementations; custom node/comparison support; Merge Sort; recursive conversion, validation, and text transformation; calculator; file/terminal I/O; housing Pearson/regression; image processing; and KNN. Audio, race, sales, farming, and airport code were inspected for entry points, dependencies, resource assumptions, and limitations, with less complete behavioral review.

The project contains 91 production Java source files. All were compiled. That count is not a claim that every line or method was individually validated. The [academic mapping](ACADEMIC_NOTES.md) identifies the sixteen PDFs considered and which historical screenshots were examined.

## Compilation checked

- Linux, Temurin OpenJDK 21.0.13; UTF-8; release target 21 for the scripts.
- The preserved original source compiled before any code changes when the bundled JAR was supplied explicitly.
- `bash scripts/build.sh` compiles all production sources with `javac` and the existing JAR.
- `bash scripts/verify.sh` compiles production and checking sources and runs `comprobaciones.Comprobaciones`.
- Maven 3.9.9: `mvn -DskipTests package` passed with JDK 21, using the existing local JAR declared in the POM. Maven and its default build-plugin downloads were held outside the repository; no application or test dependency was added. Its main-class property now points to the actual package. The executable checks must still be run with `scripts/verify.sh`.

## Checked behaviors

The executable class reports **14/14 groups passing**:

| Group | Checked scope |
| --- | --- |
| Ordinary array | Empty state, zero capacity, full rejection, valid/invalid indices, case-insensitive duplicate search/count, first-value removal, indexed modification, clone independence of storage, inclusive sublist, reverse, shrinking, clearing |
| Empty reversal regression | Empty arrays of capacities 0, 1, 3 and one-element array |
| Ascending ordered array | Increasing numeric order, duplicate rejection, signed one-based search results, value removal |
| Fixed stack | Empty pop/peek, LIFO, capacity rejection, zero capacity, repeated clearing |
| Circular fixed queue | Empty removal, capacity-one reuse, full rejection, duplicates, wrap-around, FIFO |
| Linked queue regression | Empty state, duplicate preservation in FIFO order, integer duplicates, peek, repeated clearing |
| Priority queue | Both priority directions, equal-priority insertion order, capacity rejection, invalid element type, empty queries, clearing |
| Linked list and key/value list | Head/tail insertion, duplicates, lookup, index removal bounds, clone storage independence, traversal, key update without another node, removal, clearing |
| Matrices | Out-of-range cell access, non-square transpose, numeric validation, known 2 × 2 product, incompatible dimensions, zero-size numeric operation, 3D cell access |
| Merge Sort | Seven explicit input/output pairs: empty, singleton, reversed, sorted, repeated, existing-main example, negative values |
| Recursive utilities | Report conversion/validation values, zero, negatives, `Long.MAX_VALUE`, disjoint unsorted text segments, overlap/range/length rejection, original text round-trip, empty text |
| Calculator core | Postfix form and `-0.1875` report result, arithmetic precedence on a second expression |
| Statistics | Empty-list Pearson convention, known perfectly correlated data, regression on `y = 2x + 1`, prediction at `x = 4` within `1e-6` |
| Image processing | ARGB extraction/reconstruction, grayscale value from RGB `30,60,90`, 8 × 4 to 4 × 8 rotated dimensions; JPEG output tolerance of two channel units |

Checks throw on mismatches and require no assertion flags or test framework. They use small explicit expected results, not the structure implementations as a reference oracle. No production API was changed to make testing easier. Temporary synthetic image files are removed after checking.

## Examples executed

The following observations came from current source execution. A zero exit status alone does not establish correctness of every printed result.

| Main class (under `principales`) | Input / observation |
| --- | --- |
| `utiles.ordenamiento.PrincipalMergeSort` | Existing eight values; numeric order `1,2,2,3,4,5,6,6`, including duplicates. Mixed `Double`/`Integer` output retained. |
| `utiles.conversores.PrincipalConvertidorBinario` | Existing five values; results `10010110`, `111`, `0`, `11111111`, `10000000000`. |
| `utiles.conversores.PrincipalConvertidorHexadecimal` | Existing five values; results `FE05`, `FF`, `0`, `10`, `3E8`. |
| `utiles.validadores.PrincipalValidadorBinario` | Existing five values; results `true,false,true,true,false`. |
| `colas.PrincipalColaPrioridad` | Both priority directions, empty removal, and full insertion demonstration; ties additionally checked in the executable class. |
| `recursivas.PrincipalEncriptadorRecursivo` | Reads tracked input; writes encoded/decoded files under `target/`; original phrase restored. |
| `registros.casas.PrincipalPearsonCasas` | Supplied 498 pairs; coefficient `0.7964096556804487`, matching the historical capture. |
| `registros.casas.PrincipalGCasas` | Generated an 800 × 600 PNG; image visually inspected and included in documentation. |
| `utiles.imagenes.PrincipalImagen` | Input `1`; original tracked image loaded, grayscale JPEG written under `target/`. Tiny-image behavior checked separately. |
| `utiles.regresion.PrincipalRegresion` | Input `6`; supplied data produced rounded intercept `-42.846`, slope `10.367`, prediction `19.35`. No general prediction-accuracy claim. |
| `arreglos.PrincipalArreglo` | Demonstration completed; core operations additionally checked with explicit assertions. |
| `arreglos.PrincipalANumerico` | Demonstration completed; advanced vector claims remain unverified. |
| `arreglos.PrincipalAOrdenado` | Only menu startup/exit with end-of-input; term management was not exercised. |
| `arreglos.PrincipalAOrdenadoMetodos` | Demonstration completed; descending/mutation limitations remain. Random shuffle has no fixed output claim. |
| `arreglos.PrincipalArreglo2D`, `arreglos.PrincipalArreglo2DNumerico` | Demonstrations completed; selected matrix behaviors checked independently. |
| `listas.PrincipalListaDin`, `listas.PrincipalListaDinClave` | Demonstrations completed; partial interface implementations still present. |
| `pilas.PrincipalPila` | Printed `R,M,T,S,A` on separate lines, in LIFO display order. |
| `registros.ventas.PrincipalVentas`, `registros.ventas.PrincipalVentas2` | Printed sample totals `116.0` and `124.0`; all business cases not verified. |
| `registros.carreras.PrincipalCarreras` | Completed but aggregate result was `null`; preserved as a limitation, not a passing aggregation check. |
| `registros.casas.PrincipalAnalisisCasas` | Completed with supplied files; grouping output and statistical explanatory text not fully validated. |
| `utiles.matematicas.PrincipalExpresionArit` | Existing single-character postfix example printed `-2.0`; separate infix conversion is unfinished. |

## Minimal logic corrections

Before correction, the same executable suite passed 12/14 groups. The remaining two failed for these reasons:

1. **`Arreglo.invertir`:** an empty array has `indiceSuperior == -1`; integer division made the old loop enter and access invalid cells. The loop now swaps fewer than `cantidad()/2` pairs. It preserves the original symmetric-swap approach and does no work on empty/singleton arrays.
2. **`ColaDin.quitar`:** the value overload of `ListaDin.quitar` removes all occurrences. For `uno,dos,uno`, successive dequeues returned `uno,dos,null`. Removing position zero instead produces `uno,dos,uno`. The linked-list value-removal contract is unchanged.

Other edits affect build configuration, resource/output paths, and two image-menu option labels. Sorting, numerical algorithms, recursive implementations, nodes, and public method/package names were not rewritten.

## Pending and diagnostic results

Separate temporary diagnostic programs reproduced dynamic-stack no-op removal, ascending insertion despite `DEC`, empty ordered-array reverse failure, negative linked-list indexing, count exceeding array capacity after an oversized fill, capacity-zero fixed-queue failure, and the two-row KNN error documented in [limitations](LIMITATIONS.md). These observations are **known failures**, not additional passing features or part of the 14 checked groups.

Unverified areas include the complete collection interfaces, all menu workflows, arbitrary null/mixed types, KNN correctness, general mathematical validity of advanced vector operations, audio output, farming scenarios, race aggregation, airport time behavior, exhaustive image transforms, general regression convergence, and native Windows execution. No historical self-evaluation percentage is reused as current evidence.
