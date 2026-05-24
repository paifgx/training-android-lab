# Bookshelf — Android Training Lab

Ein schrittweises Android-Trainingsprojekt: Kotlin, MVVM, Retrofit, Room, Hilt.
Von Java-Entwicklern für Java-Entwickler, die in die moderne Android-Entwicklung einsteigen.

## Konzept

**Bookshelf** ist eine Buchersuche-App, die die Open Library API nutzt. Die Teilnehmer entwickeln sie Schritt für Schritt über 4 Tage und 8 Aufgaben — von Kotlin-Grundlagen bis zum komplett integrierten Endprodukt.

### Technologiestack

| Technologie | Zweck |
|---|---|
| Kotlin 1.9.25 | Programmiersprache |
| Android SDK 34 (MinSdk 25) | Plattform |
| ViewBinding + XML | UI (kein Compose) |
| MVVM + StateFlow | Architektur |
| Retrofit 2.11 + Moshi | Netzwerk |
| Room 2.6.1 | Persistenz |
| Hilt 2.52 | Dependency Injection |
| Coil 2.7 | Image Loading |
| JUnit 4 + kotlinx-coroutines-test | Testing |

### Aufbau

```
aufgaben/              ← Aufgabenblätter (Markdown)
├── 00-praxisplan-und-differenzierung.md
├── aufgabe-01-kotlin-basics.md
├── aufgabe-02-mvvm-repository.md
├── aufgabe-03-retrofit-service.md
├── aufgabe-04-room-persistenz.md
├── aufgabe-05-single-source-truth.md
├── aufgabe-06-hilt-di.md
├── aufgabe-07-testing.md
└── aufgabe-08-final-integration.md

docs/
└── branch-guide.md    ← Wie man Branches nutzt

app/                   ← Android-Projekt
```

### Branches & Musterlösungen

Siehe [`docs/branch-guide.md`](docs/branch-guide.md).

## Voraussetzungen

- Android Studio Hedgehog (2023.1.1) oder neuer
- JDK 17
- Android SDK mit Platform 34

## Schnellstart

```bash
# Projekt klonen
git clone <repo-url> bookshelf
cd bookshelf

# Öffne in Android Studio oder baue via CLI:
./gradlew assembleDebug
```

## Didaktische Prinzipien

- **Kein Code vorgegeben** — nur Tipps, Richtungen und Musterlösungen zum Vergleichen
- **Es führt nicht nur ein Weg nach Rom** — eigene Lösungsansätze sind erwünscht
- **Aufbauend** — jede Aufgabe baut auf der vorherigen auf
- **Offline-First** — die App funktioniert auch ohne Netzwerk (gecachte Daten)
- **Praxisnah** — produktionsnahe Technologien und Architektur
