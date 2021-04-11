package hashtable;

//27个hashset分成三组...挨个验证

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//以前写的代码也会出错了..

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Integer>> row = new HashMap<>();
        Map<Integer, Set<Integer>> col = new HashMap<>();
        Map<Integer, Set<Integer>> subSudo = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            row.put(i, new HashSet<>());
            col.put(i, new HashSet<>());
            subSudo.put(i, new HashSet<>());
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!row.get(i).add(board[i][j]-'0')) {
                        return false;
                    }
                    if (!col.get(i).add(board[i][j]-'0')) {
                        return false;
                    }
                    if (!subSudo.get(i/3*3 + j/3).add(board[i][j]-'0')) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
