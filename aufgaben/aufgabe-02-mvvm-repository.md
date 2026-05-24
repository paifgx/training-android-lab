# Aufgabe 02: MVVM-Architektur, ViewModel & Repository

**Tag 1** | ⏱ ca. 60 Minuten | 🎯 Selbstständig + Live-Coding-Support

---

## Lernziel

Du baust die MVVM-Architektur auf: ViewModel mit StateFlow, Repository-Interface, FakeDataSource und eine funktionierende UI mit RecyclerView.

## Kontext

Du hast die Kotlin-Grundlagen aus Aufgabe 01 (Book, UiState, NetworkError, Extensions). Das Projekt hat noch keine Architektur — nur eine leere MainActivity.

**Was existiert:** Domain-Modelle, Extension Functions.

**Was fehlt:** ViewModel, Repository, Adapter, Layouts, UI-Logik.

## Aufgabe

### A) Repository-Interface

Erstelle ein `interface BookRepository` mit:
- `fun getBooks(query: String): Flow<List<Book>>`
- `fun getBook(id: String): Flow<Book?>`
- `suspend fun refreshBooks(query: String)`

### B) FakeBookRepository

Implementiere das Interface mit einer `MutableStateFlow<List<Book>>`, die 3–4 Beispielbücher enthält. Die `getBooks`-Methode filtert nach Titel/Autor.

### C) BookListViewModel

Erstelle ein `ViewModel` mit:
- `uiState: StateFlow<UiState<List<Book>>>`
- `searchBooks(query: String)` — lädt Daten über das Repository
- `retry()` — wiederholt die letzte Suche
- Nutze `viewModelScope.launch { }` für Coroutines

### D) RecyclerView-Adapter

Erstelle einen `BookAdapter : ListAdapter<Book, BookViewHolder>` mit DiffUtil.
Jeder Eintrag zeigt: Titel, Autoren, Jahr, Cover-Image (Platzhalter reicht).

### E) Layouts & MainActivity

- `activity_main.xml`: SearchBar + RecyclerView + ProgressBar + ErrorView + EmptyView
- `item_book.xml`: Card mit Cover-Image, Titel, Autoren, Jahr
- `MainActivity`: Verkabelt ViewModel → UI (manuell, kein Hilt)

## Hinweise & Tipps

- **Warum ein Interface für das Repository?** Im Moment hast du nur FakeBookRepository. Später kommt ein echtes (Retrofit + Room). Die ViewModel-Logik ändert sich nicht — nur die Implementierung.
- **StateFlow vs LiveData:** StateFlow ist das moderne Äquivalent. Es ist Teil von Kotlin Coroutines, nicht von Android. Es funktioniert auch in reinen Kotlin-Projekten.
- **`collectLatest { }`** = sammelt Werte aus einem Flow. Wie RxJava's `subscribe()`, aber simpler.
- **`_uiState` (Mutable) vs `uiState` (Immutable):** Das ist das "Backing Property"-Pattern. Extern darf niemand den State setzen.
- **ListAdapter + DiffUtil:** Effiziente RecyclerView-Updates. Du musst nicht `notifyDataSetChanged()` rufen — DiffUtil berechnet die Differenz.
- **ViewBinding:** `ActivityMainBinding.inflate(layoutInflater)` ersetzt `findViewById()`. Type-safe, null-safe.

## Wie weiter?

→ Branch `task/02-mvvm-repository` zeigt eine mögliche Musterlösung.
→ Nächste Aufgabe: **Aufgabe 03 — Retrofit & Netzwerk**

## Zeitaufwand

ca. 60 Minuten
