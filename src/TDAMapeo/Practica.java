package TDAMapeo;

import TDADiccionario.Dictionary;
import java.util.Iterator;

import TDADiccionario.HashExpansion;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;

public class Practica {

	public static PositionList<Entry<Integer, Integer>> mismaClaveDsitintoValor(Map<Integer,Integer> m1,Map<Integer,Integer> m2){
		PositionList<Entry<Integer, Integer>> toreturn = new DoubleLinkedList<Entry<Integer,Integer>>();
		for(int i: m1.keys()) {
			try {
				Integer aux= m2.get(i);
				Integer val= m1.get(i);
				if(aux!=null&&aux!=val) {
					toreturn.addLast(new Entrada<Integer,Integer>(i, val));
					toreturn.addLast(new Entrada<Integer,Integer>(i, aux));
				} 
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		return toreturn;
	}
	
	public static boolean m1contenidom2(Map<Integer, Integer> m1, Map<Integer, Integer> m2) {
		boolean contenido=true;
		Iterator<Integer> it = m1.keys().iterator();
		while(it.hasNext()&&contenido) {
			int i = it.next();
			try {
				if(m2.get(i)==null) {
					contenido=false;
				}
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		return contenido;
	}
	public static void main (String args[]) {
		//Problema 1
		Map<Integer, Integer> m1 = new OpenHash<Integer, Integer>();
		Map<Integer, Integer> m2 = new OpenHash<Integer, Integer>();
		try {
			m1.put(1, 2);
			m1.put(2, 3);
			m1.put(4, 5);
			m1.put(6, 7);
			m1.put(8, 9);
			String m1s="";
			int pm1=1;
			for(Entry<Integer, Integer> e:m1.entries()) {
				m1s=m1s+"Posicion "+pm1+" Clave: "+e.getKey()+" Valor: "+e.getValue()+" |";
				pm1++;
			}
			System.out.println("Mapeo 1");
			System.out.println(m1s);
			m2.put(1, 1);
			m2.put(2, 3);
			m2.put(4, 6);
			m2.put(6, 7);
			m2.put(8, 10);
			String m2s="";
			int pm2=1;
			for(Entry<Integer, Integer> e:m2.entries()) {
				m2s=m2s+"Posicion "+pm2+" Clave: "+e.getKey()+" Valor: "+e.getValue()+" ";
				pm2++;
			}
			System.out.println("Mapeo 2");
			System.out.println(m2s);
			PositionList<Entry<Integer, Integer>> pl = mismaClaveDsitintoValor(m1, m2);
			for(Entry<Integer, Integer> p: pl) {
				System.out.println("Clave: "+p.getKey()+" Valor: "+p.getValue());
			}
			//Problema 2
			Map<Integer, Integer> m3 = new OpenHash<Integer, Integer>();
			m3.put(1, 2);
			m3.put(2, 3);
			m3.put(4, 5);
			m3.put(6, 7);
			m3.put(8, 9);
			m3.put(10, 11);
			Map<Integer, Integer> m4 = new OpenHash<Integer, Integer>();
			m4.put(7, 2);
			m4.put(2, 3);
			m4.put(4, 5);
			m4.put(6, 7);
			m4.put(8, 9);
			m4.put(10, 11);
			System.out.println("m1 contenido en m2 "+m1contenidom2(m1, m2));
			System.out.println("m1 contenido en m3 "+m1contenidom2(m1, m3));
			System.out.println("m3 contenido en m1 "+m1contenidom2(m3, m1));
			System.out.println("m3 contenido en m4 "+m1contenidom2(m3, m4));
			//Problema 3
			HashExpansion<Integer, Character> dic= new HashExpansion<Integer, Character>();
			dic.insert(1, 'a');
			dic.insert(2, 'b');
			dic.insert(3, 'a');
			dic.insert(2, 'c');
			dic.insert(1, 'd');
			dic.insert(4, 'b');
			System.out.println("Diccionario a acomodar: ");
			for(Entry<Integer, Character> ent: dic.entries()) {
				System.out.println("Entrada: "+ent.getKey()+" Valor: "+ent.getValue());
			}
			Dictionary<Integer, Character> acom = dic.acomodar(dic);
			System.out.println("Diccionario acomodado: ");
			for(Entry<Integer, Character> ent: acom.entries()) {
				System.out.println("Entrada: "+ent.getKey()+" Valor: "+ent.getValue());
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
	}
}
