package TDAArbol;

import TDALista.InvalidPositionException;
import TDALista.Position;
import TDAMapeo.Entry;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;
import TDAMapeo.OpenHash;

public class Practica {
	
	public static Map<Character, Integer> cantidadRepeticiones(Tree<Character> t){
		Map<Character, Integer> toreturn = new OpenHash<Character, Integer>();
			if(!t.isEmpty())
				try {
					preOrden((TNode<Character>)t.root(), toreturn);
				} catch (EmptyTreeException e) {
					e.printStackTrace();
				}
		return toreturn;
	}
	
	public static Map<Character, Integer> preOrden(TNode<Character> p, Map<Character, Integer> m){
		try {
			if(m.get(p.element())==null) {
				m.put(p.element(), 1);
			}else m.put(p.element(), m.get(p.element())+1);
			for(TNode<Character> no: p.getChildren()) {
				preOrden(no, m);
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return m;
	}
	public static void main(String args[]) {
		Tree<Character> ar = new LinkedTree<Character>();
		try {
			ar.createRoot('a');
			Position<Character> raiz = ar.root();
			Position<Character> h1 =ar.addLastChild(raiz, 'a');
			Position<Character> h2 =ar.addLastChild(raiz, 'b');
			Position<Character> h3 =ar.addLastChild(raiz, 'c');
			ar.addLastChild(h1, 'a');
			ar.addLastChild(h2, 'a');
			ar.addLastChild(h2, 'b');
			ar.addLastChild(h3, 'a');
			
			Map<Character, Integer> m = cantidadRepeticiones(ar);
			for(Entry<Character, Integer> entrada:m.entries()) {
				System.out.println("Clave: "+entrada.getKey()+" Valor: "+entrada.getValue());
			}
		} catch (InvalidOperationException | EmptyTreeException | InvalidPositionException e) {
			e.printStackTrace();
		}
	}
}
