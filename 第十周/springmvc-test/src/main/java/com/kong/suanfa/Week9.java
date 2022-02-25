package com.kong.suanfa;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Week9 {
static Random random=new Random();
    public static void printArr(int[]nums){
System.out.println(Arrays.toString(nums));
}
public static void test(){
int[]nums1=new int[random.nextInt(20)+1];
for(int i=0;i<nums1.length;i++)
nums1[i]=random.nextInt(30)-10;
printArr(nums1);
printArr(task1(nums1));
System.out.println("======================");
int[]nums2=new int[random.nextInt(20)+1];
for(int i=0;i<nums2.length;i++)
nums2[i]=random.nextInt(3);
printArr(nums2);
task2(nums2);
        printArr(nums2);
}
public static void main(String[] args) {
test();
}
public static int[] task1(int[]nums) {
//第一题代码
int[]res=new int[nums.length];
    Stack<Integer> stack= new Stack<Integer>();
    stack.push(0);
    int index = 1;
    int a;
    while(index < nums.length) {
        if (!stack.isEmpty() && nums[index] > nums[stack.peek()]) {
            a = stack.pop();
            res[a] = index-a;
        } else {
            stack.push(index);
            index++;
        }
    }
        while(!stack.isEmpty()) {
            res[stack.pop()] = 0;
        }

return res;
}
public static void task2(int[] nums) {
        int temp;
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]>=nums[j]){
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }

        }
//第二题代码
}
}