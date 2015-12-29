# Java-Webchat

## Description

Java-Webchat was designed and created as the final project of ICS4U1, grade 12 computer science. It was designed as a small and portable chat application which also incorporated many skills that we accrued over the course of the year.

Over the course of this project, there were many choices as to the design of this program. This application uses RMI as the communication protocol between the server and the clients. In addition to this, user security is protected from database breaches as user credentials are encrypted using a salted SHA-256 hash. Finally, JavaFX was employed to render the UI as it gives a modern and very aesthetically pleasing design.

<img src="/DemoImages/chatting.png" alt="Image of Chat Interface" width=33% />
<img src="/DemoImages/login.png" alt="Image of Login Interface" width=33% />
<img src="/DemoImages/server.png" alt="Image of Server Interface" width=33% />

## Improvements

In order to improve upon this project, there would be a number of changes to be made. For example, RMI is a rather outdated and limiting communications protocol and as a result, should be replaced with something more standard such as SIP (Session Initiation Protocol).

In addition to this, this application does not use secure end-to-end tunneling to transmit the login credentials which could cause the credentials to be potentially eavesdropped upon. As a result, changing the application to utilise some secure tunneling protocol would improve upon security.

Finally, in order to check for any updates in the state of the chat, the project uses the `Runnable` class instead of the JavaFX compatible `Task` class. This creates the risk of desyncronization errors between the threads and should, as such, be updated.

Some more minor changes:

* More instructions in the UI.

* Add UI indicator of which user is currently logged in.

* Refactoring so that the project isn't over-abstracted.

* Including a logoff prompt.

## Contributors

* [Filip Francetic](https://github.com/Filip-F)

* [Denton Liu](https://github.com/Denton-L)

* [Jing Yi Xie](https://github.com/xiejxie)
