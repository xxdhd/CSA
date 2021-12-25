package CSA;

import java.util.HashMap;
import java.util.Stack;

public class Test1 {
	public static void main(String args[]) {
		System.out.println("第一题");
		int[] num11 = {4,1,2};
		int[] num12 = {1,3,4,2};
		int[] num21 = {2,4};
		int[] num22 = {1,2,3,4};
		int[] res = biggernum(num11,num12);
		for(int i=0;i<res.length;i++) {
			System.out.print(res[i]);
			System.out.print(",");
		}
		System.out.println();
		res =  biggernum(num21,num22);
		for(int i=0;i<res.length;i++) {
			System.out.print(res[i]);
			System.out.print(",");
		}
		System.out.println();
		System.out.println("第二题");
		int[] push1 = {1,2,3,4,5};
		int[] pop1 = {4,5,3,2,1};
		int[] pop2 = {4,3,5,1,2};
		System.out.println(moniStack(push1,pop2));
		System.out.println(moniStack(push1,pop1));
		
		System.out.println("第三题");
    	int[] test1 = {1,2,3,2,4,1};
    	int[] test2 = {2,2,2,2};
    	System.out.println(sumone(test1));
    	System.out.println(sumone(test2));
    }
    public static int sumone(int nums[])
    {
    	int sum = 0;
    	int[] hash = new int[100];
    	for(int i=0;i<nums.length;i++) {
    		hash[nums[i]]++;
    	}
    	for(int i=0;i<100;i++) {
    		if(hash[i]==1) {
    			sum = sum+i;
    		}
    	}
    	return sum;
    }
    public static boolean moniStack(int push[],int  pop[]) {
    	int pu =0;
    	int po =0;
    	int loc =0;
    	int j=0;
    	int[] res = new int[push.length]; 
    	for(int i=0;i<push.length;i++) {
    		res[loc]=push[i];
    		loc++;
    		while(loc !=0&&res[loc-1]==pop[j]) {
    			j++;
    			loc--;
    		}
    	}
    	if(j!=pop.length) {
    		return false;
    	}
    	return true;
    }
    public static int[] biggernum(int num1[], int num2[]) {
    	int res[] =new int[num1.length];
    	Stack<Integer> st = new Stack<Integer>();
    	HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
    	
    	for(int i=0;i<num2.length;i++) {
    		while(!st.isEmpty()&&st.peek()<num2[i]) {
    			hash.put(st.pop(), num2[i]);
    		}
    		st.push(num2[i]);
    	}
    	
    	for (int i=0;i<num1.length;i++) {
    		res[i] = hash.getOrDefault(num1[i], -1);
    	}
    	return res;
    }
}
