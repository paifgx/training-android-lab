# Aufgabe 02: MVVM, StateFlow & Repository


---

## Lernziel

Du baust eine erste lauffähige Architektur: UI → ViewModel → UseCases → Repository. Die App zeigt lokale Fake-Daten an, reagiert auf Suchanfragen und unterscheidet Loading, Success, Empty und Error.

## Kontext

Aus Aufgabe 01 existieren Domain-Modell und UI-State. Jetzt entsteht daraus eine kleine, aber echte Android-App-Struktur.

## Pflichtteil — gemeinsamer Mindeststand

### A) Repository-Interface

Definiere ein Repository als Vertrag zwischen ViewModel und Datenquelle.

Es soll:

- Bücher als beobachtbaren Stream liefern
- ein einzelnes Buch über eine ID liefern können
- einen Refresh auslösen können
- den Refresh als Result-Typ zurückgeben, nicht als rohe Exception

**Ziel:** Das Repository ist die Datenabstraktion. Das ViewModel hängt später nicht direkt daran, sondern nutzt UseCases.

### B) Fake-Repository

Implementiere ein In-Memory-Repository mit mehreren Beispielbüchern.

Pflichtverhalten:

- leere Suche zeigt alle Beispielbücher
- Suchtext filtert nach Titel und/oder Autor
- Refresh ist im Fake zunächst ein No-op

### C) UseCases

Erstelle kleine UseCases zwischen ViewModel und Repository:

- `SearchBooksUseCase` beobachtet Bücher für eine Query
- `RefreshBooksUseCase` stößt einen Refresh an und liefert einen Result-Typ zurück

**Ziel:** Das ViewModel koordiniert UI-State, aber kennt keine Repository-Details.

### D) ViewModel

Erstelle ein ViewModel, das:

- UI-State als `StateFlow` veröffentlicht
- eine Suche starten kann
- die letzte Suche für Retry merkt
- nicht mehrere Such-Collectors parallel laufen lässt

**Ziel:** Die UI beobachtet nur State. Sie kennt keine Datenquelle.

### E) RecyclerView-UI

Baue eine einfache XML/ViewBinding-UI mit:

- Suchfeld
- RecyclerView
- Loading-Anzeige
- Empty-State
- Error-State
- Retry-Button

### F) Adapter

Erstelle einen RecyclerView-Adapter für Bücher.

Pflichtanzeige:

- Titel
- Autoren
- Jahr/Datum, falls vorhanden
- Platzhalter für Cover

## Aufbauaufgaben

1. Suche nicht nur beim IME-Action-Button, sondern zusätzlich über einen separaten Suchbutton.
2. Ergänze eine klare Empty-State-Message, wenn keine Treffer gefunden werden.
3. Ergänze einfache Sortierung: Titel alphabetisch oder Jahr absteigend.
4. Markiere im UI sichtbar, ob gerade Fake-Daten verwendet werden.
5. Baue bewusst einen Fehlerfall ins FakeRepository ein und teste die Error-UI manuell.

## Expert-/KI-Tasks

1. Lasse KI dein ViewModel auf Flow-/Coroutine-Probleme reviewen. Prüfe speziell: Mehrfach-Collector, Cancellation, State-Konsistenz.
2. Erweitere den UI-State um einen Zustand, der Daten und Warnung gleichzeitig erlaubt, z. B. „cached data plus error“.
3. Implementiere einen Debounce für Texteingaben, ohne dass bei jedem Tastendruck sofort gesucht wird.
4. Vergleiche `StateFlow` mit `LiveData`: Welche Entscheidung würdest du in einer Legacy-App treffen?
5. Erstelle ein kleines Architekturdiagramm deiner Lösung und vergleiche es im Pair mit jemand anderem.

## Definition of Done

- Projekt baut
- Bücher werden aus Fake-Daten angezeigt
- Suche filtert sichtbar
- ViewModel nutzt UseCases statt direkt das Repository
- Repository-Refresh liefert einen Result-Typ
- UI-State wird beobachtet, nicht direkt berechnet

## Erweiterte Musterlösung

Branch: `task/02-mvvm-repository-extended`  
Tag: `task-02-extended-done`

## Musterlösung

Branch: `task/02-mvvm-repository`  
Tag: `task-02-done`
