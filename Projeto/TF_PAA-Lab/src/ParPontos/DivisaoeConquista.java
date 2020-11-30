package ParPontos;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ProduzirDados.AdmProcessos;

public class DivisaoeConquista {
	static double minDistance = 9999999;
	static Point p1;
	static Point p2;
	
	public static double calcDistance(Point a,Point b) {
		return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));				
	}
	
	public static void main(String[] args){            
		
		List<Point> pontos = new ArrayList<>(AdmProcessos.geraPontos(1000));
		List <Point> aux = new ArrayList<>();
		/// dividir os pontos pela metade
		
		ArrayList<Point> pontosM1 = new ArrayList<>();
		ArrayList<Point> pontosM2 = new ArrayList<>();
		for (int i=0;i<pontos.size();i++) {
			if (i<(pontos.size()/2)) 
			pontosM1.add(pontos.get(i));
			else pontosM2.add(pontos.get(i));
		}
		
		//ordenar os x dos arrays
		
		MergeSort ms1 = new MergeSort(pontosM1);
		MergeSort ms2 = new MergeSort(pontosM2);		
		ms1.sortGivenArray();
		ms2.sortGivenArray();		
		
		
		for(Point a:ms1.getSortedArray()){
            for (Point b: pontos) {            	
            	if (a != b && (!aux.contains(a))) {
					double distance = calcDistance(a,b);
					if (distance < minDistance) {
						minDistance = distance;
						p1 = a;
						p2 = b;						
					}												
					aux.add(a);
				}	
            }
        }	
		
		for(Point a:ms2.getSortedArray()){
            for (Point b: pontos) {            	
            	if (a != b && (!aux.contains(a))) {
					double distance = calcDistance(a,b);
					if (distance < minDistance) {
						minDistance = distance;
						p1 = a;
						p2 = b;						
					}												
					aux.add(a);
				}	
            }
        }	
		
		
		String ponto1 = "Ponto 1: ("+p1.x+","+p1.y+")";
		String ponto2 = "Ponto 2: ("+p2.x+","+p2.y+")";
		System.out.println(ponto1);
		System.out.println(ponto2);
		System.out.println("Menor distância: "+ minDistance);
        
	}
}
