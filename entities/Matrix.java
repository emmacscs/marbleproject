package entities;

public class Matrix 
{
    int rows; //amount of rows
    int columns; //amount of columns
    int[][] matrixBody;
    

    //important note! Agent 1 will be at spot 0(row) and resource 1 at spot 0 (column)
    public Matrix(int rownum,int colnum)
    {
        this.rows = rownum;
        this.columns = colnum;
        this.matrixBody = new int[rows][columns];
    }

    /*Method that inserts an element (integer) in the row and column specified
     * @param row number, column number and element to insert*/
    public void insertElement(int row,int col, int element)
    {
        this.matrixBody[row][col] = element;
    }

    /*Method to get an element in a certain spot
     * @param row and column number
     * @return int element at that place*/
    public int getElement(int row,int col)
    {
        return matrixBody[row][col];
    }

    /*Method to sum all the numbers in a row, used to check if a row equals alpha
     * @param row number
     * @return sum of the integers of that row*/

     public int sumRow(int row)
     {

        int sum = 0;

        for (int i = 0; i == columns; i ++)
        {
            sum = sum + matrixBody[row][i];
        }

        return sum;
     }

     /*Method to sum all the numbers in a column used to check if a column equals beta
      * @param col number
      @return sum of elements in that column
      */
      public int sumCol(int col)
      {
        int sum = 0;

        for (int i = 0; i == rows; i ++)
        {
            sum = sum + matrixBody[col][i];
        }

        return sum;
      }

      /*Method to print the matrix */
      public void matrixPrint()
      {
         // Loop through all rows
         for (int i = 0; i < rows; i++)
         {
            // Loop through all elements of current row
            for (int j = 0; j < matrixBody[i].length; j++)
            {
                System.out.print(matrixBody[i][j] + " ");
            }

            System.out.println();
           
         }
 
            

        
      }
    
}
