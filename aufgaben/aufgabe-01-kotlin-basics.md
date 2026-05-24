# Aufgabe 01: Kotlin-Grundlagen für Java-Entwickler

**Tag 1** | Pflicht: ca. 60–75 Minuten | Aufbau/Expert: +30–45 Minuten  
**Format:** Einzelarbeit oder Pair-Programming

---

## Lernziel

Du übersetzt zentrale Java-Denkmodelle in Kotlin: POJO → `data class`, Enum/State-Holder → sealed hierarchy, Utility-Klasse → Extension Functions, nullable Referenzen → explizite Nullability.

## Kontext

`main` enthält ein lauffähiges Android-Projekt mit Platzhalter-UI. Es fehlen noch die Kotlin-Bausteine, auf denen alle späteren Aufgaben aufbauen.

## Pflichtteil — gemeinsamer Mindeststand

### A) Domain-Modell `Book`

Lege ein Domain-Modell für ein Buch an. Es soll mindestens enthalten:

- stabile ID
- Titel
- Autorenliste
- Beschreibung
- optionale Cover-URL
- optionales Veröffentlichungsjahr/-datum

**Ziel:** Ein unveränderliches Modell, das in UI, Repository und Tests verwendet werden kann.

### B) UI-State modellieren

Erstelle eine typsichere UI-State-Hierarchie für:

- Laden
- Erfolg mit Daten
- Fehler mit Nachricht

**Ziel:** Die UI soll später nicht mit losen Boolean-Flags wie `isLoading`, `hasError`, `data != null` arbeiten müssen.

### C) Fehlerdomäne vorbereiten

Lege eine kleine Fehlerhierarchie für Netzwerk-/API-Probleme an:

- HTTP-Fehler
- Netzwerk nicht erreichbar
- Parsing-/JSON-Fehler
- unbekannter Fehler

**Ziel:** Fehler werden später nicht nur als rohe Exception weitergereicht.

### D) Extension Functions

Erstelle kleine Extensions für typische UI-/String-Fälle:

- null/blank prüfen
- lange Texte kürzen
- Autorenliste lesbar formatieren

**Ziel:** Keine statischen Java-Utility-Klassen, sondern idiomatisches Kotlin.

## Aufbauaufgaben — wenn der Pflichtteil sitzt

1. Ergänze ein zweites Domain-Modell, z. B. `BookSearchQuery` oder `BookId`, und entscheide bewusst: braucht es eine eigene Klasse oder reicht `String`?
2. Erweitere die Autorenformatierung um Grenzfälle:
   - 0 Autoren
   - 1 Autor
   - 2–3 Autoren
   - mehr als 3 Autoren
3. Dokumentiere in Kommentaren, welche Java-Boilerplate durch `data class` wegfällt.
4. Schreibe für dich selbst 5 Kotlin-Regeln, die du als Java-Entwickler beachten musst.

## Expert-/KI-Tasks

1. Lasse dir von KI zwei alternative Modellierungen für `UiState` erklären: sealed class vs sealed interface. Entscheide, welche du hier nutzen würdest und warum.
2. Prüfe, ob `Book.id` besser als `String`, Value Class oder eigener Typ modelliert wäre. Notiere Vor- und Nachteile.
3. Erstelle bewusst einen kleinen Nullability-Fehler und lasse dir vom Compiler zeigen, warum Kotlin ihn verhindert.
4. Vergleiche deine Extensions mit einer klassischen Java-`StringUtils`-Klasse. Was ist lesbarer, was ist riskanter?

## Trainer-Checkpoints

Nach ca. 30 Minuten:

- Haben alle ein kompilierendes Domain-Modell?
- Sind `val` und `var` verstanden?
- Gibt es Nullability-Fragen?

Nach ca. 60 Minuten:

- Kann jeder erklären, warum `UiState` kein Enum sein sollte?
- Hat jeder mindestens eine Extension Function geschrieben?

## Definition of Done

- Projekt baut mit `./gradlew assembleDebug`
- `Book` ist als Kotlin-Domain-Modell vorhanden
- UI-State-Hierarchie ist typisiert
- Fehlerhierarchie ist vorbereitet
- Extensions sind nutzbar und verständlich

## Musterlösung

Branch: `task/01-kotlin-basics`  
Tag: `task-01-done`
