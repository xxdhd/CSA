public class Week12 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(5);
        ListNode l4 = new ListNode(7);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=null;
        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(4);
        ListNode p3 = new ListNode(6);
        ListNode p4 = new ListNode(8);
        p1.next=p2;
        p2.next=p3;
        p3.next=p4;
        p4.next=null;
        ListNode p11 = new ListNode(1);
        ListNode p12 = new ListNode(3);
        ListNode p13 = new ListNode(5);
        ListNode p14 = new ListNode(7);
        p11.next=p12;
        p12.next=p13;
        p13.next=p14;
        p14.next=null;
        ListNode p111 = new ListNode(1);
        ListNode p112 = new ListNode(2);
        ListNode p113 = new ListNode(2);
        ListNode p114 = new ListNode(1);
        p111.next=p112;
        p112.next=p113;
        p113.next=p114;
        p114.next=null;
        System.out.println("第一题");
        print(test1(l1));
        System.out.println("第二题");
        print(test2(p11,p1));
        System.out.println("第三题");
        System.out.println(test3(p111));

    }
    public static ListNode test1(ListNode listNode){
        ListNode res= new ListNode();
        ListNode newlist = new ListNode();
        while(listNode!=null) {
            res = listNode;
            listNode = listNode.next;
            res.next = newlist.next;
            newlist.next = res;

        }

        return newlist.next;
    }
    public static ListNode test2(ListNode list1,ListNode list2){
        ListNode res = new ListNode();
        ListNode res2 = new ListNode();
        ListNode res3 = new ListNode();
        res2.next=res;
        while(list1!=null && list2!=null){
            if(list1.val<= list2.val){
                res3 = list1;
                list1=list1.next;
                res.next=res3;

                res=res.next;
                res.next=null;

            }else {
                res3 = list2;
                list2=list2.next;
                res.next=res3;

                res=res.next;
                res.next=null;
            }
        }
        if(list1!=null){
            res.next=list1;
        }else {
            res.next=list2;
        }


        return res2.next.next;
    }
    public static boolean test3(ListNode listNode){
        ListNode ex = test1(listNode);
        while(listNode!=null){
            if(listNode.val==ex.val){
                listNode=listNode.next;
                ex=ex.next;
            }else {
                return false;
            }
        }
        return true;
    }
    public static void print(ListNode listNode){
        while(listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }
    }
}
