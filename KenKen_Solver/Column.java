/*  1:   */ public class Column
/*  2:   */ {
/*  3:   */   int[] cells;
/*  4:   */   
/*  5:   */   public Column(int paramInt1, int paramInt2)
/*  6:   */   {
/*  7: 6 */     this.cells = new int[paramInt2];
/*  8: 7 */     for (int i = 0; i < paramInt2; i++) {
/*  9: 9 */       this.cells[i] = (paramInt1 + i * paramInt2);
/* 10:   */     }
/* 11:   */   }
/* 12:   */   
/* 13:   */   public boolean available(int paramInt, int[] paramArrayOfInt)
/* 14:   */   {
/* 15:17 */     boolean bool = true;
/* 16:18 */     for (int k : this.cells) {
/* 17:20 */       if (paramArrayOfInt[k] == paramInt)
/* 18:   */       {
/* 19:22 */         bool = false;
/* 20:23 */         break;
/* 21:   */       }
/* 22:   */     }
/* 23:26 */     return bool;
/* 24:   */   }
/* 25:   */ }


/* Location:           I:\Toshiba Backup\class\Fall  2012\AI\PA1\crimi_kevin_Program1.zip
 * Qualified Name:     Column
 * JD-Core Version:    0.7.0.1
 */