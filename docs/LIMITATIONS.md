# Current limits and preserved contracts

The collection stubs, dynamic-stack removal/display, fixed-queue clearing/peek, descending ordered operations, numeric KNN ordering, and application defects identified in the first review have been addressed. The current JDK suite passes its original 14 groups and 384 additional assertions. These results do not establish correctness for every possible input.

## Corrections completed

| Original finding | Current behavior |
| --- | --- |
| Dynamic stack removed nothing and printed nothing | `ListaDin.quitar()` removes the last node; reverse display uses the existing `PilaFija`, sized for all nodes. |
| Collection methods returned placeholders or threw unimplemented errors | Array conversion, matching, aliases, iterator, fill, shuffle, retention, and replacement methods are implemented; fixed queue has clearing and peek. No placeholder method bodies remain in the reviewed collection classes. |
| List helpers truncated to ten values | Conversion, reverse display, and occurrence positions use the current node count. |
| Negative list indices referred to the first item | Indexed lookup/change reject negative indices. |
| Oversized fill corrupted array count | Fill is bounded by capacity; numeric subclasses retain numeric storage. |
| Descending search/insertion and ordered mutations broke invariants | Search honors direction, inversion safely switches direction, mutations reinsert in order, and duplicate-producing replacements are rejected without losing data. |
| KNN ordered numeric distances as text | Pairs implement numeric comparison with original row index as tie-breaker; equal-distance rows are retained in the custom ordered array. Rounded-bin counts, loaded-row count, voting ties, and input checks are corrected. |
| Infix conversion was pending; terminal prompts lost piped values | Conversion reuses the existing calculator stack algorithm; terminal calls share one reader. Errors and expression capacity limits are explicit. |
| Race lookup reversed cube axes | Event/runner/year order is consistent; original aggregate is `90.0`. |
| Farming December output cast an array to a number | Uses the returned five-element array by year; empty aggregate queries are guarded. |
| Sales cashier fields were swapped | Correct constructor argument order; invalid quantities/items are rejected. |
| Statistics silently truncated unequal inputs | Paired values are validated; sample/population explanatory text now describes cancellation of normalization factors. |
| WAV headers kept old frame counts | Current buffer length determines frame count; speed, silence, and time reversal preserve full channel frames. |
| Airport epoch times overflowed `int` and priorities became stale | Times use `long`; advancing time rebuilds priorities using the existing custom queue. |

## Collection contracts retained

- Structures intentionally keep different return conventions. `Arreglo.buscar` returns a zero-based index or `-1`; `ArregloOrdenado.buscar` returns a one-based positive result or negative insertion position; `ListaDin.buscar` returns a value or `null`.
- `poner(int,Object)` replaces an occupied array element but inserts a node in a linked list. Ordered-array replacement can move the value. `ponerInicio` on an ordered array places the value according to order.
- Ordered arrays reject duplicates. Their numeric `rellenar(Object)` generates integer sequences; fractional/nonfinite/out-of-range bounds are ignored. The two-argument fill inserts at most one copy, consistent with uniqueness.
- `modificarLista(Arreglo,Arreglo)` means indices/new values; `modificarLista(ListaDatos,ListaDatos)` means old/new values. Use the intended static types, as the corrected ordered-array main does.
- Some ordinary-array/list bulk operations may partially apply before returning failure. Ordered batch replacement and KNN dataset loading validate a temporary custom structure before publishing it.
- Arrays still compare capacity in `esIgual`. Text equality is case-insensitive `toString()` equality; it does not imply general numeric or deep object equality.
- Copies/clones are shallow. Ordinary array/list substitution can share backing storage or nodes. Do not structurally mutate during iteration; not every mutator invalidates an active iterator. Direct mutable getters can bypass validation and ordering.
- `ListaDin.esSublista` tests a contiguous sequence; the array version tests membership. Linked-list resizing truncates but does not pad. Null values are supported only by explicitly checked paths; `ListaDinClave` rejects null keys/values.
- Negative capacities/dimensions follow Java allocation behavior in several constructors. No custom allocator, automatic growth policy, generic type migration, or unified collection API was introduced.

