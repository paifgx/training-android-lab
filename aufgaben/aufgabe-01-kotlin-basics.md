# Aufgabe 01: Kotlin-Grundlagen für Java-Entwickler

**Tag 1** | ⏱ ca. 45 Minuten | 🎯 Selbstständig + Pair-Programming

---

## Lernziel

Du verstehst die zentralen Kotlin-Sprachkonstrukte, die du für die restlichen Aufgaben brauchst — und erkennst, wie sie deine Java-Erfahrung übersetzen.

## Kontext

Das Projekt `main` enthält ein leeres, lauffähiges Android-Projekt. Deine Aufgabe ist es, die ersten Kotlin-Dateien anzulegen, die das Fundament für alle folgenden Aufgaben bilden.

**Was existiert:** `MainActivity.kt` (Platzhalter), `build.gradle.kts` mit allen Dependencies.

**Was fehlt:** Domain-Modelle, Error-Types, Utility-Funktionen.

## Aufgabe

### A) Book — Data Class statt POJO

Erstelle ein `data class Book` mit folgenden Feldern:
- `id: String`
- `title: String`
- `authors: List<String>`
- `description: String`
- `thumbnailUrl: String?`
- `publishedDate: String?`

### B) UiState — Sealed Interface statt Enum + State-Holder

Erstelle ein `sealed interface UiState<out T>` mit drei Ausprägungen:
- `Loading` (keine Daten)
- `Success<T>` (enthält `data: T`)
- `Error` (enthält `message: String`)

### C) NetworkError — Typsichere Fehlerhierarchie

Erstelle ein `sealed interface NetworkError` mit:
- `HttpError(code, message)`
- `NetworkUnavailable(cause)`
- `ParsingError(cause)`
- `Unknown`

### D) Extension Functions

Erstelle Extension Functions:
- `String?.isNotNullOrBlank(): Boolean`
- `String.truncate(maxLength: Int): String`
- `List<String>.formatAuthors(): String` (leer → "Unknown Author", 1 → nur Name, >3 → "A, B, C et al.")

## Hinweise & Tipps

- **Data Class:** Ersetzt POJO + getter/setter + equals/hashCode/toString + copy(). In Java wärst du bei 50+ Zeilen Boilerplate. In Kotlin: eine Zeile.
- **Sealed Interface:** Wie ein Enum, aber jedes Element kann unterschiedliche Felder haben. Der Compiler erzwingt exhaustive `when`-Ausdrücke — du kannst keinen Fall vergessen.
- **`String?`** heißt "das kann null sein". Kotlin erzwingt Null-Checks zur Compile-Zeit. Keine NullPointerException mehr zur Laufzeit (wenn du zuhörst).
- **`object` vs `class`:** `data object Loading` = Singleton, keine Instanz nötig.
- **Extension Functions:** Hängen Methoden an bestehende Klassen an — ohne Vererbung. `s.isBlank()` statt `StringUtils.isBlank(s)`.
- **`out T`** in `UiState<out T>` = Kovarianz. `UiState<Nothing>` ist dann ein Subtyp von `UiState<List<Book>>`. Musst du nicht tief verstehen, aber es ist der Grund, warum `Loading` ohne Typ-Parameter funktioniert.

## Wie weiter?

→ Branch `task/01-kotlin-basics` zeigt eine mögliche Musterlösung.
→ Nächste Aufgabe: **Aufgabe 02 — MVVM & Repository**

## Zeitaufwand

ca. 45 Minuten
