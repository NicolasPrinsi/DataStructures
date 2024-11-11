package TDAColaCP;

import TDAMapeo.InvalidKeyException;

public class Tester {
	public static void main(String args[]) {
		PriorityQueue<Integer, String> pq = new HeapPQueueOld<Integer, String>(new Comparador<Integer>());
		try {
			pq.insert(5, "Cinco");
			pq.insert(3, "Tres");
			pq.insert(1, "Uno");
			pq.insert(2, "Dos");
			pq.insert(4, "Cuatro");
			System.out.println("Minimo "+pq.min().getKey());
			System.out.println("Remover minimo "+pq.removeMin().getKey());
			System.out.println("Nuevo Minimo "+pq.min().getKey());
			System.out.println("Remover minimo "+pq.removeMin().getKey());
			System.out.println("Nuevo Minimo "+pq.min().getKey());
			System.out.println("Remover minimo "+pq.removeMin().getKey());
			System.out.println("Nuevo Minimo "+pq.min().getKey());
			System.out.println("Remover minimo "+pq.removeMin().getKey());
			System.out.println("Nuevo Minimo "+pq.min().getKey());
			System.out.println("Remover minimo "+pq.removeMin().getKey());
		} catch (InvalidKeyException | EmptyPriorityQueueException e) {
			e.printStackTrace();
		}
	}
}
