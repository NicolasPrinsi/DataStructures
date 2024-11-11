package TDAMapeoABB;

public class Entrada<K,V> implements Entry<K,V> {
	private K key;
	private V value;
	
	public Entrada(K k, V v) {
		key=k;
		value=v;
	}
	public void setKey(K k){
		key=k;
	}
	
	public void setValue(V v) {
		value=v;
	}
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

}
