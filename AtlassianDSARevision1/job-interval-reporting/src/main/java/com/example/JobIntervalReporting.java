package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JobIntervalReporting {
    public JobIntervalReporting(){}

    public List<int[]> findMinWindows(List<int[]> inputWindows){
        
        List<int[]> result = new ArrayList<>();

        List<int[]> sortedList = inputWindows.stream().sorted((w1, w2) -> {
            int cmp = Integer.compare(w1[0], w2[0]);
            if(cmp == 0){
                return Integer.compare(w1[1], w2[1]);
            }
            return cmp;
        }).collect(Collectors.toList());

        int startTime = sortedList.get(0)[0];
        int endTime = sortedList.get(0)[1];

        for(int i = 1; i < sortedList.size(); i++){
            int[] current = sortedList.get(i);

            if(current[0] <= endTime){
                endTime = Math.max(current[1], endTime);
            }
            else{
                result.add(new int[]{startTime, endTime});
                startTime = sortedList.get(i)[0];
                endTime = sortedList.get(i)[1];
            }
        }

        result.add(new int[]{startTime, endTime});
        
        return result;
    }

    public List<int[]> findTwoJobWindow(List<int[]> inputWindows){
        int start = -1;
        int end = -1;

        List<int[]> result = new ArrayList<>();

        if(inputWindows.isEmpty()){
            return Collections.emptyList();
        }

        // Collections.sort(inputWindows, (w1, w2) -> {
        //     int cmp = Integer.compare(w1[0], w2[0]);
        //     if(cmp == 0){
        //         return Integer.compare(w1[1], w2[1]);
        //     }
        //     return cmp;
        // });

        List<int[]> events = new ArrayList<>();
        
        for(int[] window : inputWindows){
            events.add(new int[]{window[0], 1});
            events.add(new int[]{window[1], -1});
        }
        
        Collections.sort(events, (e1, e2) -> {
            int cmp = Integer.compare(e1[0], e2[0]);
            if(cmp == 0){
                return Integer.compare(e1[1], e2[1]);
            }
            return cmp;
        });

        int count = 0;
        int flag = 0;
        for(int[] event : events){
            int value = event[1];
            int time = event[0];

            if(value == 1){
                count++;
                if(count == 2){
                    start = time;
                    flag = 1;
                }
            }
            if(value == -1){
                count--;
                if(flag == 1 && count == 1){
                    end = time;
                    result.add(new int[]{start, end});
                    flag = 0;
                }
            }
        }

        if(result.isEmpty()){
            return Collections.emptyList();
        }

        return result;
    }

    public List<int[]> findBusiestWindow(List<int[]> inputWindows){
        int start = -1;
        int end = -1;

        List<int[]> result = new ArrayList<>();

        if(inputWindows.isEmpty()){
            return Collections.emptyList();
        }

        // Collections.sort(inputWindows, (w1, w2) -> {
        //     int cmp = Integer.compare(w1[0], w2[0]);
        //     if(cmp == 0){
        //         return Integer.compare(w1[1], w2[1]);
        //     }
        //     return cmp;
        // });

        List<int[]> events = new ArrayList<>();
        
        for(int[] window : inputWindows){
            events.add(new int[]{window[0], 1});
            events.add(new int[]{window[1], -1});
        }
        
        Collections.sort(events, (e1, e2) -> {
            int cmp = Integer.compare(e1[0], e2[0]);
            if(cmp == 0){
                return Integer.compare(e1[1], e2[1]);
            }
            return cmp;
        });

        int count = 0;
        int maxCount = Integer.MIN_VALUE;
        for(int[] event : events){
            int value = event[1];
            int time = event[0];

            if(value == 1){
                count++;
                if(count > 0){
                    if(count > maxCount){
                        result.clear();
                        start = time;
                        maxCount = count;
                    }
                    if(count == maxCount){
                        start = time;
                    }
                }
            }
            if(value == -1){
                count--;
                if(count == maxCount - 1){
                    end = time;
                    result.add(new int[]{start, end});
                }
            }
        }

        return result;
    }
}
