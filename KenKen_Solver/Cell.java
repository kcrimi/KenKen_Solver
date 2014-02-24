/*  1:   */ import java.util.Arrays;
/*  2:   */ 
/*  3:   */ public class Cell
/*  4:   */ {
/*  5:   */   Cage cage;
/*  6:   */   Row row;
/*  7:   */   Column column;
/*  8:   */   Cell nextCell;
/*  9:   */   int number;
/* 10:   */   
/* 11:   */   public Cell(int paramInt)
/* 12:   */   {
/* 13:13 */     this.number = paramInt;
/* 14:14 */     this.nextCell = null;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void explore(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/* 18:   */   {
/* 19:18 */     for (int k : paramArrayOfInt2) {
/* 20:22 */       if ((this.column.available(k, paramArrayOfInt1)) && (this.row.available(k, paramArrayOfInt1)))
/* 21:   */       {
/* 22:26 */         paramArrayOfInt1[this.number] = k;
/* 23:27 */         if (this.cage.isFilled(paramArrayOfInt1))
/* 24:   */         {
/* 25:29 */           if (this.cage.goalSatisfied(paramArrayOfInt1)) {
/* 26:31 */             if (this.nextCell == null) {
/* 27:33 */               KenKenPuzzleSolver.printSolution(paramArrayOfInt1);
/* 28:   */             } else {
/* 29:37 */               this.nextCell.explore(Arrays.copyOf(paramArrayOfInt1, paramArrayOfInt1.length), paramArrayOfInt2);
/* 30:   */             }
/* 31:   */           }
/* 32:   */         }
/* 33:   */         else {
/* 34:43 */           this.nextCell.explore(Arrays.copyOf(paramArrayOfInt1, paramArrayOfInt1.length), paramArrayOfInt2);
/* 35:   */         }
/* 36:   */       }
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           I:\Toshiba Backup\class\Fall  2012\AI\PA1\crimi_kevin_Program1.zip
 * Qualified Name:     Cell
 * JD-Core Version:    0.7.0.1
 */