package automata.graph;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;

public abstract class AbstractGraph {
	
	public GraphInterface graphInterface;
	
	public AbstractGraph(GraphInterface graphInterface) {
		this.graphInterface = graphInterface;
	}
	
	public abstract void clearA();

	public abstract int degreeA(Object vertex);

	public abstract int numberOfVerticesA();

	public abstract Set adjacentA(Object vertex);

	public abstract void addEdgeA(Object vertex1, Object vertex2);

	public abstract void removeEdgeA(Object vertex1, Object vertex2);

	public abstract boolean hasEdgeA(Object vertex1, Object vertex2);

	public abstract void addVertexA(Object vertex, Point2D point);

	public abstract void removeVertexA(Object vertex);

	public abstract void moveVertexA(Object vertex, Point2D point);

	public abstract Point2D pointForVertexA(Object vertex);

	public abstract Object[] verticesA();

	public abstract Point2D[] pointsA();

	public abstract void moveWithinFrameA(Rectangle2D bounds);
	
	public abstract String toStringA();
}