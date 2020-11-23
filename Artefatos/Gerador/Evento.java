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

public class Evento {
    static Random sorteio = new Random(42);                  //--> fixo para testes
    //static Random sorteio = new Random(System.nanoTime());   --> aleatório 
    
    static final int HORAMAX = 600;
    static final int DURACAOMAX = 120;
    int horaInicio; //em minutos relativos ao início
    int duracao;    //em minutos

    public Evento(){
        this.horaInicio = 10*sorteio.nextInt(HORAMAX/10);
        this.duracao = 10*sorteio.nextInt(DURACAOMAX/10);
    }

    @Override
    public String toString(){
        return "Inicio: "+this.horaInicio+" | Duracao: "+this.duracao;
    }

}