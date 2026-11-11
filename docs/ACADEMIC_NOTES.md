# Academic report mapping

The original academic reports were reviewed locally without modifying or reorganizing their source folder. This repository includes only two extracted historical captures, not the complete reports or their cover pages. Filenames below identify the original evidence; they are not links or runtime dependencies.

The reports date from 2025. Their screenshots and self-evaluation tables describe a historical state and were not treated as proof that the current source works. Narrative text, selected screenshots, and current source were compared. The most detailed review covered arrays, linked structures, priority queues, calculator, recursion, housing statistics, and KNN. Other reports were inspected for purpose, structure, representative screenshots, and correspondence to the retained modules.

| Original report | Purpose and corresponding current source | Current review outcome |
| --- | --- | --- |
| `DIBM_reporte_practica1.pdf` | `Arreglo`: reverse, count, search occurrences, remove, clone, sublist, resize, replace, fill | Figures on physical PDF pages 4–5, 8, and 11 were inspected. Selected basic behaviors checked independently; the empty reverse defect was reproduced and corrected. Equality and oversized fill remain limited. |
| `DIBM_reporte_practica2.pdf` | Farming production: `Campesino`, `GestorGranja`, `PrincipalGranja` | Representative production-menu captures inspected. Random/generated production and all menu queries were not verified. |
| `DIBM_reporte_practica3.pdf` | `ArregloOrdenado` and term-index example | Ascending insertion, duplicate rejection, search, and removal checked. Historical descending-fill examples do not validate descending insertion/search. |
| `DIBM_reporte_practica4.pdf` | `ArregloNumerico`: scalar arithmetic, vector products/norms, dependence, orthogonality | Method explanations and representative numeric captures inspected. Main executed; advanced mathematical correctness not established. The dependence helper checks supplied coefficients, not all possible combinations. |
| `DIBM_reporte_practica5.pdf` | `AudioFileRecord`, numeric sample buffers and WAV processing | Waveform captures inspected. Current `PrincipalAudio` is commented out; historical audio output is not a current execution result. |
| `DIBM_reporte_practica6.pdf` | `Arreglo2D` and `Arreglo3D`: access, transpose, resizing, row/column operations | Method descriptions and result-page context inspected. Bounds, a non-square transpose, and 3D cell access checked. |
| `DIBM_reporte_practica7.pdf` | `Arreglo2DNumerico`: scalar/matrix operations, powers, triangular forms, folding | Representative matrix-result captures inspected. Known 2 × 2 product and incompatible dimensions checked; full main executed. |
| `DIBM_reporte_practica8.pdf` | `ProcesadorImagen`: custom pixel matrix, colors, transforms, resizing | Pixel and grayscale design compared with current code; representative menu capture inspected. Grayscale menu path executed; tiny-image component/grayscale/rotation checks added. |
| `DIBM_reporte_practica9.pdf` | `Calculadora.convertirPostfija`, `evaluarExpresion`; stack-based `Encriptador` | Calculator figure on physical PDF page 8 yields `-0.1875`; core methods reproduced this result. Variable-entry UI and iterative encrypter were not verified. |
| `DIBM_reporte_practica10.pdf` | `ColaPrioridad`, `SimulacionAeropuerto`, `Avion` | Priority and airport captures on physical PDF pages 6–7 inspected. Queue orders/ties/capacity checked independently. The report's general heap complexity table does not describe this array-scan implementation. |
| `DIBM_reporte_practica11.pdf` | `ListaDin`, `PilaDin`, `ColaDin` | Descriptions and captures on physical PDF pages 10 and 16 inspected. The report describes last-node removal and reverse printing for the dynamic stack; corresponding current list methods are stubs. FIFO duplicate removal was reproduced as a defect and minimally corrected in `ColaDin`. |
| `DIBM_reporte_practica12.pdf` | `DatosCasas`, `CalculadorPearson`, `AnalizadorPrecios`, `GraficadorDispersion` | Narrative design and physical PDF page 15's Pearson capture compared. Current coefficient matches `0.7964096556804487`; a new plot was generated. Sample/population explanatory wording has a mathematical limitation. |
| `DIBM_reporte_practica13.pdf` | `ListaDinClave` and key/value nodes | Design and physical PDF page 20's results inspected. Basic insertion, duplicate-key update, lookup, removal, and clearing checked; full demonstration executed. |
| `DIBM_reporte_practica14.pdf` | Recursive text transform, binary validation, binary/hexadecimal converters | Physical PDF pages 9, 11, 13, and 15 contain the selected original cases. Current checks include those conversion/validation inputs, text round-trip, invalid segments, negative values, zero, and `Long.MAX_VALUE`. |
| `DIBM_reporte_tf.pdf` | `Dato`, `ConjuntoDatos`, `KNN`, distance/class pairs | Class design and physical PDF page 17's prediction captures inspected. Current code falls back to string comparison of pairs. A separate two-row diagnostic reproduced an incorrect nearest-neighbor prediction; historical outputs do not establish correctness. |
| `DIBM_reporte_tp1.pdf` | UML scenarios: gallery, vehicle manufacture, carpentry | Diagram context inspected. These are design exercises, not evidence of three implemented applications in this repository. |

Old package references such as `com.mycompany.ed_4b_25.principales` and `edlineal.auxiliares.colapriori` differ from the current packages. Build instructions use the package declarations in the current source. Merge Sort is present in the source, but no specific report was identified for it; verification uses its existing main and independently specified numeric cases.

## Selected image provenance

| Repository image | Source | Treatment and meaning |
| --- | --- | --- |
| [Priority queue](images/priority-queue-historical.png) | `DIBM_reporte_practica10.pdf`, physical PDF page 6, figure 1 | Original embedded PNG extracted without alteration; historical console output. |
| [Binary conversion](images/binary-conversion-historical.png) | `DIBM_reporte_practica14.pdf`, physical PDF page 15, figure 15 | Original embedded PNG extracted without alteration; historical code and console output. |
| [Housing scatter plot](images/housing-scatter.png) | Current `PrincipalGCasas`, supplied housing text files | Newly generated 800 × 600 PNG, copied from `target/grafico_casas.png`. |

The two extracted captures were visually inspected at their original resolution: they contain code, educational tasks, and numeric output, with no visible author names, email addresses, student identifiers, credentials, or personal filesystem paths. Cover pages, grades, instructor details, and unrelated screenshots were not copied. Spanish program text in the images is preserved.

The existing input JPEG and WAV remain in their original project paths. Generated historical image/audio files remain locally preserved but are excluded from Git; the examples use tracked input resources. No provenance or licensing claim beyond the available files is inferred for these resources.
