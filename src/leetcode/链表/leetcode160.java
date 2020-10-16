package leetcode.链表;
//两个链表何时相交。思路类似环形链表相遇
//两个链表同时遍历，到头再去另一个链表重来，相遇时即为相交点
public class leetcode160 {
    //暴力法
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        while(headA!=null){
            ListNode temp=headB;
            while(temp!=null){
                if(temp!=headA){
                    temp=temp.next;
                }else{
                    return temp;
                }
            }
            headA=headA.next;
        }
        return null;
    }
    //同时遍历的思路
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if(headA==null||headB==null){return null;}
        ListNode p1=headA;
        ListNode p2=headB;
        while(p1!=p2){
            if(p1==null){
                p1=headB;
            }else{
                p1=p1.next;
            }
            if(p2==null){
                p2=headA;
            }else{
                p2=p2.next;
            }
        }
        return p1;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
