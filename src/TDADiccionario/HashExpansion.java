package TDADiccionario;

import TDAMapeo.Entry;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;

import TDAMapeo.OpenHash;

public class HashExpansion<K,V> extends HashDictionary<K,V>{
	
	public Dictionary<K,V> acomodar(Dictionary<K,V> d){
		Dictionary<K,V> toreturn = new HashDictionary<K,V>();
		Map<K,V> cleaner = new OpenHash<K,V>();
		for(Entry<K, V> e: d.entries()) {
			try {
				cleaner.put(e.getKey(), e.getValue());
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			}
		}
		for(Entry<K,V> e: cleaner.entries()) {
			try {
				toreturn.insert(e.getKey(), e.getValue());
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			}
		}
		return toreturn;
	}
}
