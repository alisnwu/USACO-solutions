import java.io.*;
import java.util.*;
public class fencedin{
	public int N;
	public int E;
	public void FencedIn(int N, int E){
		this.N = N;
		this.E = E;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
		StringTokenizer st = new StringTokenizer(fin.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] vertical = new int[N];
		int[] horizontal = new int[M];
		for(int i=0; i<N; i++) vertical[i] = Integer.parseInt(fin.readLine());
		for(int i=0; i<M; i++) horizontal[i] = Integer.parseInt(fin.readLine());
		Arrays.sort(vertical);
		Arrays.sort(horizontal);
		int E = 2 * N * M + N + M;
		Edge[] edges = new Edge[E];
		int e = 0;
		for(int i=0; i<N+1; i++){
			int weight = A - vertical[N - 1];
			if(i>0 && i<N) weight = vertical[i] - vertical[i - 1];
			else if(i==0) weight = vertical[0];
			for(int j=0; j<M; j++){
				edges[e] = new Edge(j * (N + 1) + i, (j + 1) * (N + 1) + i, weight);
				e++;
			}
		}
		for(int i=0; i<M+1; i++){
			int weight = B - horizontal[M - 1];
			if(i>0 && i<M) weight = horizontal[i] - horizontal[i - 1];
			else if(i==0) weight = horizontal[0];
			for(int j=0; j<N; j++){
				edges[e] = new Edge(i * (N + 1) + j, i * (N + 1) + j + 1, weight);
				e++;
			}
		}
		Arrays.sort(edges);
		FencedIn solver = new FencedIn((N + 1) * (M + 1), E);
		fout.println(solver.kruskal(edges));
		fout.close();
	}
	public long kruskal(Edge[] edges){
		DS ds = new DS(N);
		int edgesAdded = 0;
		int ind = 0;
		long totalWeight = 0L;
		while(edgesAdded<N-1){
			Edge curr = edges[ind];
			if(!ds.find(curr.source, curr.dest)){
				totalWeight += (long) curr.weight;
				ds.union(curr.source, curr.dest);
				edgesAdded++;
			}
			ind++;
		}
		return totalWeight;
	}
	static class DS{
		int[] arr;
		int[] size;
		int N;
		public DS(int N){
			this.N = N;
			arr = new int[N];
			size = new int[N];
			Arrays.fill(size, 1);
			for(int i=0; i<N; i++) arr[i] = i;
		}
		public int root(int curr){
			while(arr[curr]!=curr){
				arr[curr] = arr[arr[curr]];
				curr = arr[curr];
			}
			return curr;
		}
		public boolean find(int a, int b){
			if (root(a) == root(b)) return true;
			return false;
		}
		public void union(int a, int b){
			int aRoot = root(a);
			int bRoot = root(b);
			if(size[aRoot]<size[bRoot]){
				arr[aRoot] = arr[bRoot];
				size[bRoot] += size[aRoot];
			}
      else{
				arr[bRoot] = arr[aRoot];
				size[aRoot] += size[bRoot];
			}
		}
	}
	static class Edge implements Comparable<Edge>{
		int source;
		int dest;
		int weight;
		public Edge(int s, int d, int w){
			source = s;
			dest = d;
			weight = w;
		}
		@Override
		public int compareTo(Edge o){
			return Integer.compare(this.weight, o.weight);
		}
	}
}
