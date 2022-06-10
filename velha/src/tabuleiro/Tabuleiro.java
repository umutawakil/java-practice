package tabuleiro;

/**
 * Esta classe representa a Tabuleiro interio, não somenthe 'X' e 'O' mas todos os symbolos e caracteres no tele.
 * Quando falando sobre 'X' ou 'O' nos usamos terminologia como 'posicao' ou 'lugar' e confrequencia tem que convert
 * a posicao relativo que somente tem a ver com 'X' e 'O' para a posicao fisico que tem mais que 'X' e 'O'
 */
public class Tabuleiro {
    private int      larguraEmCaracteres; // Largura da tela em caracteres reais ao oposto espaços, incluindo '-', '|', ' '
    private int      cuboTomanho;         // Número de caracteres em um único espaço (estético)
    private int      larguraEmCubos;      // Um cubo é um espaco mas é diferente de um index no array de caracteres.
    private char[][] caracteres;          // o tabuleiro atual incluindo '-', '|'
    private int      espaçosOcupados;
    private int      espaçosTotais;

    public Tabuleiro(int larguraEmCaracteres, int cuboTomanho) {
        this.cuboTomanho         = cuboTomanho;
        this.larguraEmCaracteres = larguraEmCaracteres;
        this.caracteres          = new char[larguraEmCaracteres][larguraEmCaracteres];
        this.larguraEmCubos      = this.larguraEmCaracteres / this.cuboTomanho;
        this.espaçosTotais       = this.larguraEmCubos * this.larguraEmCubos;
        this.espaçosOcupados     = 0;
        initializeTabulero();
    }

    private void initializeTabulero() {
        for(int l = 0; l < larguraEmCaracteres; l++) {
            for(int c = 0; c < larguraEmCaracteres; c++ ) {
                this.caracteres[l][c] = initializeCaractere(l,c,larguraEmCaracteres);
            }
        }
    }

    private char initializeCaractere(int linha,int coluna, int larguraEmCaracteres) {
        if (linha % this.cuboTomanho == 0 || (linha == larguraEmCaracteres - 1)) {
            return '-';
        }
        if (coluna % this.cuboTomanho == 0 || (coluna == larguraEmCaracteres - 1)) {
            return '|';
        }
        return ' ';
    }

    public void mostre() {
        for(int l = 0; l < larguraEmCaracteres; l++) {
            for(int c = 0; c < larguraEmCaracteres; c++) {
                System.out.print(this.caracteres[l][c]);
            }
            System.out.println();
        }
    }

    public boolean põeValor(char entradaValor, int linha, int coluna) {
        int linhaValorAdjustado  = (this.cuboTomanho/2) + this.cuboTomanho*linha;
        int colunaValorAdjustada = (this.cuboTomanho/2) + this.cuboTomanho*coluna;

        char valorDoIndex;
        try {
            valorDoIndex = this.caracteres[linhaValorAdjustado][colunaValorAdjustada];
        } catch (Exception e) { // Somente executado durante errors/exceptions.
            System.out.println("LinhaValorAjustado: " + linhaValorAdjustado);
            System.out.println("ColunaValorAjustada3: " + colunaValorAdjustada);
            System.out.println("linha: " + linha);
            System.out.println("coluna: " + coluna);
            throw new RuntimeException(e);
        }
        if (valorDoIndex == 'X' || valorDoIndex == 'O') {
            System.out.println("Aquele lugar ja está ocupada");
            return false;
        }
        this.caracteres[convertaAsCoordenadasRelativasParaAbsolutas(linha)][convertaAsCoordenadasRelativasParaAbsolutas(coluna)] = entradaValor;
        espaçosOcupados++;
        return true;
    }

    private int convertaAsCoordenadasRelativasParaAbsolutas(int posiçãoRelativa) {
        return (this.cuboTomanho/2) + this.cuboTomanho*posiçãoRelativa;
    }

    /** Usado para determinar se existe um a fila. Esta função verifica esqueirda, direito, top, de baixo, e diagonals.*/
    public boolean hasFila(int linhaEntrada, int colunaEntrada, char symboloDoJogador) {
        int linha                = convertaAsCoordenadasRelativasParaAbsolutas(linhaEntrada);
        int coluna               = convertaAsCoordenadasRelativasParaAbsolutas(colunaEntrada);
        int linhaDeslocamento[]  = {0, -1*this.cuboTomanho, -1*this.cuboTomanho, -1*this.cuboTomanho};
        int colunaDeslocamento[] = {-1*this.cuboTomanho, -1*this.cuboTomanho, 0, this.cuboTomanho};

        for(int d = 0; d < linhaDeslocamento.length; d++) {
            //-1 está usado no primero 'count' para
            int count = sequeLinha(
                            linha,
                            coluna,
                            linhaDeslocamento[d],
                            colunaDeslocamento[d],
                        0, symboloDoJogador
            )
            +
                        sequeLinha(
                            linha,
                            coluna,
                            (-1 * linhaDeslocamento[d]),
                            (-1 * colunaDeslocamento[d]),
                            0, symboloDoJogador)
            -
            1;
            if(count == this.larguraEmCubos) {
                return true;
            }
        }
        return false;
    }

    /** Esta parte é a parte mais complicada. Recursão é usada para verificar se qual quer lugar proxima a posicao atual é o mesmo
    symbolo e depois verifica os posições proxima aquele novo posição e continua ate saindo o tabuleiro ou encontrando um symbol/caractere
    que nao é o mesmo.

    Rercusão - Um método de repetindo operações em que um função pode se chama se mesmo.
     **/
    private int sequeLinha(int linhaAtual, int colunaAtual, int linhaDeslocamento, int colunaDeslocamento, int count, char symboloDoJogador) {
        if ((linhaAtual < 0 )|| (linhaAtual >= larguraEmCaracteres)|| (colunaAtual < 0) || (colunaAtual >= larguraEmCaracteres)) {
            return count;
        }
        if (this.caracteres[linhaAtual][colunaAtual] != symboloDoJogador) {
            return count;
        }
        return sequeLinha(
                linhaAtual + linhaDeslocamento,
                colunaAtual + colunaDeslocamento,
                linhaDeslocamento,
                colunaDeslocamento,
                count + 1,
                symboloDoJogador
        );
    }

    public int getLarguraEmCubos() {
        return larguraEmCubos;
    }

    public int getEspaçosOcupados() {
        return espaçosOcupados;
    }

    public int getEspaçosTotais() {
        return espaçosTotais;
    }
}
