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
#### Daten verschlüsselt versenden (HTTPS, SSL)
Dieses Feature stellt sicher, dass die Kommunikation zwischen der Android-App und dem Backend-Server über eine sichere HTTPS-Verbindung erfolgt. Es verwendet SSL/TLS-Zertifikate zur Verschlüsselung der Datenübertragung. Hier wird erklärt, wie dieses Feature implementiert ist und welche Dateien und Konfigurationen dabei beteiligt sind.

##### 1. Backend-Konfiguration
###### 1.1 Keystore erstellen
* Für die HTTPS-Kommunikation wird im Backend (Spring Boot) ein SSL-Zertifikat benötigt. Der Keystore `keystore.p12` wird im Backend-Projekt im folgenden Pfad abgelegt: `app/IntervallTimerBackend/src/main/resources/keystore.p12`

###### 1.2 Konfiguration in `application.properties`
* In der `application.properties`-Datei des Backend-Projekts sind die HTTPS-Konfigurationen definiert. Diese Konfigurationen legen fest, dass der Server HTTPS auf Port 8443 verwendet und den Keystore für die SSL-Verschlüsselung verwendet.

###### 1.3 Sicherheitskonfiguration in `SecurityConfig.java`
* In der `SecurityConfig`-Klasse wird konfiguriert, dass alle eingehenden Anfragen über HTTPS erfolgen müssen. Diese Klasse stellt sicher, dass die Sicherheitsanforderungen für HTTPS in der Spring Boot-Anwendung durchgesetzt werden.

##### 2. Frontend-Konfiguration
###### 2.1 Zertifikat für HTTPS
* Im Frontend-Projekt wurde aus dem Keystore `keystore.p12` ein Zertifikat generiert und im folgenden Pfad abgelegt: `app/IntervallTimerFrontend/app/src/main/res/raw/certificate.cert`
* Dieses Zertifikat wird verwendet, um eine vertrauenswürdige HTTPS-Verbindung in der Android-App aufzubauen.

###### 2.2 Flag für HTTPS in `MainActivity.java`
* In der `MainActivity.java` wurde ein Flag `httpsActivated` hinzugefügt, das steuert, ob die App HTTP oder HTTPS verwendet. Je nach Wert dieses Flags wird beim Zugriff auf den `ExerciseService` entweder eine HTTP- oder HTTPS-Verbindung verwendet.

###### 2.3 API-Client in `ApiClient.java`
* Die `ApiClient`-Klasse ist verantwortlich für die Konfiguration der Netzwerkverbindungen. Sie bietet Methoden sowohl für HTTP- als auch für HTTPS-Verbindungen:
* HTTP-Client: Wird über die Methode `getClient()` konfiguriert und verwendet grundlegende Authentifizierung sowie optionales Logging.
* HTTPS-Client: Die Methode `getClientHttps(Context context)` konfiguriert einen HTTPS-Client, der das Zertifikat aus den Ressourcen verwendet. Diese Konfiguration umfasst das Laden des Zertifikats, das Erstellen eines `KeyStore`, das Einrichten eines `TrustManager` und eines `SSLContext` für sichere Verbindungen.
* Die Methoden `getExerciseServiceHttps(Context context)` und `getHttpsClient(Context context)` ermöglichen die Verwendung von HTTPS

#### Wiederverwendbare Layoutbausteine: Fragments
Dieses Feature nutzt Fragments als wiederverwendbare Layoutbausteine, um verschiedene UI-Komponenten modular und flexibel zu gestalten. In der `MainActivity` wird das `MainFragment` eingebunden, das eine eigenständige UI-Komponente darstellt und unabhängig von anderen Teilen der App verwendet werden kann. Hier wird erklärt, wie dieses Feature implementiert ist und welche Dateien beteiligt sind.

###### 1. `MainFragment.java`:
* Aufbau der UI: Das `MainFragment` ist ein Fragment, das die Benutzeroberfläche für die Anzeige der Übungen in einer `RecyclerView` verwaltet. Es nutzt Data Binding (`FragmentMainBinding`), um die UI-Elemente mit den entsprechenden Layout-Resourcen zu verbinden.
* Lifecycle-Management: Das Fragment implementiert die `onCreateView`- und `onDestroyView`-Methoden, um die Benutzeroberfläche zu erstellen und aufzuräumen, wenn das Fragment angezeigt bzw. entfernt wird.
###### 2. `fragment_main.xml`:
Layout-Struktur: Das Layout des Fragments besteht aus einer vertikalen `LinearLayout`, die eine `RecyclerView` enthält. Diese `RecyclerView` wird zur Anzeige der Übungen verwendet.

