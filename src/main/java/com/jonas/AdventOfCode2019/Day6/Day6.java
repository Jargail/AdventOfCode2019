package com.jonas.AdventOfCode2019.Day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {

    private Map<String, Item> itemMap = new HashMap<>();
    private class Item {
        int distanceFromCom;
        String orbitingObject;
        List<String> orbitingMe;
    }

    public void run1(String[] input) {
        initializeMapFromArray(input);

        int ret = sumDistances();
        System.out.println(ret);
    }

    public void run2(String[] input) {
        initializeMapFromArray(input);

        List<Item> path1 = getPathToCom("YOU");
        List<Item> path2 = getPathToCom("SAN");

        Item greatestCommon = findGreatestEqual(path1, path2);

        int you = (itemMap.get("YOU").distanceFromCom - 1);
        int san = (itemMap.get("SAN").distanceFromCom - 1);
        int gc = greatestCommon.distanceFromCom;

        System.out.println("YOU " + you);
        System.out.println("SAN " + san);
        System.out.println("GC " + gc);
        System.out.println("Total " + (you - gc + san - gc));

    }

    private void initializeMapFromArray(String[] input) {
        Item start = new Item();
        start.orbitingMe = new ArrayList<>();
        start.distanceFromCom = 0;
        start.orbitingObject = null;
        itemMap.put("COM", start);

        for(String inp : input) {
            String orbiting = inp.substring(0, inp.indexOf(')'));
            String object = inp.substring(inp.indexOf(')') + 1);
            Item item = new Item();
            item.orbitingObject = orbiting;
            item.distanceFromCom = 0;
            item.orbitingMe = new ArrayList<>();
            itemMap.put(object, item);
        }

        /* Add entries to other items orbitingMe-lists */
        for (Map.Entry<String, Item> entry : itemMap.entrySet()) {
            if (entry.getValue().orbitingObject != null)
                itemMap.get(entry.getValue().orbitingObject)
                        .orbitingMe.add(entry.getKey());
        }

        Item startItem = itemMap.get("COM");
        setDistances(startItem.orbitingMe, startItem.distanceFromCom);
    }

    private void setDistances(List<String> lst, int distance) {
        if (lst.isEmpty())
            return;
        else {
            for(String key : lst) {
                Item item = itemMap.get(key);
                item.distanceFromCom = distance + 1;
                setDistances(item.orbitingMe, distance + 1);
            }
        }
    }

    private int sumDistances() {
        int ret = 0;
        for (Map.Entry<String, Item> entry : itemMap.entrySet()) {
            ret += entry.getValue().distanceFromCom;
        }
        return ret;
    }

    private List<Item> getPathToCom(String key) {
        List<Item> lst = new ArrayList<>();
        Item item = itemMap.get(key);

        while (!key.equals("COM")) {
            key = item.orbitingObject;
            item = itemMap.get(key);
            lst.add(item);
            key = item.orbitingObject;
        }
        return lst;
    }

    private Item findGreatestEqual(List<Item> path1, List<Item> path2) {
        for (Item item : path1) {
            if (path2.contains(item))
                return item;
        }
        return null;
    }
}
