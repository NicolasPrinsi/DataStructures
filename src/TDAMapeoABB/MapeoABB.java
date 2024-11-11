package TDAMapeoABB;

import java.util.Comparator;

import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class MapeoABB<K,V> implements Map<K,V> {
	private int size;
	private Comparator<K> comp;
	private NodoABB<Entry<K,V>> root;
	
	public MapeoABB(Comparator<K> com) {
		size=0;
		comp=com;
		root= new NodoABB<Entry<K,V>>(null,null);
	}
	private void checkKey(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Error, llave nula");
	}
	
	private NodoABB<Entry<K,V>> buscar(K key){
		return buscarAux(key, root);
	}
	private NodoABB<Entry<K,V>> buscarAux(K key, NodoABB<Entry<K,V>> n){
		NodoABB<Entry<K,V>> toreturn = null;
		if(n.getElement()==null) {
			toreturn=n;
		}
		else if(n.getElement()!=null) {
			int c=comp.compare(key, n.getElement().getKey());
			if(c==0)
				toreturn=n;
			else 
				if (c<0)
					 toreturn = buscarAux(key, n.getLeft());
				else toreturn = buscarAux(key, n.getRight());
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
	public V get(K key) throws InvalidKeyException {
		this.checkKey(key);
		V toreturn=null;
		NodoABB<Entry<K,V>> n= this.buscar(key);
		if(n.getElement()!=null)
			toreturn=n.getElement().getValue();
		return toreturn;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		this.checkKey(key);
		NodoABB<Entry<K,V>> n= this.buscar(key);
		Entrada<K,V> ent = new Entrada<K,V>(key, value);
		V toreturn=null;
		if(n.getElement()!=null) {
			toreturn = n.getElement().getValue();
			n.setElement(ent);
		} else {
			n.setElement(ent);
			n.setLeft(new NodoABB<Entry<K,V>>(null, n));
			n.setRight(new NodoABB<Entry<K,V>>(null, n));
			size++;
		}
		return toreturn;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		this.checkKey(key);
		NodoABB<Entry<K,V>> n = this.buscar(key);
		V toreturn = null;
		if(n.getElement()!=null) {
			toreturn = n.getElement().getValue();
			eliminar(n);
		}
		return toreturn;
	}
	private void eliminar(NodoABB<Entry<K,V>> n) {
		if(n==root)
			this.eliminarRaiz();
		else {
			if(this.isExternal(n)) {
				n.setElement(null);
				n.setLeft(null);
				n.setRight(null);
			} else
				if(this.soloIzq(n)) {
					if(n.getParent().getLeft()==n)
						 n.getParent().setLeft(n.getLeft());
					else n.getParent().setRight(n.getLeft());
					n.getLeft().setParent(n.getParent());
				} else if(this.soloDer(n)) {
						if(n.getParent().getLeft()==n) {
							   n.getParent().setLeft(n.getRight());
						} else n.getParent().setRight(n.getRight());
						n.getRight().setParent(n.getParent());
				} else n.setElement(this.eliminarMin(n.getRight()));
		}
		size--;
	}
	private void eliminarRaiz() {
		if(this.isExternal(root)) {
			root.setElement(null);
			root.setLeft(null);
			root.setRight(null);
		} else { 
			if(this.soloIzq(root)) 
					root=root.getLeft();
			else if(this.soloDer(root))
				    root=root.getRight();
			else root.setElement(eliminarMin(root.getRight()));
			
		}
		root.setParent(null);
	}
	
	private Entry<K,V> eliminarMin(NodoABB<Entry<K,V>> n) {
		Entrada<K,V> toreturn=null;
		if(n.getLeft().getElement()==null) {
			toreturn = (Entrada<K,V>) n.getElement();
			if(this.isExternal(n)) {
				n.setLeft(null);
				n.setRight(null);
				n.setElement(null);
			} else {
				n.getParent().setLeft(n.getRight());
				n.getRight().setParent(n.getParent());
			}
		} else toreturn = (Entrada<K,V>) this.eliminarMin(n.getLeft());
		return toreturn;
	}
	
	@Override
	public Iterable<K> keys() {
		PositionList<K> toreturn = new DoubleLinkedList<K>();
		for(Entry<K,V> k:this.entries()) {
			toreturn.addLast(k.getKey());
		}
		return toreturn;
	}
	
	@Override
	public Iterable<V> values() {
		PositionList<V> toreturn = new DoubleLinkedList<V>();
		for(Entry<K,V> v : this.entries()) {
			toreturn.addLast(v.getValue());
		}
		return toreturn;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> toreturn = new DoubleLinkedList<Entry<K,V>>();
		if(!this.isEmpty()) {
			preOrden(root, toreturn);
		}
		return toreturn;
	}
	
	private void preOrden(NodoABB<Entry<K,V>> n, PositionList<Entry<K,V>> l) {
		l.addLast(n.getElement());
		if(n.getLeft().getElement()!=null) {
			preOrden(n.getLeft(),l);
		}
		if(n.getRight().getElement()!=null) {
			preOrden(n.getRight(),l);
		}
		
	}
	private boolean isExternal(NodoABB<Entry<K,V>> n) {
		return (n.getLeft().getElement()==null&&n.getRight().getElement()==null);
	}
	private boolean soloIzq(NodoABB<Entry<K,V>> n) {
		return (n.getLeft().getElement()!=null&&n.getRight().getElement()==null);
	}
	private boolean soloDer(NodoABB<Entry<K,V>> n) {
		return (n.getLeft().getElement()==null&&n.getRight().getElement()!=null);
	}
}
