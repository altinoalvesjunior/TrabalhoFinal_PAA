package Subsequencia;

public class Metodo {

    static final int diagonal = 1;
    static final int acima = 2;
    static final int anterior = 3;

    public int encontraSubsequencia(String palavra1, String palavra2) {
        int m = palavra1.length() + 1;
        int n = palavra2.length() + 1;

        int[][] aux = new int[m][n];
        int[][] mat = new int[m][n];

        for (int i = 1; i < m; i++)
            mat[i][0] = 0;

        for (int j = 0; j < n; j++)
            mat[0][j] = 0;


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // Se o elemento da linha for igual a coluna então soma + 1 ao elemento da linha e coluna anterior (DIAGONAL)
                if (palavra1.charAt(i-1) == palavra2.charAt(j-1)) {
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                    aux[i][j] = diagonal;
                }

                else {
                    //Se o elemento da linha anterior for maior OU igual elemento da coluna anterior, pega o elemento da linha anterior(ACIMA)
                    if (mat[i - 1][j] >= mat[i][j - 1]) {
                        mat[i][j] = mat[i - 1][j];
                        aux[i][j] = acima;
                    }

                    else {
                        //Caso contrário pega o elemento da coluna anterior (ANTERIOR)
                        mat[i][j] = mat[i][j - 1];
                        aux[i][j] = anterior;
                    }
                } // fecha else

            } //fecha for j
        } // fecha for i

        imprimeSubsequencia(aux, palavra1, palavra1.length(), palavra2.length());
        return mat[m-1][n-1];
    }

    public void imprimeSubsequencia(int[][] mat, String palavra, int i, int j) {

        if ((i == 0) || (j == 0)) {
            return ;
        }

        if (mat[i][j] == diagonal) {
            imprimeSubsequencia(mat, palavra, i - 1, j - 1);
            System.out.print(palavra.toUpperCase().charAt(i - 1) + " ");
        }

        else {
            if (mat[i][j] == acima ) imprimeSubsequencia(mat, palavra, i - 1, j);
            else imprimeSubsequencia(mat, palavra, i, j - 1);
        }
    }
}
