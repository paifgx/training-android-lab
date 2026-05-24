# Branch- und Tag-Leitfaden

## Für Teilnehmer

Dieses Repository nutzt Branches, um Aufgaben und Musterlösungen zu verwalten.

### Überblick

| Tag | Aufgabe | Branch | Inhalt | Tag |
|---|---:|---|---|---|
| — | Start | `main` | **Startpunkt** — leeres Projekt, lauffähig | — |
| Tag 1 | 01 | `task/01-kotlin-basics` | Kotlin-Grundlagen: data class, sealed, extensions | `task-01-done` |
| Tag 1 | 02 | `task/02-mvvm-repository` | MVVM: ViewModel, StateFlow, UseCases, Repository, FakeData | `task-02-done` |
| Tag 2 | 03 | `task/03-retrofit-service` | Netzwerk: Retrofit, DTOs, Moshi, Error-Mapping | `task-03-done` |
| Tag 2 | 04 | `task/04-room-persistenz` | Datenbank: Room Entity, DAO, TypeConverter | `task-04-done` |
| Tag 2 | 05 | `task/05-single-source-truth` | Integration: API → Room → UI offline-first, Result-Typen | `task-05-done` |
| Tag 3 | 06 | `task/06-hilt-di` | Dependency Injection: Hilt Module, Qualifier, Scopes | `task-06-done` |
| Tag 4 | 07 | `task/07-testing` | Unit-Tests: FakeRepo, Repository, ViewModel, Mapper | `task-07-done` |
| Tag 4 | 08 | `task/08-final-integration` | Abschlussreview: Integration, Review, Transfer | `task-08-done` |
| Abschluss | — | `solution/final` | **Komplettlösung** — stabiler Alias des finalen Stands | `final-solution` |
| Optional | 01–08 | `task/01-...-extended` bis `task/08-...-extended` | Erweiterte Musterlösungen für Aufbau-/Expert-Aufgaben | `task-01-extended-done` … `task-08-extended-done` |
| Optional | — | `solution/extended` | Vollständiger erweiterter Endstand | `extended-solution` |

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


### Erweiterte Musterlösungen

Für schnelle Teilnehmer gibt es pro Aufgabe zusätzlich einen Extended-Branch. Diese Branches sind optional und dienen als Vergleichslösung für Aufbau- und Expert-/KI-Tasks. Der Pflichtpfad bleibt weiterhin `task/01-*` bis `task/08-*`.
