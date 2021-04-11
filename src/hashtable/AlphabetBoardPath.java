package hashtable;

//怎么写出这种clean的代码啊...

public class AlphabetBoardPath {
    public String alphabetBoardPath(String target) {
        if(target == null) return "";
        char[] chs = target.toCharArray();
        StringBuilder sb = new StringBuilder();
        int previ = 0, prevj = 0;
        for(int i = 0; i < chs.length; i++) {
            int curi = (chs[i] - 'a') / 5;
            int curj = (chs[i] - 'a') % 5;
            if(curi == previ && curj == prevj) {
                sb.append("!");
            } else {
                printPath(sb, previ, prevj, curi, curj);
                sb.append("!");
                previ = curi; prevj = curj;
            }
        }

        return sb.toString();
    }
    private void printPath(StringBuilder sb, int previ, int prevj, int curi, int curj) {
        while(curi < previ) {
            sb.append("U"); curi++;
        }
        while(curj > prevj) {
            sb.append("R"); curj--;
        }
        while(curj < prevj) {
            sb.append("L"); curj++;
        }
        while(curi > previ) {
            sb.append("D"); curi--;
        }
    }
}
