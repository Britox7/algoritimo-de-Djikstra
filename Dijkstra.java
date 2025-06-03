import java.security.InvalidAlgorithmParameterException;
import java.util.*;

public class Dijkstra {

    /**
     * Matriz de adjacência que armazena os pesos das arestas do grafo.
     * - vertices[i][j] representa o peso da aresta do vértice i para o vértice j.
     * - O valor 0 indica ausência de aresta (ou aresta com peso zero, se aplicável).
     */
    private int vertices[][];

    /**
     * Inicializa a matriz de adjacência para o algoritmo de Dijkstra.
     *
     * @param numVertices Número de vértices do grafo. Determina o tamanho da matriz (numVertices x numVertices).
     *
     * @example
     * // Cria um grafo com 3 vértices:
     * Dijkstra dijkstra = new Dijkstra(3);
     */

    public Dijkstra(final int numVertices) {
        vertices = new int[numVertices][numVertices];
    }

    /**
     * Adiciona uma aresta bidirecional (não direcionada) entre dois nós do grafo com um peso específico.
     *
     * @param noOrigem  O índice do nó de origem (deve ser um índice válido na matriz de adjacência)
     * @param noDestino O índice do nó de destino (deve ser um índice válido na matriz de adjacência)
     * @param peso      O peso da aresta (deve ser um valor inteiro maior ou igual a 1)
     *
     * @throws IllegalArgumentException Se:
     *         - O peso for menor que 1
     *         - Os índices dos nós forem inválidos (negativos ou excederem o tamanho da matriz)
     *
     * @example
     * // Adiciona uma aresta entre os nós 0 e 1 com peso 5
     * grafo.criarAresta(0, 1, 5);
     *
     * @implNote
     * - Esta implementação assume um grafo não direcionado, portanto a aresta é adicionada
     *   em ambas as direções (origem→destino e destino→origem)
     * - Para grafos direcionados, remova a segunda atribuição
     */

    public void criarAresta(final int noOrigem, final int noDestino, final int peso) {
        if (peso < 1) {
            throw new IllegalArgumentException("O peso do nó origem [" + noOrigem +
                    "] para o nó destino [" + noDestino + "] deve ser maior ou igual a 1!");
        }

        if (noOrigem < 0 || noDestino < 0 || noOrigem >= vertices.length || noDestino >= vertices.length) {
            throw new IllegalArgumentException("Nó de origem ou destino inválido!");
        }

        vertices[noOrigem][noDestino] = peso;
        vertices[noDestino][noOrigem] = peso; // Se o grafo for não direcionado
    }

    /**
     * Encontra o nó não visitado com a menor distância atual na lista de custos.
     *
     * @param listaCustos Array contendo as distâncias atuais de cada nó em relação à origem
     * @param naoVisitados Conjunto de nós que ainda não foram visitados/processados
     * @return O índice do nó não visitado mais próximo (com menor distância na lista de custos)
     *
     * @implNote
     * - Este método é um componente chave do algoritmo de Dijkstra
     * - Percorre todos os nós não visitados para encontrar aquele com menor custo atual
     * - Retorna 0 caso todos os nós tenham distância Integer.MAX_VALUE
     * - Complexidade: O(n), onde n é o número de nós não visitados
     *
     * @example
     * // Supondo:
     * // listaCustos = [0, 3, 1, Integer.MAX_VALUE]
     * // naoVisitados = {1, 2, 3}
     * int proximo = getMaisProximo(listaCustos, naoVisitados); // retornará 2
     */

    private int getMaisProximo(final int listaCustos[], final Set<Integer> naoVisitados) {
        int minDistancia = Integer.MAX_VALUE;
        int noProximo = 0;
        for (Integer i : naoVisitados) {
            if (listaCustos[i] < minDistancia) {
                minDistancia = listaCustos[i];
                noProximo = i;
            }
        }
        return noProximo;
    }

    /**
     * Obtém a lista de nós vizinhos diretamente conectados ao nó especificado.
     *
     * @param no O índice do nó para o qual se deseja obter os vizinhos (deve ser um índice válido)
     * @return Lista contendo os índices de todos os nós vizinhos diretamente conectados ao nó especificado
     *         (lista vazia se não houver conexões)
     *
     * @implNote
     * - Um vizinho é definido como qualquer nó conectado por uma aresta com peso > 0
     * - A lista retornada é uma nova instância, podendo ser modificada sem afetar a estrutura do grafo
     * - Complexidade: O(n), onde n é o número total de nós no grafo
     *
     * @example
     * // Para um grafo com conexões: 0->1 (peso 2), 0->2 (peso 3)
     * List<Integer> vizinhos = getVizinhos(0); // retornará [1, 2]
     */

