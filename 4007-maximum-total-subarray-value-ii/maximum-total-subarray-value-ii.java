import java.util.*;

class Solution {

    static class SparseTable {
        int[][] mx, mn;
        int[] lg;

        SparseTable(int[] a) {
            int n = a.length;
            int m = 32 - Integer.numberOfLeadingZeros(n) + 1;

            mx = new int[n][m];
            mn = new int[n][m];
            lg = new int[n + 1];

            for (int i = 2; i <= n; i++)
                lg[i] = lg[i >> 1] + 1;

            for (int i = 0; i < n; i++) {
                mx[i][0] = mn[i][0] = a[i];
            }

            for (int j = 1; j < m; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    mx[i][j] = Math.max(mx[i][j - 1],
                            mx[i + (1 << (j - 1))][j - 1]);

                    mn[i][j] = Math.min(mn[i][j - 1],
                            mn[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        int getMax(int l, int r) {
            int k = lg[r - l + 1];
            return Math.max(mx[l][k], mx[r - (1 << k) + 1][k]);
        }

        int getMin(int l, int r) {
            int k = lg[r - l + 1];
            return Math.min(mn[l][k], mn[r - (1 << k) + 1][k]);
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        SparseTable st = new SparseTable(nums);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));

        for (int l = 0; l < n; l++) {
            long val = (long) st.getMax(l, n - 1) - st.getMin(l, n - 1);
            pq.offer(new long[]{val, l, n - 1});
        }

        long ans = 0;

        while (k-- > 0) {
            long[] cur = pq.poll();

            long val = cur[0];
            int l = (int) cur[1];
            int r = (int) cur[2];

            ans += val;

            if (r > l) {
                long nxt = (long) st.getMax(l, r - 1) - st.getMin(l, r - 1);
                pq.offer(new long[]{nxt, l, r - 1});
            }
        }

        return ans;
    }
}