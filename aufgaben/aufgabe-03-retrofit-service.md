# Aufgabe 03: Retrofit-Service & Netzwerklayer

**Tag 2** | ⏱ ca. 50 Minuten | 🎯 Selbstständig

---

## Lernziel

Du baust den Retrofit-basierten Netzwerklayer: API-Interface, DTOs mit Moshi, DTO→Domain-Mapping und typsichere Fehlerbehandlung.

## Kontext

Die MVVM-Architektur aus Aufgabe 02 nutzt noch die FakeBookRepository. Jetzt baust du den echten Netzwerkzugriff auf die Open Library API.

**Was existiert:** Repository-Interface, ViewModel, UI, Domain-Modelle.

**Was fehlt:** API-Service, DTOs, Mapper, Retrofit-Konfiguration.

## Aufgabe

### A) DTOs (Data Transfer Objects)

Erstelle Kotlin-Datenklassen, die den JSON-Response der Open Library Search API abbilden:

- `OpenLibrarySearchResponse(docs: List<OpenLibraryBookDto>?)`
- `OpenLibraryBookDto(key, title, author_name, first_publish_year, cover_i, edition_count, first_sentence)`

Nutze Moshi-Annotationen: `@JsonClass(generateAdapter = true)`, `@Json(name = "...")`.

### B) Retrofit-Service-Interface

Erstelle ein `interface OpenLibraryApiService` mit:

```kotlin
@GET("search.json")
suspend fun searchBooks(
    @Query("q") query: String,
    @Query("limit") limit: Int = 20,
    @Query("fields") fields: String = "key,title,author_name,first_publish_year,cover_i,edition_count,first_sentence"
): OpenLibrarySearchResponse
```

### C) DTO → Domain Mapper

Schreibe Extension Functions:

- `OpenLibrarySearchResponse.toDomainModels(): List<Book>`
- Behandle `null` auf jeder Ebene (`docs`, `key`, `title`, `author_name`, `cover_i`)
- Entferne bei IDs das Präfix `/works/`
- Baue Cover-URLs aus `cover_i`: `https://covers.openlibrary.org/b/id/{cover_i}-M.jpg`

### D) Retrofit konfigurieren

Erstelle einen `object RetrofitFactory` (oder eine Factory-Klasse), der:

- Moshi mit `KotlinJsonAdapterFactory` konfiguriert
- OkHttpClient mit Logging-Interceptor und Timeouts (15s)
- Retrofit mit `https://openlibrary.org/` als BaseURL
- Den API-Service erzeugt

### E) Fehler-Mapping

Schreibe eine Extension `Exception.toNetworkError(): NetworkError`, die Retrofit-Exceptions auf die sealed NetworkError-Hierarchie mappt.

## Hinweise & Tipps

- **Open Library:** Der Search-Endpoint braucht keinen API-Key und ist damit für Trainingsgruppen robuster.
- **DTOs ≠ Domain-Modelle:** Die JSON-Struktur der API und deine App-Struktur werden sich mit der Zeit auseinanderentwickeln. Halte sie getrennt.
- **`suspend fun`** in Retrofit: Der Netzwerk-Call ist asynchron. Keine Callback-Hölle wie in Java. Der Aufrufer entscheidet über den Dispatcher.
- **Moshi vs Gson:** Moshi ist Kotlin-first, kleiner, schneller. Gson funktioniert, aber Moshi unterstützt Nullability und Default-Werte nativ.
- **`@JsonClass(generateAdapter = true)`**: Moshi generiert den Adapter zur Compile-Zeit per KSP — kein Reflection zur Laufzeit. Bessere Performance.
- **Null-Sicherheit:** Jedes Feld der Open Library API kann `null` sein. Der Mapper ist der Ort, wo du das einmalig behandelst — danach sind deine Domain-Objekte sauber.
- **Cover-Bilder:** Open Library liefert im Suchresultat meist nur `cover_i`. Daraus baut ihr die eigentliche Bild-URL selbst.

## Wie weiter?

→ Branch `task/03-retrofit-service` zeigt eine mögliche Musterlösung.
→ Nächste Aufgabe: **Aufgabe 04 — Room & Persistenz**

## Zeitaufwand

ca. 50 Minuten
