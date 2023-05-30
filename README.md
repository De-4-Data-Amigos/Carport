# default-web-applikation med page controller

## carport-kode på 2. semester sommer 2023

Dette repository er lavet i forbindelse med 2. semester på
Datamatikeruddannelsen i Lyngby, sommer 2023.

## Java version
I pom.xml er diverse dependencies valgt, så projektet kan bygges og køres i Java 19. Nyere versioner af 
Java vil formentlig også fungere, men vi har kun testet med version 19.

## Tomcat
Brug version 9.x

## Deployment på Droplet
T.B.D.

## Du skal gøre følgende for at få webapplikationen i luften:

1. Først skal du clone projektet eller downloade en zip-fil med projektet til din arbejdsstation.
   1. Clone: `https://github.com/De-4-Data-Amigos/Carport`
   2. Slet .git folderen, så du kan gøre projektet til dit eget git-projekt
      1. > `rm -rf .git/`
   3. Opret dit eget git repository, hvis du ville have et repo:
      1. > `git init`
2. Åbn Workbench og kør sql-filen `carport.sql`, som ligger i mappen `resources`. Den opretter en database for projektet. 
3. Du skal evt. ændre kodeord til databasen i projektet. Det gøres i filerne: `/persistence/ConnectionPool` i linje 14 og 15. Du skal også ændre i alle testene.
4. Til sidst skal du lave en Tomcat konfiguration. Dvs, 
   1. klik på "Add Configuration ..."
   2. Klik på "+" og vælg "Tomcat Server Local".
   3. Klik på "Fix knappen"
   4. Vælg war-exploded som deployment type
   5. Nu kan du klikke på den grønne play-knap for at bygge og køre projektet.

## Bemærkninger

### Koden indeholder følgende:

- Strukturering i passende packages for overblik (MVC). Noget af strukturen er også givet af Maven, og kan ikke laves om. F.eks. opdelingen i `/java` og `/webapp`.
- Javaservlets
- JSP sider. 
- En super skrabet css-fil til styling
- Datamapper for user-tabellen, som anvender en connection pool. Alle mappers er package-protected
- En facadeklasser, som `UserFacade`, der bruges til at tilgå mappermetoderne.
- Fejlhåndtering med exceptions for databaseoperationer. Den skriver også til Tomcat log.
- Integrationstest af datamapperen for User og andre.

### Funktionelt kan applikationen:

- Vise en forside med links til undersider, som endnu ikke er lavet
- Logge en user på. Der findes to brugere i databasen.
    1. `admin@mail.com` med password: `1234` (rolle: `admin`)
- Hvis man indtaster ugyldige data under indlogging, bliver man sendt til en en fejlside.
- Logge en bruger af

## MVC arkitektur

![](documentation/mvc.jpg)
