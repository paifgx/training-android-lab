# Aufgabe 04: Room-Persistenz


---

## Lernziel

Du persistierst Bücher lokal mit Room und verstehst die Rollen von Entity, DAO, Database, TypeConverter und Mappern. Der Fokus liegt auf sauberer Trennung zwischen Domain- und Datenbankmodell.

## Kontext

Der Netzwerklayer kann Bücher holen. Ohne Persistenz sind diese Daten aber nicht lokal beobachtbar und nicht offline verfügbar. Room wird zur lokalen Single Source of Truth vorbereitet.

## Pflichtteil — gemeinsamer Mindeststand

### A) Entity erstellen

Lege eine Room-Entity für Bücher an.

Pflichtfelder:

- ID als Primary Key
- Titel
- Autorenliste
- Beschreibung
- optionale Cover-URL
- optionales Jahr/Datum
- Suchbegriff, durch den das Buch gecacht wurde
- Cache-Zeitpunkt

### B) TypeConverter

Room kann Listen nicht direkt speichern. Schreibe einen Converter für die Autorenliste.

**Ziel:** Entity bleibt einfach nutzbar, Datenbank speichert primitive Werte.

### C) DAO

Erstelle DAO-Methoden für:

- Bücher nach Suchbegriff beobachten
- einzelnes Buch nach ID beobachten
- mehrere Bücher speichern
- gecachte Treffer zu einer Suche löschen
- optional: gesamten Cache löschen

### D) Database

Erstelle eine RoomDatabase mit DAO-Zugriff.

Für dieses Lab darf Schema-Export deaktiviert sein. In echten Migrationsprojekten sollte Schema-Export bewusst aktiviert und versioniert werden.

### E) Mapper Entity ↔ Domain

Schreibe Mapper in beide Richtungen:

- Entity → Domain
- Domain → Entity mit Suchbegriff
- Listenvarianten

## Aufbauaufgaben

1. Ergänze einen Cache-Status: Wann wurde ein Treffer gespeichert?
2. Sortiere DAO-Ergebnisse sinnvoll, z. B. nach Cache-Zeit oder Titel.
3. Schreibe bewusst eine zweite Entity-Version auf Papier: Was wäre Version 2?
4. Überlege, welche Felder in eine separate Tabelle gehören würden, wenn die App größer wird.
5. Ergänze einen DAO-Query für „alle Bücher“ zur Debug-/Demo-Zwecke.

## Expert-/KI-Tasks

1. Lasse KI eine GreenDao→Room-Mapping-Tabelle erstellen und prüfe sie kritisch.
2. Entwerfe eine Migration von Version 1 auf Version 2, ohne sie zwingend zu implementieren.
3. Diskutiere TypeConverter vs. separate Author-Tabelle: Wann kippt die Entscheidung?
4. Prüfe, ob `lastSearchQuery` als Cache-Strategie fachlich sauber ist oder nur Trainingsvereinfachung.
5. Entwickle Testideen für Mapper und DAO, bevor du sie implementierst.

## Definition of Done

- Room-Entity vorhanden
- DAO kompiliert
- Database kompiliert
- TypeConverter vorhanden
- Mapper Entity ↔ Domain vorhanden
- Projekt baut

## Erweiterte Musterlösung

Branch: `task/04-room-persistenz-extended`  
Tag: `task-04-extended-done`

## Musterlösung

Branch: `task/04-room-persistenz`  
Tag: `task-04-done`
