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
 * This solver completes 
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
      case '.': 
        j = 0;
        break;
      case '+': 
        j = 1;
        break;
      case '-': 
        j = 2;
        break;
      case '*': 
        j = 3;
        break;
      case '/': 
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
  
  private void createCells()
  {
    this.cells = new Cell[this.maxCell + 1];
    for (int i = 0; i <= this.maxCell; i++) {
      this.cells[i] = new Cell(i);
    }
  }
  
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
  
  public int[] generatePossibles(int paramInt)
  {
    int[] arrayOfInt = new int[paramInt];
    for (int i = 0; i < paramInt; i++) {
      arrayOfInt[i] = (i + 1);
    }
    return arrayOfInt;
  }
  
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