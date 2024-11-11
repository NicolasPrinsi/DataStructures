package TDAGraph;

import java.util.Iterator;

import TDACola.ColaConPila;
import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDALista.DoubleLinkedList;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo.Entrada;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;
import TDAMapeo.OpenHash;
import TDAPila.ArrayStack;
import TDAPila.EmptyStackException;
import TDAPila.LinkedStack;
import TDAPila.Stack;

public class Grafo<V,E> implements Graph<V,E> {
	private PositionList<Vertex<V>> vertices;
	private PositionList<Edge<E>> arcos;
	private int instanciarecorrido;
	public Grafo() {
		vertices=new DoubleLinkedList<Vertex<V>>();
		arcos=new DoubleLinkedList<Edge<E>>();
		instanciarecorrido=0;
	}
	@Override
	public Iterable<Vertex<V>> vertices() {
		return vertices;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		return arcos;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		VerticeInterno<V> pos = this.validateVertex(v);
		return pos.arcos;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		ArcoInterno<E> arc= this.validateEdge(e);
		VerticeInterno<V> vert = this.validateVertex(v);
		Vertex<V> toreturn= null;
		if(arc.getFirstVertex()==vert) {
			toreturn=arc.getSecondVertex();
		} else if (arc.getSecondVertex()==vert) {
			toreturn=arc.getFirstVertex();
		} else throw new InvalidEdgeException("Error, el vertice no pertenece al arco");
		return toreturn;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		ArcoInterno<E> arc = this.validateEdge(e);
		Vertex<V>[] toreturn= new Vertex[2];
		toreturn[0] = arc.getFirstVertex();
		toreturn[1] = arc.getSecondVertex();
		return toreturn;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		VerticeInterno<V> v1 = this.validateVertex(v);
		VerticeInterno<V> w1 = this.validateVertex(w);
		boolean toreturn = false;
		Iterator<Edge<E>> it = v1.getArcos().iterator();
		while(it.hasNext()&&!toreturn) {
			ArcoInterno<E> arc= (ArcoInterno<E> )it.next();
			if((arc.getFirstVertex()==v1&&arc.getSecondVertex()==w1)||(arc.getSecondVertex()==v1&&arc.getFirstVertex()==w1)) {
				toreturn=true;
			}
		}
		return toreturn;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		VerticeInterno<V> vert= this.validateVertex(v);
		V toreturn = vert.element();
		vert.setElemento(x);
		return toreturn;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		VerticeInterno<V> toreturn = null;
		try {
			toreturn = new VerticeInterno<V>(x);
			vertices.addLast(toreturn);
			toreturn.setPosition(vertices.last());
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return toreturn;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		VerticeInterno<V> v1 = this.validateVertex(v);
		VerticeInterno<V> w1 = this.validateVertex(w);
		ArcoInterno<E> toreturn=null;
		try {
			toreturn = new ArcoInterno<E>(e,v1,w1);
			arcos.addLast(toreturn);
			toreturn.setPosition(arcos.last());
			v1.getArcos().addLast(toreturn);
			toreturn.setFirstVertexPosition(v1.getArcos().last());
			w1.getArcos().addLast(toreturn);
			toreturn.setSecondVertexPosition(w1.getArcos().last());
		} catch(EmptyListException l) {
			l.printStackTrace();
		}
		return toreturn;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		VerticeInterno<V> vert= this.validateVertex(v);
		V toreturn = vert.element();
		for(Edge<E> ed: vert.getArcos()) {
			try {
				this.removeEdge(ed);
			} catch (InvalidEdgeException e) {
				e.printStackTrace();
			}
		}
		try {
			vertices.remove(vert.getPosicion());
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		vert.setElemento(null);
		vert.setPosition(null);
		return toreturn;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		ArcoInterno<E> arc= this.validateEdge(e);
		E toreturn = arc.element();
		try {
			VerticeInterno<V> v =(VerticeInterno<V>)arc.getFirstVertex();
			VerticeInterno<V> w =(VerticeInterno<V>)arc.getSecondVertex();
			v.getArcos().remove(arc.getFirstVertexPos());
			w.getArcos().remove(arc.getSecondVertexPos());
			arcos.remove(arc.getPosition());
			arc.setElement(null);
			arc.setFirstVertex(null);
			arc.setSecondVertex(null);
			arc.setPosition(null);
		} catch(InvalidPositionException l) {
			l.printStackTrace();
		}
		return toreturn;
	}
//Vertice y Arco
	private class VerticeInterno<V> implements Vertex<V>{
		private V elemento;
		private Position<Vertex<V>> pos;
		private PositionList<Edge<E>> arcos;
		private Map<Integer,Boolean> instVisitado;
		
		public VerticeInterno(V elem) {
			elemento=elem;
			arcos= new DoubleLinkedList<Edge<E>>();
			instVisitado= new OpenHash<Integer, Boolean>();
		}
		@Override
		public V element() {
			return elemento;
		}
		
		public Position<Vertex<V>> getPosicion(){
			return pos;
		}
		
		public PositionList<Edge<E>> getArcos(){
			return arcos;
		}
		public void setElemento(V elem) {
			elemento=elem;
		}
		public void setPosition(Position<Vertex<V>> p) {
			pos=p;
		}
		public Map<Integer, Boolean> getInstancia(){
			return instVisitado;
		}
	}
	
	private class ArcoInterno<E> implements Edge<E>{
		private E elemento;
		private Position<Edge<E>> pos, fvp, svp;
		private Vertex<V> v1,v2;
		
		public ArcoInterno(E elem, Vertex<V> vert1, Vertex<V> vert2) {
			elemento=elem;
			v1=vert1;
			v2=vert2;
		}
		@Override
		public E element() {
			return elemento;
		}
		
		public Vertex<V> getFirstVertex(){
			return v1;
		}
		
		public Vertex<V> getSecondVertex(){
			return v2;
		}
		
		public Position<Edge<E>> getPosition(){
			return pos;
		}
		public Position<Edge<E>> getFirstVertexPos(){
			return fvp;
		}
		public Position<Edge<E>> getSecondVertexPos(){
			return svp;
		}
		public void setPosition(Position<Edge<E>> p) {
			pos=p;
		}
		public void setFirstVertexPosition(Position<Edge<E>> fp) {
			fvp=fp;
		}
		public void setSecondVertexPosition(Position<Edge<E>> sp) {
			svp=sp;
		}
		
		public void setElement(E elem) {
			elemento=elem;
		}
		
		public void setFirstVertex(Vertex<V> ver1) {
			v1=ver1;
		}
		
		public void setSecondVertex(Vertex<V> ver2) {
			v2=ver2;
		}
	}
	
	private VerticeInterno<V> validateVertex(Vertex<V> v) throws InvalidVertexException{
		VerticeInterno<V> toreturn=null;
		if(v==null)
			throw new InvalidVertexException("Error, vertice nulo");
		if(v instanceof VerticeInterno) {
			try {
			toreturn=(VerticeInterno<V>) v;
			} catch(ClassCastException e) {
				e.printStackTrace();
			}
		} else throw new InvalidVertexException("No es un vertice perteneciente al grafo");
		return toreturn;
	}
	
	private ArcoInterno<E> validateEdge(Edge<E> e) throws InvalidEdgeException{
		ArcoInterno<E> toreturn=null;
		if(e==null)
			throw new InvalidEdgeException("Error, Arco Nulo");
		if(e instanceof ArcoInterno) {
			try {
				toreturn = (ArcoInterno<E>) e;
			}catch(ClassCastException c) {
				c.printStackTrace();
			}
		} else throw new InvalidEdgeException("No es un arco perteneciente al grafo");
		return toreturn;
	}
	
	public boolean DFSShellVertexBoole() {
		int instanciaActual=instanciarecorrido++;
		int toreturn=0;
		for(Vertex<V> v: this.vertices) {
			VerticeInterno<V> ver = (VerticeInterno<V>) v;
			try {
				ver.getInstancia().put(instanciaActual, false);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		for(Vertex<V> v: this.vertices) {
			VerticeInterno<V> ver = (VerticeInterno<V>) v;
			try {
				if(ver.getInstancia().get(instanciaActual)!=null&&ver.getInstancia().get(instanciaActual)==false) {
					toreturn++;
					DFSVertexBoole(this, v, instanciaActual);
				}
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Cantidad de partes conexas(Atributo Boole): "+toreturn);
		return toreturn==1;
	}
	
	private void DFSVertexBoole(Grafo<V,E> g, Vertex<V> v, int Instancia) {
		VerticeInterno<V> ver = (VerticeInterno<V>) v;
		try {
			ver.getInstancia().put(Instancia, true);
			for(Edge<E> e: g.incidentEdges(ver)) {
				VerticeInterno<V> op=(VerticeInterno<V>) g.opposite(ver, e);
				if(op.getInstancia().get(Instancia)!=null&&op.getInstancia().get(Instancia)==false) {
					DFSVertexBoole(g,op,Instancia);
				}
			}
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {
			e.printStackTrace();
		}
	}
	
	public boolean DFSShellMapeo() {
		int toreturn =0;
		Map<Vertex<V>, Boolean> visitas = new OpenHash<Vertex<V>, Boolean>();
		for(Vertex<V> v: this.vertices) {
			try {
				visitas.put(v, false);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		for(Vertex<V> v:this.vertices) {
			try {
				if(visitas.get(v)==false) {
					toreturn++;
					DFSMapeo(this, v, visitas);
				}
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Cantidad de partes conexas(Metodo Mapeo auxiliar): "+toreturn);
		return toreturn==1;
	}
	
	private void DFSMapeo(Grafo<V,E> g, Vertex<V> v, Map<Vertex<V>, Boolean> m) {
		try {
			m.put(v, true);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		try {
			for(Edge<E> e: g.incidentEdges(v)) {
				Vertex<V> op = g.opposite(v, e);
				if(m.get(op)==false) {
					DFSMapeo(g,op,m);
				}
			}
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {
			e.printStackTrace();
		}
	}
	
	public void BFSShell(Vertex<V> v) {
		int InstanciaActual= instanciarecorrido++;
		for (Vertex<V> ver:this.vertices){
			VerticeInterno<V> vert = (VerticeInterno<V>) ver;
			try {
				vert.getInstancia().put(InstanciaActual, false);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		BFS(this, v, InstanciaActual);
	}
	
	private void BFS(Grafo<V,E> g, Vertex<V> v,int Instancia) {
		Queue<Entrada<Vertex<V>,Integer>> cola = new ColaConPila<Entrada<Vertex<V>, Integer>>();
		cola.enqueue(new Entrada<Vertex<V>, Integer>(v,0));
		VerticeInterno<V> aux= (VerticeInterno<V>) v;
		try {
		aux.getInstancia().put(Instancia, true);
		while(!cola.isEmpty()) {
				Entrada<Vertex<V>,Integer> ent= cola.dequeue();
				System.out.println("Nivel: "+ent.getValue()+" Rotulo: "+ent.getKey().element());
				VerticeInterno<V> vert= (Grafo<V, E>.VerticeInterno<V>) ent.getKey();
				for(Edge<E> e: g.incidentEdges((Vertex<V>)vert)) {
					VerticeInterno<V> ver=(VerticeInterno<V>) g.opposite((Vertex<V>)vert, e);
					if(ver.getInstancia().get(Instancia)!=null&&ver.getInstancia().get(Instancia)==false) {
						ver.getInstancia().put(Instancia, true);
						cola.enqueue(new Entrada<Vertex<V>, Integer>(ver, ent.getValue()+1));
					}
				}
		}
		} catch (EmptyQueueException | InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {
			e.printStackTrace();
		}
	}
	
	public boolean BFScaminoMasCorto(Vertex<V> alpha, Vertex<V> omega) {
		int Instancia= instanciarecorrido++;
		for(Vertex<V> v:this.vertices) {
			VerticeInterno<V> castOff = (VerticeInterno<V>) v;
			try {
				castOff.getInstancia().put(Instancia, false);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		return this.BFSShortestPath(this, alpha, omega, Instancia);
	}
	
	private boolean BFSShortestPath(Grafo<V,E> g, Vertex<V> start, Vertex<V> end, int Instancia) {
		Queue<Vertex<V>> cola = new ColaConPila<Vertex<V>>();
		Map<Vertex<V>,Vertex<V>> parent = new OpenHash<Vertex<V>, Vertex<V>>();
		boolean found= false;
		
		try {
			parent.put(start, null);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		cola.enqueue(start);
		VerticeInterno<V> ini= (VerticeInterno<V>) start;
		try {
		ini.getInstancia().put(Instancia, true);
		while(!cola.isEmpty()&&!found) {
				VerticeInterno<V> act = (VerticeInterno<V>) cola.dequeue();
				for(Edge<E> e: act.getArcos()) {
					VerticeInterno<V> son =(VerticeInterno<V>) g.opposite(act, e);
					if(son.getInstancia().get(Instancia)!=null&&son.getInstancia().get(Instancia)==false) {
						cola.enqueue(son);
						son.getInstancia().put(Instancia, true);
						parent.put(son, act);
						if(son==end) {
							found=true;
						}
					}
				}
			
		}
	} catch (EmptyQueueException | InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {
		e.printStackTrace();
	}
		if(found) {
			try {
				Vertex<V> pos = parent.get(end);
				Stack<Vertex<V>> pila = new ArrayStack<Vertex<V>>(1000);
				pila.push(end);
				while(pos!=null) {
					pila.push(pos);
					pos= parent.get(pos);
				}
				while(!pila.isEmpty()) {
					
					System.out.println(pila.pop().element());
				}
			} catch (InvalidKeyException | EmptyStackException e) {
				e.printStackTrace();
			}
		} else System.out.println("Camino imposible");
		return found;
	}

}
