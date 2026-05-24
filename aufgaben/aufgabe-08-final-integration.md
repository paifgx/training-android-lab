# Aufgabe 08: Integration, Review & Transfer


---

## Lernziel

Du überprüfst die komplette App kritisch: Architekturfluss, Fehlerfälle, Testabdeckung und Übertragbarkeit auf echte Legacy-Projekte.

## Kontext

Die App hat jetzt alle Schichten: Kotlin-Modelle, MVVM, Retrofit/Open Library, Room, Hilt und Unit-Tests. In dieser Aufgabe geht es nicht mehr um ein neues Framework, sondern um Integration, Qualität und Transfer.

## Pflichtteil — gemeinsamer Mindeststand

### A) Architekturfluss nachvollziehen

Beschreibe den Datenfluss ohne Code:

1. Nutzer startet Suche
2. ViewModel setzt Loading-State
3. Repository fragt API oder Fallback
4. Repository schreibt nach Room
5. UI beobachtet Room via Flow
6. RecyclerView zeigt Daten
7. Fehlerfall: Netzwerk down, Cache/Fallback vorhanden

### B) Kritischer Code-Review

Prüfe:

- Kennt UI Infrastrukturdetails?
- Sind DTO, Entity und Domain sauber getrennt?
- Sind Coroutines lifecycle-aware?
- Werden alte Suchjobs gecancelt?
- Ist Fallback-Verhalten nachvollziehbar?
- Sind Hilt-Scopes sinnvoll?
- Sind Mapper sinnvoll getestet?

### C) Transfernotizen

Notiere für eine bestehende Legacy-App:

- Welche Klasse wäre dort Repository?
- Wo sitzt aktuell Persistenz?
- Welche Schicht darf Netzwerk kennen?
- Wo würdest du DI zuerst einführen?
- Welche Tests geben bei Migration am meisten Sicherheit?

### D) Abschlussvalidierung

Führe aus:

- Clean Build
- Unit-Tests
- App starten
- Suche ausführen
- Netzwerk aus / Fallback prüfen

## Aufbauaufgaben

1. Ergänze eine kleine „About/Debug“-Anzeige: API oder Fallback?
2. Erstelle eine Risiko-/Nutzen-Matrix für Migration: Kotlin, Room, Hilt, Tests.
3. Formuliere drei Team-Regeln für Repository- und Mapper-Code.
4. Plane einen nächsten Sprint: Welche 3 Verbesserungen wären realistisch?
5. Schreibe ein kurzes README-Kapitel „Wie erweitere ich diese App?“

## Expert-/KI-Tasks

1. Lasse KI einen Architekturreview erstellen und markiere alle Aussagen, die du belegen kannst oder nicht belegen kannst.
2. Entwerfe eine robuste Error-UX für „Daten vorhanden, Refresh fehlgeschlagen“.
3. Skizziere Pagination mit Open Library, ohne die UI an API-Parameter zu koppeln.
4. Plane eine echte Room-Migration von Version 1 auf Version 2.
5. Erstelle eine „Do/Don't“-Liste für KI-generierten Android-Code im Team.

## Definition of Done

- App baut und Tests laufen grün
- Datenfluss kann erklärt werden
- Mindestens drei Transferentscheidungen sind notiert
- Mindestens ein Verbesserungs- oder Modernisierungspfad ist beschrieben

## Erweiterte Musterlösung

Branch: `task/08-final-integration-extended`  
Tag: `task-08-extended-done`

## Musterlösung

Branch: `task/08-final-integration`  
Tag: `task-08-done`  
Stabiler Komplettstand: `solution/final` / `final-solution`
