package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DSU {
    int[] par;
    int[] size;

    public void init(int n) {
        par = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;
        }
    }

    public int findPar(int u) {
        if (par[u] == u) return u;
        return par[u] = findPar(par[u]);
    }

    public void merge(int p1, int p2) {
        if (size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    public void dsu(int[][] edges, int n) {
        init(n);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int p1 = findPar(u);
            int p2 = findPar(v);
            if (p1 != p2) {
                merge(p1, p2);
            }
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        init(n);
        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            int p1 = findPar(u);
            int p2 = findPar(v);
            if (p1 != p2) {
                merge(p1, p2);
            } else return edge;
        }
        return new int[]{};
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        init(26);
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            int u = ch1 - 'a';
            int v = ch2 - 'a';
            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                par[p1] = par[p2] = Math.min(p1, p2);
            }
        }
        String ans = "";
        for (int i = 0; i < baseStr.length(); i++) {
            char ch = baseStr.charAt(i);
            int p1 = findPar(ch - 'a');
            char t = (char) (p1 + 'a');
            ans += t;

        }
        return ans;
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        init(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (isConnected[i][j] == 1) {
                        int p1 = findPar(i);
                        int p2 = findPar(j);
                        if (p1 != p2) {
                            merge(p1, p2);
                        }
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < par.length; i++) {
            if (par[i] == i) count++;
        }
        return count;
    }

    public int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        init(n);
        int totalComps = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i != j) {
                    if (isConnected[i][j] == 1) {
                        int p1 = findPar(i);
                        int p2 = findPar(j);
                        if (p1 != p2) {
                            merge(p1, p2);
                            totalComps--;
                        }

                    }
                }
            }
        }
        return totalComps;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        int n = grid.length, m = grid[0].length;
        init(n * m); // in init u can create par[i*m+j] = i & size[i*m + j] = 1 only if grid[i][j] = 1 else par[i*m+j] = -1 & size[i*m + j] = 0

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = grid[i][j];
                if (value == 1) {
                    int p1 = findPar(i * m + j); // if finding p1 here,make sure all the neighbours leader will be this otherwise find p1 evertime
                    for (int k = 0; k < xdir.length; k++) {
                        int r = xdir[k] + i;
                        int c = ydir[k] + j;
                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                            int p2 = findPar(r * m + c);
                            if (p1 != p2) {
                                par[p2] = p1;
                                size[p1] += size[p2];
                            }
                        }
//                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
//                            int p1 = findPar(i * m + j);
//                            int p2 = findPar(r * m + c);
//                            if (p1 != p2) {
//                                merge(p1, p2);
//                            }
//                        }
                    }
                }
            }

        }
        int max = 0;
        for (int i = 0; i < n * m; i++) {
            if (par[i] == i && grid[i / m][i % m] == 1) {
                max = Math.max(size[i], max);
            }
        }
        return max;
    }

    public int[] numberOfIslandII(int n, int m, int[][] queries, int k) {
        par = new int[n * m];
        int xdir[] = new int[]{0, -1, 0, 1};
        int ydir[] = new int[]{-1, 0, 1, 0};
        Arrays.fill(par, -1);
        List<Integer> res = new ArrayList<>();
        int count = 0;
        for (int[] q : queries) {
            int r = q[0], c = q[1];
            if (par[r * m + c] != -1) {
                res.add(count);
                continue;
            }
            par[r * m + c] = r * m + c;
            count++;

            for (int i = 0; i < xdir.length; i++) {
                int x = r + xdir[i];
                int y = c + ydir[i];
                if (x >= 0 && y >= 0 && x < n && y < m && par[x * m + y] != -1) {
                    int p2 = findPar(x * m + y);
                    int p1 = r * m + c;
                    if (p1 != p2) {
                        par[p2] = p1;
                        count--;
                    }
                }
            }
            res.add(count);

        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

}
