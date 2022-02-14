import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
public class Week {
    public static void main(String[] args) {
        System.out.println("第一题");
        int a[] = {2,7,9};
        System.out.println(t1(a,2));
        System.out.println("第二题");
        int b[] = {0,0,1,0};
        t2(b);
        String s="fbxzaad";
        System.out.println("第三题");
        System.out.println(t3(s,31,100,3,32));
        System.out.println("第四题");
        String[] words = {"a","ab","abc"};
        int c[] = t4(words);
        System.out.println(c[0]+"   "+c[1]);
    }

    public static int t1(int nums[], int ori) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (ori == nums[i]) {
                ori = ori * 2;
            }

        }
        return ori;
    }

    public static void t2(int nums[]) {
        int total = 0;
        int res[] = new int[nums.length + 1];
        int res2[] = new int[nums.length + 1];
        int one = 0;
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            one = 0;
            zero = 0;
            if (nums[i] == 0) {
                total++;
            }
            for (int j = 0; j < i; j++) {
                if (nums[j] == 0) {
                    zero++;
                }
            }
            for (int k = i; k < nums.length; k++) {
                if (nums[k] == 1) {
                    one++;
                }
            }
            res[i] = zero + one;

        }
        res[nums.length] = total;
        int max = 0;
        for (int i = 0; i < res.length - 1; i++) {
            if (res[i + 1] > res[i]) {
                max = i + 1;
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (res[i] == res[max]) {
                System.out.println(i);
            }
        }
    }

    public static String t3(String s,int pow,int m,int k, int hashvalue){
        int len = s.length();
        int pos=-1;
        int val=0;
        int p=1;
        for(int i=len-1;i>len-k;i--){
            val = pow*(val+s.charAt(i)-96)%m;
            p = pow*p%m;
        }
        for(int i=len-k;i>=0;i--){
            val = (val+s.charAt(i)-96)%m;
            if(val==hashvalue){
                pos=i;
            }
            val=pow*(val+m-p*(s.charAt(i+k-1)-96)%m)%m;
        }
        return s.substring(pos,k);

    }
    static Map<Integer, Integer> map = new HashMap<>();
    static HashSet<Integer> set = new HashSet<>();
    public static int[] t4(String[] words) {
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String s = words[i];
            int mark = 0;
            for (int j = 0, len = s.length(); j < len; j++) {
                int v = s.charAt(j) - 'a';
                mark += (1 << v);
            }
            Integer v = map.get(mark);
            if (v == null) {
                map.put(mark, 1);
            } else {
                map.put(mark, v + 1);
            }
        }
        int max = 0;
        int total = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            int u = entry.getKey();
            if (set.contains(u)) {
                continue;
            }
            total++;
            int num = bfs(u);
            if (num > max) {
                max = num;
            }
        }
        return new int[] { total, max };
    }

    //广度优先
    public static int bfs(int root) {
        int cnt = map.get(root);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        set.add(root);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int i = 0; i < 26; i++) {
                if ((u & (1 << i)) == 0) {
                    int v = u + (1 << i);
                    Integer num = map.get(v);
                    if (num == null) {
                        continue;
                    }
                    if (set.contains(v)) {
                        continue;
                    }
                    queue.add(v);
                    set.add(v);
                    cnt += num;
                }
            }
            for (int i = 0; i < 26; i++) {
                if ((u & (1 << i)) > 0) {
                    int v = u - (1 << i);
                    Integer num = map.get(v);
                    if (num == null) {
                        continue;
                    }
                    if (set.contains(v)) {
                        continue;
                    }
                    queue.add(v);
                    set.add(v);
                    cnt += num;
                }
            }
            for (int i = 0; i < 26; i++) {
                if ((u & (1 << i)) > 0) {
                    for (int j = 0; j < 26; j++) {
                        if( (u & (1<<j)) == 0){
                            int v = u - (1<<i) + (1<<j);
                            Integer num = map.get(v);
                            if(num == null){
                                continue;
                            }
                            if( set.contains(v)){
                                continue;
                            }
                            queue.add(v);
                            set.add(v);
                            cnt += num;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}