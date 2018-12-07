
	import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
	 

	public class StrassanMatrix{
		

		public static int a[][] ;
		public static int b[][] ;
		
	  
		 public static void main (String[] args) throws IOException
		    {
		    	
		    	Scanner keyboardScanner = new Scanner(System.in);
		    	
		    	  System.out.println("Enter the input file name:");
			        // reading the input text file from the user
			        String inputFileName = keyboardScanner.nextLine();
		 
			        System.out.println("Enter the output file name:");
			        // reading the output text file from the user
			        String outputFileName = keyboardScanner.nextLine();
			        
			        
			        System.out.println("Enter Matrices dimension:");
			        // reading the input dimension from the user
			        int dimension = keyboardScanner.nextInt();
			        
			        System.out.println("Enter base case number power of two :");
			        // reading the input dimension from the user
			        int value = keyboardScanner.nextInt();
			        
			        // getting the file from the user
			        Scanner fileScanner = new Scanner(new File(inputFileName));
			        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));


			        // initializing the arrays
			        a = new int[(int) Math.pow(2, dimension)][(int) Math.pow(2, dimension)];
			        b = new int[(int) Math.pow(2, dimension)][(int) Math.pow(2, dimension)];
			         
			        // reading the matrices and storing it in the 'a' and 'b'
				    readingMetrices(fileScanner,a,b);
				  
				    StrassanMatrix s = new StrassanMatrix();
		 
				    int[][] result = s.multiply(a, b,value);
				    // writing to the file 

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
		 
		
	    public int[][] multiply(int[][] A, int[][] B,int value)
	    {        
	        int n = A.length;
	        int[][] R = new int[n][n];
	        if (n == value)
	        	   for (int i = 0; i < value; i++) { 
			            for (int j = 0; j < value; j++) { 
			            		R[i][j] = 0;
			                for (int k = 0; k < value; k++) { 
			                    R[i][j] += A[i][k] * B[k][j];
			                }
			            }
			        }
	        else
	        {
	            int[][] A11 = new int[n/2][n/2];
	            int[][] A12 = new int[n/2][n/2];
	            int[][] A21 = new int[n/2][n/2];
	            int[][] A22 = new int[n/2][n/2];
	            int[][] B11 = new int[n/2][n/2];
	            int[][] B12 = new int[n/2][n/2];
	            int[][] B21 = new int[n/2][n/2];
	            int[][] B22 = new int[n/2][n/2];
	 
	            // Dividing matrix A into 4 halves **/
	            split(A, A11, 0 , 0);
	            split(A, A12, 0 , n/2);
	            split(A, A21, n/2, 0);
	            split(A, A22, n/2, n/2);
	            // Dividing matrix B into 4 halves 
	            split(B, B11, 0 , 0);
	            split(B, B12, 0 , n/2);
	            split(B, B21, n/2, 0);
	            split(B, B22, n/2, n/2);
	  
	 
	            int [][] M1 = multiply(add(A11, A22), add(B11, B22),value);
	            int [][] M2 = multiply(add(A21, A22), B11,value);
	            int [][] M3 = multiply(A11, sub(B12, B22),value);
	            int [][] M4 = multiply(A22, sub(B21, B11),value);
	            int [][] M5 = multiply(add(A11, A12), B22,value);
	            int [][] M6 = multiply(sub(A21, A11), add(B11, B12),value);
	            int [][] M7 = multiply(sub(A12, A22), add(B21, B22),value);
	 
	     
	            int [][] C11 = add(sub(add(M1, M4), M5), M7);
	            int [][] C12 = add(M3, M5);
	            int [][] C21 = add(M2, M4);
	            int [][] C22 = add(sub(add(M1, M3), M2), M6);
	 
	            
	            join(C11, R, 0 , 0);
	            join(C12, R, 0 , n/2);
	            join(C21, R, n/2, 0);
	            join(C22, R, n/2, n/2);
	        }
	          
	        return R;
	    }
	    
	    // Funtion to sub two matrices 
	    public int[][] sub(int[][] A, int[][] B)
	    {
	        int n = A.length;
	        int[][] C = new int[n][n];
	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < n; j++)
	                C[i][j] = A[i][j] - B[i][j];
	        return C;
	    }
	    
	    // Funtion to add two matrices 
	    public int[][] add(int[][] A, int[][] B)
	    {
	        int n = A.length;
	        int[][] C = new int[n][n];
	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < n; j++)
	                C[i][j] = A[i][j] + B[i][j];
	        return C;
	    }
	    
	    // Funtion to split parent matrix into child matrices 
	    public void split(int[][] P, int[][] C, int iB, int jB) 
	    {
	        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
	            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
	                C[i1][j1] = P[i2][j2];
	    }
	    
	    // Funtion to join child matrices intp parent matrix 
	    public void join(int[][] C, int[][] P, int iB, int jB) 
	    {
	        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
	            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
	                P[i2][j2] = C[i1][j1];
	    }    

	   
	    
	    
	   
	}
	

