import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.*;
import java.util.Scanner;

public class IterativeMatrix {

	public static int a[][] ;
	public static int b[][] ;
	
		    public static void main(String[] args) throws IOException {
		        Scanner keyboardScanner = new Scanner(System.in);

		        System.out.println("Enter the input file name");
		        // reading the input text file from the user
		        String inputFileName = keyboardScanner.nextLine();

		        System.out.println("Enter the output file name");
		        // reading the output text file from the user
		        String outputFileName = keyboardScanner.nextLine();

		        
		        System.out.println("Enter Matrices dimension");
		        // reading the input dimension from the user
		        int dimension = keyboardScanner.nextInt();


		        // getting the file from the user
		        Scanner fileScanner = new Scanner(new File(inputFileName));
		        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));


		        // initializing the arrays
		        a = new int[(int) Math.pow(2, dimension)][(int) Math.pow(2, dimension)];
		         b = new int[(int) Math.pow(2, dimension)][(int) Math.pow(2, dimension)];

		       

		        // reading the matrices and storing it in the 'a' and 'b'
		        readingMetrices(fileScanner,a,b);
		      
		        

		        int result[][] = matrixMultiplicationFinal(a, b);
		        // writing to the file 
		        System.out.println("Finish computation");
		        int count = 0;
		        writer.flush();
		        for (int i = 0; i < b.length; i++) {
		            for (int j = 0; j < b.length; j++) {
		               count++;
		               writer.append(result[i][j] + " ");
		               if(count % Math.pow(2, dimension) == 0) {
		                   writer.append("\n");
		               }
		            }
		        }

		    }

		    private static void readingMetrices(Scanner fileScanner, int a[][] , int b[][]) {
		    	  for (int i = 0; i < a.length; i++) {
			            for (int j = 0; j < a.length; j++) {
			                a[i][j] = fileScanner.nextInt();
			            }
			        }
			        for (int i = 0; i < b.length; i++) {
			            for (int j = 0; j < b.length; j++) {
			                b[i][j] = fileScanner.nextInt();
			            }
			        }
				
			}

			public static int[][] matrixMultiplicationFinal(int[][] A, int[][] B) {

		        return matrixMultiplication( A, B, A.length);
	 }
			
		    public static int[][] matrixMultiplication(int[][] A, int[][] B,  int size) {

		        int[][] C = new int[size][size];

		        for (int i = 0; i < size; i++) {
		            for (int j = 0; j < size; j++) {
		                C[i][j] = 0;
		            }
		        }
		        for (int i = 0; i < size; i++) { 
		            for (int j = 0; j < size; j++) { 
		                for (int k = 0; k < size; k++) { 
		                    C[i][j] += A[i][k] * B[k][j];
		                }
		            }
		        }
		        return C;
		    }
		    
}

		
		
		
	


