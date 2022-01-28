import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class Week8 {
    static Random random = new Random();

    public static void test() {
        System.out.println(task1("abbc", "dog cat cat fish"));
        System.out.println(task2(new int[]{1, 2, 2, 3, 0}));
        for (int i = 0; i < 5; i++) {
            int target = random.nextInt(15) - 3;
            System.out.println("target:" + target + "\tresult" + task3(new int[]
                    {0, 4, 5, 6, 8}, target));
        }
    }

    public static void main(String[] args) {
        test();


    }

    public static boolean task1(String pattern, String str) {
//第一题代码

        String[] arr = str.split("\\s+");
        if(pattern.length()!=arr.length){
            return false;
        }
        Map<Character,String> map = new HashMap<>();
        for(int i=0;i<pattern.length();i++){
            if(map.containsKey(pattern.charAt(i))){
                if(map.get(pattern.charAt(i)).equals(arr[i])==false){
                    return false;
                }
            }
            else{
                if(map.containsValue(arr[i])){
                    return false;
                }
                map.put(pattern.charAt(i),arr[i]);
            }
        }
        return true;
    }

    public static int task2(int[] nums) {
//第二题代码
        int res = 0;
        int res1[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res1[nums[i]]++;
            if (res1[nums[i]] > 1) {
                return nums[i];
            }
        }
        return 0;
    }

    public static int task3(int[] nums, int target) {
//第三题代码
        int temp = 0;
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int res = (left+right)/2;
            if(nums[res] == target){
                return res;
            }
            else if(nums[res]<target){
                left = res+1;
            }
            else{
                right = res-1;
            }
            temp = res;
        }
        return 0-temp;
    }
}