import java.io.*;
import java.util.*;
public class mootube{
	public static void main(String[] args) throws IOException{
		BufferedReader min = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter mout = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer st = new StringTokenizer(min.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		LinkedList<Edge>[] edges = new LinkedList[N];
		for(int i=0; i<N; i++){
			edges[i] = new LinkedList<Edge>();
		}
		for(int a=1; a<N; a++){
			st = new StringTokenizer(min.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			edges[x].add(new Edge(y, w));
			edges[y].add(new Edge(x, w));
		}
		for(int query=0; query<Q; query++){
			st = new StringTokenizer(min.readLine());
			int threshold = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken())-1;
			int ret = 0;
			LinkedList<Integer> queue = new LinkedList<Integer>();
			queue.add(start);
			boolean[] seen = new boolean[N];
			seen[start] = true;
			while(!queue.isEmpty()){
				int curr = queue.removeFirst();
				for(Edge out: edges[curr]){
					if(!seen[out.d] && out.w>=threshold){
						seen[out.d] = true;
						queue.add(out.d);
						ret++;
					}
				}
			}
			mout.println(ret);
		}
		mout.close();
	}
	static class Edge{
		public int d, w;
		public Edge(int a, int b){
			d = a;
			w = b;
		}
	}
}
