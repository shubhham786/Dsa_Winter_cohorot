package binarySearch;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//leetcode 981 Time Based Key-Value Store
public class TimeMap {

    public class TimeValue {

        int number;
        String value;

        public TimeValue(int number, String value) {
            this.number = number;
            this.value = value;
        }

    }

    HashMap<String, ArrayList<TimeValue>> map;

    public TimeMap() {

        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {

        if (!map.containsKey(key)) {

            TimeValue timeMap = new TimeValue(timestamp, value);
            map.put(key, new ArrayList<>());
            map.get(key).add(timeMap);
        } else {

            //insert karte time hi sahi order maintain karte hai
            ArrayList<TimeValue> list=map.get(key);
            int si = 0;
            int ei = list.size() - 1;

            int ans = ei + 1;

            while (si <= ei) {
                int mid = (ei + si) / 2;

                if (list.get(mid).number >= timestamp) {
                    ans = mid;
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            }

             list.add(ans, new TimeValue(timestamp, value));
        }

    }

    public String get(String key, int timestamp) {

        if (!map.containsKey(key)) {
            return "";
        } else {

            ArrayList<TimeValue> list = map.get(key);



            int si = 0;
            int ei = list.size() - 1;

            int ans = ei + 1;

            while (si <= ei) {
                int mid = (ei + si) / 2;

                if (list.get(mid).number > timestamp) {
                    ans = mid;
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            }

            if(ans==0) {
                return "";
            }
            else
            {
                return list.get(ans-1).value;
            }

        }
    }
}

/*
//better solution
class TimeMap {
    public class Time{
        String key;
        String value;
        int timestamp;
        Time prev;

        public Time(String key, String value, int timestamp){
            this.key = key;
            this.value = value;
            this.timestamp = timestamp;
            this.prev = null;
        }
    }

    Time timeMap;

    public TimeMap() {
        timeMap = null;
    }

    public void set(String key, String value, int timestamp) {
        Time temp = new Time(key,value,timestamp);
        temp.prev = timeMap;
        timeMap = temp;
    }

    public String get(String key, int timestamp) {
        return get(key, timestamp, timeMap);
    }

    private String get(String key, int timestamp, Time map){
        if (map == null){
            return "";
        }
        if (key.equals(map.key)){
            if (map.timestamp <= timestamp){
                return map.value;
            }
        }
        return get(key, timestamp, map.prev); //using recursion
    }
}
 */