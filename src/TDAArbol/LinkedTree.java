package TDAArbol;

import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class LinkedTree<E> implements Tree<E>{
	protected int size;
	protected TNode<E> root;
	
	public LinkedTree() {
		size=0;
		root=null;
	}
	
	protected TNode<E> chechPosition(Position<E> v) throws InvalidPositionException {
		TNode<E> toreturn=null;
		if(v==null) {
			throw new InvalidPositionException("Error, posicion nula");
		}
		if(this.isEmpty()) {
			throw new InvalidPositionException("Error, arbol vacio");
		}
		try {
			toreturn = (TNode<E>) v;
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
			preOrdenAuxiliar(this.root, toreturn);
		}
		return toreturn;
	}
	
	private void preOrdenAuxiliar(TNode<E> pos, PositionList<Position<E>> lista) {
		lista.addLast(pos);
		if(!pos.getChildren().isEmpty()) {
			for(TNode<E> p: pos.getChildren())
				preOrdenAuxiliar(p, lista);
		}
	}
	
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNode<E> p= this.chechPosition(v);
		E old = null;
		old=p.element();
		p.setElement(e);
		return old;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(this.isEmpty())
			throw new EmptyTreeException("Error el arbol esta vacio carece de raiz");
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNode<E> pos = this.chechPosition(v);
		if(pos==root)
			throw new BoundaryViolationException("Error, la raiz no tiene padre");
		return pos.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNode<E> p = this.chechPosition(v);
		PositionList<Position<E>> toreturn = new DoubleLinkedList<Position<E>>();
		for(TNode<E> el:p.getChildren()) {
			toreturn.addLast(el);
		}
		return toreturn;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNode<E> pos = this.chechPosition(v);
		return !pos.getChildren().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNode<E> pos = this.chechPosition(v);
		return pos.getChildren().isEmpty();
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNode<E> pos = this.chechPosition(v);
		return pos==root;
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if(!this.isEmpty())
			throw new InvalidOperationException("Error, este arbol ya tiene raiz");
		root= new TNode<E>(e);
		size++;
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNode<E> padre = this.chechPosition(p);
		TNode<E> hijo = new TNode<E>(e, padre);
		padre.getChildren().addFirst(hijo);
		size++;
		return hijo;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNode<E> padre = this.chechPosition(p);
		TNode<E> hijo = new TNode<E>(e, padre);
		padre.getChildren().addLast(hijo);
		size++;
		return hijo;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNode<E> padre = this.chechPosition(p);
		TNode<E> rbrother = this.chechPosition(rb);
		TNode<E> nhijo= new TNode<E>(e, padre);
		Iterator<Position<TNode<E>>> it = padre.getChildren().positions().iterator();
		Position<TNode<E>> rbpos=null;
		while(it.hasNext()&&rbpos==null) {
			Position<TNode<E>> apos= it.next();
			if(apos.element()==rbrother)
				rbpos=apos;
		}
		if(rbpos!=null) {
			padre.getChildren().addBefore(rbpos, nhijo);
		} else throw new InvalidPositionException("Error las posiciones pasadas no son padre/hijo");
		size++;
		return nhijo;
	}
	
	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNode<E> padre = this.chechPosition(p);
		TNode<E> lbrother = this.chechPosition(lb);
		TNode<E> nhijo= new TNode<E>(e, padre);
		Iterator<Position<TNode<E>>> it = padre.getChildren().positions().iterator();
		Position<TNode<E>> lbpos=null;
		while(it.hasNext()&&lbpos==null) {
			Position<TNode<E>> apos= it.next();
			if(apos.element()==lbrother)
				lbpos=apos;
		}
		if(lbpos!=null) {
			padre.getChildren().addAfter(lbpos, nhijo);
		} else throw new InvalidPositionException("Error las posiciones pasadas no son padre/hijo");
		size++;
		return nhijo;
	}
	

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNode<E> pos = this.chechPosition(p);
		if(this.isRoot(p)) {
			if(this.isExternal(p)) {
				root=null;
				size--;
			} else throw new InvalidPositionException("Nodo Interno");
		}else {
			if(this.isInternal(p))
				throw new InvalidPositionException("La posicion no es hoja");
			else {
				TNode<E> padre = pos.getParent();
				Position<TNode<E>> hp = null;
				Iterator<Position<TNode<E>>> it = padre.getChildren().positions().iterator();
				while(it.hasNext()&&hp==null) {
					Position<TNode<E>> piv =it.next();
					if(piv.element()==pos)
						hp=piv;
				}
				if(hp==null)
					throw new InvalidPositionException("Error, no es hijo");
				padre.getChildren().remove(hp);
				size--;			
			}
		}
	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNode<E> pos= this.chechPosition(p);
		if(this.isExternal(pos))
			throw new InvalidPositionException("Error, posicion Externa");
		else {
			if(this.isRoot(pos)) {
				if(pos.getChildren().size()==1) {
					try {
						root= pos.getChildren().first().element();
						root.setParent(null);
						size--;
					} catch (EmptyListException e) {
						e.printStackTrace();
					}
				} else throw new InvalidPositionException("No se puede eliminar una raiz interna con multiples hijos");	
			} else {
				TNode<E> nparent = pos.getParent();
				Position<TNode<E>> op =null;
				Iterator<Position<TNode<E>>> it = nparent.getChildren().positions().iterator();
				while(it.hasNext()&&op==null) {
					Position<TNode<E>> piv = it.next();
					if(piv.element()==pos)
						op=piv;
				}
				if(op==null)
					throw new InvalidPositionException("pasaron cosas");
				for(TNode<E> son: pos.getChildren()) {
					son.setParent(nparent);
					nparent.getChildren().addBefore(op, son);
				}
				nparent.getChildren().remove(op);
				size--;
			}
		}
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		if(this.isExternal(p))
			this.removeExternalNode(p);
		else if(this.isInternal(p))
			this.removeInternalNode(p);
	}

}
