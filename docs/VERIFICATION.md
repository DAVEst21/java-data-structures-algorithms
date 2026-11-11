# Verification record

This record separates historical academic reports, source inspection, compilation, current example execution, and checked behavior. No coverage percentage, benchmark, model accuracy, or guarantee for every input is claimed.

## Current result

- All **91 production Java source files** compile with Temurin JDK 21.0.13, UTF-8, and the existing local chart JAR.
- The original checking class passes **14/14 groups**.
- The additional checking classes pass **384 assertions** over completed collection methods, ordered mutation, KNN, I/O, calculator, records, statistics, media, and simulation.
- Maven 3.9.9 packaging with `-DskipTests` also passes. Maven uses its normal build plugins; no new application/test dependency was added.
- `bash scripts/verify.sh` compiles and runs both checking entry points. No test framework or Java assertion flag is required.

The source was inspected in detail around every changed method and its callers. The original sixteen PDFs were already reviewed and remain untouched; the [report mapping](ACADEMIC_NOTES.md) distinguishes their historical claims from the current source.

## Checked behaviors

The original executable class still reports **14/14 groups passing**:

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

## Expanded checks

| Area | Explicit checks |
| --- | --- |
| Linked stack and list | Fifteen-node reverse display, full LIFO removal, singleton/empty reuse, conversion and occurrence results beyond ten nodes, negative indices, contiguous overlapping sublists, self-append, aliases, zero-column matrix conversion |
| Array interface methods | Head insertion, full rejection, aliases, reverse output, iterators, occurrence lists, filtered copies, matrix conversion/import by rows and columns, capacity preflight, membership, retention, old/new replacement, bounded/default fill, numeric storage, null comparison, empty statistics and negative maxima |
| Ordered arrays | Both directions, inversion of empty/nonempty arrays, insertion/search, duplicate rejection, replacement without data loss, inherited setters, head insertion, full bulk insertion, numeric/character fills, both batch overloads, atomic duplicate rejection and substitution; textual comparison remains case-insensitive |
| KNN | Previously failing rows `2,A` / `10,B`, query `0`, k=1 now yield `A`; partial reserved capacity; equal-distance rows and majority votes; nearest tied class; invalid k/shapes/nonfinite/empty/malformed files; failed-load preservation; fractional features rounded consistently; uniform zero-information fallback |
| Text I/O | UTF-8 two-line round-trip, empty-file write/read, missing-file result, three consecutive terminal reads, EOF and invalid number rejection |
| Calculator | Completed infix conversion, spaced numeric and legacy compact postfix, original report result, precedence, `2^3^2 == 512`, malformed syntax/parentheses/operators, division by zero, stack capacity and repeated use |
| Race records | Two runners, three events and two years; `90 + 10 == 100` on unequal axis dimensions; missing runner and invalid distance |
| Farming | Explicit monthly values rather than random output as oracle; mean `6.5`, month `12`, second-period sum `26`, final-year December `60`, best year `5`, season, worst farmer and empty-manager behavior |
| Sales | Invalid/null items and nonpositive quantities rejected, fixed-capacity parallel arrays stay aligned, known total `14`, and cashier name mapping |
| Housing/regression | Equal finite pairs, negative correlation `-1`, grouped mean `20`, minimum/maximum `10/30`, mismatched length/nonfinite data rejection; original known regression remains checked |
| WAV | Synthetic 16-bit mono/stereo fixtures, known energy, speed output lengths, header frame counts after transformations, silence removal to zero frames, empty filter/write, channel-preserving reversal, invalid factors/thresholds/divisor |
| Images | Known grayscale pixel blocks after horizontal/vertical flips, 90/180/270-degree rotations, transpose, negative; JPEG tolerance ten intensity units; one-pixel resize, frame size, extreme positive brightness, invalid factor and negative frame |
| Airport | Epoch timestamps beyond int range, waiting minutes, refreshed priorities after 20 simulated minutes with a newly arriving plane, one-runway processing, empty queue and negative-time rejection |
| Remaining guards | Inherited numeric mutations, vector dimensions/zero coordinates, parallel-list lengths, retained null values, and null key/value rejection |

The additional assertion count is printed by the test code. Tests use known expected results and temporary files; those files are removed. Shuffling is checked for element preservation, not a particular random permutation. No production structure was replaced by a library collection, and no production API was added solely to expose test internals.

## Current example runs

The following original mains were executed after the corrections. Successful process termination alone is not treated as verification of every printed claim.

| Example | Current observation |
| --- | --- |
| `PrincipalPilaDin` | Input `5,10,15`; removes `15.0`, displays `10.0->5.0->null`, then clears. |
| `PrincipalColaDin` | Input `uno,dos,uno`; first removal preserves `dos->uno`. |
| `PrincipalCarreras` | Original aggregation now reports `Recorridos 90.0`. |
| `PrincipalGranja` | All menu options A–G followed by X executed; random quantities are not fixed-result evidence. Deterministic fixtures supply the assertions. |
| `PrincipalVentas`, `PrincipalVentas2` | Retain example totals `116.0` and `124.0`; cashier name no longer displays the RFC. |
| `PrincipalCalculadora` | Expression `(a-2.0)*f/2^(c-8)` with values `1,3,12` yields `-0.1875` through consecutive terminal prompts. |
| `PrincipalKnnFrutas`, `PrincipalKnnVehiculos` | Both load their supplied files and complete prompted classification. Correctness assertions use synthetic known-result data, not real-file predictions as an accuracy claim. |
| `PrincipalAudio` | Reads the supplied stereo WAV, reports energy and writes a reversed WAV under `target/`; synthetic fixtures verify output framing separately. |
| `PrincipalSimulacionAeropuerto` | Uses full epoch timestamps and updates waiting durations/priorities after advancing time. |
| `PrincipalAOrdenadoMetodos` | Runs both directions and the corrected old/new-value overload; random shuffle has no fixed-output assertion. |
| `PrincipalAnalisisCasas` | Completes with supplied files and corrected Pearson normalization explanation. |

The selected Merge Sort, recursive converters/validator, recursive text, Pearson, chart, and grayscale examples remain reproducible through the scripts. Their outputs in the README are distinguished from historical captures.

## Changes and historical evidence

The first preservation pass fixed two lines: empty ordinary-array reversal and linked-queue duplicate removal. That initial suite moved from 12/14 to 14/14 passing groups. The follow-up request explicitly authorized completing the pending modules and repairing the identified defects; those changes are separate commits.

The implementation still uses custom arrays, links, stacks, queues, matrix loops, recursive algorithms and gradient descent. Direction-aware search retains the original linear insertion approach. KNN retains its custom ordered storage. Reverse linked-stack display retains its temporary custom fixed stack. Audio retains direct sample/frame manipulation and its original filter formula.

Every remaining limitation listed in [LIMITATIONS.md](LIMITATIONS.md) refers to the current code. Original report screenshots and self-evaluations do not stand in for current checks. General dependence solving, arbitrary numeric stability, signal-processing quality, KNN model accuracy and native Windows execution remain outside the verified claims.

The aircraft time fields, constructor parameters, time updater and planned-time getter now use `long`. Existing int call arguments still widen normally; source consumers assigning that getter to an int must update their variable type. The original airport main was updated accordingly.
