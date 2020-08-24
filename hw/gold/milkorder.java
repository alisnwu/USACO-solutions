import java.io.*;
import java.util.*;
public class milkorder{
  private static int N;
  private static int M;
  private static ArrayList<Integer>[] adj;
  private static int[] inedge;
  public static void main(String[] args) throws IOException{
    BufferedReader min = new BufferedReader(new FileReader("milkorder.in"));
    PrintWriter mout = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
    StringTokenizer st = new StringTokenizer(min.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    adj = new ArrayList[N];
    inedge = new int[N];
    pq = new PriorityQueue<>();
    visited = new boolean[N];
    for(int i=0; i<N; i++) adj[i] = new ArrayList<>();
    for(int i=0; i<M; i++){
      st = new StringTokenizer(min.readLine());
      int total = Integer.parseInt(st.nextToken());
      int[] line = new int[total];
      for(int j=0; j<total; j++) line[j] = Integer.parseInt(st.nextToken()) - 1;
      for(int j=0; j<total-1; j++){
        adj[line[j]].add(line[j+1]);
        inedge[line[j+1]]++;
      }
      if(checkCycle()){
        for(int k=0; k<total-1; k++){
          adj[line[k]].remove(adj[line[k]].size()-1);
          inedge[line[k+1]]--;
        }
        break;
      }
    }
    for(int i=0; i<N; i++) if(inedge[i]==0) pq.offer(i);
    ArrayList<Integer> result = dfs();
    for(int i=0; i<N; i++){
      if(i<N-1) mout.print(result.get(i) + 1 + " ");
      else mout.print(result.get(i) + 1);
    }
    mout.close();
  }
  private static boolean checkCycle(int v, List<Integer> visited){
    if (visited.contains(v)) return true;
    visited.add(v);
    Iterator<Integer> it = adj[v].iterator();
    while(it.hasNext()){
      int i = it.next();
      if(checkCycle(i,visited)) return true;
    }
    visited.remove(visited.size()-1);
    return false;
  }
  private static boolean checkCycle(){
    List<Integer> visited = new ArrayList<>();
    for(int i=0; i<N; ++i) if(checkCycle(i,visited)) return true;
    return false;
  }
  private static boolean[] visited;
  private static PriorityQueue<Integer> pq;
  private static ArrayList<Integer> dfs(){
    ArrayList<Integer> result = new ArrayList<>();
    while(!pq.isEmpty()){
      int v = pq.poll();
      if(!visited[v]){
        visited[v] = true;
        result.add(v);
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()){
          int i = it.next();
          if (!visited[i]){
            inedge[i]--;
            if (inedge[i] == 0) pq.offer(i);
          }
        }
      }
    }
    return result;
  }
}
