package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Basics {
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

    public void display(List<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (Edge e : graph[i]) {
                System.out.print(i + " -> " + e + ",");
            }
            System.out.println();
        }
    }

    public boolean hasPath(int src, int dest, List<Edge>[] graph, boolean vis[]) {
        if (src == dest) {
            return true;
        }
        vis[src] = true;
        boolean res = false;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                res = res || hasPath(e.v, dest, graph, vis);
            }
        }
        //not necessary
//        vis[src] = false;
        return res;
    }

    public void allPath(int src, int dest, List<Edge>[] graph, boolean vis[], String psf) {
        if (src == dest) {
            System.out.println(psf);
            return;
        }
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                allPath(e.v, dest, graph, vis, psf + e.v + ",");
            }
        }
        vis[src] = false;
    }

    public void createGraph() {
        int N = 8;
        List<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 11);
        addEdge(graph, 1, 3, 7);
        addEdge(graph, 1, 4, 12);
        addEdge(graph, 2, 4, 9);
        addEdge(graph, 2, 5, 3);
        addEdge(graph, 5, 6, 6);
        addEdge(graph, 5, 7, 5);
        addEdge(graph, 6, 7, 4);
//        display(graph);
        boolean vis[] = new boolean[N];
//        allPath(0, 7, graph, vis, "" + 0);
//        System.out.println(getConnectedComponents(graph));
//        bfs_with_cycle(graph);
//        bfs_without_cycle(graph);
        kahnAlgo(graph);
    }


    private void addEdge(List<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));

    }

    public int getConnectedComponents(List<Edge>[] graph) {
        int n = graph.length;
        int componentCount = 0;
        boolean vis[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                gcc_dfs(graph, i, vis);
                componentCount++;
            }
        }
        return componentCount;
    }

    private void gcc_dfs(List<Edge>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) gcc_dfs(graph, e.v, vis);
        }
    }

    public void hamiltonianPath(List<Edge>[] graph, int mainSr, int src, boolean vis[], int edgeCount, String psf) {
        if (edgeCount == vis.length) {
            if (checkEdge(graph, src, mainSr)) {
                System.out.println("cycle: " + psf);
            } else {
                System.out.println("path: " + psf);
            }
        }
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) hamiltonianPath(graph, mainSr, e.v, vis, edgeCount + 1, psf + e.v + ",");
        }
        vis[src] = false;
    }

    private boolean checkEdge(List<Edge>[] graph, int src, int mainSr) {
        for (Edge e : graph[src]) {
            if (e.v == mainSr) return true;
        }
        return false;
    }


    public void topologicalSort(List<Edge>[] graph) {
        int n = graph.length;
        List<Integer> topologicalOrder = new ArrayList<>();
        int vis[] = new int[n];
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) topo_dfs(graph, i, vis, topologicalOrder);
        }
        Collections.reverse(topologicalOrder); // order for u -> v
    }

    private boolean topo_dfs(List<Edge>[] graph, int src, int[] vis, List<Integer> topologicalOrder) {
        vis[src] = 1;

        for (Edge e : graph[src]) {
            if (vis[e.v] == 1) {
                //cycle found
                return true;
            } else if (vis[e.v] == 0) {
                if (topo_dfs(graph, e.v, vis, topologicalOrder)) return true;
            }
        }
        topologicalOrder.add(src);
        vis[src] = 2;
        return false;
    }


    public void bfs_with_cycle(List<Edge>[] graph) {
        List<Integer> que = new ArrayList<>();
        int n = graph.length;
        boolean vis[] = new boolean[n];
        int src = 0;
        que.add(src);
        int lvl = 0;
        while (que.size() > 0) {
            int size = que.size();
            System.out.println("LVL: " + lvl);
            while (size-- > 0) {
                int vtx = que.removeFirst();

                if (vis[vtx]) {
                    System.out.println("cycle found at: " + vtx);
                    continue;
                }
                System.out.print(vtx + ",");
                vis[vtx] = true;
                for (Edge e : graph[vtx]) {
                    if (!vis[e.v]) {
                        que.addLast(e.v);
                    }
                }
            }
            System.out.println();
            lvl++;
        }
    }

    public void bfs_without_cycle(List<Edge>[] graph) {
        List<Integer> que = new ArrayList<>();
        int n = graph.length;
        boolean vis[] = new boolean[n];
        int src = 0;
        que.add(src);
        vis[src] = true;
        int lvl = 0;
        while (que.size() > 0) {
            int size = que.size();
            System.out.println("LVL: " + lvl);
            while (size-- > 0) {
                int vtx = que.removeFirst();
                System.out.print(vtx + ",");
                for (Edge e : graph[vtx]) {
                    if (!vis[e.v]) {
                        vis[e.v] = true;
                        que.addLast(e.v);
                    }
                }
            }
            System.out.println();
            lvl++;
        }
    }

    public void kahnAlgo(List<Edge>[] graph) {
        int n = graph.length;
        int[] indegree = new int[n];
        List<Integer> topoOrder = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (Edge e : graph[i]) {
                int v = e.v;
                indegree[v]++;
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
            for (Edge e : graph[rem]) {
                indegree[e.v]--;
                if (indegree[e.v] == 0) {
                    topoOrder.add(e.v);
                    que.add(e.v);
                }
            }
        }
        if (topoOrder.size() != n) {
            System.out.println("CYCLE PRESENT");
        } else {
            System.out.println(topoOrder);
        }
    }


    public static void main(String[] args) {
        Basics c = new Basics();
        c.createGraph();
    }
}
