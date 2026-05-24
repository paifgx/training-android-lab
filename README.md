# Bookshelf — Android Training Lab

Ein schrittweises Android-Trainingsprojekt: Kotlin, MVVM, UseCases, Retrofit, Room, Hilt.
Von Java-Entwicklern für Java-Entwickler, die in die moderne Android-Entwicklung einsteigen.

## Konzept

**Bookshelf** ist eine Buchersuche-App, die die Open Library API nutzt. Die Teilnehmer entwickeln sie Schritt für Schritt über 4 Tage und 8 Aufgaben — von Kotlin-Grundlagen bis zum komplett integrierten Endprodukt.

### Technologiestack

| Technologie | Zweck |
|---|---|
| Kotlin 1.9.25 | Programmiersprache |
| Android SDK 34 (MinSdk 25) | Plattform |
| ViewBinding + XML | UI (kein Compose) |
| MVVM + StateFlow + UseCases | Architektur |
| Retrofit 2.11 + Moshi | Netzwerk |
| Room 2.6.1 | Persistenz |
| Hilt 2.52 | Dependency Injection |
| Coil 2.7 | Image Loading |
| JUnit 4 + kotlinx-coroutines-test | Testing |

### Aufgaben, Branches und Tags

| Trainingstag | Aufgabe | Thema | Pflicht-Branch / Tag | Erweiterter Branch / Tag |
|---|---:|---|---|---|
| Tag 1 | 01 | Kotlin-Grundlagen | `task/01-kotlin-basics` / `task-01-done` | `task/01-kotlin-basics-extended` / `task-01-extended-done` |
| Tag 1 | 02 | MVVM, StateFlow, Repository, UseCases | `task/02-mvvm-repository` / `task-02-done` | `task/02-mvvm-repository-extended` / `task-02-extended-done` |
| Tag 2 | 03 | Retrofit + Open Library | `task/03-retrofit-service` / `task-03-done` | `task/03-retrofit-service-extended` / `task-03-extended-done` |
| Tag 2 | 04 | Room-Persistenz | `task/04-room-persistenz` / `task-04-done` | `task/04-room-persistenz-extended` / `task-04-extended-done` |
| Tag 2 | 05 | Single Source of Truth + Result-Typen | `task/05-single-source-truth` / `task-05-done` | `task/05-single-source-truth-extended` / `task-05-extended-done` |
| Tag 3 | 06 | Hilt DI + Qualifier | `task/06-hilt-di` / `task-06-done` | `task/06-hilt-di-extended` / `task-06-extended-done` |
| Tag 4 | 07 | Unit- und Repository-Tests | `task/07-testing` / `task-07-done` | `task/07-testing-extended` / `task-07-extended-done` |
| Tag 4 | 08 | Integration, Review, Transfer | `task/08-final-integration` / `task-08-done` | `task/08-final-integration-extended` / `task-08-extended-done` |
| Abschluss | — | Komplettstand | `solution/final` / `final-solution` | `solution/extended` / `extended-solution` |

### Aufbau

```
aufgaben/              ← Aufgabenblätter (Markdown)
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
- JDK 17 oder Android Studio Embedded JDK
- Android SDK mit Platform 34
- Git-Zugriff auf das private Repository: `https://github.com/paifgx/training-android-lab`

## Schnellstart

```bash
# Projekt klonen
git clone https://github.com/paifgx/training-android-lab.git bookshelf
cd bookshelf

# Startpunkt für eigene Arbeit
git checkout main

# Öffne in Android Studio oder baue via CLI:
./gradlew assembleDebug
```

### CLI-/Setup-Hinweise

Wenn Android Studio den SDK-Pfad nicht automatisch setzt, kopiere `local.properties.sample` nach `local.properties` und passe `sdk.dir` lokal an. `local.properties` wird nicht committed.

```bash
# Linux/macOS Beispiel
export JAVA_HOME=/path/to/jdk17
export ANDROID_HOME=/path/to/Android/Sdk
./gradlew clean assembleDebug
```

Typische Setup-Probleme:

- **Falsche Gradle-JVM:** In Android Studio auf JDK 17 bzw. Embedded JDK stellen.
- **SDK 34 fehlt:** Über SDK Manager installieren.
- **Dependency-Download blockiert:** Proxy/VPN/Maven-Zugriff prüfen.
- **Emulator startet nicht:** Erst CLI-Build prüfen, dann Device/Emulator debuggen.
- **Eigener Stand hängt:** Passenden `task/XX-*`-Branch auschecken und von dort weiterarbeiten.

## Didaktische Prinzipien

- **Kein Code vorgegeben** — nur Tipps, Richtungen und Musterlösungen zum Vergleichen
- **Es führt nicht nur ein Weg nach Rom** — eigene Lösungsansätze sind erwünscht
- **Aufbauend** — jede Aufgabe baut auf der vorherigen auf
- **Offline-First** — die App funktioniert auch ohne Netzwerk (gecachte Daten)
- **Praxisnah** — produktionsnahe Technologien und Architektur
