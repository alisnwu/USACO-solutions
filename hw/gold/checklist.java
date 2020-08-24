import java.io.*;
import java.util.*;
public class checklist{
	public static void main(String[] args) throws IOException{
		BufferedReader cin = new BufferedReader(new FileReader("checklist.in"));
		PrintWriter cout = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
		StringTokenizer st = new StringTokenizer(cin.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		State[] holstein = new State[N];
		State[] guernsey = new State[M];
		for(int i=0; i<N; i++){
			st = new StringTokenizer(cin.readLine());
			holstein[i] = new State(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for(int i=0; i<M; i++){
			st = new StringTokenizer(cin.readLine());
			guernsey[i] = new State(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		long[][][] dp = new long[N+1][M+1][2];
		for(int i=0; i<dp.length; i++){
			for(int j=0; j<dp[i].length; j++){
				Arrays.fill(dp[i][j], 1L << 60);
			}
		}
		dp[1][0][0] = 0;
		for(int i=0; i<dp.length; i++){
			for(int j=0; j<dp[i].length; j++){
				for(int k=0; k<2; k++){
					if(k==0 && i==0) continue;
					if(k==1 && j==0) continue;
					State source;
					if(k==0) source = holstein[i-1];
					else source = guernsey[j-1];
					if(i<N){
						dp[i+1][j][0] = Math.min(dp[i+1][j][0], dp[i][j][k] + source.dist(holstein[i]));
					}
					if(j<M){
						dp[i][j+1][1] = Math.min(dp[i][j+1][1], dp[i][j][k] + source.dist(guernsey[j]));
					}
				}
			}
		}
		cout.println(dp[N][M][0]);
		cout.close();
	}
	static class State{
		public int x, y;
		public State(int a, int b){
			x=a;
			y=b;
		}
		public int dist(State s){
			return (x-s.x)*(x-s.x) + (y-s.y)*(y-s.y);
		}
	}
}
