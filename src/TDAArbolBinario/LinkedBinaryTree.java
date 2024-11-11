package TDAArbolBinario;

import java.util.Iterator;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class LinkedBinaryTree<E> implements BinaryTree<E> {
	protected int size;
	protected BTNode<E> root;
	
	protected BTNode<E> chechPosition(Position<E> v) throws InvalidPositionException {
		BTNode<E> toreturn=null;
		if(v==null) {
			throw new InvalidPositionException("Error, posicion nula");
		}
		if(this.isEmpty()) {
			throw new InvalidPositionException("Error, arbol vacio");
		}
		try {
			toreturn = (BTNode<E>) v;
		}catch(ClassCastException e) {
			e.printStackTrace();
		}
		return toreturn;
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
	public Iterator<E> iterator() {
		PositionList<E> toreturn = new DoubleLinkedList<E>();
		for(Position<E> pos: this.positions()) {
			toreturn.addLast(pos.element());
		}
		return toreturn.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toreturn = new DoubleLinkedList<Position<E>>();
		if(!this.isEmpty()) {
			preOrden(root, toreturn);
		}
		return toreturn;
	}
	
	private void preOrden(BTNode<E> p, PositionList<Position<E>> l) {
		l.addLast(p);
		if(p.getLeft()!=null) {
			preOrden(p.getLeft(),l);
		}
		if(p.getRight()!=null) {
			preOrden(p.getRight(),l);
		}
	}
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		E toreturn = pos.element();
		pos.setElement(e);
		return toreturn;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(this.isEmpty())
			throw new EmptyTreeException("Error, un arbol vacio no tiene raiz");
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> pos = this.chechPosition(v);
		if(pos==root)
			throw new BoundaryViolationException("La raiz carece de padre");
		return pos.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		PositionList<Position<E>> toreturn = new DoubleLinkedList<Position<E>>();
		if(pos.getLeft()!=null) {
			toreturn.addLast(pos.getLeft());
		}
		if(pos.getRight()!=null) {
			toreturn.addLast(pos.getRight());
		}
		return toreturn;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		return (pos.getLeft()!=null||pos.getRight()!=null);
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		return (pos.getLeft()==null&&pos.getRight()==null);
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		return pos==root;
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> pos = this.chechPosition(v);
		if(pos.getLeft()==null)
			throw new BoundaryViolationException("Error, no hay hijo izquierdo");
		return pos.getLeft();
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> pos = this.chechPosition(v);
		if(pos.getRight()==null)
			throw new BoundaryViolationException("Error, no hay hijo derecho");
		return pos.getRight();
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		return (pos.getLeft()!=null);
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		return (pos.getRight()!=null);
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if(root!=null)
			throw new InvalidOperationException("El arbol no esta vacio y por lo tanto no se puede crear una raiz");
		root= new BTNode<E>(r);
		size=1;
		return root;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNode<E> parent= this.chechPosition(v);
		if(parent.getLeft()!=null)
			throw new InvalidOperationException("Error, ya cuenta con un hijo izquierdo");
		BTNode<E> son= new BTNode<E>(r);
		son.setParent(parent);
		parent.setLeft(son);
		size++;
		return son;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNode<E> parent= this.chechPosition(v);
		if(parent.getRight()!=null)
			throw new InvalidOperationException("Error, ya cuenta con hijo derecho");
		BTNode<E> son= new BTNode<E>(r);
		son.setParent(parent);
		parent.setRight(son);
		size++;
		return son;
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		if(pos.getLeft()!=null&&pos.getRight()!=null)
			throw new InvalidOperationException("Error, no se puede eliminar un nodo con ambos hijos");
		BTNode<E> hijo = (pos.getLeft()!=null ? pos.getLeft():pos.getRight());
		if(hijo!=null)
			hijo.setParent(pos.getParent());
		if(pos==root) {
			root=hijo;
		}else {
			BTNode<E> padre = pos.getParent();
			if(padre.getLeft()==pos)
				padre.setLeft(hijo);
			else padre.setRight(hijo);
		}
		size--;
		E toreturn = pos.element();
		pos.setElement(null);
		pos.setParent(null);
		pos.setLeft(null);
		pos.setRight(null);
		return toreturn;
	}

	@Override
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(r);
		if(pos.getLeft()!=null||pos.getRight()!=null) 
			throw new InvalidPositionException("Error, el nodo debe ser hoja");
		size+=T1.size()+T2.size();
		if(!T1.isEmpty()) {
			try {
				BTNode<E> ra=(BTNode<E>) T1.root();
				ra.setParent(pos);
				pos.setLeft(ra);
			} catch (EmptyTreeException e) {
				e.printStackTrace();
			}
		}
		if(!T2.isEmpty()) {
			try {
				BTNode<E> ra = (BTNode<E>) T2.root();
				ra.setParent(pos);
				pos.setRight(ra);
			} catch (EmptyTreeException e) {
				e.printStackTrace();
			}
		}
		
	}

}
