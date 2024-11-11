package TDAArbol;

import TDALista.DoubleLinkedList;
import TDALista.Position;
import TDALista.PositionList;

public class TNode<E> implements Position<E> {
	private E element;
	private TNode<E> parent;
	private PositionList<TNode<E>> children;
	
	public TNode(E elem) {
		element=elem;
		parent=null;
		children = new DoubleLinkedList<TNode<E>>();
	}
	
	public TNode(E elem, TNode<E> pa) {
		element=elem;
		parent=pa;
		children = new DoubleLinkedList<TNode<E>>();
	}
	
	@Override
	public E element() {
		return element;
	}
	
	public TNode<E> getParent(){
		return parent;
	}
	
	public PositionList<TNode<E>> getChildren(){
		return children;
	}
	
	public void setElement(E elem) {
		element=elem;
	}
	
	public void setParent(TNode<E> pa) {
		parent = pa;
	}
	
	

}
