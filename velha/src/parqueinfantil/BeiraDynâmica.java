package parqueinfantil;

/**
 * Use TABULEIRO_LARGURA e QUADRADO_LARGURA para controlar os dimensões.
 * veja a Classe QuadradoDynamico antes de tenando enter esta classe.
 */
public class BeiraDynâmica {
    private static final int TABULEIRO_LARGURA = 64;

    public static void main(String[] args) {
        char[][] tabuleiro = new char[TABULEIRO_LARGURA][TABULEIRO_LARGURA];
        int linhas  = tabuleiro[0].length;
        int colunas = tabuleiro.length;

        initializaTabuleiro(tabuleiro, linhas, colunas);
        mostreTabuleiro(tabuleiro, linhas, colunas);
    }

    private static void initializaTabuleiro(char[][] tabuleiro, int linhas, int colunas) {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                tabuleiro[l][c] = escolheCaracter(l, c, linhas, colunas);
            }
        }
    }

    private static char escolheCaracter(int linha, int coluna, int linhas, int colunas) {
        if (linha == 0 || linha == linhas - 1) {
            return '-';
        }
        if (coluna  == 0 || (coluna == colunas - 1)) {
            return '|';
        }
        return ' ';
    }

    private static void mostreTabuleiro(char[][] tabuleiro, int linhas, int colunas) {
        System.out.println("Linhas: " + linhas);
        System.out.println("Colunas: " + colunas);

        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                System.out.print(tabuleiro[l][c]);
            }
            System.out.println();
        }
    }
}
