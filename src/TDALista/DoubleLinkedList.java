package TDALista;

import java.util.Iterator;

public class DoubleLinkedList<E> implements PositionList<E>, Iterable<E>{
	protected DNodo<E> front, rear;
	protected int size;
	
	public DoubleLinkedList() {
		size=0;
		front = new DNodo<E>(null,null,null);
		rear = new DNodo<E>(null,null,null);
		front.setNext(rear);
		rear.setPrev(front);
	}
	
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if(p==null)
			throw new InvalidPositionException("Error, posicion nula recibida");
		if(p==front)
			throw new InvalidPositionException("Error, el nodo front no es una posicion valida");
		if(p==rear)
			throw new InvalidPositionException("Error, el nodo rear no es una posicion valida");
		try {
			DNodo<E> toreturn = (DNodo<E>) p;
			if(toreturn.getNext()==null||toreturn.getPrev()==null)
				throw new InvalidPositionException("Error, posicion no perteneciente a una lista");
			return toreturn;
		}catch(ClassCastException e) {
			e.printStackTrace();
			throw new InvalidPositionException("Error, la posicion es de tipo erroneo para esta lista");
		}
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(this.isEmpty())
			throw new EmptyListException("Error, lista vacia");
		return front.getNext();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(this.isEmpty())
			throw new EmptyListException("Error, lista vacia");
		return rear.getPrev();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> temp = this.checkPosition(p);
		if(temp==rear.getPrev())
			throw new BoundaryViolationException("Error, no se puede obtener el siguiente de la ultima posicion");
		return temp.getNext();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> temp = this.checkPosition(p);
		if(temp==front.getNext())
			throw new BoundaryViolationException("Error no se puede obtener el previo de la primera posicion");
		return temp.getPrev();
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> n  = new DNodo<E>(element, null, null);
		DNodo<E> t = front.getNext();
		t.setPrev(n);
		front.setNext(n);
		n.setNext(t);
		n.setPrev(front);
		size++;	
	}

	@Override
	public void addLast(E element) {
		DNodo<E> n= new DNodo<E>(element, null, null);
		DNodo<E> t= rear.getPrev();
		t.setNext(n);
		rear.setPrev(n);
		n.setPrev(t);
		n.setNext(rear);
		size++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos= this.checkPosition(p);
		DNodo<E> n = new DNodo<E>(element,null,null);
		n.setPrev(pos);
		n.setNext(pos.getNext());
		pos.getNext().setPrev(n);
		pos.setNext(n);
		size++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos = this.checkPosition(p);
		DNodo<E> n = new DNodo<E>(element,null,null);
		n.setNext(pos);
		n.setPrev(pos.getPrev());
		pos.getPrev().setNext(n);
		pos.setPrev(n);
		size++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> pos=this.checkPosition(p);
		pos.getPrev().setNext(pos.getNext());
		pos.getNext().setPrev(pos.getPrev());
		E toreturn = pos.element();
		pos.setPrev(null);
		pos.setNext(null);
		pos.setElement(null);
		size--;
		return toreturn;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos = this.checkPosition(p);
		E toreturn = pos.element();
		pos.setElement(element);
		return toreturn;
	}

	@Override
	public Iterator<E> iterator() {
		return new DoubleLinkedListIterator(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toreturn = new DoubleLinkedList<Position<E>>();
		if(!this.isEmpty()) {
			DNodo<E> p = front.getNext();
			while(p!=rear) {
				toreturn.addLast(p);
				p=p.getNext();
			}
		}
		return toreturn;
	}

}
