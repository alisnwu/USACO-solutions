import java.io.*;
import java.util.*;
public class moocast{
	public static void main(String[] args) throws IOException{
		BufferedReader min = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter mout = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		int N = Integer.parseInt(min.readLine());
		int[] x = new int[N];
		int[] y = new int[N];
		for(int i=0; i<N; i++){
			StringTokenizer st = new StringTokenizer(min.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		parent = new int[N];
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i=0; i<N; i++){
			parent[i] = i;
			for(int j=0; j<i; j++){
				int distance = (x[i]-x[j]) * (x[i]-x[j]) + (y[i]-y[j]) * (y[i]-y[j]);
				edges.add(new Edge(i, j, distance));
			}
		}
		Collections.sort(edges);
		int lastWeight = 0;
		int numComponents = N;
		for(Edge curr: edges){
			if(find(curr.i)!=find(curr.j)){
				merge(curr.i, curr.j);
				lastWeight = curr.w;
				if(--numComponents==1){
					break;
				}
			}
		}
		mout.println(lastWeight);
		mout.close();
	}
	static int[] parent;
	public static int find(int curr){
		return parent[curr] == curr ? curr : (parent[curr] = find(parent[curr]));
	}
	public static void merge(int x, int y){
		parent[find(x)] = find(y);
	}
	static class Edge implements Comparable<Edge>{
		public int i, j, w;
		public Edge(int a, int b, int c){
			i=a;
			j=b;
			w=c;
		}
		public int compareTo(Edge e){
			return w - e.w;
		}
	}
}
