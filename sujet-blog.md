# Blog Engine

L'objectif de cet exercice est de réaliser une plateforme de blog.

## Trucs administratifs

Le sujet est à réaliser entre le lundi 10 juillet à réception du projet et le mardi 11 juillet à 18:00. Par réalisé on entend développé *et* évalué par mes soins selon une grille de notation que je vous communiquerais peut-être. Ou pas. Selon mon humeur et votre avancement.

### Groupes

Le projet est à réaliser par groupes de deux ou trois étudiants, à votre convenance. Aucune "dérogation" ne sera délivrée. Non aucune. De toute façon je suis beaucoup trop cher pour vous.

La quantité de travail (fonctionnalités) attendue pour un rendu donné est bien sur fonction du nombre d'étudiants constituant le groupe. La qualité attendue est elle fixe.

### Bonus rapidité :

* Tout groupe évalué avant 14:00 aura 2 points bonus.
* Tout groupe évalué avant 16:00, 1 point bonus.
* Tout groupe non évalué à 18:00 mardi soir aura 0. Non négociable.

### Ressources et documents:

* Tous documents autorisés.
* Vos voisins sont des documents comme les autres.
* Leur travail en revanche n'est pas un document autorisé.

Je suis évidemment disponible pour répondre à vos questions, dans la limite des stocks disponibles.


### Technologies :

Votre projet doit utiliser JEE7 et maven. La connexion à la base de donnée doit s'effectuer par JPA. Le serveur d'application pour le déploiement est Wildfly 10.
Les seules dépendances autorisées par défaut sont lombok, mysql-connector (via wildfly) et primefaces. Pour tout autre besoin, me demander explicitement avant toute utilisation.

Les technologies doivent être intégrées selon les méthodes et principe vues en cours/TD.

## Sujet

Vous devez réaliser un moteur de blog présentant les fonctionnalités suivantes:

* Création / Archivage d'un blog.
* Création / Archivage / Edition d'un billet.
* Création / Modération d'un commentaire.
* Page billet présentant le billet en question et ses commentaires associés.
* Gestion d'utilisateurs, et éventuels droits associés.
* Mise en place d'un thème graphique (les gouts et les couleurs de chacun ne seront pas discutés, la qualité de l'intégration du thème potentiellement).

