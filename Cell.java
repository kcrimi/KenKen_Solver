import java.util.Arrays;

/*
 * Basic datastructure of a cell in the KenKen puzzle
 */
public class Cell
{
  Cage cage;
  Row row;
  Column column;
  Cell nextCell;
  int number;
  
  public Cell(int paramInt)
  {
    this.number = paramInt;
    this.nextCell = null;
  }
  
  /*
   * Explores the possible values of the cell
   */
  public void explore(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    for (int k : paramArrayOfInt2) {
      if ((this.column.available(k, paramArrayOfInt1)) && (this.row.available(k, paramArrayOfInt1)))
      {
        paramArrayOfInt1[this.number] = k;
        if (this.cage.isFilled(paramArrayOfInt1))
        {
          if (this.cage.goalSatisfied(paramArrayOfInt1)) {
            if (this.nextCell == null) {
              KenKenPuzzleSolver.printSolution(paramArrayOfInt1);
            } else {
              this.nextCell.explore(Arrays.copyOf(paramArrayOfInt1, paramArrayOfInt1.length), paramArrayOfInt2);
            }
          }
        }
        else {
          this.nextCell.explore(Arrays.copyOf(paramArrayOfInt1, paramArrayOfInt1.length), paramArrayOfInt2);
        }
      }
    }
  }
}