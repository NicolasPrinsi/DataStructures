package TP1;

import java.util.Vector;

public class ConjuntoVector<E> implements Conjunto<E> {

	private int size;
	private Vector<E> v;
	
	public ConjuntoVector() {
		v= new Vector<E>();
	}
	@Override
	public int size() {
		return v.size();
	}

	@Override
	public int capacity() {
		return v.capacity();
	}

	@Override
	public boolean isEmpty() {
		return v.isEmpty();
	}

	@Override
	public E get(int i) {
		return v.get(i);
	}

	@Override
	public void put(E elem) {
		if(!v.contains(elem)) {
			v.add(elem);
		}
	}

	@Override
	public boolean pertenece(E elem) {
		return v.contains(elem);
	}

	@Override
	public Conjunto<E> interseccion(Conjunto<E> conj) {
		Conjunto<E> toreturn = new ConjuntoVector<E>();
		if(v.size()>conj.size())
			for (int i=0; i<conj.size();i++) {
				if(v.contains(conj.get(i)))
					toreturn.put(conj.get(i));
			}
		else for(int j=0; j<v.size();j++) {
			if(conj.pertenece(v.get(j))) {
				toreturn.put(v.get(j));
			}
		}
		return toreturn;
	}

	public String toString() {
		String toreturn="";
		
		for(int i=0; i<v.size();i++) {
			toreturn =toreturn+v.get(i)+" ";
		}
		return toreturn;
	}
}
