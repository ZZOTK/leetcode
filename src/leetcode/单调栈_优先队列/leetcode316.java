package leetcode.单调栈_优先队列;
//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
//单调栈。
//这里判断条件较多。也是两个思路
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class leetcode316 {
    public String removeDuplicateLetters(String s) {
        //哈希表记录出现位置！！！！！！！这里不是出现次数
        HashMap<Character,Integer> map=new HashMap();
        LinkedList<Character> q=new LinkedList();
        for(int i=0;i<s.length();i++){
            map.put(s.charAt(i),i);
        }

        for(int i=0;i<s.length();i++){
            //char i 不在栈中
            if(!q.contains(s.charAt(i))){
                char c=s.charAt(i);
                //除了为空，小于peek。最后还有个最后出现位置大于i才能删除。不然最后结果会缺失这个数字
                while(!q.isEmpty()&&c<q.peekLast()&&map.get(q.peekLast())>i){
                    q.pollLast();
                }
                q.add(s.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            sb.append(q.poll());
        }
        return sb.toString();

    }

    //加入一个boolean数组记录是否在栈中
    public String removeDuplicateLetters1(String s) {
        Stack<Character> stk = new Stack<>();

        // 维护一个计数器记录字符串中字符的数量
        // 因为输入为 ASCII 字符，大小 256 够用了
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        boolean[] inStack = new boolean[256];
        for (char c : s.toCharArray()) {
            // 每遍历过一个字符，都将对应的计数减一
            count[c]--;

            if (inStack[c]) continue;

            while (!stk.isEmpty() && stk.peek() > c) {
                // 若之后不存在栈顶元素了，则停止 pop
                if (count[stk.peek()] == 0) {
                    break;
                }
                // 若之后还有，则可以 pop
                inStack[stk.pop()] = false;
            }
            stk.push(c);
            inStack[c] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.empty()) {
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
    }

}
