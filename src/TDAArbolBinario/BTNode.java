package TDAArbolBinario;

import TDALista.Position;

public class BTNode<E> implements Position<E> {
	private E element;
	private BTNode<E> left, right, parent;
	
	public BTNode(E elem, BTNode<E> par, BTNode<E> l, BTNode<E> r) {
		element=elem;
		left=l;
		right=r;
		parent=par;
	}
	
	public BTNode(E elem){
		element=elem;
		left=null;
		right=null;
		parent=null;
	}
	@Override
	public E element() {
		return element;
	}
	
	public BTNode<E> getLeft(){
		return left;
	}
	
	public BTNode<E> getRight(){
		return right;
	}
	public BTNode<E> getParent(){
		return parent;
	}
	
	public void setElement(E elem) {
		element=elem;
	} 
	public void setLeft(BTNode<E> l) {
		left=l;
	}
	public void setRight(BTNode<E> r) {
		right=r;
	}
	public void setParent(BTNode<E> par) {
		parent=par;
	}

}
