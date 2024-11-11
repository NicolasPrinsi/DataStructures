package TP3;

import java.util.ArrayList;
import java.util.Stack;

public class MetodosLista<E> {
	
	public boolean Tp3e1a(ArrayList<E> l, E x){
		boolean found = false;
		int index=0;
		while(!found&&index<l.size()) {
			if(l.get(index).equals(x))
				found=true;
			index++;
		}
		return found;
	} 
	
	public boolean Tp3e1b(ArrayList<E> l, E x, int c) {
		boolean toreturn =false;
		int index=0;
		int count=0;
		while(index<l.size()&&count<c) {
			if(l.get(index)==x)
				count++;
			index++;
		}
		toreturn = count>=c;
		return toreturn;
	}
	public boolean Tp3e1c(ArrayList<E>l, E x, int c) {
		boolean toreturn=false;
		int index=0;
		int count=0;
		while(index<l.size()&&count<=c+1) {
			if(l.get(index)==x)
				count++;
			index++;
		}
		toreturn=count==c;
		return toreturn;
	}
	
	public ArrayList<E> Tp3e2a(ArrayList<E> l1, ArrayList<E> l2){
		ArrayList<E> toreturn = new ArrayList<E>();
		int l1i=0;
		int l2i=0;
		while(l1i<l1.size()&&l2i<l2.size()) {
			toreturn.add(l1.get(l1i));
			toreturn.add(l2.get(l2i));
			l1i++;
			l2i++;
		}
		while(l1i<l1.size()) {
			toreturn.add(l1.get(l1i));
			l1i++;
		}
		while(l2i<l2.size()) {
			toreturn.add(l2.get(l2i));
			l2i++;
		}
		return toreturn;	
	}
	
	public ArrayList<E> Tp3e2b(ArrayList<E>l1, ArrayList<E> l2){
		ArrayList<E> toreturn= new ArrayList<E>();
		int l1i=0;
		int l2i=0;
		while(l1i<l1.size()&&l2i<l2.size()) {
			if(toreturn.indexOf(l1.get(l1i))==-1) {
				toreturn.add(l1.get(l1i));
			}
			if(toreturn.indexOf(l2.get(l2i))==-1) {
				toreturn.add(l2.get(l2i));
			}
			l1i++;
			l2i++;
		}
		while(l1i<l1.size()) {
			if(toreturn.indexOf(l1.get(l1i))==-1) {
				toreturn.add(l1.get(l1i));
			}
			l1i++;
		}
		while(l2i<l2.size()) {
			if(toreturn.indexOf(l2.get(l2i))==-1) {
				toreturn.add(l2.get(l2i));
			}
			l2i++;
		}
		return toreturn;
	}
	
	public void Tp3e3(ArrayList<E> l) {
		Stack<E> s = new Stack<E>();
		while(!l.isEmpty()) {
			s.push(l.remove(0));
		}
		while(!s.isEmpty()) {
			l.add(s.pop());
		}
	}
	
	public boolean Tp3e4(ArrayList<E> l1, ArrayList<E> l2) {
		boolean toreturn=true;
		if(l1.size()==2*l2.size()) {
			int l1i=0;
			while(l1i<l2.size()&& toreturn) {
				if(!l1.get(l1i).equals(l2.get(l1i)))
					toreturn=false;
				l1i++;
			}
			int l2i=l2.size()-1;
			while(l2i>=0 && toreturn) {
				if(!l1.get(l1i).equals(l2.get(l2i)))
					toreturn=false;
				l1i++;
				l2i--;
			}
		} else toreturn=false;
		return toreturn;
	}
	
	public void Tp3e5(ArrayList<E> l1, ArrayList<E> l2) {
		int i = 0;
		while(i<l2.size()) {
			if(l1.contains(l2.get(i))) {
				l1.remove(l2.get(i));
			}
			i++;
		}
		int i2=l2.size()-1;
		while(i2>=0) {
			l1.add(l2.get(i2));
			i2--;
		}
	}
}
