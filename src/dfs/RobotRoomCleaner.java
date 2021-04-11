package dfs;

//经典的google难题 扫地机器人...

//题干其实特别简单... 一个房间 然后一个起点 设计一个算法去clean room
//然后难点在于所有东西你需要自己去定义 你需要什么...比如这个interface 他告诉你robot有4个API
//算法用DFS应该毋庸置疑 但是如何把握好方向是个比较难的地方
//其实还是很那的这道题 因为你平时做DFS return回来就完事了...但是这是一个实际的机器人给了你实际的API 所以你往回走也需要自己coing

///允许这道题写不出来...但是我希望你在充分思考之后看完别人的解法能够好好想想 你在什么地方卡住了？

//如果就像google那样考你这题 只是告诉你你需要设计一个扫地机器人...其他所有的不管是其实条件还是interface都需要你自己去设计
//我可能会想 我们的robot是一个class  然后里面有4个基本method 并且 有一个state(field)表示当前robot的方向
//我们在trunLeft和turnRight的时候 都可以同时改变direction的这个状态
//至少到目前 这个第一步想起来还是make sense的  那么我们在写DFS的时候如果控制robot的方向呢？
//就像之前那些基本的DFS的题 我们有一个dfs的method  我们需要传进去什么？ 当前的位置，当前的方向需要吗？
//DFS的本质是什么 我先往一个方向走到不能再走了再回头一步走其他方向

//我的基本代码思路可能就是下面那样...
//可能最后会考虑下 我们如何避免clean已经clean过的点

import java.util.Set;

public class RobotRoomCleaner {
    interface Robot {
        public boolean move();
        public void turnLeft();
        public void turnRight();
        public void clean();
    }
    class CleanRobot implements Robot {
        //intialized direction is "up"
        @Override
        public boolean move() {
            return false;
        }

        @Override
        public void turnLeft() {

        }

        @Override
        public void turnRight() {

        }

        @Override
        public void clean() {

        }
    }
    public void cleanRoom(Robot robot, int[] room, int[] start) {

    }
    private void dfs(CleanRobot robot, int[] room, Set<String> visited, int x, int y, int curDir) {
        String pos = x + "->" + y;
        if (!visited.add(pos)) {
            return;
        }
        //clean current grid
        robot.clean();
        for (int i = 0; i < 4; i++) {
            if (robot.move()) {
                //next position
                int row = x, col = y;
                switch(curDir) {
                    case 0:
                        row = x-1;
                        break;
                    case 90:
                        col = y+1;
                        break;
                    case 180:
                        row = x+1;
                        break;
                    case 270:
                        col = y-1;
                        break;
                    default:
                        break;
                }
                dfs(robot, room, visited, row, col, curDir);
                //end this direction and back to position
                //这一步就是考察你对DFS的理解了。。。
                //比如robot目前朝上 然后做了一次DFS 那么对于下一次stack上的dfs call
                //robot应该是做完了4个方向的DFS 当前方向回到了up
                //这个时候robot的位置是 x-1,y dir=0
                //我们希望他回到原来的位置并且保持原来的方向也就是up
                //所以先掉头 并且move一次
                //然后再掉头回到原来的方向
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            //首先不管能不能去下一步 我们都改变方向去下一个方向 做DFS
            //after one move; current position: x,y,curDir
            robot.turnRight();
            curDir += 90;
            curDir %= 360;
        }
        //after 4 directions you need to go back
        //go back means you turn 180 degree and move
        //这里move吗
        //我4个方向做完了之后 应该是robot应该在x,y这个位置 方向是一开始进来的方向
        //所以你不需要move 啊。。。
        //robot.move();
    }
}


//这是别人的solution 我直接放在这里 你看看别人的思路是怎样的 会觉得不难 但是为什么自己写不下去呢？
//首先你把机器人多一重设计...让他自身有一个state表示方向其实已经走偏了
//然后用一个int 0, 90, 180, 270这样表示方向真的会更合理一些
//我自己可能受到平时写那些DFS题目的影响 觉得{0,1}这样表示方向可能会更好？
//你看看自己写的代码的基础部分 我在四个方向循环完了 还加了一个回头？？？是不是对DFS理解不对了就
//因为你想清楚对于当前的点 四次循环 就表示四个方向都已经走过了 那么我直接就结束了 回到了上一层循环的点？？？
//但是其实这部分是在循环内的 就是我上一层结束了 就是代码中的 go back to starting point这一步是关键
//这里其实自己想过 就是对于下一层的DFS 我应该四个方向都走过了 那么我走完4个方向并且想回来的时候 我应该是在当前点往当前方向走一步
//并且仍然保持这个方向 所以要转180度不管向左向右 然后移动一步步再转回来就是回到当前位置并且保持当前方向
//可能难点就在想通这一步...

//其实这道题看懂别人的solution之后可以让你对DFS理解加深 但是我觉得更宝贵的是 能够记得你自己那道这道陌生的题目的时候心路历程
//并且能够发现自己卡在了什么地方 1.direction的表示上  2.在于DFS理解 机器人需要回头移动 这个应该放在四个方向中

/*
    public void cleanRoom(Robot robot) {
        // A number can be added to each visited cell
        // use string to identify the class
        Set<String> set = new HashSet<>();
        int cur_dir = 0;   // 0: up, 90: right, 180: down, 270: left
        backtrack(robot, set, 0, 0, 0);
    }

    public void backtrack(Robot robot, Set<String> set, int i,
                          int j, int cur_dir) {
        String tmp = i + "->" + j;
        if(set.contains(tmp)) {
            return;
        }

        robot.clean();
        set.add(tmp);

        for(int n = 0; n < 4; n++) {
            // the robot can to four directions, we use right turn
            if(robot.move()) {
                // can go directly. Find the (x, y) for the next cell based on current direction
                int x = i, y = j;
                switch(cur_dir) {
                    case 0:
                        // go up, reduce i
                        x = i-1;
                        break;
                    case 90:
                        // go right
                        y = j+1;
                        break;
                    case 180:
                        // go down
                        x = i+1;
                        break;
                    case 270:
                        // go left
                        y = j-1;
                        break;
                    default:
                        break;
                }

                backtrack(robot, set, x, y, cur_dir);
                // go back to the starting position
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnRight();
                robot.turnRight();

            }
            // turn to next direction
            robot.turnRight();
            cur_dir += 90;
            cur_dir %= 360;
        }

    }

 */
