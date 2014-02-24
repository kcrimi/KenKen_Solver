KenKen_Solver
=============

Program to solve the numerical puzzle game, KenKen

Examples of kenken puzzles can be found in the /examples/ directory. Each row and column should contain one of each number between 1 and the number of cells in the row/column. Constaraints of cells must be met by equaling the marked number in the case of 'equals' cages (1 cell), subtracting or dividing one cell from another (2 cells),or multiplying or adding (multiple cells) with the marked result number. 

This program sorts the cages by ease of solving by the number of cells in each, therefore, the equals cells are first since those values are defined explicitly by their constraints. This eliminates the amount of branching needed to find the solution to the puzzle. The solution is found by then iterating through cells and setting them to one of their possible values and then continuing until a constraint is violated and then backtracking in the tree and trying a different possibility. All possible solutions are returned with this strategy.

+++++++++++++
Input example
+++++++++++++

1 . 1
2 + 2 3
2 . 0

This input would define the puzzle of 2 x 2 squares with the op left cell equaling 2, the top right cell equalling 1 and the bottom cells adding up to 2.
