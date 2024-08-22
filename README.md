[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/NgBK_mOW)
# Intervall-Timer

## Projektbeschreibung
Die geplante Android-App ist ein Intervall-Timer. Die Kernfunktionalität ist das Aufrufen von
Intervall-Timern, welche zuvor vom Benutzer individuell konfiguriert und gespeichert
werden. Die Konfiguration der Übung (Exercise) soll Timer-Variablen wie beispielsweise
Start-Countdown, Rundenanzahl, Ausführungszeit der Übung, Pausenzeit und einen AlarmSound enthalten.

### Projektziele

[Beschreiben Sie hier in einer kurzen Zusammenfassung Hintergrund, Ziele und Funktionen Ihrer Anwendung. Fügen Sie mehrere sinnvollen Screenshot ein, die den Funktionsumfang der Anwendung demonstrieren.]

### Funktionen

[Beschreiben Sie die Funktionen ihrer App. Beziehen Sie sich dabei auf den Anforderungskatalog und stellen Sie eine Verbindung zu den entsprechneden Files oder Directories her.]

### Demo

[Verlinken Sie zum Abschluss des Projekts ein kurzes Video, in dem Sie die wesentlichen Nutzungsszenarien Ihrer Anwendung demonstrieren.]

## Team

### Vorstellung

Daniel Rindin:
E-Mail:  daniel.rindin@stud-mail.uni-wuerzburg.de
GitHub:  daniel1r  (Aus irgendeinem Grund werden die Commits als "Daniel Rindin", anstelle des GitHub Namens "daniel1r" angezeigt)

Daniel Rataj:
E-Mail:  daniel.rindin@stud-mail.uni-wuerzburg.de
GitHub:  Daniel-Rataj

Mehmet Tan:
E-Mail:  m.tan@hm.edu
GitHub:  Metanhm

### Zuständigkeiten

Die einzelnen Issues sind detailierter in den entsprechenden Issues in GitHub definiert.

Daniel Rindin:
Issue #1: Übungsübersicht erstellen
Issue #4: Exercise Model
Issue #5: ExerciseDialog(Fragment)
Issue #11: Initialisierung des Frontends 
Issue #14: Implementierung der Klingeltonwiedergabe (Abspielen von Sounds und Musik)

Daniel Rataj:
Issue #6: ExerciseRepository
Issue #7: ExerciseLogic
Issue #8: ExerciseController
Issue #9: DataAccess ExerciseModel
Issue #10: DataTransfer ExerciseModel
Issue #12: Initialisierung des Backends
Issue #13: Notification (Interaktiv)
Issue #15: Daten verschlüsselt versenden (HTTPS)

Mehmet Tan:
Issue #3: Service und API-Client
Issue #2: Übungsdetailseite (Intervall-Timer)
Issue#18: Tabbing (Swipe to change screen)


## Guidelines zur Nutzung dieses Repositorys

Das Backend und Frontend sind in unserer Lösung entkoppelt voneinander. 
Zum starten des Backends führen Sie die Datei "abschlussprojekt-ddm\app\IntervallTimerBackend\src\main\java\intervall_timer_backend\Application.java" in einem anderen Editor als Android Studio aus (z.B. Visual Studio Code).
Das Frontend wird wie gewohnt in Android Studio ausgeführt.
Alle Funktionalitäten des Intervall Timers funktionieren nur, wenn das Backend ausgeführt ist und eine entsprechende Verbindung (über localhost) besteht.

### Allgemeine Hinweise und Vorgaben

* Das Repository besteht im initialen Stand aus einem einzelnen Master-Branch. Versuchen Sie bei der Arbeit am Projekt darauf zu achten, dass sich in diesem Branch stets die aktuell lauffähige und fehlerfreie Version Ihrer Anwendung befindet. Nutzten Sie für die eigentliche Entwicklung ggf. weitere Branches.
* Gehen Sie sorgfältig bei der Erstellung von Issues und *Commit Messages* vor: Die Qualität dieser Artefakte fließt nicht in die Bewertung ein, trotzdem sollten Sie versuchen, Ihr Vorgehen anhand nachvollziehbarer Versionseinträge und klarere Aufgabenbeschreibung gut zu dokumentieren.
* Halten Sie diese Readme-Datei(en) stets aktuell.
* Diese sollte auch wichtige Informationen für die Nutzung beinhalten (Handbuch).
* Spätestens zur Projektabgabe legen Sie eine Release-Version des finalen Stands Ihrer Anwendung an und hängen an diese eine installierbare (Debug-) APK-Datei an.
* Achten Sie insbesondere darauf anzugeben, welches API-Level / NDK-Version ihrem Projekt zugrunde liegt und auf die 'Kompilierbarkeit' ihres finalen Codes.
