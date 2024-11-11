package TDALista;

public class main {
	public static void main(String args[]) {
		DoubleLinkedListTp3<Integer> l1 = new DoubleLinkedListTp3<Integer>();
		l1.addLast(1);
		DoubleLinkedListTp3<Integer> l2 = new DoubleLinkedListTp3<Integer>();
		DoubleLinkedListTp3<Integer> l3 = new DoubleLinkedListTp3<Integer>();
		l3.addLast(1);
		l3.addLast(3);
		l3.addLast(5);
	
		System.out.println("Lista 1 con un elemento, insercion 3,2");
		
		l1.Tp3E2(3, 2);
		for(Integer p:l1) {
			System.out.println(p);
		}
		System.out.println("Lista 2 vacia, insercion 2,1");
	
		l2.Tp3E2(2, 1);
		for(Integer p:l2) {
			System.out.println(p);
		}
		System.out.println("Lista 3 con 3 elementos, incersion 2,4");
		
		l3.Tp3E2(2, 4);
		for(Integer p:l3) {
			System.out.println(p);
		}
		
	}
}
