package automata.graph;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;

public class MyGraph extends AbstractGraph {

	public MyGraph(GraphInterface graphInterface) {
		super(graphInterface);
	}

	@Override
	public String toStringA() {
		return graphInterface.toString();
	}

	@Override
	public void clearA() {
		graphInterface.clear();
	}

	@Override
	public int degreeA(Object vertex) {
		return graphInterface.degree(vertex);
	}

	@Override
	public int numberOfVerticesA() {
		return graphInterface.numberOfVertices();
	}

	@Override
	public Set adjacentA(Object vertex) {
		return graphInterface.adjacent(vertex);
	}

	@Override
	public void addEdgeA(Object vertex1, Object vertex2) {
		graphInterface.addEdge(vertex1, vertex2);
	}

	@Override
	public void removeEdgeA(Object vertex1, Object vertex2) {
		graphInterface.removeEdge(vertex1, vertex2); 
	}

	@Override
	public boolean hasEdgeA(Object vertex1, Object vertex2) {
		return graphInterface.hasEdge(vertex1, vertex2);
	}

	@Override
	public void addVertexA(Object vertex, Point2D point) {
		// TODO Auto-generated method stub
		graphInterface.addVertex(vertex, point);
	}

	@Override
	public void removeVertexA(Object vertex) {
		graphInterface.removeVertex(vertex);
	}

	@Override
	public void moveVertexA(Object vertex, Point2D point) {
		graphInterface.moveVertex(vertex, point);
	}

	@Override
	public Point2D pointForVertexA(Object vertex) {
		return graphInterface.pointForVertex(vertex);
	}

	@Override
	public Object[] verticesA() {
		return graphInterface.vertices();
	}

	@Override
	public Point2D[] pointsA() {
		return graphInterface.points();
	}

	@Override
	public void moveWithinFrameA(Rectangle2D bounds) {
		graphInterface.moveWithinFrame(bounds);
	}
}
