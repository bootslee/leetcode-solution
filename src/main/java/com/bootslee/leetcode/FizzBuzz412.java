package com.bootslee.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz412 {
    public List<String> fizzBuzz3(int n) {
        final int[] count3 = {1};
        final int[] count5 = { 1 };
        return IntStream.range(1, n + 1).mapToObj(
                i->{
                    if(count5[0] ==5 && count3[0] ==3){
                        count3[0] =1;
                        count5[0] =1;
                        return "FizzBuzz";
                    }else if(count5[0] ==5){
                        count5[0] =1;
                        count3[0]++;
                        return "Buzz";
                    }else  if(count3[0] ==3){
                        count3[0] =1;
                        count5[0]++;
                        return "Fizz";
                    }else {
                        count3[0]++;
                        count5[0]++;
                        return  String.valueOf(i);
                    }
                }
        ).collect(Collectors.toList());
    }

    public List<String> fizzBuzz(int n) {
        List<String> list=new ArrayList<>(n);
        int count3=1,count5=1;
        for (int index=1;index<=n;index++,count3++,count5++){
            if(count5==5 && count3==3){
                list.add("FizzBuzz");
                count3=0;
                count5=0;
            }else if(count5==5){
                list.add("Buzz");
                count5=0;
            }else  if(count3==3){
                list.add("Fizz");
                count3=0;
            }else {
                list.add(String.valueOf(index));
            }
        }
        return list;
    }
    public List<String> fizzBuzz2(int n ){
        String[] list=new String[n];
        for (int index=2;index<n;index+=3)
            list[index]="Fizz";
        for (int index=4;index<n;index+=5) {
            if ((null == list[index])) {
                list[index] = "Buzz";
            } else {
                list[index] = "FizzBuzz";
            }
        }
        for (int index=0;index<n;index++) {
            if (null == list[index]) {
                list[index] = String.valueOf(index+1);
            }
        }
        return Arrays.asList(list);
    }
}
