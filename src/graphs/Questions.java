package graphs;

import java.lang.reflect.Array;
import java.util.*;

public class Questions {
    public void numIslands(char[][] grid, int sr, int sc, int[] xdir, int[] ydir) {
        grid[sr][sc] = '0';

        for (int i = 0; i < xdir.length; i++) {
            int r = sr + xdir[i];
            int c = sc + ydir[i];
            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == '1')
                numIslands(grid, r, c, xdir, ydir);
        }
    }


    public int numIslands(char[][] grid) {
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        int n = grid.length, m = grid[0].length;
        int islandCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    numIslands(grid, i, j, xdir, ydir);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    public void solve(char[][] grid, int sr, int sc, int[] xdir, int[] ydir) {
        grid[sr][sc] = '#';
        for (int i = 0; i < xdir.length; i++) {
            int r = sr + xdir[i];
            int c = sc + ydir[i];
            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 'O')
                solve(grid, r, c, xdir, ydir);
        }
    }

    public void solve(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && grid[i][j] == 'O') {
                    solve(grid, i, j, xdir, ydir);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '#') {
                    grid[i][j] = 'O';
                } else {
                    grid[i][j] = 'X';
                }
            }
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, maxAreaOfIsland(grid, i, j, xdir, ydir));
                }
            }
        }
        return max;
    }

    private int maxAreaOfIsland(int[][] grid, int sr, int sc, int[] xdir, int[] ydir) {
        grid[sr][sc] = 0;
        int count = 1;
        for (int i = 0; i < xdir.length; i++) {
            int r = sr + xdir[i];
            int c = sc + ydir[i];
            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1)
                count += maxAreaOfIsland(grid, r, c, xdir, ydir);
        }
        return count;
    }

    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, islandPerimeter(grid, i, j, xdir, ydir));
                }
            }
        }
        return max;
    }

    private int islandPerimeter(int[][] grid, int sr, int sc, int[] xdir, int[] ydir) {
        int perimeter = 4;

        grid[sr][sc] = 2;
        for (int i = 0; i < xdir.length; i++) {
            int r = sr + xdir[i];
            int c = sc + ydir[i];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length) {
                if (grid[r][c] == 1 || grid[r][c] == 2) {
                    perimeter -= 1;
                }
                if (grid[r][c] == 1) {
                    perimeter += islandPerimeter(grid, r, c, xdir, ydir);
                }

            }
        }
        return perimeter;
    }

    public int maxDistance(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        List<Integer> que = new LinkedList<>();
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        boolean[] vis = new boolean[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    vis[i * m + j] = true;
                    que.add(i * m + j);
                }
            }
        }
        if (que.size() == n * m) return -1;
        int lvl = -1;
        while (que.size() > 0) {
            int size = que.size();
            lvl++;
            while (size-- > 0) {
                int rem = que.removeFirst();
                int r = rem / m;
                int c = rem % m;

                for (int i = 0; i < xdir.length; i++) {
                    int x = r + xdir[i];
                    int y = c + ydir[i];
                    if (x >= 0 && y >= 0 && x < n && y < m && !vis[x * m + y]) {
                        vis[x * m + y] = true;
                        que.add(x * m + y);
                    }
                }
            }
        }
        return lvl;
    }

    public int[][] updateMatrix(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        List<Integer> que = new LinkedList<>();
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        int ans[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = 2;
                    que.add(i * m + j);
                }
            }
        }
        int lvl = 0;
        while (que.size() > 0) {
            int size = que.size();


            while (size-- > 0) {
                int rem = que.removeFirst();
                int r = rem / m;
                int c = rem % m;
                ans[r][c] = lvl;

                for (int i = 0; i < xdir.length; i++) {
                    int x = r + xdir[i];
                    int y = c + ydir[i];
                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        que.add(x * m + y);
                    }
                }
            }
            lvl++;
        }
        return ans;
    }

    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        List<Integer> que = new LinkedList<>();
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        int fresh = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    que.add(i * m + j);
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        int lvl = 0;
        if (fresh == 0) return 0;
        if (que.size() == 0) return -1;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int rem = que.removeFirst();
                int r = rem / m;
                int c = rem % m;
                for (int i = 0; i < xdir.length; i++) {
                    int x = xdir[i] + r;
                    int y = ydir[i] + c;
                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        fresh--;
                        if (fresh == 0) return lvl + 1;
                        que.add(x * m + y);
                    }
                }
            }
            lvl++;
        }
        return -1;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int xdir[] = new int[]{0, -1, 0, 1, -1, 1, -1, 1};
        int ydir[] = new int[]{-1, 0, 1, 0, -1, -1, 1, 1};
        int n = grid.length, m = grid[0].length;
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) return -1;
        List<Integer> que = new LinkedList<>();

        que.add(0);
        grid[0][0] = 1;
        int lvl = 0;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int vtx = que.removeFirst();
                int r = vtx / m;
                int c = vtx % m;
                if (r == n - 1 && c == m - 1) {
                    return lvl + 1;
                }
                for (int i = 0; i < xdir.length; i++) {
                    int x = xdir[i] + r;
                    int y = ydir[i] + c;
                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 0) {
                        grid[x][y] = 1;
                        que.addLast(x * m + y);
                    }
                }
            }
            lvl++;
        }
        return -1;
    }

    public static int[][] wallsAndGates(int[][] grid, int n, int m) {
        List<Integer> que = new LinkedList<>();
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    que.add(i * m + j);
                }
            }
        }
        int lvl = 1;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int vtx = que.removeFirst();
                int r = vtx / m;
                int c = vtx % m;
                grid[r][c] = lvl;
                for (int i = 0; i < xdir.length; i++) {
                    int x = r + xdir[i];
                    int y = c + ydir[i];
                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == Integer.MAX_VALUE) {
                        que.add(x * m + y);
                        grid[x][y] = 0;
                    }
                }
            }
            lvl++;
        }
        return grid;
    }

    public int shortestBridge(int[][] grid) {
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        List<Integer> que = new LinkedList<>();
        int n = grid.length, m = grid[0].length;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dfs_shortedBridge(grid, i * m + j, xdir, ydir);
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    que.add(i * m + j);
                }
            }
        }
        int lvl = 0;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int vtx = que.removeFirst();
                int r = vtx / m;
                int c = vtx % m;
                for (int i = 0; i < xdir.length; i++) {
                    int x = xdir[i] + r;
                    int y = ydir[i] + c;
                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] != 1) {
                        if (grid[x][y] == 2) return lvl;
                        grid[x][y] = 1;
                        que.add(x * m + y);
                    }
                }
            }
            lvl++;
        }
        return -1;
    }


    private void dfs_shortedBridge(int[][] grid, int src, int[] xdir, int[] ydir) {
        int n = grid.length, m = grid[0].length;
        int r = src / m;
        int c = src % m;
        grid[r][c] = 2;
        for (int i = 0; i < xdir.length; i++) {
            int x = r + xdir[i];
            int y = c + ydir[i];
            if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1) {
                dfs_shortedBridge(grid, x * m + y, xdir, ydir);
            }
        }
    }

    public int[] kahnAlgo(List<Integer>[] graph) {
        int n = graph.length;
        int[] indegree = new int[n];
        List<Integer> topoOrder = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int nbr : graph[i]) {
                indegree[nbr]++;
            }
        }
        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                topoOrder.add(i);
                que.add(i);
            }
        }
        while (que.size() > 0) {
            int rem = que.removeFirst();
            for (int nbr : graph[rem]) {
                indegree[nbr]--;
                if (indegree[nbr] == 0) {
                    topoOrder.add(nbr);
                    que.add(nbr);
                }
            }
        }
        if (topoOrder.size() == n) {
            return topoOrder.stream().mapToInt(Integer::intValue).toArray();
        } else {
            return new int[]{};
        }
    }

    public boolean topo_dfs(List<Integer>[] graph, int src, int vis[], List<Integer> topoOrder) {
        vis[src] = 1;
        for (int nbr : graph[src]) {
            if (vis[nbr] == 0) {
                if (topo_dfs(graph, nbr, vis, topoOrder)) return true;
            } else if (vis[nbr] == 1) {
                return true;
            }
        }
        topoOrder.add(src);
        vis[src] = 2;
        return false;
    }

    public boolean dfs(int src, int[][] graph, int[] vis) {
        vis[src] = 1;

        for (int nbr : graph[src]) {
            if (vis[nbr] == 1) {
                return true;
            } else if (vis[nbr] == 0) {
                if (dfs(nbr, graph, vis) == true) return true;
            }
        }

        vis[src] = 2;
        return false;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n];
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                dfs(i, graph, vis);
            }
        }
        List<Integer> safeStates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (vis[i] == 2) {
                safeStates.add(i);
            }
        }
        return safeStates;
    }

    public boolean canFinish(int n, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] arr : prerequisites) {
            int u = arr[1];
            int v = arr[0];
            graph[u].add(v);
        }
        int[] value = kahnAlgo(graph);
        return value.length == n;
    }

    public int[] findOrder(int n, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] arr : prerequisites) {
            int u = arr[1];
            int v = arr[0];
            graph[u].add(v);
        }
        List<Integer> topoOrder = new ArrayList<>();
