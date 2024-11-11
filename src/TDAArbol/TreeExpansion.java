package TDAArbol;

import java.util.Iterator;

import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;

public class TreeExpansion<E> extends LinkedTree<E> {
	
	public void eliminarUltimoHijo(Position<E> p) throws InvalidPositionException, InvalidOperationException{
		TNode<E> pos = this.chechPosition(p);
		if(pos==root)
			throw new InvalidOperationException("La raiz no es ultimo hijo");
		TNode<E> parent = pos.getParent();
		Iterator<Position<TNode<E>>> it = parent.getChildren().positions().iterator();
		Position<TNode<E>> epos =null;
		while(it.hasNext()&& epos==null) {
			Position<TNode<E>> piv = it.next();
			if(piv.element()==pos) {
				epos=piv;
			}
		}
		try {
			if(epos!=null&&epos==parent.getChildren().last()) {
				for(TNode<E> n: pos.getChildren()) {
					n.setParent(parent);
					parent.getChildren().addBefore(epos, n);
				}
				parent.getChildren().remove(epos);
				size--;
			}
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
	}
}
