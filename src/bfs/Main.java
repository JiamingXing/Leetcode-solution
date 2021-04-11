package bfs;

public class Main {
    public static void main(String[] args) {
        int[][] routes = {{1,2,7},{3,6,7}};
        BusRoutes s = new BusRoutes();
        System.out.println(s.numBusesToDestination(routes, 1, 6));
    }
}