#### Einfacher Adapter
Dieses Feature ermöglicht es der App, Daten effizient in einer Liste anzuzeigen. Der `ExerciseAdapter` wird verwendet, um die Daten der Übungen in einer `RecyclerView` darzustellen. Hier wird erklärt, wie dieses Feature implementiert ist und welche Dateien beteiligt sind.

###### 1. Die `ExerciseAdapter.java`-Klasse verwaltet die Darstellung der Übungsdaten in der `RecyclerView`. Ihre Hauptaufgaben sind:
* Bereitstellung von Layout-Elementen: Der `ExerciseAdapter` erstellt und bindet die Layout-Elemente für jede einzelne Übung in der Liste. In der Methode `onCreateViewHolder` wird das Layout für jedes Listen-Element bereitgestellt, indem ein neues `ExerciseAdapterVH`-Objekt (ViewHolder) erstellt wird. Dieses Objekt dient als Container für die UI-Elemente.
* Datenbindung: Die Methode `onBindViewHolder` wird verwendet, um die Übungsdaten an die einzelnen Layout-Elemente zu binden. Hier wird der Name der Übung (`exerciseName`) an das entsprechende `TextView` gebunden, sodass die Übungsinformationen korrekt angezeigt werden.
* Klickereignisse: Der `ExerciseAdapter` implementiert die Schnittstelle `ClickedItem`, um Klickereignisse auf einzelne Listen-Elemente zu handhaben. Diese Ereignisse werden in der `MainActivity` verarbeitet, um die ausgewählte Übung zu öffnen oder zu bearbeiten. Dies geschieht durch die Methoden `ClickedExercise` für das Anzeigen der Details, `deleteExercise` für das Löschen und `openEditDialog` für das Bearbeiten von Übungen.
* Verwaltung von Interaktionen: Der `ExerciseAdapter` ermöglicht die Bearbeitung und das Löschen von Übungen direkt aus der Liste heraus. Die entsprechenden Buttons (`deleteExercise` und `editExercise`) sind in der `ExerciseAdapterVH`-Klasse definiert und mit den entsprechenden Methoden in der `MainActivity` verbunden.
###### 2. Die `MainActivity.java` integriert den `ExerciseAdapter` in die `RecyclerView`. Ihre Hauptaufgaben sind:
* Initialisierung des Adapters: In der `MainActivity` wird eine Instanz des `ExerciseAdapter` erstellt und der `RecyclerView` zugewiesen. Dies erfolgt in der Methode `onCreate`, wo der Adapter mit der `RecyclerView` verbunden wird, um die Liste der Übungen darzustellen.
* Datenbereitstellung: Die Methode `getAllExercises` in der `MainActivity` ruft die Übungsdaten vom Server ab und übergibt sie an den `ExerciseAdapter`. Dieser stellt die Daten dann in der `RecyclerView` dar und sorgt für eine aktuelle Anzeige, sobald neue Übungen hinzugefügt, bearbeitet oder gelöscht wurden.
###### 3. Das Layout für ein einzelnes Listen-Element (`row_exercise.xml`) definiert das Aussehen der einzelnen Übungen in der Liste. Ihre Hauptaufgaben sind:
* Strukturierung der UI-Elemente: Das Layout verwendet ein `CardView`-Element, um jede Übung in der Liste in einer ansprechenden Karte darzustellen.
* Anzeige der Übungsdaten: Das `TextView`-Element mit der ID `exerciseName` zeigt den Namen der Übung an. Die Buttons `deleteExercise` und `editExercise` sind für das Löschen und Bearbeiten der jeweiligen Übung zuständig. Diese Buttons verwenden Icons (`ic_delete` und `ic_edit`) als Hintergrund, um ihre Funktionalität visuell zu verdeutlichen.
* Interaktive Elemente: Die Buttons `deleteExercise` und `editExercise` sind interaktiv und rufen die entsprechenden Methoden in der `MainActivity` auf, wenn sie gedrückt werden.

#### Intents mit Payload
Dieses Feature ermöglicht es der App, beim Starten einer neuen Aktivität Daten an diese zu übergeben. In der `MainActivity` werden Übungen ausgewählt und deren Details an die `ExerciseDetailsActivity` übergeben, um dort weitere Informationen anzuzeigen. Hier wird erklärt, wie dieses Feature implementiert ist und welche Dateien beteiligt sind.

