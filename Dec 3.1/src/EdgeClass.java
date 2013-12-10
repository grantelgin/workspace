
public class EdgeClass {
	
	private Vertex list[];
	private boolean visited[];
	
	public EdgeClass(int vertices) {
		list = new Vertex[vertices];
		visited = new boolean[vertices];
		
		for (int i = 0; i < list.length; i++) {
			list[i] = null;
			visited[i] = false;
		}
	}
	
	public void connect(int from, int to, int weight) {
		
		Vertex v = new Vertex();
		v.setWeight(weight);
		v.setVertex(to);
		v.setNext(null);
		
		if (list[from] == null) {
			list[from] = v;
		} else {
			v.setNext(list[from]);
			list[from] = v;
		}
	}
	
	public void disconnect(int from, int to) {
		if (list[from] == null) {
			return;
		} else {
			Vertex cur = list[from];
			Vertex prev = null;
			while (cur != null) {
				if (cur.getVertex() == to) {
					// Magic happens here
					if (prev == null) {
						list[from] = list[from].getNext();
					} else {
						prev.setNext(cur.getNext());
					}
					return;
				}
				prev = cur;
				cur = cur.getNext();
			}
		}
	}
	
	public int isConnected(int from, int to) {
		int rtn = -1;
		Vertex v = list[from];
		
		while (v != null) {
			if (v.getVertex() == to) {
				return v.getWeight();
			} 
			v = v.getNext();
		}
		
		return rtn;
	}
	
	
	public String toString() {
		String rtn = "";
		for (int i = 0; i < list.length; i++) {
			rtn += "V" + i + " = ";
			if (list[i] == null) {
				rtn += "<null>";
			} else {
				Vertex v = list[i];
				while (v != null) {
					rtn += "V" + v.getVertex() + "(" + v.getWeight() + ") ";
					v = v.getNext();
				}
			}
			rtn += "\n";
		}
		
		return rtn;
	}
	
	
	public void depthFirst(int vertex) {
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		
		dfHelper(vertex);
		for (int i = 0; i < visited.length; i++) {
			System.out.println("visited[" + i + "] = " + visited[i]);
		}
	}
	
	private void dfHelper(int vertex) {
		
		Vertex v = list[vertex];
		
		while (v != null) {
			if (visited[v.getVertex()] == false) {
				visited[v.getVertex()] = true;
				dfHelper(v.getVertex());
			}
			v = v.getNext();
		}
	}
	
	

}
