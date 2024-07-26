# 🎓 Résoudre les Dépendances de Cours avec l'Algorithme de Kahn : Une Introduction Ludique ! 🎓

Salut la communauté ! Aujourd'hui, je veux partager avec vous une solution amusante pour gérer les dépendances de cours, grâce à l'algorithme de Kahn. 🎉 Cette solution est en lien avec le problème LeetCode 207 dont voici le lien : [Lien LeetCode](https://leetcode.com/problems/course-schedule/description/)

## Intuition

L'intuition derrière la solution repose sur la détection d'une possible dépendance cyclique entre les cours, ce qui rendrait impossible de tous les compléter. Imaginez que pour suivre le cours X, vous devez avoir terminé le cours Y, mais le cours Y nécessite également d'avoir terminé le cours X. Un vrai casse-tête, n'est-ce pas ? 🤯

Pour résoudre ce problème, nous utilisons une méthode appelée **tri topologique**, qui est comme organiser vos livres préférés en ordre chronologique pour une meilleure compréhension. 📚🕰️

## Décomposition de l'Intuition

1. **Construction du graphe :** Imaginez que chaque cours est un nœud et chaque prérequis est une flèche pointant d'un cours à un autre. 🎯
   
2. **Comptage des arêtes entrantes :** C'est comme compter combien de livres doivent être lus avant de comprendre un livre spécifique. Si un livre (ou un cours) n'a pas de prérequis, vous pouvez le lire tout de suite ! 📖

3. **Sélection et suppression des nœuds :** Commencez par les livres sans prérequis, lisez-les (ou suivez le cours), puis enlevez-les de votre liste. Continuez ainsi jusqu'à ce que vous ayez lu (ou suivi) tous les livres (ou cours). 📚✅

## Approche Technique en Java

### Représentation du Graphe

```java
List<List<Integer>> graphe = new ArrayList<>();
for (int i = 0; i < nombreDeCours; i++) {
    graphe.add(new ArrayList<>());
}
```

### Construction du Graphe et du Tableau des Degrés Entrants

```java
int[] degresEntrants = new int[nombreDeCours];
for (int[] prerequi : prerequis) {
    int cours = prerequi[0];
    int coursPrerequi = prerequi[1];
    graphe.get(coursPrerequi).add(cours);
    degresEntrants[cours]++;
}
```

### Traitement des Nœuds avec Zéro Degrés Entrants

```java
Deque<Integer> file = new ArrayDeque<>();
for (int i = 0; i < nombreDeCours; i++) {
    if (degresEntrants[i] == 0) {
        file.offer(i);
    }
}
```

### Tri Topologique

```java
int coursTraites = 0;
while (!file.isEmpty()) {
    int cours = file.poll();
    coursTraites++;
    for (int voisin : graphe.get(cours)) {
        degresEntrants[voisin]--;
        if (degresEntrants[voisin] == 0) {
            file.offer(voisin);
        }
    }
}
return coursTraites == nombreDeCours;
```


## Pourquoi est-ce important ?

Parce que cela garantit que vous pouvez suivre tous les cours sans être coincé dans une boucle infinie de prérequis impossibles à satisfaire. 🎓🚀

Et voilà ! 🎉 Vous avez maintenant une méthode efficace et amusante pour gérer vos dépendances de cours. **Curieux d'en savoir plus ? Découvrez mes projets et mon expertise en visitant mon portfolio personnel : [ismaelmohamedbouh.com](https://www.ismaelmohamedbouh.com/)** 🌐

J'espère que cela répond à tes attentes ! Si tu as besoin de modifications ou d'ajouts, fais-le moi savoir.