package hashtable;

import java.util.*;

class Product {
    int id;
    String name;
    String category;
    double price;
    String feature;
    int clicks;

    public Product(int id, String name, String category, double price, String feature, int clicks) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.feature = feature;
        this.clicks = clicks;
    }
}

//我感觉面试的时候做到这种题也可能会卡壳 就是紧张的状态下你不会好好分析了。。。
public class ProductStream {

    public void processStream(List<Product> products) {

    }

    //find most click for each Category
    public Map<String, Product> mostClicksForEachCategory(List<Product> products) {
        Map<String, Product> res = new HashMap<>();
        Map<String, Integer> map = new HashMap<>();
        for (Product prod : products) {
            if (prod.clicks > map.getOrDefault(prod.category, 0)) {
                map.put(prod.category, prod.clicks);
                res.put(prod.category, prod);
            }
        }
        return res;
    }

    //follow up:
    //get top k  product for each category
    public Map<String, List<Product>> mostClickForEachCategory(List<Product> products, int k) {
        Map<String, List<Product>> res = new HashMap<>();
        Map<String, PriorityQueue<Product>> map = new HashMap<>();
        for (Product prod : products) {
            if (!map.containsKey(prod.category)) {
                map.put(prod.category, new PriorityQueue<>((a,b) -> a.clicks-b.clicks));
            }
            map.get(prod.category).add(prod);
            if (map.get(prod.category).size() > k) {
                map.get(prod.category).poll();
            }
        }
        for (String category : map.keySet()) {
            //res.put(category, new ArrayList<>());
            res.put(category, new LinkedList<>());
            while (!map.get(category).isEmpty()) {
                //insert at head -> use LinkedList
                res.get(category).add(0, map.get(category).poll());
            }
        }
        return res;
    }
}
