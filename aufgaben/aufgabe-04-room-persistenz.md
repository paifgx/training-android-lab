# Aufgabe 04: Room-Datenbank & Persistenz

**Tag 2** | ⏱ ca. 60 Minuten | 🎯 Selbstständig + GreenDao-Vergleich

---

## Lernziel

Du baust die Room-Persistenzschicht: Entity, DAO, Database, TypeConverter — und verstehst die Parallelen und Unterschiede zu GreenDao.

## Kontext

Der Netzwerklayer (Aufgabe 03) holt Bücher von der API. Aktuell gehen die Daten bei jeder neuen Suche verloren. Jetzt speicherst du sie lokal in Room.

**Was existiert:** Domain-Modelle, Netzwerk-DTOs, Mapper.

**Was fehlt:** Datenbank-Entity, DAO, Database, TypeConverter, Entity↔Domain-Mapper.

## Aufgabe

### A) BookEntity

Erstelle eine Room `@Entity`:
- Gleiche Felder wie `Book`, plus `lastSearchQuery: String` und `cachedAt: Long`
- `@PrimaryKey val id`
- `@TypeConverters(BookTypeConverters::class)` für `List<String>`

### B) BookDao

Erstelle ein `@Dao`-Interface mit:
- `@Query("SELECT * FROM books WHERE lastSearchQuery LIKE '%' || :query || '%'")` → `Flow<List<BookEntity>>`
- `@Query("SELECT * FROM books WHERE id = :id")` → `Flow<BookEntity?>`
- `@Insert(onConflict = OnConflictStrategy.REPLACE)` → `suspend fun insertAll(books: List<BookEntity>)`
- `@Query("DELETE FROM books WHERE ...")` → `suspend fun deleteByQuery(query: String)`

### C) BookTypeConverters

Room kann `List<String>` nicht nativ speichern. Schreibe einen TypeConverter:
- `fromStringList(List<String>) → String` (z.B. kommasepariert)
- `toStringList(String) → List<String>`

### D) BookDatabase

Erstelle eine `@Database`-Klasse:
- entities = [BookEntity::class], version = 1
- Abstract `bookDao(): BookDao`
- Singleton-Pattern mit `Room.databaseBuilder()`

### E) Entity ↔ Domain Mapper

Schreibe bidirektionale Mapper:
- `BookEntity.toDomainModel(): Book`
- `Book.toEntity(query: String): BookEntity`
- List-Varianten für beide Richtungen

## Hinweise & Tipps

- **GreenDao → Room Migration:** Dieser Schritt zeigt die Migration von GreenDao zu Room. Die Konzepte sind identisch (Entity, DAO, Database), aber Room nutzt Annotation Processing (KSP) statt Code-Generierung.
- **Drei Modellebenen:** Network (DTO) ↔ Database (Entity) ↔ Domain (Book). Jede Ebene hat eigene Typen. Keine direkte Kopplung.
- **TypeConverter:** In GreenDao würdet ihr die Liste als Text-Spalte oder Join-Tabelle abbilden. Room's TypeConverter ist eleganter — eine Annotation und fertig.
- **`Flow` als Return-Type:** Room beobachtet die Tabelle. Wenn sich die Daten ändern (Insert, Delete), emittiert Room automatisch ein Update. Kein manuelles Refresh nötig.
- **`@Insert(onConflict = REPLACE)`:** Wenn ein Buch mit gleicher ID schon existiert, wird es überschrieben. Einfacher als `INSERT OR UPDATE` per Hand.
- **Schema-Version:** `version = 1` bedeutet: kein Migration-Code nötig. Bei `version = 2` müsst ihr eine `Migration(1, 2)` schreiben. Das kommt in der Praxis — heute nicht.

## Wie weiter?

→ Branch `task/04-room-persistenz` zeigt eine mögliche Musterlösung.
→ Nächste Aufgabe: **Aufgabe 05 — Single Source of Truth**

## Zeitaufwand

ca. 60 Minuten
