package TDAGraph;

public class HighwayToTheTestingZone {
	
	public static void main(String args[]) {
		Grafo<Integer,Character> g = new Grafo<Integer,Character>();
		System.out.println("Sin Vertices: "+g.DFSShellVertexBoole()+" / "+ g.DFSShellMapeo());
		Vertex<Integer> t1= g.insertVertex(1);
		System.out.println("Vertice Uno: "+g.DFSShellVertexBoole()+" / "+ g.DFSShellMapeo());
		Vertex<Integer> t2=g.insertVertex(2);
		System.out.println("Vertice 1 sin arco a un Vertice 2: "+g.DFSShellVertexBoole()+" / "+ g.DFSShellMapeo());
		try {
			g.insertEdge(t2, t1, 'a');
			System.out.println("Vertice 1 con arco a Vertice 2: "+g.DFSShellVertexBoole()+" / "+ g.DFSShellMapeo());
		} catch (InvalidVertexException e) {
			e.printStackTrace();
		}
		Vertex<Integer> t3 = g.insertVertex(3);
		System.out.println("Vertice 1 con arco a vertice 2 y un vertice 3 sin arcos: "+g.DFSShellVertexBoole()+" / "+ g.DFSShellMapeo());
		try {
			g.insertEdge(t3, t1, 'a');
			System.out.println("Vertice 1 con arcos a vertice 2 y 3: "+g.DFSShellVertexBoole()+" / "+ g.DFSShellMapeo());
		} catch (InvalidVertexException e) {
			e.printStackTrace();
		}
		Vertex<Integer> t4=g.insertVertex(4);
		Vertex<Integer> t5=g.insertVertex(5);
		try {
			g.insertEdge(t2, t4, 'h');
			g.insertEdge(t4, t5, 'i');
		} catch (InvalidVertexException e) {
			e.printStackTrace();
		}
		System.out.println("Vertices 1,2 y 3 conectados con arcos, 4 y 5 no conectados: "+g.DFSShellVertexBoole()+" / "+ g.DFSShellMapeo());
		
		//g.BFSShell(t1);
	}
}
