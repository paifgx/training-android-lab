# Aufgabe 06: Hilt Dependency Injection


---

## Lernziel

Du ersetzt manuelle Objektverkabelung durch Hilt. Dabei lernst du Module, Scopes, `@Provides`, `@Binds`, `@Inject`, `@HiltViewModel` und typische Fehlerbilder kennen.

## Kontext

Die App funktioniert bereits, aber die Activity erstellt zu viele Dinge selbst. Das macht Code schwerer testbar und skaliert schlecht. Hilt übernimmt den Dependency Graph.

## Pflichtteil — gemeinsamer Mindeststand

### A) Hilt aktivieren

- Application-Klasse erstellen
- Application im Manifest registrieren
- Hilt-App-Annotation setzen

### B) NetworkModule

Stelle bereit:

- Moshi
- OkHttpClient
- Retrofit mit Open-Library-Base-URL
- `OpenLibraryApiService`

### C) DatabaseModule

Stelle bereit:

- RoomDatabase
- BookDao

Achte bewusst auf ApplicationContext statt ActivityContext.

### D) RepositoryModule

Binde das Repository-Interface an die echte Implementierung.

Entscheide bewusst, wo `@Binds` sinnvoller ist als `@Provides`.

### E) ViewModel mit Hilt

- ViewModel bekommt Repository per Constructor Injection
- Activity holt ViewModel über den AndroidX/Hilt-konformen Weg
- manuelle Repository-Erzeugung verschwindet aus der Activity

### F) Fehlerdiagnose

Erzeuge oder analysiere mindestens zwei typische Hilt-Fehler:

- Binding fehlt
- falscher Scope
- falscher Context
- ViewModel falsch injiziert

## Aufbauaufgaben

1. Ergänze Qualifier für Base URL oder OkHttpClient, auch wenn es aktuell nur eine Variante gibt.
2. Füge ein separates Module für Fallback-/Training-Konfiguration hinzu.
3. Dokumentiere den Dependency Graph als Liste: Wer braucht wen?
4. Entferne bewusst eine Provider-Methode und interpretiere die Compiler-Fehlermeldung.
5. Prüfe, welche Objekte wirklich Singleton sein sollten — und welche nicht.

## Expert-/KI-Tasks

1. Lasse KI die Hilt-Fehlermeldung erklären, aber formuliere selbst den Fix.
2. Entwerfe eine Test-Variante, bei der Repository oder API-Service ersetzt werden kann.
3. Vergleiche Hilt mit manuellem DI und Koin: Wo sind Compile-Time-Vorteile?
4. Baue ein kleines „broken examples“-Dokument mit drei Hilt-Fehlern und Lösungen.
5. Prüfe, ob dein Graph UI-Abhängigkeiten in Datenmodule zieht. Falls ja: refactoren.

## Definition of Done

- App baut mit Hilt
- Activity erstellt Repository/Retrofit/Room nicht mehr manuell
- ViewModel erhält Repository per Injection
- Module sind sinnvoll getrennt
- Mindestens zwei Hilt-Fehler wurden verstanden oder dokumentiert

## Musterlösung

Branch: `task/06-hilt-di`  
Tag: `task-06-done`
