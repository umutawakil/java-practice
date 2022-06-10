import tabuleiro.Tabuleiro;

import java.util.Scanner;

/**
 * Resumo:
 *  Esta programa pode ser dividio em tres partes.:
 *
 *  Parte um é o parte que desenya o tabuleiro usuando regras que determinar qual caractere um posicao deveria ter.
 *  isso é feito na função initializeTabulero(). Somente para criar os parades do tabuleiro, nada tem a ver com 'X' or 'O'
 *
 *  Parte duas é o parte que converta os coordenadas do usuario de relativa espacos a posicoas absolutas representando caracteres specificos.
 *
 *  Parte tres é a outra parte que usa 'brute force' e 'recursão' para checker cada espaco para a mesma caractere proxima e recorda ate um linha e detectada
 */

public class JogoDaVelha {
    private static final int LARGURA_EM_CARACTERES = 36;
    private static final int CUBO_TOMANHO          = 12;
    private static final Scanner scanner           = new Scanner(System.in);

    public static void main(String[] args) {
        novoJogo();
    }

    private static void novoJogo() {
        System.out.println("Novo Jogo");
        Tabuleiro tabuleiro = new Tabuleiro(LARGURA_EM_CARACTERES, CUBO_TOMANHO);
        tabuleiro.mostre();

        while(true) { // <-- Isto quer dizer atue para sempre.
            jogadorOVez(tabuleiro);
            jogadorXVez(tabuleiro);
        }
    }

    /*Isso controle a exeçucão para jogador 'X'*/
    private static void jogadorXVez(Tabuleiro tabuleiro) {
        jogadorVez("Jogador X", 'X',tabuleiro);
    }

    /*Isso controle a exeçucão para jogador 'O'*/
    private static void jogadorOVez(Tabuleiro tabuleiro) {
        jogadorVez("Jogador O", 'O',tabuleiro);
    }

    /* Isto é um função geral que control a vez por um jogador specfico */
    private static void jogadorVez(String jogadorName,  char symbolo, Tabuleiro tabuleiro) {
        System.out.println(jogadorName + " é sua vez.");
        System.out.println();

        System.out.println("Digite o numero da linha voce quere de 0 a " +(tabuleiro.getLarguraEmCubos() - 1));
        int linha = Integer.parseInt(scanner.nextLine());

        System.out.println("Digite o numero da coluna voce quere de 0 a " +(tabuleiro.getLarguraEmCubos() - 1));
        int coluna = Integer.parseInt(scanner.nextLine());

        if(!tabuleiro.põeValor(symbolo, linha, coluna)) {
            jogadorVez(jogadorName,symbolo,tabuleiro);
        }

        tabuleiro.mostre();

        if(tabuleiro.hasFila(linha, coluna, symbolo)) {
            System.out.println("TIC TAC TOE!!!");
            System.out.println("Jogardor " + symbolo + " ganha!!!!");
            System.exit(0);
        }

        /** Reinicie o jogo se todos os espaços são levados sem um vencedor **/
        if(tabuleiro.getEspaçosOcupados() == tabuleiro.getEspaçosTotais()) {
            novoJogo();
        }
    }
}
