package TDAMapeo;

import java.util.ArrayList;
import java.util.Iterator;

import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class OpenHash<K,V> implements Map<K,V> {
	private PositionList<Entry<K,V>>[] buckets;
	private  final float lf = 0.5f;
	private int size;
	private int prime;
	
	public OpenHash() {
		prime=7;
		buckets =new PositionList[prime];
		for (int i=0; i<buckets.length;i++) {
			buckets[i]=new DoubleLinkedList<Entry<K,V>>();
		}
		size=0;
	}
	
	private int hashThis(K k) {
		return (k.hashCode() % prime);
	}
	
	private void refactor() {
		int np = NextPrimeFinder.nextPrime(prime);
		PositionList<Entry<K,V>>[] nbucket = new PositionList[np];
		for (int i=0; i<np;i++) {
			nbucket[i]=new DoubleLinkedList<Entry<K,V>>();
		}
		for (int j=0; j<prime;j++) {
			for(Entry<K,V> e: buckets[j]) {
				int npos= (e.getKey().hashCode()%np);
				nbucket[npos].addLast(e);
			}
		}
		prime=np;
		buckets=nbucket;
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
		if(key==null)
			throw new InvalidKeyException("Error, clave invalida");
		V toreturn=null;
		int s= this.hashThis(key);
		Iterator<Entry<K,V>> it =buckets[s].iterator();
		while(it.hasNext()&&toreturn==null) {
			Entry<K,V> pos=it.next();
			if(pos.getKey()==key)
				toreturn=pos.getValue();
		}
		return toreturn;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Error, clave invalida");
		V toreturn=null;
		int s= this.hashThis(key);
		Iterator<Entry<K,V>> it = buckets[s].iterator();
		while(it.hasNext()&&toreturn==null) {
			Entry<K,V> pos = it.next();
			if(pos.getKey()==key) {
				toreturn=pos.getValue();
				Entrada<K,V> ac= (Entrada<K,V>) pos;
				ac.setValue(value);
			}
		}
		if(toreturn==null) {
			buckets[s].addLast(new Entrada<K,V>(key, value));
			size++;
		}
		if((size/prime)>lf)
			refactor();
		return toreturn;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Error, clave invalida");
		V toreturn = null;
		int s= this.hashThis(key);
		Iterator<Position<Entry<K,V>>> it = buckets[s].positions().iterator();
		while(it.hasNext()&&toreturn==null) {
			Position<Entry<K,V>> pos= it.next();
			if(pos.element().getKey()==key) {
					try {
						toreturn = pos.element().getValue();
						buckets[s].remove(pos);
						size--;
					} catch (InvalidPositionException e) {
						e.printStackTrace();
					}
				
			}
		}
		if(toreturn==null)
			System.out.println("No hay elemento asociado a esta llave que pertenezca al mapa");
		return toreturn;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> toreturn = new DoubleLinkedList<K>();
		for(int i=0;i<prime;i++) {
			for(Entry<K,V> e: buckets[i]) {
				toreturn.addLast(e.getKey());
			}
		}
		return toreturn;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> toreturn = new DoubleLinkedList<V>();
		for(int i=0;i<prime;i++) {
			for(Entry<K,V> e: buckets[i]) {
				toreturn.addLast(e.getValue());
			}
		}
		return toreturn;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> toreturn = new DoubleLinkedList<Entry<K,V>>();
		for(int i=0;i<prime;i++) {
			for(Entry<K,V> e: buckets[i]) {
				toreturn.addLast(e);
			}
		}
		return toreturn;
	}

}
