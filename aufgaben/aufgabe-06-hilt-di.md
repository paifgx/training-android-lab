# Aufgabe 06: Hilt — Dependency Injection

**Tag 3** | ⏱ ca. 70 Minuten | 🎯 Selbstständig + Fehler-Diagnose-Übungen

---

## Lernziel

Du ersetzt die manuelle Verkabelung aus Aufgabe 05 durch Hilt. Du verstehst den Dependency-Graph, Scopes und Module — und erkennst, was unter der Haube passiert.

## Kontext

Die App funktioniert komplett, aber die MainActivity erstellt alle Dependencies von Hand (RetrofitFactory, BookDatabase, DefaultBookRepository). Das ist fehleranfällig und schlecht testbar.

**Was existiert:** Vollständig funktionierende App (Aufgabe 05).

**Was ändert sich:** Manuelle Verkabelung → Hilt verwaltet den Dependency-Graph.

## Aufgabe

### A) Application-Klasse

Erstelle eine `BookshelfApp : Application()` und annotiere sie mit `@HiltAndroidApp`. Registriere sie im AndroidManifest via `android:name=".BookshelfApp"`.

### B) NetworkModule

Erstelle ein `@Module @InstallIn(SingletonComponent::class) object NetworkModule`:
- `@Provides @Singleton fun provideMoshi(): Moshi`
- `@Provides @Singleton fun provideOkHttpClient(): OkHttpClient`
- `@Provides @Singleton fun provideRetrofit(moshi, okHttpClient): Retrofit`
- `@Provides @Singleton fun provideApiService(retrofit): GoogleBooksApiService`

### C) DatabaseModule

Erstelle ein `@Module @InstallIn(SingletonComponent::class) object DatabaseModule`:
- `@Provides @Singleton fun provideDatabase(@ApplicationContext context): BookDatabase`
- `@Provides fun provideBookDao(database): BookDao`

### D) RepositoryModule

Erstelle ein `@Module @InstallIn(SingletonComponent::class) abstract class RepositoryModule`:
- `@Binds @Singleton abstract fun bindBookRepository(impl: DefaultBookRepository): BookRepository`

### E) HiltViewModel & AndroidEntryPoint

- Markiere `BookListViewModel` mit `@HiltViewModel` und `@Inject constructor`
- Markiere `DefaultBookRepository` mit `@Singleton` und `@Inject constructor`
- Markiere `MainActivity` mit `@AndroidEntryPoint`
- Ersetze `lateinit var viewModel` durch `val viewModel: BookListViewModel by viewModels()`

### F) Aufräumen

Entferne `RetrofitFactory` — die Logik lebt jetzt in NetworkModule.

## Hinweise & Tipps

- **@Module = "Hier stelle ich Dependencies zur Verfügung."** Hilt scannt diese Klassen und baut den Graphen.
- **@InstallIn(SingletonComponent) = "Lebt so lange wie die Application."** Alternativen: ActivityComponent, ViewModelComponent, etc.
- **@Provides vs @Binds:** @Provides = "hier ist eine Funktion, die das Objekt erstellt." @Binds = "wenn jemand X will, gib ihm Y." @Binds braucht eine abstract class.
- **@Singleton = "Nur eine Instanz."** Ohne @Singleton erstellt Hilt bei jeder Anfrage eine neue Instanz. Bei OkHttpClient/Retrofit wäre das Verschwendung.
- **@ApplicationContext = "Gib mir den Application-Context."** Hilt injiziert ihn automatisch. Niemals Activity-Context für Singletons verwenden → Memory Leak.
- **by viewModels() = Kotlin Property Delegate.** Erstellt das ViewModel via Hilt und überlebt Configuration Changes. Kein ViewModelProvider.of(this) mehr.
- **Hilt generiert Code zur Compile-Zeit.** Wenn etwas falsch ist, siehst du es als Compile-Fehler, nicht erst zur Laufzeit.

## Wie weiter?

→ Branch `task/06-hilt-di` zeigt eine mögliche Musterlösung.
→ Nächste Aufgabe: **Aufgabe 07 — Testing**

## Zeitaufwand

ca. 70 Minuten
