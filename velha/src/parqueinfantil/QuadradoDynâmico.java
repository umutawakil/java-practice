package parqueinfantil;

import java.util.Scanner;

/**
 * Created by Usman Mutawakil on 6/9/22.
 */
public class QuadradoDynâmico {
    public static void main(String[] args) {

        //Passo um: Obta o tomanho do usuario
        System.out.println("Entre o tomaho do quadrado usando numeros, por exemplo 64 vai criar um quadrado com 64 caracteres de comprimento");
        Scanner scanner = new Scanner(System.in);
        int tomanho = scanner.nextInt();

        //Passo dois: Cria o tabuleiro usando a data do usuario
        char[][] tabuleiro = new char[tomanho][tomanho];
        int linhas  = tabuleiro[0].length;
        int colunas = tabuleiro.length;

        //Passo tres: Encha cada index do Array
        enchaQuadrado(tabuleiro, linhas, colunas);

        // Passo quatro: print cada index do Array
        mostreTabuleiro(tabuleiro, linhas, colunas);
    }

    private static void enchaQuadrado(char[][] tabuleiro, int linhas, int colunas) {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                tabuleiro[l][c] = '*';
            }
        }
    }

    private static void mostreTabuleiro(char[][] tabuleiro, int linhas, int colunas) {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                System.out.print(tabuleiro[l][c]);
            }
            System.out.println();
        }
    }
}
