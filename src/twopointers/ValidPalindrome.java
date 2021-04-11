package twopointers;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(s.isEmpty() || s.length() == 1){
            return true;
        }
        int length = s.length();
        int i = 0;
        int j = length - 1;
        String st = s.toLowerCase();
        while(i <= j){
            if(!Character.isLetterOrDigit(st.charAt(i))){
                i ++;
            }else if(!Character.isLetterOrDigit(st.charAt(j))){
                j --;
            }else{
                if(st.charAt(i) != st.charAt(j)){
                    return false;
                }
                i ++;
                j --;
            }

        }
        return true;
    }
}