## Mathematical and application boundaries

**Vector dependence:** `sonLinealmenteDependientes` still checks one supplied nonzero coefficient combination. Establishing whether any nontrivial combination exists requires a separate algorithm, such as elimination. That redesign was not substituted for the original exercise. Orthogonality now checks dimensions; parallelism handles zero coordinates and rejects zero vectors as directionless, using a small relative tolerance for ratios.

**KNN:** correctness checks use explicit small datasets, including the previously failing two-row case, rounded-feature weights, equal distances, voting ties, partial-capacity loading, and malformed inputs. They are not an accuracy study. The implementation retains raw weighted squared distances, integer-rounded bins, custom array storage, and insertion-based ordering; it does not add scaling or missing-feature inference. Recreate the classifier after reloading/mutating its training data, because weights are calculated at construction. Very large distances are rejected on nonfinite arithmetic. This implementation is intended for small educational datasets.

**Calculator:** numeric binary operators `+ - * / ^` are supported; unary minus, implicit multiplication, symbolic algebra, and general scientific-notation grammar are not. Exponentiation is now right-associative. The legacy postfix utility accepts compact single-digit examples or whitespace-separated numeric tokens. It returns `null` for invalid expressions; calculator methods throw `IllegalArgumentException`.

**Recursive encoding and sorting:** text transformation does not escape literal/nested parentheses and is not secure encryption. Recursion is not bounded for arbitrarily large text. Merge Sort still compares numbers via `double`, can change wrapper types, and can lose large-integer precision; the original numeric approach was preserved.

**Statistics:** empty/zero-variance Pearson input returns `0.0` by the existing convention, although correlation is mathematically undefined there. Finite values and equal lengths are required; extreme floating-point magnitudes can still lose precision. Regression retains its fixed learning rate and 100,000 iterations, rejects invalid pairs and detects nonfinite divergence, but does not guarantee convergence for arbitrary data.

**Audio:** WAV frame integrity is checked for synthetic mono/stereo data, empty results, speed changes, silence removal, and writing. The original plus-sign filter formula and direct frame sampling are retained; no filtering-quality or resampling-quality claim is made. PCM serialization rejects nonfinite/out-of-range amplitudes instead of silently wrapping them; a negative full-scale quantization margin is allowed. Adjust volume before writing if amplification exceeds range. Arbitrary WAV formats and very large files are not exhaustively tested.

**Images:** known grayscale pixel blocks check flips, rotations, transpose, negative and brightness; frame/resize dimensions and a 1 × 1 result are checked. JPEG output remains lossy and opaque. Size validation does not guarantee that every positive allocation fits available memory.

**Records and simulation:** farming calculations use generated values in the original main; deterministic fixtures validate selected queries without changing that generation approach. Sales remain sample records without a full business-rule model. Airport processing is discrete and priority-based, bounded to 50 queued aircraft; it is not a real-time runway/scheduling model. All queued planes are eligible for priority processing, including future planned times. Waiting minutes still use an `int` result, checked for overflow.

## Build and distribution

JDK 21 and the existing bundled chart JAR are required. No new application/test dependency was added. Maven's `systemPath` warning remains because the project describes its already bundled JAR; it is not designed for transitive consumption as another Maven project's dependency. Resources remain filesystem inputs, the packaged JAR has no main manifest, and native Windows execution has not been checked. Run the executable verification with `scripts/verify.sh`; Maven's default test discovery does not run these plain main classes.

Original PDFs and historic output folders remain untouched. Historical screenshots are illustrations; current results come from executable checks and recorded example runs.

## Separate proposals

A general vector-dependence solver, a unified/null-safe generic collection API, immutable datasets/iterators, normalized KNN evaluation on a held-out dataset, improved audio resampling/filter design, and general expression grammar would change the original approach more substantially. They remain separate proposals, not claimed features.
