# Aufgabe 03: Retrofit-Service & Open Library


---

## Lernziel

Du baust einen echten Netzwerklayer mit Retrofit und Moshi. Du trennst API-DTOs sauber vom Domain-Modell und behandelst Nullability an der richtigen Stelle.

## Kontext

Bis jetzt nutzt die App Fake-Daten. Jetzt wird ein echter API-Service vorbereitet. Die gewählte API ist **Open Library**, weil der Search-Endpoint ohne API-Key funktioniert und damit trainingsrobuster ist.

Endpoint zur Orientierung:

`https://openlibrary.org/search.json?q=kotlin&limit=20`

## Pflichtteil — gemeinsamer Mindeststand

### A) API-Response verstehen

Öffne die Open-Library-URL im Browser oder per HTTP-Client und identifiziere:

- Liste der Bücher
- eindeutiger Work-Key
- Titel
- Autoren
- erstes Veröffentlichungsjahr
- Cover-ID
- optionale Kurzbeschreibung / erster Satz

### B) DTOs modellieren

Erstelle DTO-Klassen für genau die Felder, die du brauchst.

**Wichtig:** DTOs bilden die API ab, nicht deine App-Architektur.

### C) Retrofit-Service

Erstelle ein Retrofit-Interface für die Suche.

Pflichtparameter:

- Suchbegriff
- Limit
- Fields-Auswahl, damit die Antwort nicht unnötig groß wird

### D) Mapper DTO → Domain

Schreibe Mapper vom API-Modell zum Domain-`Book`.

Pflichtfälle:

- fehlende Liste → leere Liste
- fehlender Key → Buch ignorieren
- fehlender Titel → Buch ignorieren
- fehlende Autoren → leere Autorenliste
- Cover-URL aus Cover-ID bauen

### E) Retrofit-Konfiguration

Konfiguriere:

- Base URL Open Library
- Moshi
- OkHttpClient
- Logging nur für Entwicklung
- Timeouts

## Aufbauaufgaben

1. Begrenze die API-Antwort über `fields` bewusst und prüfe den Unterschied zur vollen Antwort.
2. Ergänze Mapping für weitere Felder, z. B. `language` oder `subject`, aber nur wenn du begründen kannst, wohin sie im Domain-Modell gehören.
3. Behandle doppelte Treffer im Mapper (`distinctBy`).
4. Führe eine absichtlich fehlerhafte Query aus und beobachte das Verhalten.
5. Logge im Debug-Build, welche URL Retrofit tatsächlich aufruft.

## Expert-/KI-Tasks

1. Lasse KI die Open-Library-JSON-Struktur analysieren und ein DTO vorschlagen. Prüfe kritisch, welche Felder du wirklich brauchst.
2. Vergleiche zwei Mapper-Strategien: harte Filterung vs. Fallback-Werte. Welche ist für UI, Tests und Domain sauberer?
3. Erstelle eine kleine Error-Mapping-Strategie: HTTP, Netzwerk, JSON, unbekannt.
4. Diskutiere, ob API-DTOs `internal` sein sollten und was das für Modulgrenzen bedeutet.
5. Skizziere, wie du Pagination später ergänzen würdest.

## Definition of Done

- Retrofit-Service kompiliert
- DTOs modellieren Open Library gezielt, nicht vollständig blind
- Mapper liefert `List<Book>`
- Base URL zeigt auf Open Library
- Projekt baut

## Erweiterte Musterlösung

Branch: `task/03-retrofit-service-extended`  
Tag: `task-03-extended-done`

## Musterlösung

Branch: `task/03-retrofit-service`  
Tag: `task-03-done`
