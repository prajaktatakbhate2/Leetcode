class Solution {
    public int pairSum(ListNode head) {

        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int max = 0;
        int i = 0;
        int j = list.size() - 1;

        while (i < j) {
            max = Math.max(max, list.get(i) + list.get(j));
            i++;
            j--;
        }

        return max;
    }
}