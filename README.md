# TopicClassification_NLP
1. Comment fonctionne la classification des thèmes ? <br>
Dans la classification de l'apprentissage automatique, des exemples de texte et les catégories attendues (données de formation AKA) sont utilisés pour former un modèle de classification de thème NLP. Ce modèle apprend des données d'apprentissage (à l'aide du traitement du langage naturel) pour reconnaître des modèles et classer le texte dans les catégories que vous définissez.<br>
Premièrement, les données d'apprentissage doivent être transformées en quelque chose qu'une machine peut comprendre, c'est-à-dire des vecteurs (c'est-à-dire des listes de nombres qui encodent des informations). En utilisant des vecteurs, le modèle peut extraire des informations pertinentes (caractéristiques) qui l'aideront à apprendre des données d'apprentissage et à faire des prédictions. Il existe différentes méthodes pour y parvenir, 
mais l'une des plus utilisées est la vectorisation du sac de mots. En savoir plus sur la vectorisation de texte.<br>
Une fois les données d'apprentissage transformées en vecteurs, elles alimentent un algorithme qui les utilise pour produire un modèle capable de classer les textes à venir. <br>

2. Implémentation <br>
Voici les étapes que nous allons suivre pour faire le pré-traitement de texte :<br>
• Tokénisation : fractionnement d'un document en mots ou jetons individuels.<br>
• Supprimer les numéros<br>
• Supprimer les signes diacritiques<br>
• Supprimer les caractères remplacés<br>
• Supprimer les mots non arabes…<br>
• Supprimer les ponctuations<br>
• Supprimer les mots vides<br>
• Stemming : réduction des mots à leur forme de base, par exemple, conversion de "sauts" en "saut".<br>
• Lemmatisation : Réduction des mots à leur forme de base.<br>

3. BOW (Vectorisation)<br>
Le sac de mots est une technique de traitement du langage naturel de modélisation de texte. En termes techniques, on peut dire qu'il s'agit d'une méthode d'extraction de caractéristiques avec des données textuelles. <br>
Cette approche est un moyen simple et flexible d'extraire des caractéristiques de documents.<br>
Le modèle sac de mots (BOW) est une représentation qui transforme un texte arbitraire en vecteurs de longueur fixe en comptant le nombre de fois où chaque mot apparaît. Ce processus est souvent appelé vectorisation.<br>

4. Cosinus Similitude ?<br>
La similarité cosinus est une métrique utilisée pour mesurer la similarité de deux vecteurs. Plus précisément, il mesure la similitude dans la direction ou l'orientation des vecteurs en ignorant les différences dans leur amplitude ou leur échelle. Les deux vecteurs doivent faire partie du même espace de produit interne, ce qui signifie qu'ils doivent produire un scalaire par multiplication du produit interne. La similarité de deux vecteurs est mesurée par le cosinus de l'angle qui les sépare.<br>
La similarité cosinus est largement utilisée dans les applications de science des données et d'apprentissage automatique.
Les exemples incluent la mesure de la similarité de :<br>
• Documents en traitement automatique du langage naturel <br>
• Films, livres, vidéos ou utilisateurs dans les systèmes de recommandation <br>
• Images en vision par ordinateur w<br>
