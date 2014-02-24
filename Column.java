public class Column
{
  int[] cells;
  
  public Column(int paramInt1, int paramInt2)
  {
    this.cells = new int[paramInt2];
    for (int i = 0; i < paramInt2; i++) {
      this.cells[i] = (paramInt1 + i * paramInt2);
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
