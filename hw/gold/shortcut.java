import java.io.*;
import java.util.*;
class shortcut{
   public static int[] par;
   public static void main(String[] args) throws IOException{
      BufferedReader sin = new BufferedReader(new FileReader("shortcut.in"));
      PrintWriter sout = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
      StringTokenizer st = new StringTokenizer(sin.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int T = Integer.parseInt(st.nextToken());
      long[] c = new long[N+1];
      st = new StringTokenizer(sin.readLine());
      for(int i=1; i<=N; i++){
         c[i] = Long.parseLong(st.nextToken());
      }
      ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>(N+1);
      for(int i=0; i<=N; i++) adj.add(new ArrayList<Edge>());
      for(int i=0; i<M; i++){
         st = new StringTokenizer(sin.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         long w = Long.parseLong(st.nextToken());
         adj.get(a).add(new Edge(b,w));
         adj.get(b).add(new Edge(a,w));
      }
      long[] djik = new long[N+1];
      par = new int[N+1];
      Arrays.fill(djik,Long.MAX_VALUE);
      djik[1] = 0L;
      Arrays.fill(par,-1);
      PriorityQueue<State> pq = new PriorityQueue<State>();
      pq.add(new State(1,0));
      HashSet<Integer> seen = new HashSet<Integer>();
      seen.add(1);
      while(!pq.isEmpty()){
         State cur = pq.poll();
         int u = cur.v;
         seen.add(u);
         for(Edge e : adj.get(u)){
            int v = e.to;
            if(seen.contains(v)) continue;
            long newdis = djik[u] + e.w;
            if(newdis < djik[v]){
               djik[v] = newdis;
               par[v] = u;
               pq.add(new State(v,newdis));
            }
            else if(newdis == djik[v]){
               if(u < par[v]){
                  djik[v] = newdis;
                  par[v] = u;
                  pq.add(new State(v,newdis));
               }
            }
         }
      }
      long[] nums = new long[N+1];
      for(int i=1; i<=N; i++){
         int j = i;
         while(j != -1){
            nums[j] += c[i];
            j = par[j];
         }
      }
      long max = 0L;
      for(int i=1; i<=N; i++){
         System.out.println(nums[i] + " " + djik[i]);
         max = Math.max(max,nums[i]*(djik[i]-T));
      }
      System.out.println(max);
      sout.println(max);
      sout.close();
   }
   public static class State implements Comparable<State>{
      int v;
      long w;
      public State(int a, long b){
         v = a;
         w = b;
      }
      public int compareTo(State s){
         if(w > s.w) return 1;
         if(w < s.w) return -1;
         return 0;
      }
   }
   public static class Edge{
      int to;
      long w;
      public Edge(int a, long b){
         to = a;
         w = b;
      }
   }
}
