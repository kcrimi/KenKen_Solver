/*  1:   */ import java.util.ArrayList;
/*  2:   */ import java.util.Iterator;
/*  3:   */ 
/*  4:   */ public class Cage
/*  5:   */ {
/*  6:   */   ArrayList<Integer> cells;
/*  7:   */   Cage nextCage;
/*  8:   */   int goal;
/*  9:   */   int operator;
/* 10:   */   
/* 11:   */   public Cage(int paramInt1, int paramInt2)
/* 12:   */   {
/* 13:13 */     this.cells = new ArrayList();
/* 14:14 */     this.nextCage = null;
/* 15:15 */     this.goal = paramInt1;
/* 16:16 */     this.operator = paramInt2;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public int currentValue(int[] paramArrayOfInt)
/* 20:   */   {
/* 21:22 */     int i = 0;
/* 22:23 */     for (Iterator localIterator = this.cells.iterator(); localIterator.hasNext();)
/* 23:   */     {
/* 24:23 */       int j = ((Integer)localIterator.next()).intValue();
/* 25:   */       
/* 26:25 */       i += paramArrayOfInt[j];
/* 27:   */     }
/* 28:27 */     return i;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public boolean isFilled(int[] paramArrayOfInt)
/* 32:   */   {
/* 33:34 */     boolean bool = true;
/* 34:35 */     for (Iterator localIterator = this.cells.iterator(); localIterator.hasNext();)
/* 35:   */     {
/* 36:35 */       int i = ((Integer)localIterator.next()).intValue();
/* 37:37 */       if (paramArrayOfInt[i] == 0)
/* 38:   */       {
/* 39:39 */         bool = false;
/* 40:40 */         break;
/* 41:   */       }
/* 42:   */     }
/* 43:43 */     return bool;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public boolean goalSatisfied(int[] paramArrayOfInt)
/* 47:   */   {
/* 48:47 */     int i = 0;
/* 49:   */     Iterator localIterator1;
/* 50:   */     int k;
/* 51:   */     Iterator localIterator2;
/* 52:   */     int n;
/* 53:48 */     switch (this.operator)
/* 54:   */     {
/* 55:   */     case 0: 
/* 56:   */     case 1: 
/* 57:52 */       i = 0;
/* 58:53 */       for (localIterator1 = this.cells.iterator(); localIterator1.hasNext();)
/* 59:   */       {
/* 60:53 */         k = ((Integer)localIterator1.next()).intValue();
/* 61:   */         
/* 62:55 */         i += paramArrayOfInt[k];
/* 63:   */       }
/* 64:57 */       break;
/* 65:   */     case 2: 
/* 66:59 */       int j = Math.max(paramArrayOfInt[((Integer)this.cells.get(0)).intValue()], paramArrayOfInt[((Integer)this.cells.get(1)).intValue()]);
/* 67:60 */       k = Math.min(paramArrayOfInt[((Integer)this.cells.get(0)).intValue()], paramArrayOfInt[((Integer)this.cells.get(1)).intValue()]);
/* 68:61 */       i = j - k;
/* 69:62 */       break;
/* 70:   */     case 3: 
/* 71:64 */       i = 1;
/* 72:65 */       for (localIterator2 = this.cells.iterator(); localIterator2.hasNext();)
/* 73:   */       {
/* 74:65 */         n = ((Integer)localIterator2.next()).intValue();
/* 75:   */         
/* 76:67 */         i *= paramArrayOfInt[n];
/* 77:   */       }
/* 78:69 */       break;
/* 79:   */     case 4: 
/* 80:71 */       int m = Math.max(paramArrayOfInt[((Integer)this.cells.get(0)).intValue()], paramArrayOfInt[((Integer)this.cells.get(1)).intValue()]);
/* 81:72 */       n = Math.min(paramArrayOfInt[((Integer)this.cells.get(0)).intValue()], paramArrayOfInt[((Integer)this.cells.get(1)).intValue()]);
/* 82:73 */       i = m / n;
/* 83:   */     }
/* 84:77 */     if (i == this.goal) {
/* 85:79 */       return true;
/* 86:   */     }
/* 87:81 */     return false;
/* 88:   */   }
/* 89:   */ }


/* Location:           I:\Toshiba Backup\class\Fall  2012\AI\PA1\crimi_kevin_Program1.zip
 * Qualified Name:     Cage
 * JD-Core Version:    0.7.0.1
 */