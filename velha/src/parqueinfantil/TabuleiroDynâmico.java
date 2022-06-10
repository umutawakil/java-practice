package parqueinfantil;

/**
 *  Use TABULEIRO_LARGURA e QUADRADO_LARGURA para controlar os dimensões.
 *  * veja a Classe BeiraDynâmico antes de tentando enter esta classe.
 */
public class TabuleiroDynâmico {
    private static final int TABULEIRO_LARGURA = 64; // Em caracteres
    private static final int QUADRADO_LARGURA = 4; // o tomanho de um espaco or lugar do tabuleiro

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

    /**
     *Isto é onde o magico acontece
     */
    private static char escolheCaracter(int linha, int coluna, int linhas, int colunas) {
        if (linha % QUADRADO_LARGURA == 0 || linha == linhas - 1) {
            return '-';
        }
        if (coluna % QUADRADO_LARGURA == 0 || coluna == colunas - 1) {
            return '|';
        }
        return ' ';
    }

    /**
     * Simplesmente travessa a tabuleiro esquerda para a direita, linha por linha, coluna por coluna.
     */
    private static void mostreTabuleiro(char[][] tabuleiro, int linhas, int colunas) {
        System.out.println(String.format("Linhas: %s, Colunas: %s",linhas));

        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                System.out.print(tabuleiro[l][c]);
            }
            System.out.println();
        }
    }
}
