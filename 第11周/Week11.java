import java.util.Arrays;
import java.util.Comparator;

public class Week11 {
    public static void main(String[] args) {
        System.out.println("第一题");
        System.out.println(test1(new int[]{1, 2, 1, 1, 1, 1, 2, 2, 3}));
        System.out.println("第二题");
        System.out.println(test2(new int[]{1, 0, 0, 0, 1}, 2));
        System.out.println("第三题");
        System.out.println(test3(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
    }

    public static int test1(int[] nums){
        int[] res = new int[nums.length];

        for(int i=0;i<nums.length;i++){
            res[nums[i]]=res[nums[i]]+1;
            if(res[nums[i]]==nums.length/2+1){
                return nums[i];
            }
        }
        return -1;
    }
    public static boolean test2(int[] flowerbed,int n) {
        int num=0,count=1;
        for(int i=0;i<flowerbed.length;i++){
            if(flowerbed[i]==0){
                count++;
            }else{
                count=0;
            }
            if(count==3){
                num++;
                count=0;
            }
        }
        if(count==2){
            num++;
        }
        return num>=n;
    }
    //按照边界排序，选择范围小的区间，用区间总数减去非交叉区间的个数
    public static int test3(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]!=o2[1]){
                    return o1[1]-o2[1];
                }else {
                    return o1[0]-o2[0];
                }
            }
        });
        int count=1;
        int s = intervals[0][1];
        for(int i=1;i<intervals.length;i++){
            if(s<=intervals[i][0]){
                count++;
                s=intervals[i][1];
            }
        }
            return intervals.length-count;
    }
}
