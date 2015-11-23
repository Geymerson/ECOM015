package automata.graph;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;

public interface GraphInterface {

	/** Clears all vertices and edges. */
	public void clear();

	/** Returns the degree of a vertex. */
	public int degree(Object vertex);

	/** Returns the number of vertices. */
	public int numberOfVertices();

	/** Returns the set of vertices a vertex is adjacent to. */
	public Set adjacent(Object vertex);

	/** Adds an edge between two vertices. */
	public void addEdge(Object vertex1, Object vertex2);

	/** Removes an edge between two vertices. */
	public void removeEdge(Object vertex1, Object vertex2);

	/** Returns if an edge exists between two vertices. */
	public boolean hasEdge(Object vertex1, Object vertex2);

	/** Adds a vertex. */
	public void addVertex(Object vertex, Point2D point);

	/** Removes a vertex. */
	public void removeVertex(Object vertex);

	/** Moves a vertex to a new point. */
	public void moveVertex(Object vertex, Point2D point);

	/** Returns the point for a given vertex. */
	public Point2D pointForVertex(Object vertex);

	/** Returns the list of vertex objects. */
	public Object[] vertices();
	/**
	 * Returns the list of vertex points. The order they appear is not
	 * necessarily the same as the vertices.
	 */
	public Point2D[] points();

	/** Reforms the points so they are enclosed within a certain frame. */
	public void moveWithinFrame(Rectangle2D bounds);

	/** Returns a string description of the graph. */
	public String toString();
}