###### 1. Die `MainActivity.java` verwaltet die Auswahl der Übung und das Senden der Übungsdaten an die `ExerciseDetailsActivity`. Ihre Hauptaufgaben sind:
* Übungsauswahl: Wenn der Benutzer eine Übung aus der Liste auswählt, wird die Methode `ClickedExercise` aufgerufen. Diese Methode erstellt eine neue `Intent`-Instanz, um die `ExerciseDetailsActivity` zu starten.
* Datenübergabe: In der `ClickedExercise`-Methode werden die Übungsdaten als Extra-Daten (`putExtra`) zum `Intent` hinzugefügt. Die Daten werden mithilfe eines Schlüssels (`exerciseDetailKey`) übertragen, der in den Ressourcen definiert ist.
* Aktivitätsstart: Nach dem Hinzufügen der Extra-Daten wird die `ExerciseDetailsActivity` mit der Methode `startActivity` gestartet.
###### 2. Die `ExerciseDetailsActivity` empfängt die übergebenen Übungsdaten und zeigt sie an. Ihre Hauptaufgaben sind:
* Empfang der Daten: In der `onCreate`-Methode der `ExerciseDetailsActivity` wird der empfangene `Intent` abgefragt, um die übergebenen Übungsdaten zu extrahieren.
* Anzeige der Daten: Die extrahierten Übungsdetails werden verwendet, um die entsprechenden UI-Elemente in der `ExerciseDetailsActivity` zu aktualisieren und die Informationen anzuzeigen.

#### Notification interaktiv
Dieses Feature ermöglicht es der App, eine interaktive Benachrichtigung anzuzeigen, wenn der Intervall-Timer in der `ExerciseDetailsActivity` abgelaufen ist. Die Benachrichtigung enthält eine Schaltfläche, um den Timer direkt neu zu starten. Hier wird erklärt, wie dieses Feature implementiert ist und welche Dateien beteiligt sind.

###### 1. Die `ExerciseDetailsActivity.java` verwaltet den Intervall-Timer und zeigt eine Benachrichtigung an, wenn der Timer abgelaufen ist. Ihre Hauptaufgaben sind:
* BroadcastReceiver für den Timer-Reset: Ein `BroadcastReceiver` wird definiert, um auf das Zurücksetzen des Timers zu reagieren. Wenn der Benutzer auf die Schaltfläche "Neu starten" in der Benachrichtigung klickt, wird dieser Receiver aktiviert, um den Timer zurückzusetzen und neu zu starten.
* Erstellen des `NotificationChannels`: Beim Erstellen der `ExerciseDetailsActivity` wird ein `NotificationChannel` erstellt, um sicherzustellen, dass Benachrichtigungen auf Geräten mit Android Oreo (API 26) und höher korrekt angezeigt werden.
* Timer-Start: Wenn der Timer abläuft, wird die Methode zur Anzeige der Benachrichtigung aufgerufen, um den Benutzer zu informieren und eine Aktion zu ermöglichen.

###### 2. Die `NotificationHelper`-Klasse verwaltet die Erstellung und Anzeige der Benachrichtigung. Ihre Hauptaufgaben sind:
* Benachrichtigung anzeigen: Diese Methode erstellt die Benachrichtigung und fügt eine "Neu starten"-Schaltfläche hinzu. Wenn der Benutzer diese Schaltfläche klickt, wird eine Broadcast-Nachricht gesendet, um den Timer zurückzusetzen.
* Erstellen des `NotificationChannels`: Diese Methode stellt sicher, dass der `NotificationChannel` auf Geräten mit Android Oreo und höher eingerichtet ist, um Benachrichtigungen korrekt anzuzeigen.

###### 3. Die `ResetReceiver`-Klasse ist ein `BroadcastReceiver`, der eine Broadcast-Nachricht sendet, wenn er eine Absicht zum Zurücksetzen des Timers empfängt. Ihre Hauptaufgaben sind:
* Empfangsnachricht verarbeiten und eine lokale Broadcast-Nachricht versenden, die dann von der `ExerciseDetailsActivity` verarbeitet wird, um den Timer zurückzusetzen und neu zu starten.

##### Selbsterstellte Custom Views
Dieses Feature ermöglicht es der App, einen benutzerdefinierten Dialog zum Erstellen und Bearbeiten von Intervall-Timern anzuzeigen. Der Dialog bietet eine benutzerfreundliche Oberfläche zur Eingabe und Validierung von Übungsdetails. Hier wird erklärt, wie dieses Feature implementiert ist und welche Dateien beteiligt sind.

