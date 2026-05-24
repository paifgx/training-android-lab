# Aufgabe 02: MVVM, StateFlow & Repository

**Tag 1** | Pflicht: ca. 90–120 Minuten | Aufbau/Expert: +45–60 Minuten  
**Format:** Pair-Programming empfohlen

---

## Lernziel

Du baust eine erste lauffähige Architektur: UI → ViewModel → Repository. Die App zeigt lokale Fake-Daten an, reagiert auf Suchanfragen und unterscheidet Loading, Success, Empty und Error.

## Kontext

Aus Aufgabe 01 existieren Domain-Modell und UI-State. Jetzt entsteht daraus eine kleine, aber echte Android-App-Struktur.

## Pflichtteil — gemeinsamer Mindeststand

### A) Repository-Interface

Definiere ein Repository als Vertrag zwischen ViewModel und Datenquelle.

Es soll:

- Bücher als beobachtbaren Stream liefern
- ein einzelnes Buch über eine ID liefern können
- einen Refresh auslösen können

**Ziel:** Das ViewModel hängt nicht an einer konkreten Datenquelle.

### B) Fake-Repository

Implementiere ein In-Memory-Repository mit mehreren Beispielbüchern.

Pflichtverhalten:

- leere Suche zeigt alle Beispielbücher
- Suchtext filtert nach Titel und/oder Autor
- Refresh ist im Fake zunächst ein No-op

### C) ViewModel

Erstelle ein ViewModel, das:

- UI-State als `StateFlow` veröffentlicht
- eine Suche starten kann
- die letzte Suche für Retry merkt
- nicht mehrere Such-Collectors parallel laufen lässt

**Ziel:** Die UI beobachtet nur State. Sie kennt keine Datenquelle.

### D) RecyclerView-UI

Baue eine einfache XML/ViewBinding-UI mit:

- Suchfeld
- RecyclerView
- Loading-Anzeige
- Empty-State
- Error-State
- Retry-Button

### E) Adapter

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

## Trainer-Checkpoints

Nach ca. 45 Minuten:

- Repository-Interface vorhanden?
- FakeRepository liefert Daten?
- Alle verstehen, warum Interface vor Implementierung kommt?

Nach ca. 90 Minuten:

- UI reagiert auf StateFlow?
- RecyclerView zeigt Daten?
- Loading/Empty/Error sind als Zustände erkennbar?

## Definition of Done

- Projekt baut
- Bücher werden aus Fake-Daten angezeigt
- Suche filtert sichtbar
- ViewModel hängt nur am Repository-Interface
- UI-State wird beobachtet, nicht direkt berechnet

## Musterlösung

Branch: `task/02-mvvm-repository`  
Tag: `task-02-done`
