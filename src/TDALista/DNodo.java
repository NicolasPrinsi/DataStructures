package TDALista;

public class DNodo<E> implements Position<E> {
	private E element;
	private DNodo<E> prev, next;
	
	public DNodo(E elem, DNodo<E> p, DNodo<E>n) {
		element=elem;
		prev=p;
		next=n;	
	}
	@Override
	public E element() {
		return element;
	}
	
	public DNodo<E> getNext(){
		return next;
	}
	public DNodo<E> getPrev(){
		return prev;
	}
	public void setElement(E elem) {
		element=elem;
	}
	
	public void setNext(DNodo<E> n) {
		next=n;
	}
	
	public void setPrev(DNodo<E> p) {
		prev=p;
	}

}
