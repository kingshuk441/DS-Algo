package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

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
        return "{" + this.u + " -> " + this.v + ": " + this.w + "}";
    }
}

public class Algo {
    public static void displayGraph(List<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print("Edges on vertex " + i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print(e + ",");
            }
            System.out.println();
        }
    }

    public static void addEdge(List<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }

    public static void main(String[] args) {
        int N = 9;
        List<Edge>[] graph = new ArrayList[N];
        int[][] edges = {{0, 6, 9}, {6, 2, 11}, {1, 2, 4}, {0, 1, 8}, {2, 3, 5}, {3, 4, 9}, {3, 5, 13}, {4, 5, 7}, {5, 7, 41}, {7, 8, 53}};
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            addEdge(graph, edge[0], edge[1], edge[2]);
        }

        List<Edge>[] mst = getMST_ByPrims(graph, N);
//        int src = 0;
//        int[] dis = dikstraAlgo(graph, N, src);
//        for (int i = 0; i < dis.length; i++) {
//            System.out.println("Distance from " + src + " to " + i + " is " + dis[i]);
//        }
//        displayGraph(mst);
    }

    static class PrimsPair {
        int vtx;
        int wt;
        int par;

        PrimsPair(int vtx, int wt, int par) {
            this.vtx = vtx;
            this.wt = wt;
            this.par = par;
        }
    }

    private static List<Edge>[] getMST_ByPrims(List<Edge>[] graph, int n) {
        List<Edge>[] mst = new ArrayList[n];
        for (int i = 0; i < n; i++) mst[i] = new ArrayList<>();
        PriorityQueue<PrimsPair> pq = new PriorityQueue<>((a, b) -> a.wt - b.wt);
        pq.add(new PrimsPair(0, 0, -1));
        int edgeCount = 0;
        int[] parent = new int[n];
        boolean vis[] = new boolean[n];
        parent[0] = -1;
        int lastNode = -1;

        while (edgeCount < n - 1) {
            PrimsPair rem = pq.remove();
            int par = rem.par, wt = rem.wt, vtx = rem.vtx;
            if (vis[vtx]) continue;

            vis[vtx] = true;

            if (par != -1) {
                parent[vtx] = par;
                lastNode = vtx;
                addEdge(mst, par, vtx, wt);
                edgeCount++;
            }
            for (Edge e : graph[vtx]) {
                if (!vis[e.v]) {
                    pq.add(new PrimsPair(e.v, e.w, vtx));
                }
            }
        }
        System.out.println(lastNode);
        while (lastNode != -1) {
            lastNode = parent[lastNode];
            System.out.println(lastNode);
        }
        return mst;
    }

    static class DikstraPair {
        int vtx;
        int wsf;
        int par;

        DikstraPair(int vtx, int wsf, int par) {
            this.vtx = vtx;
            this.wsf = wsf;
            this.par = par;
        }
    }

    public static int[] dikstraAlgo(List<Edge>[] graph, int n, int src) {
        PriorityQueue<DikstraPair> pq = new PriorityQueue<>((a, b) -> a.wsf - b.wsf);
        pq.add(new DikstraPair(src, 0, -1));
        int dis[] = new int[n];
        Arrays.fill(dis, (int) 1e8);
        int[] parent = new int[n];
        parent[src] = -1;
        dis[src] = 0;
        while (pq.size() > 0) {
            DikstraPair rem = pq.remove();
            int par = rem.par, vtx = rem.vtx, wsf = rem.wsf;
            if (dis[vtx] < wsf) continue;
            if (par != -1) {
                parent[vtx] = par;
            }
            for (Edge e : graph[vtx]) {
                if (e.w + wsf < dis[e.v]) {
                    dis[e.v] = e.w + wsf;
                    pq.add(new DikstraPair(e.v, e.w + wsf, vtx));
                }
            }
        }
        return dis;
    }

    public int[] bellmonFord(int[][] edges, int n, int src) {
        int dis[] = new int[n];
        Arrays.fill(dis, (int) 1e8);
        dis[src] = 0;
        boolean negativeCycle = false;
        for (int i = 0; i <= n; i++) {
            int[] nDis = Arrays.copyOf(dis, n);
            boolean isUpdate = false;
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], wt = edge[2];
                if (dis[v] > dis[u] + wt) {
                    nDis[v] = dis[u] + wt;
                    isUpdate = true;
                }
            }
            if (i == n && isUpdate) {
                negativeCycle = true;
            }
            dis = nDis;
            if (!isUpdate) break;
        }

        return negativeCycle ? null : dis;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int dis[] = new int[n];
        Arrays.fill(dis, (int) 1e8);
        dis[src] = 0;
        for (int i = 1; i <= k + 1; i++) {
            int nDis[] = Arrays.copyOf(dis, n);
            for (int edge[] : flights) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (dis[u] + w < nDis[v]) {
                    nDis[v] = dis[u] + w;
                }
            }
            dis = nDis;
        }
        return dis[dst] != (int) 1e8 ? dis[dst] : -1;
    }
}
