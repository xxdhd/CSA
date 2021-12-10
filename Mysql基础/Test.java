package CSA;

import java.util.ArrayList;
import java.util.List;
 
public class Test {
 
    public static void main(String args[]) {
    	System.out.println("第一题：");
        int[] nums = {1,2,3,4};
        List<List<Integer>> res = subset(nums);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
        System.out.println("第二题：");
        String n = "1230";
        String n1 = "-125";
        System.out.println(diandao(n));
        System.out.println(diandao(n1));
        System.out.println("第三题：");
        System.out.println(climb(3));
        System.out.println(climb(4));
        
    }

    public static List<List<Integer>> subset(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        int n = (int) Math.pow(2, len);
 
        for (int i = 0; i<n; i++) {
            String s = Integer.toBinaryString(i);
            List<Integer> temp = new ArrayList<>();
            for (int j = s.length() - 1; j >= 0; j--) {
                if (s.charAt(j) == '1') {
                    temp.add(nums[s.length()-1-j]); 
                }
            }
            result.add(temp);
        }
        return result;
    }
    public static int diandao(String num) {
    	String res = "";
		int a;
		int len = num.length();
		if(num.charAt(0)=='-') {
			for(int i=len-1;i>0;i--) {
				res = res+num.charAt(i);
			}
			a = Integer.parseInt(res);
			a = 0-a;
		}
		else {
			for(int i=len-1;i>=0;i--) {
				res = res+num.charAt(i);
			}
			a = Integer.parseInt(res);
		}
		
		return a;
    	
    }
    public static int climb(int n) {
    	if(n==1||n==0) {
    		return 1;
    	}
    	return climb(n-1)+climb(n-2);
    }
}