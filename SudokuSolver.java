public class SudokuSolver {
    private static final int GRID_SIZE = 9;

    public static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    public static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValidSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[9];
            boolean[] colCheck = new boolean[9];
            boolean[] boxCheck = new boolean[9];

            for (int j = 0; j < 9; j++) {
                // Check row validity
                if (board[i][j] != 0) {
                    if (rowCheck[board[i][j] - 1]) return false;
                    rowCheck[board[i][j] - 1] = true;
                }

                // Check column validity
                if (board[j][i] != 0) {
                    if (colCheck[board[j][i] - 1]) return false;
                    colCheck[board[j][i] - 1] = true;
                }

                // Check subgrid validity
                int boxRow = 3 * (i / 3);
                int boxCol = 3 * (i % 3);
                int value = board[boxRow + j / 3][boxCol + j % 3];
                if (value != 0) {
                    if (boxCheck[value - 1]) return false;
                    boxCheck[value - 1] = true;
                }
            }
        }
        return true;
    }

    public static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                System.out.print(board[row][column]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test Case 1:
        System.out.println("----------------------Test Case 1------------------------");
        int[][] sudokuBoard1 = {
                {0, 1, 3, 8, 0, 0, 4, 0, 5},
                {0, 2, 4, 6, 0, 5, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 9, 3, 0},
                {4, 9, 0, 3, 0, 6, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 5, 0, 0},
                {0, 0, 0, 7, 0, 1, 0, 9, 3},
                {0, 6, 9, 0, 0, 0, 7, 4, 0},
                {0, 0, 0, 2, 0, 7, 6, 8, 0},
                {1, 0, 2, 0, 0, 8, 3, 5, 0}
        };
        System.out.println("The Sudoku Problem is printed below");
        printBoard(sudokuBoard1);
        if (solveBoard(sudokuBoard1)) {
            System.out.println("Solved successfully!");
        } else {
            System.out.println("Unsolvable board :(");
        }
        printBoard(sudokuBoard1);
        if (isValidSudoku(sudokuBoard1)) {
            System.out.println("Valid solution");
        } else {
            System.out.println("Invalid solution");
        }
        // Test Case 2:
        System.out.println("----------------------Test Case 2------------------------");
        int[][] sudokuBoard2 = {
                {0, 0, 2, 0, 0, 0, 0, 4, 1},
                {0, 0, 0, 0, 8, 2, 0, 7, 0},
                {0, 0, 0, 0, 4, 0, 0, 0, 9},
                {2, 0, 0, 0, 7, 9, 3, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 8, 0},
                {0, 0, 6, 8, 1, 0, 0, 0, 4},
                {1, 0, 0, 0, 9, 0, 0, 0, 0},
                {0, 6, 0, 4, 3, 0, 0, 0, 0},
                {8, 5, 0, 0, 0, 0, 4, 0, 0}
        };
        System.out.println("The Sudoku Problem is printed below:");
        printBoard(sudokuBoard2);
        if (solveBoard(sudokuBoard2)) {
            System.out.println("Solved successfully!");
        } else {
            System.out.println("Unsolvable board :(");
        }
        printBoard(sudokuBoard2);
        if (isValidSudoku(sudokuBoard2)) {
            System.out.println("Valid solution");
        } else {
            System.out.println("Invalid solution");
        }
    }

}
