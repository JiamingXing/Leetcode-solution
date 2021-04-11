package greedy;

//因为你要走过所有的点 我们如果遍历一遍cost的和如果大于gas一定不存在答案
//如果gas的和大于cost一定存在答案
//但是那个起点在哪里？ 我们通过gas和cost生成一个[gas-cost]的array  然后转换成maximum subarray in circle 对应的起点？

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] dif = new int[n];
        for (int i = 0; i < n; i++) {
            dif[i] = gas[i] - cost[i];
        }
        int sum = 0;
        for (int num : dif) {
            sum += num;
        }
        if (sum < 0) {
            return -1;
        }
        //find start point and assuming we only have 1 result
        //下面是被人的思路啊...感觉像是排除法？？？
        //不行的点去除可行的一定是我们的答案？

        //就是我们的可能只有n个 那么我们就从第一个点开始走 如果走的不行 那么久去除从下一个点开始从0开始咯
        //如果可以那么肯定就是这个点？
        int start = 0, accumulate = 0;
        for (int i = 0; i < n; i++) {
            if (accumulate + dif[i] < 0) {
                start = i+1;
                accumulate = 0;
            } else {
                accumulate += dif[i];
            }
        }
        return start;
    }
}
