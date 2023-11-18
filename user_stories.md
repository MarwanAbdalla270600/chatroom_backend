
## User-Stories & Akzeptanzkriterien:

1. **Als Nutzer möchte ich, dasss ich mich mit meiner E-Mail-Adresse und Nutzernamen registrieren kann, damit ich als gültiger Nutzer im System aufgenommen werde und mich einloggen kann.**
- E-Mail wird verifiziert, durch Senden einer E-Mail mit Authentifizierungslink.
- Authentifizierungslink muss angeklickt werden, damit Registrierung erfolgreich ist.
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
- Die Länge einer Textnachricht wird mit ... spezifiziert.
- Das Abschicken einer Nachricht erfolgt über ein "Send" Button.
- Die geschickte Nachricht wird bei beiden Kommunikatoren im Chat-Verlauf angezeigt.

4. **Als Nutzer möchte ich, dass ich Gruppen-Chats erstellen kann, damit ich mit mehreren Nutzern gleichzeitig kommunizieren kann.**
- Jeder Nutzer kann einen Gruppen-Chat erstellen.
- Der Ersteller jeder Gruppe ist automatisch auch ein Admin der Gruppe.
- Ein Admin kann anderen Gruppenmitglieder als Admin zuweisen.
- Ein Admin kann einen Gruppen-Namen sowie Gruppen-Bild hinzufügen.
- Jeder Admin einer Gruppe kann andere Nutzer hinzufügen bzw. entfernen.

5. ...