# Proiect Java - Discord bot 

Bot pentru Discord care sa ofere raspunsuri la intrebari legate de grafuri/algoritmi, mesaje preluate prin RSS(utilizand ROME).

## Functionalitati

Functia **help** afiseaza functionalitatile robotului.

```bash
!help
```
Functia **rss** afiseaza titlul si descrierea unui rss feed dat ca argument printr-un link.

```bash
rss:<url>
```
Functia **!question** va afisa definitiile urmatoarelor notiuni: isomorphic, digraph, k-clique, bipartite graph, planar graph, connected graph, connected component, tree, bridge, Eurelian, Hamiltonian. 

```bash
!<question>
```
Functia **dfs** afiseaza parcurgerea dfs a unui graf dat ca parametru.

```bash
dfs: <number of nodes> <starting node> <edges>
```
Functia **bfs** afiseaza parcurgerea dfs a unui graf dat ca parametru.

```bash
bfs: <number of nodes> <starting node> <edges>
```
Functia **connected** afiseaza daca un graf dat ca parametru este conex sau nu.

```bash
connected: <number of nodes> <edges>
```
Functia **draw** returneaza o imagine **.png** reprezentand graful desenat.

```bash
draw: <number of nodes> <edges>
```

## Notiuni folosite pentru implementarea codului

```java
import 
```

## Autori
Leagan Dan-Adrian
Tablan Andrei-Razvan
