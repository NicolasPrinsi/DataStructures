package TDADiccionario;

import java.util.ArrayList;
import java.util.Iterator;

import TDALista.DoubleLinkedList;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo.Entrada;
import TDAMapeo.Entry;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.NextPrimeFinder;

public class HashDictionary<K,V> implements Dictionary<K,V> {
	private PositionList<Entry<K,V>>[] bucket;
	private final float lf =0.5f;
	private int size;
	private int prime;
	
	public HashDictionary() {
		prime=7;
		bucket =new PositionList[prime];
		for (int i=0; i<bucket.length;i++) {
			bucket[i]=new DoubleLinkedList<Entry<K,V>>();
		}
		size=0;
	}
	
	private int hashThis(K k) {
		return (k.hashCode() % prime);
	}
	
	private void refactor() {
		int np = NextPrimeFinder.nextPrime(prime);
		System.out.println(np);
		PositionList<Entry<K,V>>[] nbucket = new PositionList[np];
		for (int i=0; i<np;i++) {
			nbucket[i]=new DoubleLinkedList<Entry<K,V>>();
		}
		for (int j=0; j<prime;j++) {
			for(Entry<K,V> e: bucket[j]) {
				int npos= (e.getKey().hashCode()%np);
				nbucket[npos].addLast(e);
			}
		}
		prime=np;
		bucket=nbucket;
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
	public Entry<K, V> find(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Error, clave invalida");
		Entry<K,V> toreturn=null;
		int s= this.hashThis(key);
		Iterator<Entry<K,V>> it =bucket[s].iterator();
		while(it.hasNext()&&toreturn==null) {
			Entry<K,V> pos=it.next();
			if(pos.getKey().equals(key))
				toreturn=pos;
		}
		return toreturn;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Error, clave invalida");
		DoubleLinkedList<Entry<K,V>> toreturn= new DoubleLinkedList<Entry<K,V>>();
		int s= this.hashThis(key);
		Iterator<Entry<K,V>> it =bucket[s].iterator();
		while(it.hasNext()) {
			Entry<K,V> pos=it.next();
			if(pos.getKey().equals(key))
				toreturn.addLast(pos);
		}
		return toreturn;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Error, clave invalida");
		int s=this.hashThis(key);
		Entry<K,V> toinsert = new Entrada<K,V>(key, value);
		bucket[s].addLast(toinsert);
		size++;
		if(size/prime>lf) {
			refactor();
		}
		return toinsert;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if(e==null)
			throw new InvalidEntryException("Error, entrada invalida");
		int s = this.hashThis(e.getKey());
		Entry<K,V> toreturn=null;
		Iterator<Position<Entry<K,V>>> it= bucket[s].positions().iterator();
		while(it.hasNext()&&toreturn==null) {
			Position<Entry<K,V>> pos = it.next();
			if(pos.element().getKey()==e.getKey()&&pos.element().getValue()==e.getValue()) {
				try {
					toreturn = pos.element();
					bucket[s].remove(pos);
					size--;
				} catch (InvalidPositionException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(toreturn==null)
			throw new InvalidEntryException("Error, entrada no encontrada");
		return toreturn;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		DoubleLinkedList<Entry<K,V>> toreturn = new DoubleLinkedList<Entry<K,V>>();
		for(int i=0;i<prime;i++) {
			for(Entry<K,V> pos:bucket[i])
				toreturn.addLast(pos);
		}
		return toreturn;
	}

}
