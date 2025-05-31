import java.util.Scanner;

public class DijkstraTeste {

    private static final int S1 = 0;
    private static final int S2 = 1;
    private static final int S3 = 2;
    private static final int S4 = 3;
    private static final int S5 = 4;
    private static final int S6 = 5;
    private static final int S7 = 6;
    private static final int S8 = 7;
    private static final int S9 = 8;
    private static final int S10 = 9;
    private static final int S11 = 10;
    private static final int S12 = 11;
    private static final int S13 = 12;
    private static final int S14 = 13;
    private static final int S15 = 14;
    private static final int S16 = 15;
    private static final int S17 = 16;
    private static final int S18 = 17;
    private static final int S19 = 18;
    private static final int S20 = 19;

    public static void main(String[] args) {
        Dijkstra dijkstraAlg = new Dijkstra(20);

        // Linha vermelha
        dijkstraAlg.criarAresta(S1, S2, 16);
        dijkstraAlg.criarAresta(S2, S3, 14);
        dijkstraAlg.criarAresta(S3, S4, 12);
        dijkstraAlg.criarAresta(S4, S5, 12);
        dijkstraAlg.criarAresta(S5, S6, 14);

        // Linha verde
        dijkstraAlg.criarAresta(S7, S8, 15);
        dijkstraAlg.criarAresta(S8, S9, 11);
        dijkstraAlg.criarAresta(S9, S10, 13);
        dijkstraAlg.criarAresta(S10, S11, 16);
        dijkstraAlg.criarAresta(S11, S6, 15);

        // Linha amarela
        dijkstraAlg.criarAresta(S12, S8, 11);
        dijkstraAlg.criarAresta(S8, S2, 8);
        dijkstraAlg.criarAresta(S2, S15, 7);
        dijkstraAlg.criarAresta(S15, S16, 7);
        dijkstraAlg.criarAresta(S16, S17, 12);
        dijkstraAlg.criarAresta(S17, S18, 9);

        // Linha azul
        dijkstraAlg.criarAresta(S12, S9, 17);
        dijkstraAlg.criarAresta(S9, S13, 7);
        dijkstraAlg.criarAresta(S13, S14, 9);
        dijkstraAlg.criarAresta(S14, S5, 9);
        dijkstraAlg.criarAresta(S5, S17, 10);

        // Linha roxa
        dijkstraAlg.criarAresta(S10, S13, 11);
        dijkstraAlg.criarAresta(S13, S3, 13);
        dijkstraAlg.criarAresta(S3, S16, 11);
        dijkstraAlg.criarAresta(S16, S19, 13);
        dijkstraAlg.criarAresta(S19, S20, 12);

        Scanner in = new Scanner(System.in);
        System.out.println("Bem-vindo ao Subway System");
        System.out.println("----------------------------------------------------");

        while (true) {
            System.out.println("Por favor, entre com a sua rota ou pressione ENTER para sair do programa.");
            int origem = lerEstacao("Origem", in);
            int destino = lerEstacao("Destino", in);

            for (Integer estacao : dijkstraAlg.caminhoMinimo(origem, destino)) {
                System.out.print((estacao + 1) + " -> ");
            }

            System.out.println("Fim da Rota");
        }
    }

    private static int lerEstacao(String tipo, Scanner in) {
        while (true) {
            try {
                System.out.print("Digite o número da estação de " + tipo + " (1 a 20): ");
                String entrada = in.nextLine();
                if (entrada.isEmpty()) {
                    System.out.println("Encerrando o programa...");
                    System.exit(0);
                }
                int estacao = Integer.parseInt(entrada);
                if (estacao >= 1 && estacao <= 20) {
                    return estacao - 1; // ajusta para índice baseado em 0
                } else {
                    System.out.println("Número inválido. Digite um número entre 1 e 20.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }
    }
}
