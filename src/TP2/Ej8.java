package TP2;

import java.util.LinkedList;

import TDACola.ArrayQueue;
import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDAPila.Stack;
import TDAPila.ArrayStack;
import TDAPila.EmptyStackException;
public class Ej8 {
	public static void main(String args[]) {
		//Creacion y relleno de cola ;)
				Queue<Integer> cola= new ArrayQueue<Integer>();
				cola.enqueue(9);
				cola.enqueue(8);
				cola.enqueue(7);
				cola.enqueue(6);
				cola.enqueue(5);
				cola.enqueue(4);
				cola.enqueue(3);
				cola.enqueue(2);
				cola.enqueue(1);
				cola.enqueue(0);
				System.out.println("Cola original: "+cola.toString());
				//creacion de nueva cola solo con los impares
				Queue<Integer> aux= soloImpares(cola);
				System.out.println("Cola con solo los impares: "+aux.toString());
				//intercalado de pilas
				//Creacion y rellenado de pila 1
				Stack<Integer> pila1 = new ArrayStack<Integer>();
				pila1.push(1);
				pila1.push(3);
				pila1.push(5);
				pila1.push(7);
				pila1.push(9);
				pila1.push(11);
				pila1.push(13);
				System.out.println("pila 1: "+pila1.toString());
				//Creacion y rellenado de pila 2
				Stack<Integer> pila2 = new ArrayStack<Integer>();
				pila2.push(2);
				pila2.push(4);
				pila2.push(6);
				pila2.push(8);
				System.out.println("pila 2: "+pila2.toString());
				//pila 3 resultado de intercalar pila 1 y pila 2
				Stack<Integer> pila3 = intercalarPilas(pila1,pila2);
				System.out.println("Pila 3 resultante de intercalar pila 1 y pila 2: "+pila3.toString());
	
	}
	
	public static Queue<Integer> soloImpares(Queue<Integer> cola){
		Queue<Integer> toreturn = new ArrayQueue<Integer>();
		while(!cola.isEmpty()) {
			try {
				Integer aux;
				aux = cola.dequeue();
				if(aux%2!=0)
					toreturn.enqueue(aux);
			} catch (EmptyQueueException e) {
				e.printStackTrace();
			}
		}
		return toreturn;
	}
	
	
	public static Stack<Integer> intercalarPilas(Stack<Integer>p1, Stack<Integer> p2){
		Stack<Integer> toreturn = new ArrayStack<Integer>();
		while(!p1.isEmpty()&&!p2.isEmpty()) {
			try {
				toreturn.push(p1.pop());
				toreturn.push(p2.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		while(!p1.isEmpty()) {
			try {
				toreturn.push(p1.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		while(!p2.isEmpty()) {
			try {
				toreturn.push(p2.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		return toreturn;
		
	}
}