    private List<Integer> getVizinhos(final int no) {
        List<Integer> vizinhos = new ArrayList<Integer>();
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[no][i] > 0) {
                vizinhos.add(i);
            }
        }
        return vizinhos;
    }

    /**
     * Obtém o custo (peso) da aresta entre dois nós específicos.
     *
     * @param noOrigem Índice do nó de origem (deve ser um índice válido na matriz de adjacência)
     * @param noDestino Índice do nó de destino (deve ser um índice válido na matriz de adjacência)
     * @return O valor do peso/custo da aresta entre os nós especificados.
     *         Retorna 0 caso não exista conexão direta entre os nós.
     *
     * @implNote
     * - Este método realiza acesso direto à matriz de adjacência sem validações adicionais
     * - O valor 0 pode indicar tanto ausência de conexão quanto conexão com peso zero
     * - Para verificar existência de conexão, combine com verificação de índice válido
     *
     * @example
     * // Para um grafo com aresta 0→1 de peso 5:
     * int custo = getCusto(0, 1);  // retorna 5
     * int custo2 = getCusto(1, 0); // retorna 0 (caso grafo seja direcionado)
     */

    private int getCusto(final int noOrigem, final int noDestino) {
        return vertices[noOrigem][noDestino];
    }


    /**
     * Calcula o caminho de custo mínimo entre dois nós usando o algoritmo de Dijkstra.
     *
     * @param noOrigem Índice do nó de partida (deve ser um índice válido)
     * @param noDestino Índice do nó de destino (deve ser um índice válido)
     * @return Lista contendo a sequência de nós do caminho mínimo (incluindo origem e destino),
     *         ou null se não existir caminho entre os nós
     *
     * @implNote
     * - Implementação clássica do algoritmo de Dijkstra para grafos com pesos não-negativos
     * - Utiliza três estruturas principais:
     *   1. Array 'custo' para armazenar as distâncias acumuladas
     *   2. Array 'antecessor' para reconstruir o caminho
     *   3. Conjunto 'naoVisitado' para controlar os nós pendentes
     * - Complexidade: O(V²) onde V é o número de vértices
     *
     * @algorithm
     * 1. Inicializa todas as distâncias como infinito, exceto a origem (0)
     * 2. Enquanto houver nós não visitados:
     *    a. Seleciona o nó não visitado com menor custo atual
     *    b. Atualiza as distâncias dos seus vizinhos
     *    c. Quando alcançar o destino, reconstrói e retorna o caminho
     *
     * @example
     * // Para um grafo com arestas:
     * // 0-1 (peso 2), 0-2 (peso 4), 1-2 (peso 1)
     * List<Integer> caminho = caminhoMinimo(0, 2);
     * // Retorna [0, 1, 2] (caminho com custo total 3)
     */

    public List<Integer> caminhoMinimo(final int noOrigem, final int noDestino) {

        int custo[] = new int[vertices.length];
        int antecessor[] = new int [vertices.length];
        Set<Integer> naoVisitado = new HashSet<Integer>();
        custo[noOrigem] = 0;

        for (int v = 0; v < vertices.length; v++) {
            if (v != noOrigem) {
                custo[v] = Integer.MAX_VALUE;
            }
            antecessor[v] = -1;
            naoVisitado.add(v);
        }

        while (!naoVisitado.isEmpty()) {
            int noMaisProximo = getMaisProximo(custo, naoVisitado);
            naoVisitado.remove(noMaisProximo);

            for (Integer vizinhos : getVizinhos(noMaisProximo)) {
                int custoTotal = custo[noMaisProximo] + getCusto(noMaisProximo, vizinhos);
                if (custoTotal < custo[vizinhos]) {
                    custo[vizinhos] = custoTotal;
                    antecessor[vizinhos] = noMaisProximo;
                }
            }

            if (noMaisProximo == noDestino) {
                return caminhoMaisProximo(antecessor, noMaisProximo);
            }
        }

        return null;
    }

    /**
     * Reconstrói o caminho mínimo a partir do array de antecessores calculado pelo algoritmo de Dijkstra.
     *
     * @param antecessor Array onde antecessor[i] contém o nó anterior no caminho mínimo até o nó i
     * @param noMaisProximo Nó de destino a partir do qual o caminho será reconstruído
     * @return Lista contendo a sequência de nós do caminho mínimo, em ordem da origem ao destino
     *
     * @implNote
     * - O caminho é reconstruído em ordem inversa (do destino para a origem) e depois invertido
     * - O valor -1 no array antecessor indica o fim do caminho (nó de origem)
     * - O método assume que o array antecessor foi corretamente inicializado pelo algoritmo principal
     *
     * @example
     * // Para antecessor = [-1, 0, 1] e noMaisProximo = 2:
     * List<Integer> caminho = caminhoMaisProximo(antecessor, 2);
     * // Retorna [0, 1, 2] (caminho 0 → 1 → 2)
     */

    public List<Integer> caminhoMaisProximo(final int antecessor[], int noMaisProximo) {
        List<Integer> caminho = new ArrayList<Integer>();
        caminho.add(noMaisProximo);

        while (antecessor[noMaisProximo] != -1) {
            caminho.add(antecessor[noMaisProximo]);
            noMaisProximo = antecessor[noMaisProximo];
        }
        Collections.reverse(caminho);
        return caminho;
    }

}
