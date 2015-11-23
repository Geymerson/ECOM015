package gui.transform;

import java.util.HashMap;

public class MatrixFactory {
	
	private static final HashMap<String, MatrixInterface> matrixMap = new HashMap<>();
	
	public static MatrixInterface getMatrix(String matrixType) {
		Matrix matrix = (Matrix) matrixMap.get(matrixType);
		
		if(matrixType == null) {
			matrix = new Matrix();
			matrixMap.put(matrixType, matrix);
		}
		return matrix;
	}
	
	public static MatrixInterface getMatrix(String matrixType,
			double a11, double a12, double a13, double a14,
			double a21, double a22, double a23, double a24,
			double a31, double a32, double a33, double a34) {
		
		Matrix matrix = (Matrix) matrixMap.get(matrixType);
		
		if(matrixType == null) {
			matrix = new Matrix(a11, a12, a13, a14,
								a21, a22, a23, a24,
								a31, a32, a33, a34);
			matrixMap.put(matrixType, matrix);
		}
		return matrix;
	}
	
	public static MatrixInterface getMatrix(String matrixType, Matrix m) {
		
		Matrix matrix = (Matrix) matrixMap.get(matrixType);
		
		if(matrixType == null) {
			matrix = new Matrix(m);
			matrixMap.put(matrixType, matrix);
		}
		return matrix;
	}
}
