package graphs;

import java.nio.channels.Pipe;
import java.util.*;

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

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        init(n);
        int tc = n;
        for (int i = 0; i < n; i++) {
            int p1 = findPar(i);
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    int p2 = findPar(j);
                    if (p1 != p2) {
                        tc--;
                        par[p2] = p1;
                    }
                }
            }
        }
        return tc;

    }

    private boolean isSimilar(String str, String str1) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str1.charAt(i)) {
                count++;
            }
        }
        return count <= 2;
    }

    public boolean equationsPossible(String[] equations) {
        init(26);
        for (String s : equations) {
            if (s.charAt(1) == '=') {
                int u = s.charAt(0) - 'a';
                int v = s.charAt(3) - 'a';
                int p1 = findPar(u);
                int p2 = findPar(v);
                if (p1 != p2) {
                    merge(p1, p2);
                }
            }
        }
        for (String s : equations) {
            if (s.charAt(1) == '!') {
                int u = s.charAt(0) - 'a';
                int v = s.charAt(3) - 'a';
                int p1 = findPar(u);
                int p2 = findPar(v);
                if (p1 == p2) return false;
            }
        }
        return true;

    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        LinkedList<String> que = new LinkedList<>();
        Set<String> set = new HashSet<>();
        que.add(beginWord);
        for (String s : wordList) {
            set.add(s);
        }
        set.remove(beginWord);

        int lvl = 1;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                String word = que.remove();
                List<String> toRemove = new ArrayList<>();

                for (String s : set) {
                    if (isPossible(s, word)) {
                        if (s.equals(endWord)) return lvl + 1;
                        que.add(s);
                        toRemove.add(s);
                    }
                }
                toRemove.forEach(set::remove);
            }
            lvl++;
        }
        return 0;
    }

    private boolean isPossible(String s, String word) {
        if (s.length() != word.length()) return false;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != word.charAt(i)) count++;
        }
        return count == 1;
    }

    private void addEdge(Map<String, Set<String>> graph, String u, List<String> wordList) {
        for (String s : wordList) {
            if (isPossible(s, u)) {
                graph.get(u).add(s);
                graph.get(s).add(u);
            }
        }
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {

        Map<String, Set<String>> graph = new HashMap<>();
        graph.put(beginWord, new HashSet<>());

        for (int i = 0; i < wordList.size(); i++) {
            graph.put(wordList.get(i), new HashSet<>());
        }
        addEdge(graph, beginWord, wordList);
        for (int i = 0; i < wordList.size(); i++) {
            addEdge(graph, wordList.get(i), wordList);
        }

        Set<String> vis = new HashSet<>();
        vis.add(beginWord);
        LinkedList<String> que = new LinkedList<>();
        que.add(beginWord);
        int lvl = 1;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                String word = que.remove();
                for (String nbr : graph.get(word)) {
                    if (!vis.contains(nbr)) {
                        que.add(nbr);
                        vis.add(nbr);
                        if (nbr.equals(endWord)) return lvl + 1;
                    }
                }
            }
            lvl++;
        }
        return 0;
    }

    public class Pair {
        String word;
        String psf;

        Pair(String word, String psf) {
            this.word = word;
            this.psf = psf;
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, Set<String>> graph = new HashMap<>();
        graph.put(beginWord, new HashSet<>());

        for (int i = 0; i < wordList.size(); i++) {
            graph.put(wordList.get(i), new HashSet<>());
        }
        addEdge(graph, beginWord, wordList);
        for (int i = 0; i < wordList.size(); i++) {
            addEdge(graph, wordList.get(i), wordList);
        }

        Set<String> vis = new HashSet<>();
        vis.add(beginWord);
        LinkedList<Pair> que = new LinkedList<>();
        que.add(new Pair(beginWord, beginWord + ","));
        int lvl = 1;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                Pair pair = que.remove();
                String word = pair.word;
                String psf = pair.psf;
                for (String nbr : graph.get(word)) {
                    if (!vis.contains(nbr)) {
                        que.add(new Pair(nbr, psf + nbr + ","));
                        vis.add(nbr);
                        if (nbr.equals(endWord)) {
                            System.out.println(psf);
                        }
                    }
                }
            }
            lvl++;
        }
        return null;
    }

    class Edge {
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" + "u=" + u + ", v=" + v + ", w=" + w + '}';
        }
    }

    private void addEdge(List<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));

    }

    public void kruskalAlgo(int[][] edges, int n) {
        init(n);
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        List<Edge> graph[] = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            int p1 = findPar(u);
            int p2 = findPar(v);
            if (p1 != p2) {
                addEdge(graph, u, v, w);
                merge(p1, p2);
            }
        }
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int totalEdgeCount = (n * (n + 1)) / 2;
        int edges[][] = new int[totalEdgeCount][3];
        int idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges[idx][0] = i;
                edges[idx][1] = j;
                edges[idx][2] = getDistance(points[i], points[j]);
                idx++;
            }
        }
        init(n);
        int total = 0;
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            int p1 = findPar(u);
            int p2 = findPar(v);
            if (p1 != p2) {
                total += w;
                merge(p1, p2);
            }
        }
        return total;

    }

    private int getDistance(int[] pt1, int[] pt2) {
        int u1 = pt1[0], v1 = pt1[1], u2 = pt2[0], v2 = pt2[1];
        return Math.abs(u1 - u2) + Math.abs(v1 - v2);
    }

    public int supplyWater(int n, int k, int[] wells, int[][] pipes) {
        int edgeCount = pipes.length + n;
        int edges[][] = new int[edgeCount][3];
        int idx = 0;
        for (int p[] : pipes) {
            edges[idx++] = p;
        }
        for (int i = 0; i < n; i++) {
            edges[idx++] = new int[]{0, i + 1, wells[i]};
        }
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        init(n + 1);
        int cost = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            int p1 = findPar(u);
            int p2 = findPar(v);
            if (p1 != p2) {
                merge(p1, p2);
                cost += w;
            }

        }
        return cost;

    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        init(n);
        Map<Integer, String> parentStringMap = new HashMap<>();
        for (int i = 0; i < pairs.size(); i++) {
            int u = pairs.get(i).get(0);
            int v = pairs.get(i).get(1);
            int p1 = findPar(u);
            int p2 = findPar(v);
            if (p1 != p2) {
                par[p1] = p2;
            }
        }
        for (int i = 0; i < n; i++) {
            int p1 = findPar(i);

            if (parentStringMap.containsKey(p1)) {
                parentStringMap.put(p1, parentStringMap.get(p1) + s.charAt(i));
            } else {
                parentStringMap.put(p1, s.charAt(i) + "");
            }
        }
        for (Map.Entry<Integer, String> entry : parentStringMap.entrySet()) {
            String value = entry.getValue();
            char[] chars = value.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            entry.setValue(sorted);
        }
        int[] pos = new int[n];
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int p1 = findPar(i);
            int idx = pos[p1];
            char value = parentStringMap.get(p1).charAt(idx);
            pos[p1]++;
            ans.append(value);

        }

        return ans.toString();

    }
}
