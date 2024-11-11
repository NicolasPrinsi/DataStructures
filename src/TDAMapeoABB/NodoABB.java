package TDAMapeoABB;

public class NodoABB<E> {

	private E element;
	private NodoABB<E> left,right,parent;
	
	public NodoABB(E elem, NodoABB<E> par) {
		element=elem;
		parent=par;
	}
//Consultas
	public E getElement() {
		return element;
	}
	
	public NodoABB<E> getParent(){
		return parent;
	}
	
	public NodoABB<E> getLeft(){
		return left;
	}
	
	public NodoABB<E> getRight(){
		return right;
	}
//Metodos
	public void setElement(E elem) {
		element=elem;
	}
	
	public void setParent(NodoABB<E> par) {
		parent=par;
	}
	
	public void setLeft(NodoABB<E> l) {
		left=l;
	}
	
	public void setRight(NodoABB<E> r) {
		right=r;
	}
}
