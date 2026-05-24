# Aufgabe 08: Integration, Review & Transfer

**Tag 4** | ⏱ ca. 45 Minuten | 🎯 Abschlussübung + Code-Review

---

## Lernziel

Du überprüfst die komplette App kritisch: Architekturfluss, Fehlerfälle, Testabdeckung und Übertragbarkeit auf echte Legacy-Projekte.

## Kontext

Die App hat jetzt alle Schichten: Kotlin-Modelle, MVVM, Retrofit, Room, Hilt und Unit-Tests. In dieser Aufgabe geht es nicht mehr um ein neues Framework, sondern um saubere Integration und Transferfähigkeit.

**Was existiert:** End-to-End-App mit API → Room → UI, Hilt, Tests.

**Was fehlt:** Bewusster Qualitätscheck, Refactoring-Entscheidungen, Transfernotizen.

## Aufgabe

### A) Architekturfluss nachvollziehen

Zeichne oder notiere den Datenfluss:

1. Nutzer startet Suche in der UI
2. ViewModel setzt Loading-State
3. Repository lädt per Retrofit
4. Repository schreibt nach Room
5. UI beobachtet Room via Flow
6. RecyclerView zeigt Daten
7. Fehlerfall: Netzwerk down, Cache vorhanden/nicht vorhanden

### B) Kritischer Code-Review

Prüfe den Code auf folgende Punkte:

- Gibt es Stellen, an denen UI direkt Infrastruktur kennt?
- Sind DTO, Entity und Domain sauber getrennt?
- Sind Coroutines an einen Lifecycle gebunden?
- Wird ein Flow mehrfach gesammelt, obwohl nur ein aktiver Suchjob gewünscht ist?
- Sind Netzwerkfehler für Nutzer verständlich?
- Sind Hilt-Scopes sinnvoll gesetzt?
- Sind Mapper getestet?

### C) Refactoring-Optionen sammeln

Notiere mindestens drei mögliche Verbesserungen, ohne sie zwingend umzusetzen:

- Suchdebounce statt IME-only
- Detail-Screen für einzelne Bücher
- Favoriten lokal speichern
- Pagination (`page`, `limit` bzw. weitere Open-Library-Parameter)
- Separate UI-Modelle statt Domain direkt im Adapter
- Repository-Ergebnis als `Result`/sealed type statt Exception-Pfad

### D) Transfer auf Legacy-Projekte

Übertrage die Struktur gedanklich auf eine bestehende App:

- Welche Klasse wäre bei euch das Repository?
- Wo sitzt aktuell GreenDao?
- Welche Schicht dürfte Retrofit kennen?
- Wo würdet ihr Hilt zuerst einführen?
- Welche Tests geben bei Migration am meisten Sicherheit?

### E) Abschlussvalidierung

Führe aus:

```bash
./gradlew clean assembleDebug testDebugUnitTest
```

Erwartung: Build und Tests laufen grün.

## Hinweise & Tipps

- **Nicht alles refactoren:** Diese Aufgabe ist bewusst ein Review. Entscheide, was wichtig ist — nicht was möglich ist.
- **Legacy-Transfer:** In echten Apps migriert man selten alles auf einmal. Schichtweise: Mapper/Repository → Room → DI → Tests.
- **Tests zuerst bei Mappings:** Bei GreenDao→Room-Migration sind Mapper-Tests oft günstiger und wertvoller als UI-Tests.
- **Hilt nicht überziehen:** Nicht jede kleine Utility-Funktion muss injiziert werden. Hilt für Objekte mit Abhängigkeiten/Lifecycle/Varianten.
- **Mehrere Wege sind okay:** Ziel ist nicht exakt die Musterlösung, sondern begründete Architekturentscheidungen.

## Wie weiter?

→ Branch `task/08-final-integration` zeigt den finalen integrierten Stand.
→ Branch `solution/final` ist ein stabiler Alias für die Komplettlösung.

## Zeitaufwand

ca. 45 Minuten
