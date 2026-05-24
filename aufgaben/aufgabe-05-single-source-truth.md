# Aufgabe 05: Single Source of Truth


---

## Lernziel

Du verbindest Open Library API und Room zu einer Offline-First-Architektur: Die UI beobachtet Room, das Repository aktualisiert Room aus dem Netzwerk.

## Kontext

Du hast Netzwerk und Datenbank isoliert gebaut. Jetzt entsteht daraus der wichtigste Architekturfluss der App.

## Pflichtteil — gemeinsamer Mindeststand

### A) DefaultRepository implementieren

Implementiere ein echtes Repository, das:

- Bücher aus Room als Flow liefert
- einzelne Bücher aus Room liefert
- bei Refresh die API aufruft
- API-Daten über Mapper in Domain und Entity überführt
- alte Cache-Einträge zur Suche ersetzt
- neue Ergebnisse in Room speichert

### B) UI an echtes Repository hängen

Verdrahte in der Activity zunächst manuell:

- API-Service
- Database/DAO
- Repository
- ViewModel

**Ziel:** Hilt kommt erst später. Hier soll sichtbar werden, welche Objekte überhaupt gebraucht werden.

### C) Fallback bei API-Ausfall

Open Library ist öffentlich verfügbar, aber Training muss trotzdem auch bei Netzwerkproblemen funktionieren. Ergänze oder nutze lokalen Fallback:

- bei Netzwerkfehler
- bei HTTP-Fehler
- bei leerer/kaputter API-Antwort, wenn sinnvoll

Die UI soll weiterhin über Room aktualisiert werden.

### D) Manuell testen

Teste mindestens:

- Suche nach „kotlin“
- Suche nach „java“
- Suche ohne Treffer
- Flugmodus / Netzwerk aus
- App schließen und neu öffnen

## Aufbauaufgaben

1. Zeige im UI eine dezente Warnung, wenn Fallback-Daten verwendet wurden.
2. Unterscheide „keine Treffer“ von „Netzwerkfehler“.
3. Ergänze Pull-to-refresh oder einen expliziten Refresh-Button.
4. Verhindere unnötige API-Calls bei leerer Query.
5. Speichere zusätzlich, ob ein Eintrag aus API oder Fallback stammt.

## Expert-/KI-Tasks

1. Lasse KI den Repository-Code auf Offline-First-Konsistenz prüfen. Frage explizit nach Race Conditions und doppelten Flow-Collectors.
2. Entwerfe eine Result-/sealed-type-Strategie, bei der Repository-Ergebnisse Daten plus Warnung liefern können.
3. Baue einfache Cache-Invalidierung: Daten älter als X Minuten werden neu geladen.
4. Diskutiere, ob `deleteByQuery + insertAll` atomar genug ist. Was wäre mit Room-Transaktionen?
5. Skizziere, wie Pagination in diese Architektur passt, ohne UI direkt an die API zu koppeln.

## Definition of Done

- Suche aktualisiert Room
- UI beobachtet Room
- Netzwerkfehler zerstören die App nicht
- Fallback oder Cache hält die Demo lauffähig
- Projekt baut und App startet

## Musterlösung

Branch: `task/05-single-source-truth`  
Tag: `task-05-done`
