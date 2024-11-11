package TDALista;

public class DoubleLinkedListTp3<E> extends DoubleLinkedList<E> {

	public void Tp3E2(E e1, E e2) {
		switch(this.size){
		case 0:
			DNodo<E> seg= new DNodo<E>(e1,null,null);
			DNodo<E> ant=new DNodo<E>(e2,null,null);
			ant.setNext(seg);
			seg.setPrev(ant);
			front.setNext(ant);
			ant.setPrev(front);
			rear.setPrev(seg);
			seg.setNext(rear);
			size=2;
			break;
		case 1:
			System.out.println("Error, no es posible con 1 elemento");
			break;
		default:
			try {
				DNodo<E> se= new DNodo<E>(e1,null,null);
				DNodo<E> an=new DNodo<E>(e2,null,null);
				DNodo<E> f=(DNodo<E>) this.first();
				DNodo<E> l=(DNodo<E>) this.last();
				f.getNext().setPrev(se);
				se.setNext(f.getNext());
				f.setNext(se);
				se.setPrev(f);
				
				l.getPrev().setNext(an);
				an.setPrev(l.getPrev());
				l.setPrev(an);
				an.setNext(l);
				size=size+2;
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
		break;
		}
	}
}