//        return kahnAlgo(graph);
        int[] vis = new int[n];
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                if (topo_dfs(graph, i, vis, topoOrder)) return new int[0];
            }
        }
        Collections.reverse(topoOrder);
        return topoOrder.stream().mapToInt(Integer::intValue).toArray();
    }

    public int longestIncreasingPath(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        List<Integer> que = new LinkedList<>();
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        int indegree[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < xdir.length; d++) {
                    int x = xdir[d] + i;
                    int y = ydir[d] + j;
                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] > grid[i][j]) {
                        indegree[x][y]++;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (indegree[i][j] == 0) que.add(i * m + j);
            }
        }
        int lvl = 0;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int r = idx / m;
                int c = idx % m;
                for (int d = 0; d < xdir.length; d++) {
                    int x = xdir[d] + r;
                    int y = ydir[d] + c;
                    if (x >= 0 && y >= 0 && x < n && y < m && grid[r][c] < grid[x][y]) {
                        indegree[x][y]--;
                        if (indegree[x][y] == 0) que.add(x * m + y);
                    }
                }

            }
            lvl++;
        }
        return lvl;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;

        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        boolean res = true;
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                res = res && isOddCycleLen(graph, i, vis);
            }
        }
        return res;

    }

    private boolean isOddCycleLen(int[][] graph, int src, int[] vis) {
        List<Integer> que = new LinkedList<>();
        que.add(src);
        int color = 0;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int vtx = que.removeFirst();
                if (vis[vtx] != -1 && vis[vtx] != color) return false;
                vis[vtx] = color;
                for (int e : graph[vtx]) {
                    if (vis[e] == -1) {
                        que.add(e);
                    }
                }
            }
            color = (color + 1) % 2;
        }
        return true;
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] d : dislikes) {
            int u = d[0] - 1;
            int v = d[1] - 1;
            graph[u].add(v);
            graph[v].add(u);
        }
        //
        return false;
    }

    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        Stack<Integer> st = new Stack<>();
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) dfs_kosaraju_stackFill(adj, i, st, vis);
        }
        //reverse graph
        ArrayList<ArrayList<Integer>> reverseGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reverseGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> nbrs = adj.get(i);
            for (int e : nbrs) {
                reverseGraph.get(e).add(i);
            }
        }
        int count = 0;
        vis = new boolean[n];
        while (st.size() > 0) {
            int vtx = st.pop();

            if (!vis[vtx]) {
                dfs_kosaraju(reverseGraph, vtx, vis);
                count++;
            }
        }
        return count;
    }

    private void dfs_kosaraju(ArrayList<ArrayList<Integer>> reverseGraph, int vtx, boolean[] vis) {
        vis[vtx] = true;
        for (int e : reverseGraph.get(vtx)) {
            if (!vis[e]) dfs_kosaraju(reverseGraph, e, vis);
        }
    }

    private void dfs_kosaraju_stackFill(ArrayList<ArrayList<Integer>> adj, int src, Stack<Integer> st, boolean[] vis) {
        vis[src] = true;

        for (int e : adj.get(src)) {
            if (!vis[e]) dfs_kosaraju_stackFill(adj, e, st, vis);
        }
        st.push(src);
    }

    public int networkDelayTime(int[][] times, int n, int src) {

        List<int[]> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] time : times) {
            int u = time[0] - 1, v = time[1] - 1, w = time[2];
            graph[u].add(new int[]{v, w});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int dis[] = new int[n];
        Arrays.fill(dis, (int) 1e8);

        dis[src - 1] = 0;
        pq.add(new int[]{src - 1, 0});
        while (pq.size() > 0) {
            int[] rem = pq.remove();
            int u = rem[0], wsf = rem[1];
            if (dis[u] < wsf) continue;
            for (int[] nbr : graph[u]) {
                int w = nbr[1], v = nbr[0];
                if (dis[v] > wsf + w) {
                    dis[v] = wsf + w;
                    pq.add(new int[]{v, wsf + w});
                }
            }
        }
        int maxTime = 0;
        for (int i = 0; i < n; i++) {
            if (dis[i] == (int) 1e8) return -1;
            maxTime = Math.max(maxTime, dis[i]);
        }

        return maxTime;
    }

    class MinPair {
        int fsf;
        int tsf;
        int vtx;

        MinPair(int fees, int time, int vtx) {
            this.tsf = time;
            this.fsf = fees;
            this.vtx = vtx;
        }

        @Override
        public String toString() {
            return "MinPair{" + "fsf=" + fsf + ", tsf=" + tsf + ", vtx=" + vtx + '}';
        }
    }

    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<int[]> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] time : edges) {
            int u = time[0], v = time[1], w = time[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
        PriorityQueue<MinPair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.fsf));
        int timeCost[] = new int[n];
        Arrays.fill(timeCost, (int) 1e8);
        timeCost[0] = 0;
        pq.add(new MinPair(passingFees[0], 0, 0));
        while (pq.size() > 0) {
            MinPair rem = pq.remove();

            int vtx = rem.vtx, tsf = rem.tsf, fsf = rem.fsf;
            if (vtx == n - 1) return fsf;
            for (int[] nbr : graph[vtx]) {
                int w = nbr[1], v = nbr[0];
                if (timeCost[v] < tsf + w) continue;
                if (tsf + w <= maxTime) {
                    if (timeCost[v] > tsf + w) {
                        timeCost[v] = tsf + w;
                        pq.add(new MinPair(fsf + passingFees[v], tsf + w, v));
                    }

                }
            }
        }
        return -1;
    }

    class MinEffortPair {
        int idx;
        int diff;

        MinEffortPair(int idx, int diff) {
            this.idx = idx;
            this.diff = diff;
        }
    }

    public int minimumEffortPath(int[][] heights) {
        int n = heights.length, m = heights[0].length;
        PriorityQueue<MinEffortPair> pq = new PriorityQueue<>((a, b) -> a.diff - b.diff);
        int dis[][] = new int[n][m];
        for (int[] d : dis)
            Arrays.fill(d, (int) 1e8);
        dis[0][0] = 0;
        pq.add(new MinEffortPair(0, 0));
        int maxDistance = 0;
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        while (pq.size() > 0) {
            MinEffortPair rem = pq.remove();
            int idx = rem.idx, maxDiffSoFar = rem.diff;
            int r = idx / m, c = idx % m;
            if (dis[r][c] < maxDiffSoFar) continue;
            if (r == n - 1 && c == m - 1) return maxDiffSoFar;
            for (int i = 0; i < xdir.length; i++) {
                int x = xdir[i] + r;
                int y = ydir[i] + c;
                if (x >= 0 && x < n && y >= 0 && y < m) {
                    int currDiff = Math.abs(heights[x][y] - heights[r][c]);
                    int max = Math.max(currDiff, maxDiffSoFar);
                    if (dis[x][y] > max) {
                        dis[x][y] = max;
                        pq.add(new MinEffortPair(x * m + y, max));
                    }
                }
            }

        }

        return maxDistance;
    }

    public int countPaths(int n, int[][] roads) {
        int mod = (int) (1e9 + 7);
        List<int[]> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            int u = road[0], v = road[1], w = road[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
        PriorityQueue<long[]> pq = new PriorityQueue<long[]>((long[] a, long[] b) -> {
            return (int) (a[1] - b[1]);
        });
        long dis[] = new long[n];
        long paths[] = new long[n];
        Arrays.fill(dis, (long) (1e15) + 1);
        pq.add(new long[]{0, 0});
        dis[0] = 0;
        paths[0] = 1;
        while (pq.size() > 0) {
            long rem[] = pq.remove();
            int u = (int) rem[0];
            long wsf = rem[1];

            if (dis[u] < wsf) continue;

            for (int[] nbr : graph[u]) {
                int v = nbr[0], w = nbr[1];
                long newDis = (dis[u] + w);
                if (dis[v] > newDis) {
                    dis[v] = newDis;
                    paths[v] = paths[u] % mod;
                    pq.add(new long[]{v, newDis});
                } else if (dis[v] == newDis) {
                    paths[v] = (paths[v] + paths[u]) % mod;
                }
            }

        }
        int ans = (int) (paths[n - 1] % mod);
        return ans;
    }

    public int swimInWater(int[][] grid) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int n = grid.length, m = grid[0].length;
        pq.add(new int[]{0, grid[0][0]});
        boolean[][] vis = new boolean[n][m];
        vis[0][0] = true;
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        while (pq.size() > 0) {
            int[] rem = pq.remove();
            int idx = rem[0], cost = rem[1];
            int r = idx / m, c = idx % m;
            if (r == n - 1 && c == m - 1) return cost;
            for (int i = 0; i < xdir.length; i++) {
                int x = xdir[i] + r;
                int y = ydir[i] + c;
                if (x >= 0 && y >= 0 && x < n && y < n && !vis[x][y]) {
                    int newCost = Math.max(cost, grid[x][y]);
                    vis[x][y] = true;
                    pq.add(new int[]{x * m + y, newCost});
                }
            }
        }
        return -1;
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid1.length, m = grid1[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 1) {
                    if (isSubIsland(grid1, grid2, i, j)) count++;
                }
            }
        }
        return count;
    }

    private boolean isSubIsland(int[][] grid1, int[][] grid2, int r, int c) {
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        grid2[r][c] = 0;
        boolean isSub = grid1[r][c] == 1;
        for (int i = 0; i < xdir.length; i++) {
            int x = xdir[i] + r;
            int y = ydir[i] + c;
            if (x >= 0 && y >= 0 && x < grid1.length && y < grid1[0].length && grid2[x][y] == 1) {
                isSub = isSubIsland(grid1, grid2, x, y) && isSub;
            }
        }
        return isSub;
    }

    int countDistinctIslands(int[][] grid) {
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        int n = grid.length, m = grid[0].length;
        Set<List<String>> islandCountSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    List<String> list = new ArrayList<>();
                    countDistinctIslands(grid, i, j, i, j, xdir, ydir, list);
                    islandCountSet.add(list);
                }
            }
        }

        return islandCountSet.size();
    }

    private void countDistinctIslands(int[][] grid, int sr, int sc, int r, int c, int[] xdir, int[] ydir, List<String> list) {
        list.add((sr - r) + "," + (sc - c));
        grid[r][c] = 0;

        for (int i = 0; i < xdir.length; i++) {
            int x = xdir[i] + r;
            int y = ydir[i] + c;
            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1) {
                countDistinctIslands(grid, sr, sc, x, y, xdir, ydir, list);
            }
        }
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        Map<Integer, Node> map = new HashMap<>();
        cloneGraph(node, map);
        return map.get(1);
    }

    private void cloneGraph(Node node, Map<Integer, Node> map) {
        if (node == null)
            return;
        if (map.containsKey(node.val)) {
            return;
        }
        Node nn = new Node(node.val);
        map.put(nn.val, nn);

        for (Node nbr : node.neighbors) {
            cloneGraph(nbr, map);
            nn.neighbors.add(map.get(nbr.val));
        }
    }
}
