# Aufgabe 07: Unit-Tests

**Tag 4** | ⏱ ca. 60 Minuten | 🎯 Selbstständig

---

## Lernziel

Du schreibst Unit-Tests für die zentralen Komponenten — ohne Android-Gerät, ohne Emulator, ohne Mocking-Framework. Nur Kotlin + Coroutines-Test.

## Kontext

Die App ist komplett integriert (Aufgabe 06, Hilt). Jetzt sicherst du die Geschäftslogik mit Tests ab.

**Was existiert:** Vollständige App mit Hilt, Repository, ViewModel, Mapper.

**Was fehlt:** Test-Code in `app/src/test/java/`.

## Aufgabe

### A) FakeTestRepository

Erstelle ein `FakeTestRepository : BookRepository` für Tests:
- `setBooks(books: List<Book>)` — steuert, welche Daten geliefert werden
- `setErrorMode(message: String)` — simuliert Fehler
- Interne `MutableStateFlow`, die `getBooks()` und `getBook()` bedient

### B) BookListViewModelTest

Teste das ViewModel in Isolation:
- Initialer Zustand: Success mit (ggf. leeren) Daten
- `searchBooks("Kotlin")` filtert korrekt
- Leere Query gibt alle Bücher zurück
- Repository-Fehler führt zu `UiState.Error`
- `retry()` wiederholt die letzte Suche

**Wichtig:** `Dispatchers.setMain(testDispatcher)` im `@Before` und `Dispatchers.resetMain()` im `@After`.

### C) EntityMapperTest

Teste die Entity ↔ Domain-Konvertierung:
- Round-Trip: Book → Entity → Book (verlustfrei?)
- Null-Thumbnail wird korrekt übertragen
- Listen-Mapping funktioniert
- `lastSearchQuery` und `cachedAt` werden gesetzt

### D) DtoMapperTest

Teste die JSON-DTO → Domain-Konvertierung:
- Vollständiges DTO → korrektes Book
- `items = null` → leere Liste
- `volumeInfo = null` → herausgefiltert
- `authors = null` → leere Liste
- `http://` → `https://` Konvertierung

### E) ExtensionsTest

Teste die Extension Functions auf Edge Cases:
- Null/Blank-Strings
- Truncate mit exakter Länge
- Autor-Formatierung: 0, 1, 3, 4+ Autoren

## Hinweise & Tipps

- **Kein Mockito nötig:** In Java testet man oft mit `when(mock.foo()).thenReturn(bar)`. In Kotlin reicht eine einfache Fake-Klasse. Lesbarer, weniger Magie, refactor-safe.
- **`Dispatchers.setMain()`:** ViewModels nutzen intern `Dispatchers.Main`. Der existiert in Unit-Tests nicht. Du musst ihn durch einen Test-Dispatcher ersetzen. Vergessen = `IllegalStateException`.
- **`testDispatcher.scheduler.advanceUntilIdle()`**: Führt alle ausstehenden Coroutines aus. Nach diesem Aufruf sind alle asynchronen Operationen abgeschlossen.
- **`viewModel.uiState.value`**: Liest den aktuellen StateFlow-Wert synchron. Kein `await()` nötig, wenn du vorher `advanceUntilIdle()` aufgerufen hast.
- **Teststruktur:** Arrange → Act → Assert. Jeder Test testet EIN Verhalten. Lieber 5 kleine Tests als 1 riesiger.
- **Turbine (optional):** Die Library `app.cash.turbine` macht Flow-Testing eleganter. Ihr könnt sie nutzen, müsst aber nicht.

## Wie weiter?

→ Branch `task/07-testing` zeigt eine mögliche Musterlösung.
→ Branch `solution/final` enthält den kompletten Endzustand.

## Zeitaufwand

ca. 60 Minuten
