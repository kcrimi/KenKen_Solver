import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/*
 * This solver completes a KenKen puzzle from a text file that contains the constraints of the puzzle's cages
 */

public class KenKenPuzzleSolver
{
  public Cell[] cells;
  Column[] columns;
  Row[] rows;
  ArrayList<Cage> cages = new ArrayList();
  int maxCell;
  Cell rootCell;
  int[] choice;
  int[] possibles;
  static PrintStream out;
  
  /*
   * Set up a new puzzle
   */
  public KenKenPuzzleSolver(String[] paramArrayOfString)
  {
    try
    {
      createCages(paramArrayOfString);
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
    createCells();
    
    int i = (int)Math.sqrt(this.maxCell + 1);
    createColumns(i);
    createRows(i);
    cellSort();
    this.choice = new int[this.maxCell + 1];
    Arrays.fill(this.choice, 0);
    

    int[] arrayOfInt = generatePossibles(i);
    this.rootCell.explore(this.choice, arrayOfInt);
  }
  
  /*
   *  Entry point, takes in the filename of the puzzle constraints
   */
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      out = new PrintStream(new FileOutputStream(paramArrayOfString[1]));
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
    System.setOut(out);
    KenKenPuzzleSolver localKenKenPuzzleSolver = new KenKenPuzzleSolver(paramArrayOfString);
  }
  
  /*
   * Parses the puzzle from the constraints of the cages in the text file with the format being
   * [cage value] [operator] <list of cells in cage>
   * Ex. 50 * 1 2 10 = cells 1, 2, and 10 should multiply together to make 50
   */
  public void createCages(String[] paramArrayOfString)
    throws FileNotFoundException
  {
    int j = -1;
    this.maxCell = 0;
    int i = 0;
    

    Scanner localScanner = new Scanner(new FileInputStream(paramArrayOfString[0]));
    while (localScanner.hasNext())
    {
      String str = localScanner.nextLine();
      String[] arrayOfString = str.split(" ");
      switch (arrayOfString[1].charAt(0))
      {
      case '.': // equals
        j = 0;
        break;
      case '+': // addition
        j = 1;
        break;
      case '-': // subtraction
        j = 2;
        break;
      case '*': // multiplication
        j = 3;
        break;
      case '/': // division
        j = 4;
      }
      this.cages.add(new Cage(Integer.parseInt(arrayOfString[0]), j));
      for (int m = 2; m < arrayOfString.length; m++)
      {
        int k = Integer.parseInt(arrayOfString[m]);
        ((Cage)this.cages.get(i)).cells.add(Integer.valueOf(k));
        if (k > this.maxCell) {
          this.maxCell = k;
        }
      }
      i++;
    }
    localScanner.close();
  }
  
  /*
   * Create the cells in the puzzle
   */
  private void createCells()
  {
    this.cells = new Cell[this.maxCell + 1];
    for (int i = 0; i <= this.maxCell; i++) {
      this.cells[i] = new Cell(i);
    }
  }
  
  /*
   * Create all the columns and assign it to all of its contained cells
   */
  private void createColumns(int paramInt)
  {
    this.columns = new Column[paramInt];
    for (int i = 0; i < paramInt; i++)
    {
      this.columns[i] = new Column(i, paramInt);
      for (int j = 0; j < paramInt; j++) {
        this.cells[(i + j * paramInt)].column = this.columns[i];
      }
    }
  }
  
  /*
   * Create all the rows and assign it to all of its contained cells
   */
  private void createRows(int paramInt)
  {
    this.rows = new Row[paramInt];
    for (int i = 0; i < paramInt; i++)
    {
      this.rows[i] = new Row(i, paramInt);
      for (int j = 0; j < paramInt; j++) {
        this.cells[(paramInt * i + j)].row = this.rows[i];
      }
    }
  }
  
  /*
   * Sort the cages and cages  by the number of cells in them (ease of solving)
   */
  private void cellSort()
  {
    Collections.sort(this.cages, new Comparator()
    {
      public int compare(Cage paramAnonymousCage1, Cage paramAnonymousCage2)
      {
        if (paramAnonymousCage1.cells.size() > paramAnonymousCage2.cells.size()) {
          return 1;
        }
        if (paramAnonymousCage1.cells.size() < paramAnonymousCage2.cells.size()) {
          return -1;
        }
        return 0;
      }
    });
    Cell localCell = null;
    this.rootCell = this.cells[((Integer)((Cage)this.cages.get(0)).cells.get(0)).intValue()];
    for (int i = 0; i < this.cages.size(); i++) {
      for (int j = 0; j < ((Cage)this.cages.get(i)).cells.size(); j++)
      {
        if ((i != 0) || (j != 0)) {
          localCell.nextCell = this.cells[((Integer)((Cage)this.cages.get(i)).cells.get(j)).intValue()];
        }
        this.cells[((Integer)((Cage)this.cages.get(i)).cells.get(j)).intValue()].cage = ((Cage)this.cages.get(i));
        localCell = this.cells[((Integer)((Cage)this.cages.get(i)).cells.get(j)).intValue()];
      }
    }
  }
  
  /*
   * Generate the possibilities for the current cell 
   */
  public int[] generatePossibles(int paramInt)
  {
    int[] arrayOfInt = new int[paramInt];
    for (int i = 0; i < paramInt; i++) {
      arrayOfInt[i] = (i + 1);
    }
    return arrayOfInt;
  }
  
  /*
   * Print out the solution of the puzzle into the out file
   */
  public static void printSolution(int[] paramArrayOfInt)
  {
    for (int i = 0; i < paramArrayOfInt.length; i++)
    {
      System.out.print(paramArrayOfInt[i] + " ");
      if ((i + 1) % Math.sqrt(paramArrayOfInt.length) == 0.0D) {
        System.out.println("");
      }
    }
    System.out.print("\n");
  }
}