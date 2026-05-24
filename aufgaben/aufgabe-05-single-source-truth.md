# Aufgabe 05: Single Source of Truth — Integration

**Tag 2** | ⏱ ca. 60 Minuten | 🎯 Selbstständig + Live-Demo

---

## Lernziel

Du integrierst Retrofit und Room zu einem echten Repository: API → Room → UI. Die UI liest nur aus der Datenbank. Die Datenbank ist die "Single Source of Truth".

## Kontext

Du hast jetzt einen Netzwerklayer (Aufgabe 03) und eine Datenbank (Aufgabe 04). Beide existieren isoliert. Jetzt verbindest du sie.

**Was existiert:** Retrofit-Service, Room-DAO, Domain-Modelle, ViewModel, UI-Layouts.

**Was fehlt:** `DefaultBookRepository`, integrierte MainActivity.

## Aufgabe

### A) DefaultBookRepository

Implementiere `BookRepository`:
- `getBooks()`: delegiert an `bookDao.getBooksByQuery()` und mappt Entities → Domain
- `getBook()`: delegiert an `bookDao.getBookById()`
- `refreshBooks()`: ruft `apiService.searchBooks()` auf, mappt DTOs → Domain → Entities, speichert in Room

### B) MainActivity verkabeln

Verdrahte alle Schichten in der MainActivity (manuell, kein Hilt):
1. Retrofit API-Service erstellen
2. Room Database → DAO holen
3. DefaultBookRepository(apiService, bookDao)
4. BookListViewModel(repository)
5. RecyclerView + LayoutManager + Adapter
6. Such-Listener, UI-State-Beobachtung

### C) Fehlerbehandlung

Wenn der Netzwerk-Call in `refreshBooks()` fehlschlägt:
- Room hat noch die gecachten Daten → UI zeigt diese
- Zeige zusätzlich einen Fehler-Hinweis an
- Der "Retry"-Button soll es nochmal versuchen

## Hinweise & Tipps

- **Offline-First:** Das ist das zentrale Muster. Die UI fragt niemals direkt die API. Sie fragt Room. Room wird asynchron von der API befüllt. Fällt das Netzwerk aus → gecachte Daten.
- **Reihenfolge im Repository:** Erst `refreshBooks()` (API → Room schreiben), dann liest der Flow aus Room. Da `getBooks()` einen `Flow` zurückgibt, bekommt die UI automatisch die neuen Daten sobald Room sie schreibt.
- **Exception-Handling:** `refreshBooks()` wirft eine Exception bei Netzwerkfehlern. Das ViewModel fängt sie und setzt `UiState.Error`. Aber: die Flow-Subscription aus `getBooks()` läuft weiter — die UI zeigt gecachte Daten.
- **Warum manuelle Verkabelung?** In Aufgabe 06 ersetzt Hilt genau diese manuelle Verkabelung. Wenn du verstanden hast, was hier passiert, verstehst du auch, was Hilt für dich tut.
- **`catch (e: Exception)` im ViewModel:** In der Praxis würdest du die Fehler genauer typisieren (NetworkError). Für den Moment reicht es, die Message durchzureichen.

## Wie weiter?

→ Branch `task/05-single-source-truth` zeigt eine mögliche Musterlösung.
→ Nächste Aufgabe: **Aufgabe 06 — Hilt Dependency Injection**

## Zeitaufwand

ca. 60 Minuten
