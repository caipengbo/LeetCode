package graph;

// prim(普里姆)算法
    // 算法思路：
    //     定义一个临时的一维数组，用于存放可用的连接边，数组下标为顶点序号，值为权值
    //     任选一个点作为起点，以起点的所有权值对数组进行初始化
    //     找出数组中最小权值的边，即为最小生成树中的一条有效边
    //     将找到的最小边在数组中赋值为0，代表已经使用过。并将数组与找到顶点的所有边进行比较，若顶点的边的权值比当前数组存放的可用边的权值小，则进行覆盖
    //     重复循环2，3，4的操作直至遍历完所有顶点
class MST { 
    // Number of vertices in the graph 
    private static final int V = 5; 
  
    // A utility function to find the vertex with minimum key 
    // value, from the set of vertices not yet included in MST 
    int minKey(int key[], Boolean mstSet[]) 
    { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        for (int v = 0; v < V; v++) 
            if (mstSet[v] == false && key[v] < min) { 
                min = key[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 
  
    // A utility function to print the constructed MST stored in 
    // parent[] 
    void printMST(int parent[], int graph[][]) 
    { 
        System.out.println("Edge \tWeight"); 
        for (int i = 1; i < V; i++) 
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]); 
    } 
  
    // Function to construct and print MST for a graph represented 
    // using adjacency matrix representation 
    void primMST(int graph[][]) 
    { 
        // Array to store constructed MST 
        int parent[] = new int[V]; 
  
        // Key values used to pick minimum weight edge in cut 
        int key[] = new int[V]; 
  
        // To represent set of vertices included in MST 
        Boolean mstSet[] = new Boolean[V]; 
  
        // Initialize all keys as INFINITE 
        for (int i = 0; i < V; i++) { 
            key[i] = Integer.MAX_VALUE; 
            mstSet[i] = false; 
        } 
  
        // Always include first 1st vertex in MST. 
        key[0] = 0; // Make key 0 so that this vertex is 
        // picked as first vertex 
        parent[0] = -1; // First node is always root of MST 
  
        // The MST will have V vertices 
        for (int count = 0; count < V - 1; count++) { 
            // Pick thd minimum key vertex from the set of vertices 
            // not yet included in MST 
            int u = minKey(key, mstSet); 
  
            // Add the picked vertex to the MST Set 
            mstSet[u] = true; 
  
            // Update key value and parent index of the adjacent 
            // vertices of the picked vertex. Consider only those 
            // vertices which are not yet included in MST 
            for (int v = 0; v < V; v++) 
  
                // graph[u][v] is non zero only for adjacent vertices of m 
                // mstSet[v] is false for vertices not yet included in MST 
                // Update the key only if graph[u][v] is smaller than key[v] 
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) { 
                    parent[v] = u; 
                    key[v] = graph[u][v]; 
                } 
        } 
  
        // print the constructed MST 
        printMST(parent, graph); 
    } 

} 
public class MinimusSpanTree2 {
    public static void main(String[] args) 
    { 
        /* Let us create the following graph 
        2 3 
        (0)--(1)--(2) 
        | / \ | 
        6| 8/ \5 |7 
        | /     \ | 
        (3)-------(4) 
            9         */
        MST t = new MST(); 
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 }, 
                                      { 2, 0, 3, 8, 5 }, 
                                      { 0, 3, 0, 0, 7 }, 
                                      { 6, 8, 0, 0, 9 }, 
                                      { 0, 5, 7, 9, 0 } }; 
  
        // Print the solution 
        t.primMST(graph); 
    } 
}