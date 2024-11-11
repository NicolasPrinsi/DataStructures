package TP2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class principal {

	public static void main(String args[]) {
		//Creacion del Arreglo
		Persona[] personas = new Persona[10];
		personas[0] = new Persona("Ricardo", 1);
		personas[1] = new Persona("Carlos", 2);
		personas[2] = new Persona("Javier", 3);
		personas[3] = new Persona("Gerardo", 4);
		personas[4] = new Persona("Damiano", 5);
		//Impresion del arreglo pre inversion
		for(int i =0; i<personas.length;i++) {
			if(personas[i]!=null) {
				System.out.println(personas[i].toString());
			}
		}
		//Inversion del Arreglo
		Invertir(personas);
		//Impresion del arreglo post inversion
		for(int i =0; i<personas.length;i++) {
			if(personas[i]!=null) {
				System.out.println(personas[i].toString());
			}
		}
		//Creacion y relleno de cola ;)
		Queue<Integer> cola= new LinkedList<Integer>();
		cola.add(9);
		cola.add(8);
		cola.add(7);
		cola.add(6);
		cola.add(5);
		cola.add(4);
		cola.add(3);
		cola.add(2);
		cola.add(1);
		cola.add(0);
		System.out.println("Cola original: "+cola.toString());
		//creacion de nueva cola solo con los impares
		Queue<Integer> aux= soloImpares(cola);
		System.out.println("Cola con solo los impares: "+aux.toString());
		//intercalado de pilas
		//Creacion y rellenado de pila 1
		Stack<Integer> pila1 = new Stack<Integer>();
		pila1.add(1);
		pila1.add(3);
		pila1.add(5);
		pila1.add(7);
		pila1.add(9);
		pila1.add(11);
		pila1.add(13);
		System.out.println("pila 1: "+pila1.toString());
		//Creacion y rellenado de pila 2
		Stack<Integer> pila2 = new Stack<Integer>();
		pila2.add(2);
		pila2.add(4);
		pila2.add(6);
		pila2.add(8);
		System.out.println("pila 2: "+pila2.toString());
		//pila 3 resultado de intercalar pila 1 y pila 2
		Stack<Integer> pila3 = intercalarPilas(pila1,pila2);
		System.out.println("Pila 3 resultante de intercalar pila 1 y pila 2: "+pila3.toString());
 	}
	
	public static void Invertir(Persona[] arreglo) {
		Stack<Persona> pila = new Stack<Persona>();
		for (int i=0; i<arreglo.length;i++) {
			if(arreglo[i]!=null) {
				pila.add(arreglo[i]);
				arreglo[i]=null;
			}
		}
		int indx=0;
		while(!pila.isEmpty()) {
			arreglo[indx]=pila.pop();
			indx++;
		}
	}
	
	public static Queue<Integer> soloImpares(Queue<Integer> cola){
		Queue<Integer> toreturn = new LinkedList<Integer>();
		while(!cola.isEmpty()) {
			Integer aux = cola.poll();
			if(aux%2!=0)
				toreturn.add(aux);
		}
		return toreturn;
	}
	
	
	public static Stack<Integer> intercalarPilas(Stack<Integer>p1, Stack<Integer> p2){
		Stack<Integer> toreturn = new Stack<Integer>();
		while(!p1.isEmpty()&&!p2.isEmpty()) {
			toreturn.add(p1.pop());
			toreturn.add(p2.pop());
		}
		while(!p1.isEmpty()) {
			toreturn.add(p1.pop());
		}
		while(!p2.isEmpty()) {
			toreturn.add(p2.pop());
		}
		return toreturn;
		
	}
}
