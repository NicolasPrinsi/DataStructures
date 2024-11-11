package TDAGraph;

public class TesteoBFS {
	
	public static void main(String args[]) {
		Grafo<Character, Integer> gra = new Grafo<Character, Integer>();
		Vertex<Character> a = gra.insertVertex('A');
		Vertex<Character> b = gra.insertVertex('B');
		Vertex<Character> c = gra.insertVertex('C');
		Vertex<Character> d = gra.insertVertex('D');
		Vertex<Character> e = gra.insertVertex('E');
		Vertex<Character> f = gra.insertVertex('F');
		Vertex<Character> g = gra.insertVertex('G');
		Vertex<Character> h = gra.insertVertex('H');
		Vertex<Character> i = gra.insertVertex('I');
		Vertex<Character> j = gra.insertVertex('J');
		Vertex<Character> k = gra.insertVertex('K');
		Vertex<Character> l = gra.insertVertex('L');
		Vertex<Character> m = gra.insertVertex('M');
		Vertex<Character> n = gra.insertVertex('N');
		Vertex<Character> o = gra.insertVertex('O');
		Vertex<Character> p = gra.insertVertex('P');
		Vertex<Character> q = gra.insertVertex('Q');
		try {
			gra.insertEdge(a, b, 1);
			gra.insertEdge(a, e, 1);
			gra.insertEdge(a, f, 1);
			gra.insertEdge(b, f, 1);
			gra.insertEdge(b, c, 1);
			gra.insertEdge(c, g, 1);
			gra.insertEdge(c, d, 1);
			gra.insertEdge(d, g, 1);
			gra.insertEdge(d, h, 1);
			gra.insertEdge(e, i, 1);
			gra.insertEdge(e, f, 1);
			gra.insertEdge(f, i, 1);
			gra.insertEdge(g, j, 1);
			gra.insertEdge(g, k, 1);
			gra.insertEdge(g, l, 1);
			gra.insertEdge(h, l, 1);
			gra.insertEdge(i, j, 1);
			gra.insertEdge(i, m, 1);
			gra.insertEdge(i, n, 1);
			gra.insertEdge(j, k, 1);
			gra.insertEdge(k, n, 1);
			gra.insertEdge(k, o, 1);
			gra.insertEdge(l, p, 1);
			gra.insertEdge(m, n, 1);
			
		} catch (InvalidVertexException e1) {
			e1.printStackTrace();
		}
		System.out.println(gra.BFScaminoMasCorto(a, p));
		System.out.println(gra.BFScaminoMasCorto(a, q));
		
	}
}
