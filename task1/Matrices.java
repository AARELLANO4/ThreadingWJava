/**********************************************
 * Workshop 8 
 * Course: JAC444 - WINTER 2020
 * Last Name: Arellano
 * First Name: Alexis
 * ID: 155816184
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 *  - AA
 * Date: Tuesday November 24/2020
 * **********************************************/

package task1;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Matrices implements Runnable{

	private final double[][] matrix1;
	private final double[][] matrix2;
	
	public Matrices(double[][]a, double[][]b) {
		this.matrix1 = a;
		this.matrix2 = b;
	}
	
	@Override
	public void run() {
		double[][] sum = new double[matrix1.length][matrix1[0].length];
		
		for (int i = 0; i < matrix1.length; i++) {
			for (int j=0; j<matrix1[0].length; j++) {
				sum[i][j] = matrix1[i][j] + matrix2[i][j];
			}
		}
		
	}
	
	public static synchronized void parallelAddMatrix(double[][]a, double[][]b){
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Matrices(Arrays.copyOfRange(a, 0, 500),Arrays.copyOfRange(b, 0,500)));
		executorService.execute(new Matrices(Arrays.copyOfRange(a, 501, 1000),Arrays.copyOfRange(b, 501, 1000)));
		executorService.execute(new Matrices(Arrays.copyOfRange(a, 1001, 1500),Arrays.copyOfRange(b, 1001, 1500)));
		executorService.execute(new Matrices(Arrays.copyOfRange(a, 1501, 2000),Arrays.copyOfRange(b, 1501, 2000)));
		executorService.shutdown();
		System.out.println("... Parallel addition complete.");
	}
	
	public static double[][] sequentialAddMatrix(double[][]c, double[][]d){
		double[][] sum = new double[c.length][c[0].length];
		for (int i = 0; i < c.length; i++) {
			for (int j=0; j<c[0].length; j++) {
				sum[i][j] = c[i][j] + d[i][j];
			}
		}
		System.out.println("... Sequential addition complete.");
		return sum;
	}
	
	
	public static void main (String[] args) throws InterruptedException {
		
		double[][] mat1 = new double[2000][2000];
		for (int i = 0; i < mat1.length; i++) {
			for (int j = 0; j < mat1.length; j++) {
				mat1[i][j] = Math.random();
			}
		}
		
		double[][] mat2 = new double[2000][2000];
		for (int i = 0; i < mat2.length; i++) {
			for (int j = 0; j < mat2.length; j++) {
				mat2[i][j] = Math.random();
			}
		}
		
		long start, end;
		
		System.out.println("Sequential Matrix Addition: ");
		start = System.currentTimeMillis();
		double[][] sum = sequentialAddMatrix(mat1, mat2);
		end = System.currentTimeMillis();
		System.out.printf("Sequential matrix addition took %d milliseconds.\n\n", (end-start));
		
		System.out.println("Parallel Matrix Addition:");

		
		start = System.currentTimeMillis();
		parallelAddMatrix(mat1, mat2);
		end = System.currentTimeMillis();
		System.out.printf("Parallel matrix addition took %d milliseconds.\n", (end-start));
		
		
	}

}
