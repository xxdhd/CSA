public class Week13 {
    public static void main(String[] args) {

        System.out.println(test1(new int[]{1,2,3},3));

    }

    public static int test1(int[] nums ,int tar) {
        int res=0;
        int total=0;
        for(int i=0;i<nums.length;i++){

            for(int j=i;j< nums.length;j++){
                if(total<tar) {
                    total = nums[j] + total;
                }
            }
            if(total==tar){
                res++;
            }
            total=0;
        }


        return res;
    }
}
