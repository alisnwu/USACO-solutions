import java.io.*;
import java.util.*;
public class shuffle{
	public static void main(String[] args) throws IOException{
		BufferedReader sin = new BufferedReader(new FileReader("shuffle.in"));
		PrintWriter sout = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
		int N = Integer.parseInt(sin.readLine());
		int[] to = new int[N];
		int[] parent = new int[N];
		StringTokenizer st = new StringTokenizer(sin.readLine());
		for(int i=0; i<N; i++){
			to[i] = Integer.parseInt(st.nextToken())-1;
			parent[to[i]]++;
		}
		int ret = N;
		LinkedList<Integer> q = new LinkedList<Integer>();
		for(int i=0; i<N; i++){
			if(parent[i]==0){
				q.add(i);
				ret--;
			}
		}
		while(!q.isEmpty()){
			int curr = q.removeFirst();
			if(--parent[to[curr]] == 0){
				q.add(to[curr]);
				ret--;
			}
		}
		sout.println(ret);
		sout.close();
	}
}
