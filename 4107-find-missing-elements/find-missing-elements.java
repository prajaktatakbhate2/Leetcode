class Solution {
    public List<Integer> findMissingElements(int[] nums) {
         Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);

        List<Integer> res = new ArrayList<>();
        for (int i = nums[0]; i <= nums[nums.length - 1]; i++)
            if (!set.contains(i)) res.add(i);

        return res;
    }
}
    
