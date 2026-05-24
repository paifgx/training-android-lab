# Aufgabe 03: Retrofit-Service & Netzwerklayer

**Tag 2** | ⏱ ca. 50 Minuten | 🎯 Selbstständig

---

## Lernziel

Du baust den Retrofit-basierten Netzwerklayer: API-Interface, DTOs mit Moshi, DTO→Domain-Mapping und typsichere Fehlerbehandlung.

## Kontext

Die MVVM-Architektur aus Aufgabe 02 nutzt noch die FakeBookRepository. Jetzt baust du den echten Netzwerkzugriff auf die Google Books API.

**Was existiert:** Repository-Interface, ViewModel, UI, Domain-Modelle.

**Was fehlt:** API-Service, DTOs, Mapper, Retrofit-Konfiguration.

## Aufgabe

### A) DTOs (Data Transfer Objects)

Erstelle Kotlin-Datenklassen, die den JSON-Response der Google Books API abbilden:
- `BooksResponse(items: List<VolumeItem>?)`
- `VolumeItem(id, volumeInfo)`
- `VolumeInfo(title, authors, description, publishedDate, imageLinks)`
- `ImageLinks(smallThumbnail, thumbnail)`

Nutze Moshi-Annotationen: `@JsonClass(generateAdapter = true)`, `@Json(name = "...")`.

### B) Retrofit-Service-Interface

Erstelle ein `interface GoogleBooksApiService` mit:
```
@GET("books/v1/volumes")
suspend fun searchBooks(@Query("q") query: String, ...): BooksResponse
```

### C) DTO → Domain Mapper

Schreibe Extension Functions:
- `BooksResponse.toDomainModels(): List<Book>`
- Behandle `null` auf jeder Ebene (items, volumeInfo, title, authors, imageLinks)
- Konvertiere `http://` Bild-URLs zu `https://`

### D) Retrofit konfigurieren

Erstelle einen `object RetrofitFactory` (oder eine Factory-Klasse), der:
- Moshi mit `KotlinJsonAdapterFactory` konfiguriert
- OkHttpClient mit Logging-Interceptor und Timeouts (15s)
- Retrofit mit `https://www.googleapis.com/` als BaseURL
- Den API-Service erzeugt

### E) Fehler-Mapping

Schreibe eine Extension `Exception.toNetworkError(): NetworkError`, die Retrofit-Exceptions auf die sealed NetworkError-Hierarchie mappt.

## Hinweise & Tipps

- **DTOs ≠ Domain-Modelle:** Die JSON-Struktur der API und deine App-Struktur werden sich mit der Zeit auseinanderentwickeln. Halte sie getrennt.
- **`suspend fun`** in Retrofit: Der Netzwerk-Call ist asynchron. Keine Callback-Hölle wie in Java. Der Aufrufer entscheidet über den Dispatcher.
- **Moshi vs Gson:** Moshi ist Kotlin-first, kleiner, schneller. Gson funktioniert, aber Moshi unterstützt Nullability und Default-Werte nativ.
- **`@JsonClass(generateAdapter = true)`**: Moshi generiert den Adapter zur Compile-Zeit per KSP — kein Reflection zur Laufzeit. Bessere Performance.
- **Null-Sicherheit:** Jedes Feld der Google Books API kann `null` sein. Der Mapper ist der Ort, wo du das einmalig behandelst — danach sind deine Domain-Objekte sauber.
- **`http://` → `https://`:** Android blockiert Cleartext-Traffic standardmäßig. Google Books liefert Thumbnails oft als `http://`. Konvertiere!

## Wie weiter?

→ Branch `task/03-retrofit-service` zeigt eine mögliche Musterlösung.
→ Nächste Aufgabe: **Aufgabe 04 — Room & Persistenz**

## Zeitaufwand

ca. 50 Minuten
