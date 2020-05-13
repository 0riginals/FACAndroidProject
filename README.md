# Car Retriever
Ce TP est pour le groupe TURQUIN Théo et VINOT Paul M1TNSI.

## Sommaire
### Fonctionnement général
### Classes
### Ce qu'il reste a developper

### Fonctionnement général
Ce projet ne nécessite pas l'installation de dépendances particulières.

Lors du lancement de notre application, nous arrivons sur une première activité "MainActivity" ou nous demandons les informations de l'utilisateur: pseudo et mot de passe.

Cette activité gère le controle des différents champs entré par l'utilisateur et retourne différents messsages d'erreurs avec précision sur ces derniers.

Nous avons implémenté une base de donnée en dur via la classe UserBank, elle a été conçu de telle manière a pouvoir être amélioré par la suite afin de la relier à une base de donnée SQL même si à l'heure actuelle les données sont créé lors de la création de l'activité.

Une fois les identifiants de connexion ou l'enregistrement effectué, l'utilisateur sera alors redirigé sur MapActivity ou il verra alors sa position actuelle via un marker bleu ainsi que la position de sa voiture via un marker orange si il en a une enregistré suite à une action antérieure de sa part.

La position de la voiture étant celle de l'utilisateur lors de l'ajout d'une position. Nous avons décidé de rendre le marker utilisateur déplacable afin de pouvoir avoir une différenciation sur ces derniers et de préparer l'itinéraire de cette manière. 

### Classes
Nous avons une classe User ayant en attribut un (String) pseudo, (String) un mot de passe, (Boolean) hasCar, (Car) car
Les 2 premiers attributs permettent d'avoir les informations que nous demandons à l'utilisateur de rentrer, l'attribut hasCar permet de vérifier rapidement si l'utilisateur possède déjà une voiture enregistrer dans l'application ou non, et l'attribut car est un objet de type Car

Notre classe Car contient quant à elle les informations sur la latitude et la longitude de cette dernière.

Notre classe UserBank est la classe permettant d'avoir la liste d'utilisateurs de notre application.

### Ce qu'il reste a developper
Afin d'avoir 99.9% des fonctionnalités prévues par notre application ne devrons:
- Mettre en place la base de donnée SQL
- Mettre un hash sur notre mot de passe 
- Faire l'itinéraire avec l'affichage du temps estimé ainsi qu'une mise a jour des markers lors de l'approche de l'utilisateur sur la voiture.
- Possibilité d'activer ou non une suppression du marker lorsque l'utilisateur arrive auprès de sa voiture.
