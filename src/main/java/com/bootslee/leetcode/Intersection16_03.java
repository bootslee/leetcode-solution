package com.bootslee.leetcode;

public class Intersection16_03 {
    /**
     * 两点直线公式，(x-x1)/(x2-x1)=(y-y1)/(y2-y1) (y2-y1)x-x1(y2-y1)=y(x2-x1) -y1(x2-x1)
     *  一般直线公式 ax+by+c=0
     *  a=(y2-y1),b=(x1-x2) c=y1(x2-x1)-x1(y2-y1)
     * 交点坐标公式 ((b1c2-b2c1)/(a1b2-a2b1)，(a2c1-a1c2)/(a1b2-a2b1))
     *  特殊条件判断 是否平行 a1b2=a2b1且a1c2<>a2c1
    **/
    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        int a1=(end1[1]-start1[1]);
        int b1=(start1[0]-end1[0]);
        double c1=start1[1]*(end1[0]-start1[0]) - start1[0]*(end1[1]-start1[1]);

        int a2=(end2[1]-start2[1]);
        int b2=(start2[0]-end2[0]);
        double c2=start2[1]*(end2[0]-start2[0]) - start2[0]*(end2[1]-start2[1]);
        if(a1*b2==a2*b1){
            if(a1*c2!=a2*c1){
                //两线平行
                return new double[]{};
            }else {
                //重合 则分别判断线段1、线段2的起点是否在线段内
                double[] Q1={start2[0],start2[1]};
                if(onsegment(start1,end1,Q1)){
                    return Q1;
                }
                double[] Q2={start1[0],start1[1]};
                if(onsegment(start2,end2,Q2)){
                    return Q2;
                }
            }
        }
        double x=(b1*c2-b2*c1)/(a1*b2-a2*b1);
        double y=(a2*c1-a1*c2)/(a1*b2-a2*b1);
        double[] Q={x,y};
        if(onsegment(start1,end1,Q)&&onsegment(start2,end2,Q)){
            return Q;
        }
        return new double[]{};
    }

    /**
     * 判断Q点 是否在线段内。前面已经计算过是否相交了，因此此处无需重复判断
     * @param start1
     * @param end1
     * @param Q
     * @return
     */
    private boolean onsegment(int[] start1, int[] end1,double[] Q){
        /**
         * if((Q.x - Pi.x) * (Pj.y - Pi.y) == (Pj.x - Pi.x) * (Q.y - Pi.y)  //叉乘
         *        //保证Q点坐标在pi,pj之间
         *        && min(Pi.x , Pj.x) <= Q.x && Q.x <= max(Pi.x , Pj.x)
         *        && min(Pi.y , Pj.y) <= Q.y && Q.y <= max(Pi.y , Pj.y))
         *         return true;
         *     else
         *         return false;
        **/
        //Q[0]-start1[0])*(end1[1]-start1[1])==(end1[0]-start1[0])*(Q[1]-start1[1])
        if(Math.min(start1[0],end1[0])<=Q[0]&&Q[0]<=Math.max(start1[0],end1[0])
                &&Math.min(start1[1],end1[1])<=Q[1]&&Q[1]<=Math.max(start1[1],end1[1])){
            return true;
        }else{
            return false;
        }
    }


}
