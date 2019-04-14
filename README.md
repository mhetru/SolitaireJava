Solitaire : french game in JAVA
---

What else's fun to learn programmation oriented objects with game?

It was a project at my studies in university at 2002.

Pre-requisite :
- JDK (OpenJDK or Oracle JDK)

---

* Text version of the game :

1. to compile the game :
```
cd sources
javac -encoding ISO-8859-15 solitaire/Prog.java -d ../classes
```

2. to launch the game :
```
cd classes
java solitaire/Prog
```

Commands to play :
- to draw a card : type R
- to move a card from draw to another heap : type P character followed the name with the target head, example : PC1
- to move a card from a heap to another heap : type the name of the heap followed the target head, example : C1C8
- to move a card from a heap to a stake : the the name of the heap followed the target stake, example : C2P1

---

* Graphic version of the game :

1. to compile the game :
```
cd sources
javac -encoding ISO-8859-15 solitaire/Proggraph.java -d ../classes
```

2. to launch the game :
```
cd classes
java solitaire/Proggraph
```

* To create the documentation :
```
cd sources
javadoc -encoding ISO-8859-15 -version -author *.java -package solitaire solitaire.carte solitaire.interf solitaire.util -d ../docs
```

* Browse the documentation :

See the index.html file in docs folder.
