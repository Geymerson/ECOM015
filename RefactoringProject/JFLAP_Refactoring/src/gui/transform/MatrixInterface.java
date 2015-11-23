package gui.transform;

import java.awt.geom.Point2D;

public interface MatrixInterface {
	
	public Object clone();

	public double valueAt(int row, int column);

	public void premultiply(Matrix matrix);

	public void postmultiply(Matrix matrix);

	public void pitch(double angle);
	
	public void roll(double angle);
	
	public void yaw(double angle);

	public void translate(double x, double y, double z);

	public Point2D origin(Point2D point);

	public double[] origin(double[] array);

	public String toString();

}
