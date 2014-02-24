/*   1:    */ import java.io.FileInputStream;
/*   2:    */ import java.io.FileNotFoundException;
/*   3:    */ import java.io.FileOutputStream;
/*   4:    */ import java.io.PrintStream;
/*   5:    */ import java.util.ArrayList;
/*   6:    */ import java.util.Arrays;
/*   7:    */ import java.util.Collections;
/*   8:    */ import java.util.Comparator;
/*   9:    */ import java.util.Scanner;
/*  10:    */ 
/*  11:    */ public class KenKenPuzzleSolver
/*  12:    */ {
/*  13:    */   public Cell[] cells;
/*  14:    */   Column[] columns;
/*  15:    */   Row[] rows;
/*  16: 16 */   ArrayList<Cage> cages = new ArrayList();
/*  17:    */   int maxCell;
/*  18:    */   Cell rootCell;
/*  19:    */   int[] choice;
/*  20:    */   int[] possibles;
/*  21:    */   static PrintStream out;
/*  22:    */   
/*  23:    */   public KenKenPuzzleSolver(String[] paramArrayOfString)
/*  24:    */   {
/*  25:    */     try
/*  26:    */     {
/*  27: 30 */       createCages(paramArrayOfString);
/*  28:    */     }
/*  29:    */     catch (FileNotFoundException localFileNotFoundException)
/*  30:    */     {
/*  31: 32 */       localFileNotFoundException.printStackTrace();
/*  32:    */     }
/*  33: 34 */     createCells();
/*  34:    */     
/*  35: 36 */     int i = (int)Math.sqrt(this.maxCell + 1);
/*  36: 37 */     createColumns(i);
/*  37: 38 */     createRows(i);
/*  38: 39 */     cellSort();
/*  39: 40 */     this.choice = new int[this.maxCell + 1];
/*  40: 41 */     Arrays.fill(this.choice, 0);
/*  41:    */     
/*  42:    */ 
/*  43: 44 */     int[] arrayOfInt = generatePossibles(i);
/*  44: 45 */     this.rootCell.explore(this.choice, arrayOfInt);
/*  45:    */   }
/*  46:    */   
/*  47:    */   public static void main(String[] paramArrayOfString)
/*  48:    */   {
/*  49:    */     try
/*  50:    */     {
/*  51: 51 */       out = new PrintStream(new FileOutputStream(paramArrayOfString[1]));
/*  52:    */     }
/*  53:    */     catch (FileNotFoundException localFileNotFoundException)
/*  54:    */     {
/*  55: 53 */       localFileNotFoundException.printStackTrace();
/*  56:    */     }
/*  57: 55 */     System.setOut(out);
/*  58: 56 */     KenKenPuzzleSolver localKenKenPuzzleSolver = new KenKenPuzzleSolver(paramArrayOfString);
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void createCages(String[] paramArrayOfString)
/*  62:    */     throws FileNotFoundException
/*  63:    */   {
/*  64: 62 */     int j = -1;
/*  65: 63 */     this.maxCell = 0;
/*  66: 64 */     int i = 0;
/*  67:    */     
/*  68:    */ 
/*  69: 67 */     Scanner localScanner = new Scanner(new FileInputStream(paramArrayOfString[0]));
/*  70: 70 */     while (localScanner.hasNext())
/*  71:    */     {
/*  72: 72 */       String str = localScanner.nextLine();
/*  73: 73 */       String[] arrayOfString = str.split(" ");
/*  74: 74 */       switch (arrayOfString[1].charAt(0))
/*  75:    */       {
/*  76:    */       case '.': 
/*  77: 77 */         j = 0;
/*  78: 78 */         break;
/*  79:    */       case '+': 
/*  80: 80 */         j = 1;
/*  81: 81 */         break;
/*  82:    */       case '-': 
/*  83: 83 */         j = 2;
/*  84: 84 */         break;
/*  85:    */       case '*': 
/*  86: 86 */         j = 3;
/*  87: 87 */         break;
/*  88:    */       case '/': 
/*  89: 89 */         j = 4;
/*  90:    */       }
/*  91: 92 */       this.cages.add(new Cage(Integer.parseInt(arrayOfString[0]), j));
/*  92: 93 */       for (int m = 2; m < arrayOfString.length; m++)
/*  93:    */       {
/*  94: 95 */         int k = Integer.parseInt(arrayOfString[m]);
/*  95: 96 */         ((Cage)this.cages.get(i)).cells.add(Integer.valueOf(k));
/*  96: 97 */         if (k > this.maxCell) {
/*  97: 99 */           this.maxCell = k;
/*  98:    */         }
/*  99:    */       }
/* 100:102 */       i++;
/* 101:    */     }
/* 102:104 */     localScanner.close();
/* 103:    */   }
/* 104:    */   
/* 105:    */   private void createCells()
/* 106:    */   {
/* 107:110 */     this.cells = new Cell[this.maxCell + 1];
/* 108:111 */     for (int i = 0; i <= this.maxCell; i++) {
/* 109:113 */       this.cells[i] = new Cell(i);
/* 110:    */     }
/* 111:    */   }
/* 112:    */   
/* 113:    */   private void createColumns(int paramInt)
/* 114:    */   {
/* 115:119 */     this.columns = new Column[paramInt];
/* 116:121 */     for (int i = 0; i < paramInt; i++)
/* 117:    */     {
/* 118:123 */       this.columns[i] = new Column(i, paramInt);
/* 119:124 */       for (int j = 0; j < paramInt; j++) {
/* 120:126 */         this.cells[(i + j * paramInt)].column = this.columns[i];
/* 121:    */       }
/* 122:    */     }
/* 123:    */   }
/* 124:    */   
/* 125:    */   private void createRows(int paramInt)
/* 126:    */   {
/* 127:133 */     this.rows = new Row[paramInt];
/* 128:135 */     for (int i = 0; i < paramInt; i++)
/* 129:    */     {
/* 130:137 */       this.rows[i] = new Row(i, paramInt);
/* 131:138 */       for (int j = 0; j < paramInt; j++) {
/* 132:140 */         this.cells[(paramInt * i + j)].row = this.rows[i];
/* 133:    */       }
/* 134:    */     }
/* 135:    */   }
/* 136:    */   
/* 137:    */   private void cellSort()
/* 138:    */   {
/* 139:149 */     Collections.sort(this.cages, new Comparator()
/* 140:    */     {
/* 141:    */       public int compare(Cage paramAnonymousCage1, Cage paramAnonymousCage2)
/* 142:    */       {
/* 143:153 */         if (paramAnonymousCage1.cells.size() > paramAnonymousCage2.cells.size()) {
/* 144:155 */           return 1;
/* 145:    */         }
/* 146:157 */         if (paramAnonymousCage1.cells.size() < paramAnonymousCage2.cells.size()) {
/* 147:159 */           return -1;
/* 148:    */         }
/* 149:163 */         return 0;
/* 150:    */       }
/* 151:168 */     });
/* 152:169 */     Cell localCell = null;
/* 153:170 */     this.rootCell = this.cells[((Integer)((Cage)this.cages.get(0)).cells.get(0)).intValue()];
/* 154:171 */     for (int i = 0; i < this.cages.size(); i++) {
/* 155:173 */       for (int j = 0; j < ((Cage)this.cages.get(i)).cells.size(); j++)
/* 156:    */       {
/* 157:175 */         if ((i != 0) || (j != 0)) {
/* 158:177 */           localCell.nextCell = this.cells[((Integer)((Cage)this.cages.get(i)).cells.get(j)).intValue()];
/* 159:    */         }
/* 160:179 */         this.cells[((Integer)((Cage)this.cages.get(i)).cells.get(j)).intValue()].cage = ((Cage)this.cages.get(i));
/* 161:180 */         localCell = this.cells[((Integer)((Cage)this.cages.get(i)).cells.get(j)).intValue()];
/* 162:    */       }
/* 163:    */     }
/* 164:    */   }
/* 165:    */   
/* 166:    */   public int[] generatePossibles(int paramInt)
/* 167:    */   {
/* 168:188 */     int[] arrayOfInt = new int[paramInt];
/* 169:189 */     for (int i = 0; i < paramInt; i++) {
/* 170:191 */       arrayOfInt[i] = (i + 1);
/* 171:    */     }
/* 172:193 */     return arrayOfInt;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public static void printSolution(int[] paramArrayOfInt)
/* 176:    */   {
/* 177:198 */     for (int i = 0; i < paramArrayOfInt.length; i++)
/* 178:    */     {
/* 179:200 */       System.out.print(paramArrayOfInt[i] + " ");
/* 180:201 */       if ((i + 1) % Math.sqrt(paramArrayOfInt.length) == 0.0D) {
/* 181:203 */         System.out.println("");
/* 182:    */       }
/* 183:    */     }
/* 184:206 */     System.out.print("\n");
/* 185:    */   }
/* 186:    */ }


/* Location:           I:\Toshiba Backup\class\Fall  2012\AI\PA1\crimi_kevin_Program1.zip
 * Qualified Name:     KenKenPuzzleSolver
 * JD-Core Version:    0.7.0.1
 */