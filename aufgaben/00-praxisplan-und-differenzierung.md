# Praxisplan & Differenzierung

Dieses Lab ist bewusst als **Learning-by-Doing** aufgebaut. Die Aufgaben sind nicht dafür gedacht, dass der Trainer jeden Schritt vorkodiert. Die Teilnehmer arbeiten selbst oder in Paaren; der Trainer gibt Impulse, stellt Fragen, hilft beim Debugging und moderiert kurze Checkpoints.

## Tagesziel: 2–3 Stunden echte Praxis

| Tag | Aufgaben | Pflichtzeit | Mit Aufbau/Expert |
|---|---:|---:|---:|
| Tag 1 | 01 Kotlin Basics + 02 MVVM/Repository | ca. 2:15 h | bis ca. 3:30 h |
| Tag 2 | 03 Retrofit + 04 Room + 05 Integration | ca. 2:30 h | bis ca. 4:00 h |
| Tag 3 | 06 Hilt DI | ca. 2:00 h | bis ca. 3:30 h |
| Tag 4 | 07 Testing + 08 Review/Transfer | ca. 2:15 h | bis ca. 3:30 h |

## Aufgabenstruktur

Jede Aufgabe ist in drei Niveaus gegliedert:

1. **Pflichtteil**  
   Muss jeder schaffen. Das ist der gemeinsame Stand für die nächste Aufgabe.

2. **Aufbauaufgaben**  
   Für Teilnehmer, die schneller sind oder sicherer werden wollen. Diese Aufgaben vertiefen Praxis, ohne den gemeinsamen Pfad zu verlassen.

3. **Expert-/KI-Tasks**  
   Für sehr schnelle Teilnehmer, Senior-Entwickler oder KI-gestützte Arbeit. Diese Aufgaben erzeugen zusätzlichen Mehrwert, sind aber nicht Voraussetzung für den nächsten Branch.

## KI-Nutzung im Lab

KI ist erlaubt, aber nicht als Copy/Paste-Autopilot. Wer KI nutzt, muss die Lösung erklären können.

Gute KI-Aufträge:

- „Erkläre mir die Fehlermeldung und nenne 2 Lösungswege.“
- „Reviewe meinen Mapper auf Null-Safety, aber schreibe mir nicht die komplette Datei.“
- „Welche Edge Cases fehlen in meinen Tests?“
- „Vergleiche meine Lösung mit dem Repository-Pattern und nenne Risiken.“

Schlechte KI-Aufträge:

- „Schreib mir die komplette Aufgabe.“
- „Fix alles.“
- „Gib mir irgendeinen Code, der kompiliert.“

## Trainer-Checkpoints

Nach jeder größeren Pflichtphase sollte der Trainer kurz stoppen:

- Wer hat Build-Probleme?
- Wer hat Architekturfragen?
- Welche zwei Lösungen unterscheiden sich sinnvoll?
- Wo hat KI geholfen, wo verwirrt?
- Was ist der minimale gemeinsame Stand für die nächste Aufgabe?

## Wenn jemand zurückfällt

Das ist vorgesehen. Der Teilnehmer kann jederzeit auf einen Musterlösungsbranch wechseln:

```bash
git checkout task/03-retrofit-service
git checkout -b mein/weiter-ab-03
```

Danach arbeitet er vom fertigen Stand weiter.
