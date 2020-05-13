# Car Retriever
Ce TP est pour le groupe TURQUIN Théo et VINOT Paul M1TNSI.

## Sommaire
### Fonctionnement général
### Classes

### Fonctionnement général
Ce projet ne nécessite pas l'installation de dépendances particulières.
Nous arrivons sur une première activité "MainActivity" ou nous demanderons les informations de l'utilisateur: pseudo et mot de passe.

Cette activité gère le controle des différents champs entré par l'utilisateur et retourne différents messsages d'erreurs avec précision sur ces derniers.
Nous avons implémenté une base de donnée en dur via la classe UserBank, elle a été designé de telle manière a pouvoir être amélioré par la suite pour la relier à une base de donnée SQL même si à l'heure actuelle les données sont créé à la création de l'activité.
Une fois les identifiants de connexion ou l'enregistrement effectué, l'utilisateur sera alors redirigé sur MapActivity ou il verra alors sa position actuelle ainsi que la position de sa voiture si il en a une enregistré suite à une action antérieure de sa part.

La position de la voiture étant celle de l'utilisateur dans le cadre de notre projet. Nous avons décidé de rendre le marker utilisateur déplacable afin de pouvoir avoir une différenciation sur ces derniers.

### Classes
Nous avons une classe User ayant en attribut un (String) pseudo, (String) un mot de passe, (Boolean) hasCar, (Car) car
Les 2 premiers attributs permettent d'avoir les informations que nous demandons à l'utilisateur de rentrer, l'attribut hasCar permet de vérifier rapidement si l'utilisateur possède déjà une voiture enregistrer dans l'application ou non, et l'attribut car est un objet de type Car

Notre classe Car contient quant à elle les informations sur la latitude et la longitude de cette dernière.

Notre classe UserBank est la classe permettant d'avoir la liste d'utilisateurs de notre application.
