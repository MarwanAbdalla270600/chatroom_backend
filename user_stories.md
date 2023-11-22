
## User-Stories & Akzeptanzkriterien:

1. **Als Nutzer möchte ich, dasss ich mich mit Nutzernamen & Password registrieren kann, damit ich als gültiger Nutzer im System aufgenommen werde und mich einloggen kann.**
- Nutzername muss aus ASCII-Zeichen bestehen (Buchstaben, Zahlen erlaubt)
- Passwort muss mindestens aus 8 Zeichen bestehen und Zahlen und Buchstaben enthalten
- Nutzername darf nicht schon im System vorhanden sein
- Beim Einloggen muss Nutzername & Password mit Daten bei der Registrierung übereinstimmen

2. **Als Nutzer möchte ich, dass in meinem Profil mein Nutzername und ein Profilbild hochgeladen werden kann, damit andere Nutzer mich leicht identifizieren können.**
- Nutzername ist anfangs von Registrierung übernommen.
- Nutzername kann später geändert werden.
- Nutzername steht bei jedem Chat-Verlauf ganz oben.
- Profilbild darf Dateigröße von 2MB nicht überschreiten.
- Profilbild muss .jpeg oder .png Format sein.
- Profilbild wird zusammen mit Nutzername in jedem Chat verkleinert angezeigt.

3. **Als Nutzer möchte ich Nachrichten an andere Nutzer schicken können, damit ich mit ihnen kommunizieren kann.**
- ASCII-Code Textnachrichten sind möglich zu schicken/empfangen.
- Die Länge einer Textnachricht wird noch spezifiziert.
- Das Abschicken einer Nachricht erfolgt über ein "Send" Button.
- Die geschickte Nachricht wird bei beiden Kommunikatoren im Chat-Verlauf angezeigt.

4. **Als Nutzer möchte ich, dass ich Gruppen-Chats erstellen kann, damit ich mit mehreren Nutzern gleichzeitig kommunizieren kann.**
- Jeder Nutzer kann einen Gruppen-Chat erstellen.
- Der Ersteller jeder Gruppe ist automatisch auch ein Admin der Gruppe.
- Ein Admin kann anderen Gruppenmitglieder als Admin zuweisen.
- Ein Admin kann einen Gruppen-Namen sowie Gruppen-Bild hinzufügen.
- Jeder Admin einer Gruppe kann andere Nutzer hinzufügen bzw. entfernen.


#### //Extra-Features (Implementierung nicht sicher)
5. **Als Nutzer möchte ich, dass ich eine Benachrichtigung bekomme wenn ein anderer Nutzer mir eine Nachricht schickt und es ungelesene Nachrichten in einem Chat gibt, damit ich zeitnah eine Antwort schicken kann.**
- Wenn neue Nachricht in einem nicht aktiven Chat-Fenster erfolgt, wird eine visuelle Benachrichtigung angezeigt.
- Wenn neue Nachricht in einem aktiven Chat-Fenster erfolgt, wird diese direkt im Chat-Verlauf angezeigt.
- Chat-Fenster und/oder Nutzername wird fabrlich markiert, wenn es ungelesene Nachrichten gibt.
- Wenn Nutzer gerade nicht aktiv die App geöffnet hat, wird eine Benachrichtigung auf dem Desktop erfolgen.

6. **Als Nutzer möchte ich, dass in meiner App alle Nutzer angezeigt werden, die gerade Online sind, damit ich mit aktiven Nutzern direkt kommunizieren kann.**
- Andere Nutzer die gerade online sind, werden in der App in der "Friendlist" angezeigt.
- Ein kleiner grüner Punkt zeigt "online" Nutzer an.
- Ein kleiner roter Punkt zeigt "offline" Nutzer an.