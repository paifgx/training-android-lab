# Aufgabe 07: Unit-Tests & Test Doubles


---

## Lernziel

Du testest zentrale Logik ohne Emulator: ViewModel, Mapper und kleine Utility-Funktionen. Du nutzt Fakes statt sofort ein Mocking-Framework einzuführen.

## Kontext

Die App ist integriert und nutzt Hilt. Für Unit-Tests isolieren wir die Logik: kein echtes Netzwerk, keine echte Datenbank, kein Android-Gerät.

## Pflichtteil — gemeinsamer Mindeststand

### A) Fake Repository für Tests

Erstelle ein Test-Repository, das:

- Daten kontrolliert setzen kann
- Fehler simulieren kann
- dasselbe Interface wie das echte Repository implementiert

### B) ViewModel testen

Teste mindestens:

- initialer Load mit Daten
- Suche filtert korrekt
- leere Suche liefert alle Testdaten
- Fehler im Repository führt zu Error-State
- Retry nutzt die letzte Suche

Achte auf Coroutine-Testsetup für den Main Dispatcher.

### C) Entity-Mapper testen

Teste:

- Domain → Entity
- Entity → Domain
- Roundtrip ohne Datenverlust
- Null-/Optional-Felder

### D) DTO-Mapper testen

Teste Open-Library-Mapping:

- vollständiger DTO
- fehlende Ergebnisliste
- fehlender Key
- fehlender Titel
- fehlende Autoren
- fehlende Cover-ID

### E) Extensions testen

Teste Grenzfälle deiner Extension Functions.

## Aufbauaufgaben

1. Ergänze Tests für Fallback-Daten bei API-Fehlern.
2. Teste Sortierung oder Filterlogik getrennt vom ViewModel.
3. Baue absichtlich einen fehlschlagenden Test und nutze ihn als Debuggingübung.
4. Ergänze sprechende Testdaten statt generischer `Book("1", ...)`-Objekte.
5. Erstelle eine kleine Test-Matrix: Was testen wir nicht und warum?

## Expert-/KI-Tasks

1. Lasse KI Testfälle für deinen Mapper vorschlagen. Übernimm nur die, die einen echten Fehler finden könnten.
2. Vergleiche Fake vs Mock vs Stub am konkreten Repository-Beispiel.
3. Entwerfe einen Integrationstest mit MockWebServer, ohne ihn zwingend komplett umzusetzen.
4. Prüfe, wie Hilt in Instrumentation Tests Dependencies ersetzen kann.
5. Finde einen Test, der zu stark an Implementierungsdetails hängt, und refactore ihn.

## Definition of Done

- Unit-Tests laufen lokal grün
- ViewModel wird ohne Android-Gerät getestet
- Mapper sind gegen Null-/Edge-Cases abgesichert
- FakeRepository ist verständlich und bewusst eingesetzt

## Musterlösung

Branch: `task/07-testing`  
Tag: `task-07-done`
