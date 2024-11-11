package TP1;

public class ConjuntoArreglo<E> implements Conjunto<E> {
	private int cap;
	private int size;
	private E[] array;
	
	public ConjuntoArreglo(int c) {
		cap=c;
		size=0;
		array= (E[]) new Object[c];
	}
	public ConjuntoArreglo() {
		this(10);
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public int capacity() {
		return cap;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public E get(int i) {
		return array[i];
	}

	@Override
	public void put(E elem) {
		if(size<cap) {
			boolean belongs = false;
			int count=0;
			while(count<=(cap-1)&&!belongs) {
				if(elem.equals(array[count]))
					belongs=true;
				count++;
			}
			if(!belongs) {
				array[size]=elem;
				size++;
			}
			if(belongs)
				System.out.println("Ya la tiene adentro B)");
		} else System.out.println("ya la llenaron B)");
	}

	@Override
	public boolean pertenece(E elem) {
		int count=0;
		boolean toreturn = false;
		while(count<=(cap-1)&&!toreturn) {
			if(elem.equals(array[count]))
				toreturn=true;
			count++;
		}
		return toreturn;
	}

	@Override
	public Conjunto<E> interseccion(Conjunto<E> conj) {
		Conjunto<E> toreturn = null;
		if(this.size()>conj.size()) {
			Conjunto<E> o1 = new ConjuntoArreglo<E>(this.size());
			for (int i=0; i<conj.size();i++) {
				if(this.pertenece(conj.get(i)))
					o1.put(conj.get(i));
			}
			toreturn=o1;
		} else {
			Conjunto<E> o2 = new ConjuntoArreglo<E>(conj.size());
			for(int i=0; i<this.size();i++) {
				if(conj.pertenece(this.get(i)))
					o2.put(this.get(i));
			}
			toreturn = o2;
		} 
		return toreturn;
	}

	public String toString() {
		String toreturn="";
		
		for(int i=0; i<size;i++) {
			toreturn =toreturn+array[i]+" ";
		}
		return toreturn;
	}
}
