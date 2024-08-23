[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/NgBK_mOW)
# Intervall-Timer

## Projektbeschreibung
Die geplante Android-App ist ein Intervall-Timer. Die Kernfunktionalität ist das Aufrufen von
Intervall-Timern, welche zuvor vom Benutzer individuell konfiguriert und gespeichert
werden. Die Konfiguration der Übung (Exercise) soll Timer-Variablen wie beispielsweise
Start-Countdown, Rundenanzahl, Ausführungszeit der Übung, Pausenzeit und einen Alarm-Sound enthalten.

### Projektziele

Der Android-App Intervall-Timer ist eine App, die hauptsächlich die Nutzenden bei ihren Work-out Sessions unterstützen soll. Üblicherweise werden in solch einer Session unterschiedliche Übungen für eine bestimmte Zeit, mit mehreren Wiederholungen durchgeführt. Um dies effizienter zu gestalten, dient der Intervall-Timer dazu diese Zeiten effizient zu verfolgen, sodass die Nutzenden nicht mehr mehrere Timer für jede Wiederholung stellen müssen, sondern alles in einem haben. Somit können sich die Nutzenden vollkommen auf ihr Work-out konzentrieren.

Der Intervall-Timer bietet die Option anzugeben, wie viele Wiederholungen der Übungen der Timer beeinhalten soll, wie lange eine Wiederholung gehen soll und wie lange die Pause zwischen den Übungen sein soll. Zudem ist es möglich einen Countdown vor dem Start des Intervall-Timers anzugeben, um etwas Zeit zu haben sich auf die Übungen vorzubereiten bevor der Timer startet.

![image](https://github.com/user-attachments/assets/57599c7d-e257-4469-a596-3539506a1b60)

Die Nutzereingaben werden außerdem überprüft, um eine sinnvolle und fehlerfreie Nutzung der App zu garantieren.

![image](https://github.com/user-attachments/assets/3195d4e0-86c7-4e1d-b800-9011067bb410)

Die einzelnen, angelegten, Timer werden in einer Datenbank gespeichert und in der Homepage, der Übungsübersicht, angezeigt. In dieser Ansicht können die einzelnen Timer gestartet, sowie bearbeitet und entfernt werden.

![image](https://github.com/user-attachments/assets/d6b30d9f-f1d2-4592-bfc7-fdbd2d33344c)

Beim starten des Timers werden die Nutzenden auf eine neue Ansicht weitergeleitet, die die Hauptfunkitonalität der App, den eigentlichen Timer, liefert. Der Timer kann von hier aus gestartet werden und es läuft der eingestellte Countdown vor dem Start ab. 

![image](https://github.com/user-attachments/assets/2e9dbf04-058e-4619-8330-68ce2f52df6e)

Dabei werden insgesamt 3 unterschiedliche Phasen durchlaufen, die farblich unterschiedliche gekennzeichnet sind:

* Blau: Countdown vor dem Start des Intervall Timers
* Grün: Durchführung der Wiederholung
* Rot: Pause zwischen den Wiederholungen

Dadurch können die Nutzunden über einen kurzen Blick aufs Handy sofort erkennen in welcher Phase sie sich befinden, ohne das Handy in die Hand nehmen zu müssen und sich von ihrer Übung ablenken zu lassen.
Die Aktuelle Rundenzahl wird, sowie der laufende Timer werden stets auf der Oberfläche angezeigt.

![image](https://github.com/user-attachments/assets/36f13ebc-cb40-4417-9614-c25de49e9057)

![image](https://github.com/user-attachments/assets/1832c80b-43fc-4c3b-8ab0-35d90ace1118)

Nach dem Ende jeder Phase ertönt ein Ton, der den Nutzenden ankündigt, dass sich die Phase wechselt. Somit wird dieser Phasenwechsel den Nutzenden, sowohl über ein hörbares, als auch über ein visuelles Signal angekündigt. Dadurch wird versichert, dass die Nutzenden stets wissen, wann die Wiederholung endet und die Pause anfängt, sowie wann die Pause endet und eine neue Wiederholung endet. Zum Abschluss der kompletten Übung, nachdem alle Wiederholungen durchlaufen sind, ertönt ein anderer Ton, um den Abschluss der Übung von den Phasenwechsel zu unterschieden.

Der Timer kann zudem in jeder Phase pausiert werden. In dieser Pausen-Ansicht ist es möglich den Timer zurückzusetzen und somit von neu starten zu lassen.

![image](https://github.com/user-attachments/assets/c50c513f-5cfd-47aa-a3a9-33905f6ab2d9)

Die Timer Ansicht kann jederzeit über den "Back" Button des Android Geräts oder durch eine Swipe Geste nach rechts verlassen werden. Dadurch wird der Timer abgebrochen und die Nutzenden landen wieder auf der Übungsübersichtsseite.

### Funktionen

[Beschreiben Sie die Funktionen ihrer App. Beziehen Sie sich dabei auf den Anforderungskatalog und stellen Sie eine Verbindung zu den entsprechneden Files oder Directories her.]

### Demo

[Verlinken Sie zum Abschluss des Projekts ein kurzes Video, in dem Sie die wesentlichen Nutzungsszenarien Ihrer Anwendung demonstrieren.]

## Team

### Vorstellung

**Daniel Rindin**

**E-Mail:**  daniel.rindin@stud-mail.uni-wuerzburg.de

**GitHub:**  daniel1r  (Aus irgendeinem Grund werden die Commits als "Daniel Rindin", anstelle des GitHub Namens "daniel1r" angezeigt)


**Daniel Rataj**

**E-Mail:**  daniel.rataj@stud.th-deg.de

**GitHub:**  Daniel-Rataj


**Mehmet Tan**

**E-Mail:**  m.tan@hm.edu

**GitHub:**  Metanhm

### Zuständigkeiten

Die einzelnen Issues sind detailierter in den entsprechenden Issues in GitHub definiert.


**Daniel Rindin:**

Issue #1: Übungsübersicht erstellen

Issue #4: Exercise Model

Issue #5: ExerciseDialog(Fragment)

Issue #11: Initialisierung des Frontends 

Issue #14: Implementierung der Klingeltonwiedergabe (Abspielen von Sounds und Musik)


**Daniel Rataj:**

Issue #6: ExerciseRepository

Issue #7: ExerciseLogic 

Issue #8: ExerciseController 

Issue #9: DataAccess ExerciseModel

Issue #10: DataTransfer ExerciseModel

Issue #12: Initialisierung des Backends

Issue #13: Notification (Interaktiv)

Issue #15: Daten verschlüsselt versenden (HTTPS)


**Mehmet Tan:**

Issue #2: Übungsdetailseite (Intervall-Timer)

Issue #3: Service und API-Client

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
