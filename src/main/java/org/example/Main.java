package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        emulateCache();
    }
    public static void emulateCache(){
        Scanner scanner = new Scanner(System.in);
        int cacheSize = scanner.nextInt();
        int associativity = scanner.nextInt();
        int lineSize = scanner.nextInt();
        int n = scanner.nextInt();
        int numberOfLines = cacheSize / lineSize;
        int numberOfSets = numberOfLines / associativity;
        int cache_hit = 0;
        int cache_miss = 0;
        List<HashMap<Integer, Integer>> cache = new ArrayList<>();
        for (int i = 0; i < numberOfSets; ++i) {
            cache.add(new HashMap<>());
        }
        for (int i = 1; i <= n; ++i) {
            int address = scanner.nextInt();
            int lineIndex = address / lineSize;
            int setIndex = lineIndex % numberOfSets;
            if (cache.get(setIndex).containsKey(lineIndex)){
                ++cache_hit;
                cache.get(setIndex).replace(lineIndex, i);
            } else {
                if (cache.get(setIndex).size() >= associativity){
                    int timeToRemove = Integer.MAX_VALUE;
                    int keyToRemove = 0;
                    for (Integer key : cache.get(setIndex).keySet()){
                        int tmpKey = key;
                        int tmpTime = cache.get(setIndex).get(key);
                        if (tmpTime < timeToRemove){
                            timeToRemove = tmpTime;
                            keyToRemove = tmpKey;
                        }
                    }
                    cache.get(setIndex).remove(keyToRemove);
                    cache.get(setIndex).put(lineIndex, i);
                } else {
                    cache.get(setIndex).put(lineIndex, i);
                }
                ++cache_miss;
            }
        }
        System.out.print(cache_hit + " " + cache_miss);
    }
}