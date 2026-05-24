# Branch- und Tag-Leitfaden

## Für Teilnehmer

Dieses Repository nutzt Branches, um Aufgaben und Musterlösungen zu verwalten.

### Überblick

| Branch | Inhalt | Tag |
|---|---|---|
| `main` | **Startpunkt** — leeres Projekt, lauffähig | — |
| `task/01-kotlin-basics` | Kotlin-Grundlagen: data class, sealed, extensions | `task-01-done` |
| `task/02-mvvm-repository` | MVVM: ViewModel, StateFlow, Repository, FakeData | `task-02-done` |
| `task/03-retrofit-service` | Netzwerk: Retrofit, DTOs, Moshi, Error-Mapping | `task-03-done` |
| `task/04-room-persistenz` | Datenbank: Room Entity, DAO, TypeConverter | `task-04-done` |
| `task/05-single-source-truth` | Integration: API → Room → UI offline-first | `task-05-done` |
| `task/06-hilt-di` | Dependency Injection: Hilt Module, Scopes | `task-06-done` |
| `task/07-testing` | Unit-Tests: FakeRepo, ViewModel, Mapper | `task-07-done` |
| `task/08-final-integration` | Abschlussreview: Integration, Review, Transfer | `task-08-done` |
| `solution/final` | **Komplettlösung** — stabiler Alias des finalen Stands | `final-solution` |

### Workflow

```bash
# 1. Startpunkt auschecken
git checkout main

# 2. Eigenen Branch für die Arbeit erstellen
git checkout -b mein/01-kotlin-basics

# ... entwickeln, committen ...

# 3. Musterlösung ansehen (ohne den eigenen Branch zu verlieren)
git checkout task/01-kotlin-basics

# 4. Zurück zum eigenen Branch
git checkout mein/01-kotlin-basics

# 5. Wenn du nicht weiterkommst: Von Musterlösung neu starten
git checkout task/02-mvvm-repository
git checkout -b mein/02-mvvm-repository-neustart

# 6. Dateien aus Musterlösung in eigenen Branch übernehmen
git checkout mein/02-mvvm-repository-neustart
git checkout task/02-mvvm-repository -- app/src/main/java/dev/training/bookshelf/data/
```

### Tipps

- **Nie auf `main` committen** — immer auf einem eigenen Branch arbeiten.
- **Branch-Namen:** `mein/XX-beschreibung` oder `vorname/XX-beschreibung`.
- **Tags** markieren den exakten Stand einer Musterlösung. Auch als ZIP herunterladbar.
- **Wenn du hinterherhinkst:** Kein Problem. Checke den aktuellen Task-Branch aus und arbeite von dort weiter.
