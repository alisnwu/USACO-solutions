import java.io.*;
import java.util.*;
public class barnpainting{
  static long[][] dp;
	static final int MOD = 1000000007;
	static LinkedList<Integer>[] edges;
	static int[] color;
	public static void main(String[] args) throws IOException{
		BufferedReader bin = new BufferedReader(new FileReader("barnpainting.in"));
		PrintWriter bout = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));
		StringTokenizer st = new StringTokenizer(bin.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		color = new int[N];
		Arrays.fill(color, -1);
		edges = new LinkedList[N];
		dp = new long[N][3];
		for(int i=0; i<N; i++){
			edges[i] = new LinkedList<Integer>();
			Arrays.fill(dp[i], -1);
		}
		for(int i=1; i<N; i++){
			st = new StringTokenizer(bin.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		for(int i=0; i<K; i++){
			st = new StringTokenizer(bin.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			color[a] = c;
		}
		long ret = solve(0,0,-1,-1) + solve(0,1,-1,-1) + solve(0,2,-1,-1);
		bout.println(ret % MOD);
		bout.close();
	}
	public static long solve(int currV, int currC, int parV, int parC){
		if(currC==parC || (color[currV]>=0 && currC!=color[currV])) return 0;
		if(dp[currV][currC]>=0) return dp[currV][currC];
		dp[currV][currC] = 1;
		for(int out: edges[currV]){
			if(out==parV) continue;
			long canColor = 0;
			for(int c=0; c<3; c++){
				canColor += solve(out,c,currV,currC);
				canColor %= MOD;
			}
			dp[currV][currC] *= canColor;
			dp[currV][currC] %= MOD;
		}
		return dp[currV][currC];
	}
}
