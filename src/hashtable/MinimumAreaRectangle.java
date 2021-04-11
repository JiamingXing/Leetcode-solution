package hashtable;

import java.util.HashSet;
import java.util.Set;

//思路可能走偏了。。。想着去sort x 和 y。。。。
//但是正常就要O(n^2) check pair and pair 核心思路一致 通过对角线两个点确认一个rectangle

public class MinimumAreaRectangle {


    public int minAreaRect(int[][] points) {
        Set<String> hs = new HashSet<>();
        for(int[] p: points)
            hs.add(p[0] + "#" + p[1]);

        int min = Integer.MAX_VALUE;
        for(int i=0; i<points.length; i++)
        {
            for(int j= i+1; j<points.length; j++)
            {
                if(points[i][0]==points[j][0]||points[i][1]==points[j][1]) continue;
                String p1 = points[i][0] + "#" + points[j][1];
                String p2 = points[j][0] + "#" + points[i][1];
                if(hs.contains(p1)&&hs.contains(p2))
                {
                    int area = Math.abs((points[i][0]-points[j][0])*(points[i][1]-points[j][1]));
                    min = Math.min(min, area);
                }
            }
        }
        return min==Integer.MAX_VALUE?0:min;
    }
}
