package app;

import java.util.Random;
/** 
 * MIT License
 *
 * Copyright(c) 2020 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class Mapa {
    static Random sorteio = new Random(42);
    char[][] mapa;
    int largura;

    public Mapa(int larg){
        this.largura = larg;
        mapa = new char[larg][larg];

        //cria um mapa aleatório com 50% de probabilidade de ocupação (menos as bordas)
        for(int i=1; i<largura-1; i++)
            for(int j=1; j<largura-1; j++){
                int num = sorteio.nextInt(10000);
                if(num < 5000)
                    mapa[i][j] = '0';
                else
                    mapa[i][j] = '1';
            }
        

        int linhasLivres = (((largura/10)>2) ? (largura/10):2);
        //forçamos ao menos 2 linhas livres (ou 10% de linhas)
        //para facilitar "transição" entre áreas
        if((largura/10)>2)
            linhasLivres = largura/10;
        else
            linhasLivres = 2;

        for(int i=0; i<linhasLivres; i++){
            int linha = sorteio.nextInt(largura);
            for(int j=0;j<largura;j++)
                mapa[linha][j] ='0';
        }

        //garantimos ao menos 3 caminhos de saída para cada espaço livre (evitando becos sem saída)
        for(int i=1; i<largura-1; i++){
            for(int j=1; j<largura-1; j++){
                if(mapa[i][j] == '0'){
                        int vizinhos=0;
                        vizinhos +=  (mapa[i][j-1]=='0')?1:0;
                        vizinhos +=  (mapa[i][j+1]=='0')?1:0;
                        vizinhos +=  (mapa[i-1][j]=='0')?1:0;
                        vizinhos +=  (mapa[i+1][j]=='0')?1:0;
                        if(vizinhos<2){
                            mapa[i][j-1]='0';
                            mapa[i][j+1]='0';
                            mapa[i+1][j]='0';
                        }
                }
            }
        }

        //bordas sempre intransponíveis
        for(int i=0; i<largura; i++){
            mapa[0][i] = mapa[largura-1][i] = mapa[i][0] = mapa[i][largura-1]='1';
        }
    
    
    }

    public String toString(){
        StringBuilder resp = new StringBuilder();
        for(int i=0; i<largura; i++){
            for(int j=0; j<largura; j++){
                resp.append(mapa[i][j]);
            }
            resp.append("\n");
        }
        return resp.toString();
    }


    /**
     *              0010010110
     *              0000000000
     *              0010010110
     *              0000000000    
     *              0010010110    
     *              0010010110
     *              0000000000
     *              0010010110
     *              0010010110    
     *              0010010110    
     *  
     * 
     * 
     * 
     * 
     */
    

}