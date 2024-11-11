package TDAColaCP;

import TDAMapeo.Entrada;
import TDAMapeo.Entry;
import TDAMapeo.InvalidKeyException;

public class HeapPQueueOld<K,V> implements PriorityQueue<K,V> {
	private int size;
	private Comparador comp;
	private Entrada<K,V>[] elems;
	public HeapPQueueOld(Comparador<K> comp) {
		size=0;
		this.comp=comp;
		elems =(Entrada<K,V>[]) new Entrada[10000];
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
	public Entry min() throws EmptyPriorityQueueException {
		if(this.isEmpty())
			throw new EmptyPriorityQueueException("Error, cola vacia");
		return elems[1];
	}

	@Override
	public Entry insert(Object key, Object value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Error, clave nula");
		size++;
		if(size==elems.length-2) {
			this.resize();
		}
		System.out.println(elems.length);
		elems[size]= new Entrada(key,value);
		System.out.println(elems[size].getKey()+" "+elems[size].getValue());
		int pos=size;
		boolean reached=false;
		while(pos>1&&!reached) {
			int parent=pos/2;
			if(this.esMenor(elems[pos], elems[parent])) {
				this.theOldSwitcharoo(pos, parent);
				pos=parent;
			}else reached=true;
		}
		return elems[pos];
	}
	private void resize() {
		Entrada<K,V>[] nelems= (Entrada<K,V>[]) new Entrada[(elems.length*2)];
		for (int i=1;i<=size;i++) {
			nelems[i]=elems[i];
		}
		elems=nelems;
		System.out.println(elems.length);
	}
	@Override
	public Entry removeMin() throws EmptyPriorityQueueException {
		if(this.isEmpty())
			throw new EmptyPriorityQueueException("Error, cola vacia");
		//Retorno
		Entry toreturn = elems[1];
		//muevo el ultimo elemento a la raiz
		elems[1]=elems[size];
		elems[size]=null;
		size--;
		//reordeno
		int pos= 1;
		boolean reached = false;
		while(pos<=size&&!reached) {
			int izq=pos*2;
			int der=(pos*2)+1;
			if(elems[izq]!=null&&elems[der]!=null) {
				if(this.esMenor(elems[izq], elems[der])) {
					if(this.esMenor(elems[izq], elems[pos])) {
						this.theOldSwitcharoo(izq, pos);
						pos=izq;
					} else reached=true;
				} else {
					if(this.esMenor(elems[der], elems[pos])) {
						this.theOldSwitcharoo(der, pos);
						pos=der;
					}else reached=true;
				}
			} else if(elems[izq]!=null&&elems[der]==null) {
				if(this.esMenor(elems[izq], elems[pos])) {
					this.theOldSwitcharoo(izq, pos);
					pos=izq;
				}else reached=true;
			} else reached=true;
		}
		return toreturn;
	}
	
	private boolean esMenor(Entrada k1, Entrada k2) {
		return comp.compare(k1.getKey(), k2.getKey())<0;
	}
	private void theOldSwitcharoo(int p1, int p2) {
		Entrada temp= elems[p1];
		elems[p1]=elems[p2];
		elems[p2]=temp;
	}
}
