package TDAArbolBinario;

import java.util.Iterator;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDAArbol.TNode;
import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class ArbolBinario<E> implements BinaryTree<E> {
	private int size;
	private BTNode<E> root;
	
	public ArbolBinario() {
		root=null;
		size=0;
	}
	
	private BTNode<E> chechPosition(Position<E> v) throws InvalidPositionException {
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
		for(Position<E> p: this.positions()) {
			toreturn.addLast(p.element());
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
	private void preOrden(BTNode<E> p,PositionList<Position<E>>l) {
		l.addLast(p);
		if(p.getLeft()!=null)
			preOrden(p.getLeft(), l);
		if(p.getRight()!=null)
			preOrden(p.getRight(),l);
	}
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		E toreturn= pos.element();
		pos.setElement(e);
		return toreturn;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(this.isEmpty())
			throw new EmptyTreeException("Error, arbol vacio");
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> pos = this.chechPosition(v);
		if(pos==root)
			throw new BoundaryViolationException("Error, no se puede obtener padre de la raiz");
		return pos.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos= this.chechPosition(v);
		PositionList<Position<E>> toreturn = new DoubleLinkedList<Position<E>>();
		if(pos.getLeft()!=null)
			toreturn.addLast(pos.getLeft());
		if(pos.getRight()!=null)
			toreturn.addLast(pos.getRight());
		return toreturn;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		return (pos.getLeft()!=null|pos.getRight()!=null);
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		return (pos.getLeft()==null&&pos.getRight()==null);
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos= this.chechPosition(v);
		return pos==root;
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> pos = this.chechPosition(v);
		if(pos.getLeft()==null)
			throw new BoundaryViolationException("Error, no hay hijo izquierdo");
		BTNode<E> toreturn = pos.getLeft();
		return toreturn;
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> pos = this.chechPosition(v);
		if(pos.getRight()==null)
			throw new BoundaryViolationException("Error, no hay hijo derech");
		BTNode<E> toreturn = pos.getRight();
		return toreturn;
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		return pos.getLeft()!=null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTNode<E> pos = this.chechPosition(v);
		return pos.getRight()!=null;
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if(!this.isEmpty())
			throw new InvalidOperationException("Error, no se puede crear raiz a un arbol que ya tiene");
		root=new BTNode<E>(r);
		size=1;
		return root;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNode<E> parent = this.chechPosition(v);
		if(parent.getLeft()!=null)
			throw new InvalidOperationException("El nodo ya cuenta con un hijo izquierdo");
		BTNode<E> toreturn = new BTNode<E>(r);
		toreturn.setParent(parent);
		parent.setLeft(toreturn);
		size++;
		return toreturn;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNode<E> parent = this.chechPosition(v);
		if(parent.getRight()!=null)
			throw new InvalidOperationException("El nodo ya cuenta con un hijo derecho");
		BTNode<E> toreturn = new BTNode<E>(r);
		toreturn.setParent(parent);
		parent.setRight(toreturn);
		size++;
		return toreturn;
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		BTNode<E> p = this.chechPosition(v);
			if(p.getLeft()!=null&&p.getRight()!=null)
				throw new InvalidOperationException("no se puede eliminar un elemento con 2 hijos");
		E toreturn = p.element();
		BTNode<E> hijo = p.getLeft()!=null ? p.getLeft():p.getRight() ;
		if(hijo!=null) {
			hijo.setParent(p.getParent());
		}
		if(p==root) {
			root=hijo;
		}else {
			BTNode<E> padre= p.getParent();
			if(p==padre.getLeft()) {
				padre.setLeft(hijo);
			} else padre.setRight(hijo);
		}
		size--;
		p.setElement(null);
		p.setRight(null);
		p.setLeft(null);
		p.setParent(p);
		return toreturn;
	}

	@Override
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		BTNode<E> h= this.chechPosition(r);
		if(this.isInternal(h))
			throw new InvalidPositionException("no se puede anexar poque la posicion no es hija");
		size+= T1.size()+T2.size();
		if(!T1.isEmpty()) {
			try {
				BTNode<E> T1r = (BTNode<E>) T1.root();
				h.setLeft(T1r);
				T1r.setParent(h);
			} catch (EmptyTreeException e) {
				e.printStackTrace();
			}
		}
		if(!T2.isEmpty()) {
			try {
				BTNode<E> T2r = (BTNode<E>) T2.root();
				h.setRight(T2r);
				T2r.setParent(h);
			} catch (EmptyTreeException e) {
				e.printStackTrace();
			}
		}	
	}

}
