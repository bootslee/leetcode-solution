package com.bootslee.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created By BootsLee on 2020/5/18
 **/
//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG
//
//
//
public class AddToArrayFormOfInteger989 {
    /**
     * 989. 数组形式的整数加法
     * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
     * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
     * @param A
     * @param K
     * @return
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        ArrayList<Integer> ret=new ArrayList<>(Math.max(A.length,String.valueOf(K).length())+1);
        int num=K;
        int i=A.length-1;
        while (i>=0||num>0){
            if (i >= 0)
                num += A[i];
            ret.add(num%10);
            num/=10;
            --i;
        }
        Collections.reverse(ret);
        return ret;
    }
    public List<Integer> addToArrayForm2(int[] A, int K) {
        int carry = 0;
        List<Integer> list = new ArrayList<>(Math.max(A.length,String.valueOf(K).length())+1);
        int i = A.length - 1;
        while(K > 0 && i >= 0) {
            A[i] += (K%10 + carry);
            if(A[i] >= 10) {
                carry = 1;
                A[i] -= 10;
            }else{
                carry = 0;
            }

            K/=10;
            i--;
        }
        if(i >= 0){
            while(i>=0 && carry == 1) {
                A[i] += carry;
                if(A[i] >= 10) {
                    carry = 1;
                    A[i] -= 10;
                }else{
                    carry = 0;
                }
                i--;
            }
            if(i<0 && carry == 1) {
                list.add(1);
            }
            for(int x:A) {
                list.add(x);
            }
            return list;
        }else{
            K += carry;
            while(K>0){
                list.add(K%10);
                K/=10;
            }
            Collections.reverse(list);
            for(int x:A){
                list.add(x);
            }
            return list;
        }
    }
}