###### 1. Die `ExerciseDialog.java`-Klasse implementiert den benutzerdefinierten Dialog. Ihre Hauptaufgaben sind:
* Dialog-Erstellung und Layout: Der Dialog wird mit der `onCreateDialog`-Methode erstellt, die das Layout (`layout_dialog.xml`) aufbaut und die Eingabefelder initialisiert.
* Eingabedaten verarbeiten: Die Klasse enthält Methoden zum Speichern und Bearbeiten von Übungsdetails. Im Erstellungsmodus werden neue Daten erfasst, im Bearbeitungsmodus werden bestehende Daten aktualisiert.
* Validierung: Die Eingaben werden auf ihre Korrektheit überprüft, um sicherzustellen, dass keine ungültigen Daten gespeichert werden.
###### 2. Die `layout_dialog.xml`-Datei definiert das Layout des Dialogs. Ihre Hauptaufgaben sind:
* Eingabefelder: Die Datei enthält mehrere `TextInputLayout` und `TextInputEditText` Felder für die Eingabe von Übungsbezeichnung, Wiederholungen, Ausführungszeit, Pausenzeit und Countdown.
* Schaltflächen: Zwei Schaltflächen zum Speichern oder Abbrechen der Eingaben sind vorhanden, die entsprechende Aktionen im Dialog auslösen.
###### 3. Die `MainActivity.java` verwaltet die Interaktionen mit dem Dialog. Ihre Hauptaufgaben sind:
* Dialog öffnen: Der Dialog wird entweder zur Erstellung eines neuen Intervall-Timers oder zur Bearbeitung eines bestehenden Timers aufgerufen.
* Dialog-Listener: Die `MainActivity` implementiert den `CreateExerciseDialogListener`, um die vom Dialog zurückgegebenen Daten zu verarbeiten und entsprechende Aktionen (z.B. Speichern) durchzuführen.

##### Abspielen von Sounds und Musik
Das Feature Abspielen von Sounds und Musik wurde in der `ExerciseDetailsActivity` implementiert, um akustische Signale während der Durchführung von Übungen zu integrieren. Diese Signale helfen dem Benutzer, den Übergang zwischen verschiedenen Phasen des Trainings wie Start-Countdown, Übung und Pause zu erkennen.

###### 1. Einbindung des `MediaPlayer`:
* Der Sound wird beim Ablauf des Countdowns oder beim Übergang in eine neue Phase abgespielt. Wenn der Timer endet (`onFinish()`), wird der Sound abgespielt, um das Ende der aktuellen Runde oder Pause akustisch zu signalisieren.
* Je nach Trainingssituation (z.B. letzte Runde) wird ein entsprechender Sound ausgewählt (`ring_3` für die letzte Runde und `ring_1_1` für reguläre Runden).
###### 2. Lifecycle-Management:
* Der `MediaPlayer` wird in der `onDestroy()`-Methode gestoppt und freigegeben, um Speicherlecks und unerwünschtes Abspielen von Sounds nach dem Schließen der Aktivität zu vermeiden.

##### Tabbing (Swipe to Change Screen)
Dieses Feature ermöglicht es der App, zwischen verschiedenen Bildschirmen durch Wischgesten zu navigieren. Ein Wischen nach rechts führt zurück zur vorherigen Seite, was eine flüssige und benutzerfreundliche Navigation ermöglicht. Hier wird erklärt, wie dieses Feature implementiert ist und welche Dateien beteiligt sind.

###### 1. Die `ExerciseDetailsActivity.java` Klasse implementiert die Wischgesten-Navigation. Ihre Hauptaufgaben sind:
* Gestenerkennung: Die Klasse verwendet einen `GestureDetector`, um Wischgesten auf dem Bildschirm zu überwachen. Hierbei wird die `SwipeGestureListener`-Klasse zur Erkennung von Wischbewegungen genutzt. 
* Reaktion auf Gesten: Bei einer erkannten Wischbewegung nach rechts wird die Methode `onSwipeRight()` aufgerufen. Diese Methode stoppt den Timer und löst die Navigation zur vorherigen Ansicht aus. 
* Interaktion: Die `onSwipeRight()`-Methode sorgt dafür, dass die `onBackPressed()`-Methode aufgerufen wird, um zur vorherigen Ansicht zurückzukehren.

###### 2. Die `SwipeGestureListener` Klasse verwaltet die Gestenerkennung. Ihre Hauptaufgaben sind:
* Wischgeste erkennen: Die Klasse erweitert `GestureDetector.SimpleOnGestureListener` und überschreibt die Methode `onFling()`, um Wischgesten zu erkennen. 
* Wischbewegungen verarbeiten: Die `onFling()`-Methode prüft die Wischrichtung und -geschwindigkeit, um zu bestimmen, ob es sich um eine gültige Wischgeste handelt und welche Aktion ausgeführt werden soll.


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
