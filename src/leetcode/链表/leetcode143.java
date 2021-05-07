package leetcode.链表;

import java.util.LinkedList;
import java.util.List;

//重排链表
// 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
public class leetcode143 {
    //新建一个线性表，双指针依次插入
    public void reorderList(ListNode head) {
        List<ListNode> path=new LinkedList<>();
        if(head==null){return;}
        while(head!=null){
            path.add(head);
            head=head.next;
        }
        int i=0;
        int j=path.size()-1;
        while (i<j){
            path.get(i).next= path.get(j);
            i++;
            //交错指向，判断i、j大小，需要提前跳出
            if(i>=j){break;}
            path.get(j).next=path.get(i);
            j--;
        }
        //最后一个节点指向null，避免出现环
        path.get(i).next=null;
    }
    //双端队列代替list
    public void reorderList1(ListNode head) {
        if(head == null){
            return;
        }
        LinkedList<ListNode> deque = new LinkedList<>();
        while(head != null){
            deque.add(head);
            head = head.next;
        }
        ListNode vh = new ListNode(0);
        while(!deque.isEmpty()){
            ListNode prev =  deque.pollFirst();
            vh.next = prev;
            ListNode next =  deque.pollLast();
            prev.next = next;
            if(next == null){
                return;
            }
            vh = next;
        }
        vh.next = null;
    }

}
