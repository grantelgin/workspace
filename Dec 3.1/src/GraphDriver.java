
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
	}

}
