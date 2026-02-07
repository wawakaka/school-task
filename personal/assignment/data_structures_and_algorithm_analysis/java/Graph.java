import java.util.*;

class Graph {
    private int V; // Jumlah vertices (nodes)
    // Kita pakai 'java.util.LinkedList' biar tidak bentrok dengan file lokal
    private java.util.LinkedList<Integer> adj[]; 

    // Constructor
    Graph(int v) {
        V = v;
        adj = new java.util.LinkedList[v];
        for (int i = 0; i < v; ++i)
            // Inisialisasi setiap elemen array sebagai LinkedList
            adj[i] = new java.util.LinkedList<>();
    }

    // Fungsi buat nambahin edge (koneksi antar node)
    void addEdge(int v, int w) {
        adj[v].add(w); 
    }

    // --- 1. DFS (Depth First Search) ---
    void DFS(int startNode) {
        boolean visited[] = new boolean[V];
        
        System.out.print("Hasil DFS (mulai dari node " + startNode + "): ");
        DFSUtil(startNode, visited);
        System.out.println();
    }

    private void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");

        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // --- 2. BFS (Breadth First Search) ---
    void BFS(int startNode) {
        boolean visited[] = new boolean[V];
        // Pakai java.util.LinkedList secara eksplisit
        java.util.LinkedList<Integer> queue = new java.util.LinkedList<>();

        visited[startNode] = true;
        queue.add(startNode);

        System.out.print("Hasil BFS (mulai dari node " + startNode + "): ");

        while (queue.size() != 0) {
            startNode = queue.poll();
            System.out.print(startNode + " ");

            Iterator<Integer> i = adj[startNode].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }

    public static void main(String args[]) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.BFS(2); 
        g.DFS(2); 
    }
}