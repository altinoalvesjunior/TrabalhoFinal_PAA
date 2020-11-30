package ParPontos;

import ProduzirDados.AdmProcessos;


import java.awt.*;
import java.util.List;
import java.util.ArrayList;


public class ForcaBruta {
	static double minDistance = 9999999;
	static Point p1;
	static Point p2;
	
	public static double calcDistance(Point a,Point b) {
		return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));				
	}
	
	public static void main(String[] args){            
		
		List<Point> pontos = new ArrayList<>(AdmProcessos.geraPontos(1000));  
		List <Point> aux = new ArrayList<>();
		for (Point a:pontos) {
			for (Point b:pontos) {
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
