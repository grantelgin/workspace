package matrix;

public class GraphDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GraphDriver me = new GraphDriver();
		me.doIt();
	}
	
	
	public void doIt() {
		
		
		EdgeClass g = new EdgeClass(5);
		
		g.connect(0, 3, 12);
		g.connect(3, 4, 6);
		g.connect(4, 2, 4);
		g.connect(3, 5, 7);
		g.connect(3, 0, 3);
		g.disconnect(3, 5);
		System.out.println(g);
		System.out.println(g.isConnected(3, 0));
		g.depthFirst(0);
		
		matrix();
	}
	
	public void matrix () {
		
		int[][] matrix = 
			{{0, 5, 2, -1, -1, 5, -1},
			{-1, 0, -1, -1, 2, 4, -1},
			{-1, -1, 0, -1, 3, -1, -1},
			{-1, -1, 3, 0, -1, -1, 4},
			{-1, -1, -1, -1, 0, -1, 2},
			{-1, -1, -1, -1, -1, 0, 3},
			{-1, -1, -1, -1, -1, -1, 0}};
	
		
		int best = dijkstras(matrix, 0);
		
	
	};
	
	
	private static int dijkstras(int[][] g, int start) // Added a start point.
    {
    // Dijkstra's Algorithm
    int[] best = new int[g.length];
    boolean[] visited = new boolean[g.length];
    int max = 10000; // Infinity equivalent.
    for (int i = 0; i < g.length; i++)
    {
        best[i] = max;
        visited[i] = false;
    }

    best[start] = start; // Changed the 0 to variable start.

    for(int i = 0; i < g.length; i++)
    {
        int min = max;
        int currentNode = 0;
        for (int j = 0; j < g.length; j++)
        {
            if (!visited[j] && best[j] < min && best[j] != -1)
            {
                currentNode = j;
                min = best[j];
                //System.out.println("min = " + min + ", currentNode = " + currentNode + ", j = " + j);
            }
        }
        visited[currentNode] = true;
        for (int j = 0; j < g.length; j++)
        {
            if (g[currentNode][j] < max && best[currentNode] +   g[currentNode][j] < best[j] && g[currentNode][j] != -1)
            {
                best[j] = best[currentNode] + g[currentNode][j];
                System.out.println("best = " + best[j] + ", currentNode = " + currentNode + ", j = " + j);
            }
	
        }
    }
    
            return best[g.length - 2];
}

}