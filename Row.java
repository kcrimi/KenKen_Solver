/*
 * This is the basic datastructure of rows in a kenken puzzle
 */
public class Row
{
  int[] cells; // array of cells contained in the row
  
  public Row(int paramInt1, int paramInt2)
  {
    this.cells = new int[paramInt2];
    for (int i = 0; i < paramInt2; i++) {
      this.cells[i] = (paramInt2 * paramInt1 + i);
    }
  }
  
  /*
   * checks if the number paramInt is not currently assigned to another cell in the row
   */
  public boolean available(int paramInt, int[] paramArrayOfInt)
  {
    boolean bool = true;
    for (int k : this.cells) {
      if (paramArrayOfInt[k] == paramInt)
      {
        bool = false;
        break;
      }
    }
    return bool;
  }
}