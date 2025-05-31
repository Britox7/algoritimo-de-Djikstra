# **<center>Algoritmo de Caminho de Custo Mínimo de Dijkstra</center>**

### **_O que é o Algoritmo de Dijkstra?_**

- O algoritmo de Dijkstra é um algoritmo de caminho mínimo usado em grafos. Ele é usado para encontrar o caminho mais curto entre dois vértices em um grafo ponderado, onde os pesos são associados às arestas.

### **_Como funciona?_**

- O algoritmo de Dijkstra funciona através da construção de uma árvore de caminho mínimo. Ele começa com um vértice inicial e, em seguida, explora todos os seus vizinhos, atualizando o custo para alcançar cada um deles. Em seguida, o algoritmo seleciona o vértice com o custo mais baixo e repete o processo. Dessa forma, ele constrói uma árvore de caminho mínimo que representa o caminho mais curto para cada vértice no grafo em relação ao vértice inicial.

---

### **_Exemplo:_**

#### **Suponha que temos um grafo ponderado com os seguintes vértices e arestas:**
- **Vértices:** A, B, C, D, E
- **Arestas:** (A,B,3), (A,C,2), (B,C,1), (B,D,5), (C,D,3), (C,E,6), (D,E,4)

#### **Passo a passo:**
1. Começamos com o vértice **A**, cujo custo é **0**.
2. Exploramos os vizinhos de **A** (**B** e **C**) e atualizamos seus custos:
    - Custo para chegar a **B**: **3** (A → B)
    - Custo para chegar a **C**: **2** (A → C)
3. Selecionamos o vértice com o menor custo (**C**) e exploramos seus vizinhos (**D** e **E**).
    - Custo para chegar a **D**: **5** (A → C → D)
    - Custo para chegar a **E**: **8** (A → C → E)
4. Selecionamos o próximo vértice com menor custo (**B**) e exploramos seu vizinho **D**.
    - Custo alternativo para **D**: **8** (A → B → D) → **Mantemos o menor (5)**.
5. Selecionamos **D** e exploramos seu vizinho **E**.
    - Custo para chegar a **E**: **9** (A → C → D → E)
6. O caminho mais curto de **A** para **E** é **A → C → D → E** (custo = **9**).

---

### **_Onde é aplicado?_**

- **Navegação:** Calculando a rota mais curta entre dois pontos em um mapa, como em GPS ou aplicativos de navegação.
- **Roteamento de redes:** Encontrando o caminho mais eficiente em redes de computadores.
- **Logística e transporte:** Otimizando rotas de entrega e transporte público.
- **Robótica e automação:** Planejamento de movimentos eficientes em ambientes industriais.
- **Jogos e IA:** Usado em algoritmos de pathfinding para personagens e NPCs.  

---

### DETALHES ESPECÍFICOS COMO FUNÇÕES FEITAS E IMPLEMENTAÇÃO DAS MESMAS ESTÃO DOCUMENTADOS NO CÓDIGO