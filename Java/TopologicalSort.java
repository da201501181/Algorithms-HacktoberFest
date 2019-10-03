import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TopologicalSort
 */
class TopologicalSort {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.topologicalSort();
    }
}

/**
 * Graph
 */
class Graph {
    private int numOfVertices;
    private LinkedList<Integer> adj[];

    public Graph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        this.adj = new LinkedList[numOfVertices + 1];
        for (int i = 1; i <= numOfVertices; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to) {
        this.adj[from].add(to);
    }

    public void topologicalSort() {
        int count = 0;
        int indegree[] = new int[this.numOfVertices + 1];
        for (int i = 1; i <= this.numOfVertices; i++) {
            for (int to : this.adj[i]) {
                indegree[to]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> topologicalSortedOrder = new ArrayList<>();
        for (int i = 1; i <= this.numOfVertices; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int top = q.poll();
            topologicalSortedOrder.add(top);
            count++;

            for (int to : this.adj[top]) {
                if (--indegree[to] == 0) {
                    q.add(to);
                }
            }
        }

        if (count != this.numOfVertices) {
            System.out.println("There is a cycle in the graph");
        } else {
            System.out.print("Topological sort of the given graph is:");
            for (int node : topologicalSortedOrder) {
                System.out.print(" " + node);
            }
        }
    }
}