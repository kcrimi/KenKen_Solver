import java.util.ArrayList;
import java.util.Iterator;

public class Cage
{
  ArrayList<Integer> cells;
  Cage nextCage;
  int goal;
  int operator;
  
  public Cage(int paramInt1, int paramInt2)
  {
    this.cells = new ArrayList();
    this.nextCage = null;
    this.goal = paramInt1;
    this.operator = paramInt2;
  }
  
  public int currentValue(int[] paramArrayOfInt)
  {
    int i = 0;
    for (Iterator localIterator = this.cells.iterator(); localIterator.hasNext();)
    {
      int j = ((Integer)localIterator.next()).intValue();
      
      i += paramArrayOfInt[j];
    }
    return i;
  }
  
  public boolean isFilled(int[] paramArrayOfInt)
  {
    boolean bool = true;
    for (Iterator localIterator = this.cells.iterator(); localIterator.hasNext();)
    {
      int i = ((Integer)localIterator.next()).intValue();
      if (paramArrayOfInt[i] == 0)
      {
        bool = false;
        break;
      }
    }
    return bool;
  }
  
  public boolean goalSatisfied(int[] paramArrayOfInt)
  {
    int i = 0;
    Iterator localIterator1;
    int k;
    Iterator localIterator2;
    int n;
    switch (this.operator)
    {
    case 0: 
    case 1: 
      i = 0;
      for (localIterator1 = this.cells.iterator(); localIterator1.hasNext();)
      {
        k = ((Integer)localIterator1.next()).intValue();
        
        i += paramArrayOfInt[k];
      }
      break;
    case 2: 
      int j = Math.max(paramArrayOfInt[((Integer)this.cells.get(0)).intValue()], paramArrayOfInt[((Integer)this.cells.get(1)).intValue()]);
      k = Math.min(paramArrayOfInt[((Integer)this.cells.get(0)).intValue()], paramArrayOfInt[((Integer)this.cells.get(1)).intValue()]);
      i = j - k;
      break;
    case 3: 
      i = 1;
      for (localIterator2 = this.cells.iterator(); localIterator2.hasNext();)
      {
        n = ((Integer)localIterator2.next()).intValue();
        
        i *= paramArrayOfInt[n];
      }
      break;
    case 4: 
      int m = Math.max(paramArrayOfInt[((Integer)this.cells.get(0)).intValue()], paramArrayOfInt[((Integer)this.cells.get(1)).intValue()]);
      n = Math.min(paramArrayOfInt[((Integer)this.cells.get(0)).intValue()], paramArrayOfInt[((Integer)this.cells.get(1)).intValue()]);
      i = m / n;
    }
    if (i == this.goal) {
      return true;
    }
    return false;
  }
}