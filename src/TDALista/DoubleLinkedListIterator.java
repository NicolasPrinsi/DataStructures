package TDALista;

import java.util.Iterator;

public class DoubleLinkedListIterator<E> implements Iterator<E> {
	private DoubleLinkedList<E> p;
	private DNodo<E> piv;
	
	public DoubleLinkedListIterator(DoubleLinkedList<E> p) {
		this.p=p;
		piv=null;
		if(!p.isEmpty()) {
			try {
				piv=(DNodo<E>)p.first();
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public boolean hasNext() {
		return(this.piv!=null);
	}

	@Override
	public E next() {
		E elem = null;
		try {
			if(this.piv!=null) {
				elem = this.piv.element();
				if(this.piv==p.last())
					this.piv=null;
				else this.piv=(DNodo<E>)p.next(piv);
			}
		}catch(Exception e) {
			
		}
		return elem;
	}

}
