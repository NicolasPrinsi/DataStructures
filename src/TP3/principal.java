package TP3;

import java.util.ArrayList;

public class principal {
	
	public static void main(String args[]) {
		//Trabajo Practico 3 Ejercicio 1 inciso a
		MetodosLista<Integer> tester= new MetodosLista<Integer>();
		ArrayList<Integer> aTesteara = new ArrayList<Integer>();
		
		aTesteara.add(0);
		aTesteara.add(1);
		aTesteara.add(2);
		aTesteara.add(3);
		aTesteara.add(4);
		aTesteara.add(5);
		aTesteara.add(6);
		System.out.println("Trabajo practico 3 Ejercicio 1 inciso a");
		System.out.println("Lista a testear contiene los elementos 0,1,2,3,4,5,6");
		System.out.println("Elemento 0 pertenece: "+tester.Tp3e1a(aTesteara, 0));
		System.out.println("Elemento 3 pertenece: "+tester.Tp3e1a(aTesteara, 3));
		System.out.println("Elemento 6 pertenece: "+tester.Tp3e1a(aTesteara, 6));
		System.out.println("Elemento 7 pertenece: "+tester.Tp3e1a(aTesteara, 7));
	//Trabajo Practico 3 Ejercicio 1 inciso b
		ArrayList<Integer> aTestearb = new ArrayList<Integer>();
		aTestearb.add(1);
		aTestearb.add(2);
		aTestearb.add(3);
		aTestearb.add(1);
		aTestearb.add(2);
		aTestearb.add(1);
		System.out.println("Lista a testear contiene los elementos 1,2,3,1,2,1 (3 veces el elemento 1, 2 veces el elemento 2 1 vez el elemento 3)");
		System.out.println("Trabajo practico 3 Ejercicio 1 inciso b");
		System.out.println("El elemento 1 aparece 2 o mas veces: "+tester.Tp3e1b(aTestearb, 1, 2));
		System.out.println("El elemento 1 aparece 3 o mas veces: "+tester.Tp3e1b(aTestearb, 1, 3));
		System.out.println("El elemento 1 aparece 4 o mas veces: "+tester.Tp3e1b(aTestearb, 1, 4));
	//Trabajo Practico 3 Ejercicio 1 inciso c
		System.out.println("Trabajo practico 3 Ejercicio 1 inciso c");
		System.out.println("El elemento 1 aparece exactamente 2 veces: "+tester.Tp3e1c(aTestearb, 1, 2));
		System.out.println("El elemento 1 aparece exactamente 3 veces: "+tester.Tp3e1c(aTestearb, 1, 3));
		System.out.println("El elemento 1 aparece exactamente 4 veces: "+tester.Tp3e1c(aTestearb, 1, 4));
	//Trabajo Practico 3 Ejercicio 2 inciso a
		System.out.println("Trabajo practico 3 Ejercicio 2 inciso a");
		System.out.println("Lista 1 con numeros 0,2,4,6,8");
		ArrayList<Integer> l1=new ArrayList<Integer>();
		l1.add(0);
		l1.add(2);
		l1.add(4);
		l1.add(6);
		l1.add(8);
		System.out.println("Lista 2 con numeros 1,3,5,7");
		ArrayList<Integer> l2=new ArrayList<Integer>();
		l2.add(1);
		l2.add(3);
		l2.add(5);
		l2.add(7);
		ArrayList<Integer> l12i = tester.Tp3e2a(l1, l2);
		System.out.println("Lista 1-2 resultado de intercalar lista 1 y 2: "+l12i.toString());
		ArrayList<Integer> l12ii = tester.Tp3e2a(l2, l1);
		System.out.println("Lista 1-2i resultado de intercalar lista 1 y 2 en el orden inverso al anterior: "+l12ii.toString());
	//Trabajo Practico 3 Ejercicio 2 inciso b
		System.out.println("Trabajo practico 3 Ejercicio 2 inciso b");
		System.out.println("Lista 3 con numeros 1,2,3,4,5,6,7,8,9,11");
		ArrayList<Integer> l3 = new ArrayList<Integer>();
		l3.add(1);
		l3.add(2);
		l3.add(3);
		l3.add(4);
		l3.add(5);
		l3.add(6);
		l3.add(7);
		l3.add(8);
		l3.add(9);
		l3.add(11);
		System.out.println("Lista 4 con numeros 1,2,3,4,5,6,7,8,10");
		ArrayList<Integer> l4 = new ArrayList<Integer>();
		l4.add(1);
		l4.add(2);
		l4.add(3);
		l4.add(4);
		l4.add(5);
		l4.add(6);
		l4.add(7);
		l4.add(8);
		l4.add(10);
		ArrayList<Integer> l34 = tester.Tp3e2b(l3, l4);
		System.out.println("Lista 3-4 resultado de intercalar listas 3 y 4 (el metodo no permite repeticiones)"+l34.toString());
	//Trabajo Practico 3 Ejercicio 3
		System.out.println("Trabajo practico 3 Ejercicio 3");
		ArrayList<Integer> ti = new ArrayList<Integer>();
		ti.add(1);
		ti.add(2);
		ti.add(3);
		ti.add(4);
		ti.add(5);
		System.out.println("Orden de los elementos en la lista previo a la inversion "+ti.toString());
		tester.Tp3e3(ti);
		System.out.println("Orden de los elementos en la lista posterior a la inversion "+ti.toString());
	//Trabajo Practico 3 Ejercicio 4
		System.out.println("Trabajo practico 3 Ejercicio 4");
		ArrayList<Integer> l5 = new ArrayList<Integer>();
		l5.add(1);
		l5.add(2);
		l5.add(3);
		l5.add(4);
		ArrayList<Integer> l6 = new ArrayList<Integer>();
		l6.add(1);
		l6.add(2);
		l6.add(3);
		l6.add(4);
		l6.add(4);
		l6.add(3);
		l6.add(2);
		l6.add(1);
		ArrayList<Integer> l7 = new ArrayList<Integer>();
		l7.add(1);
		l7.add(2);
		l7.add(3);
		l7.add(4);
		l7.add(4);
		l7.add(3);
		l7.add(2);
		l7.add(1);
		l7.add(5);
		System.out.println("Lista 5 contiene los elementos: "+l5.toString());
		System.out.println("Lista 6 contiene los elementos: "+l6.toString());
		System.out.println("Lista 7 contiene los elementos: "+l7.toString());
		System.out.println("Lista 6 es copia espejo de 5: "+tester.Tp3e4(l6,l5));
		System.out.println("Lista 7 es copia espejo de 5: "+tester.Tp3e4(l7,l5));
		System.out.println("Lista 5 es copia espejo de 6: "+tester.Tp3e4(l5, l6));
		System.out.println("Trabajo practico 3 Ejercicio 5");
		ArrayList<Integer> l8= new ArrayList<Integer>();
		l8.add(1);
		l8.add(2);
		l8.add(3);
		l8.add(4);
		l8.add(5);
		l8.add(6);
		l8.add(7);
		l8.add(8);
		System.out.println("Lista 8 contiene los elementos: "+l8.toString());
		ArrayList<Integer> l9= new ArrayList<Integer>();
		l9.add(3);
		l9.add(5);
		l9.add(7);
		l9.add(9);
		System.out.println("Lista 9 contiene los elementos: "+l9.toString());
		tester.Tp3e5(l8, l9);
		System.out.println("Lista 8 despues del ejercicio 5 contiene los elementos: "+l8.toString());
	}
	
}
