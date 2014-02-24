/*
 * This is the basic datastructure of the 
 */
public class Row
{
  int[] cells;
  
  public Row(int paramInt1, int paramInt2)
  {
    this.cells = new int[paramInt2];
    for (int i = 0; i < paramInt2; i++) {
      this.cells[i] = (paramInt2 * paramInt1 + i);
    }
  }
  
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