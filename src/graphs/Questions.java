package graphs;

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
            if (!vis[i])
                dfs_kosaraju_stackFill(adj, i, st, vis);
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
            if (!vis[e])
                dfs_kosaraju_stackFill(adj, e, st, vis);
        }
        st.push(src);
    }
}
