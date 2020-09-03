import java.io.*;
import java.util.*;
public class cbarn2{
	public static void main(String[] args) throws IOException{
		BufferedReader cin = new BufferedReader(new FileReader("cbarn2.in"));
		PrintWriter cout = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
		StringTokenizer st = new StringTokenizer(cin.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] cows = new int[N];
		for(int i=0; i<N; i++) cows[i] = Integer.parseInt(cin.readLine());
		long ans = Long.MAX_VALUE;
		for(int start=0; start<N; start++){
			int[] rotated = new int[N];
			for(int r=start; r<start+N; r++) rotated[r - start] = cows[r % N];
			long[][] dp = new long[K][N];
			for(int i=1; i<N; i++){
				dp[0][i] = dp[0][i - 1] + rotated[i] * i;
			}
			for(int k=1; k<K; k++){
				for(int j=k+1; j<N; j++){
					dp[k][j] = dp[k - 1][j - 1];
					int sum = 0;
					int curr = rotated[j];
					int lastDoor = j - 1;
					while(lastDoor>0 && sum<dp[k][j]){
						sum += curr;
						dp[k][j] = Math.min(dp[k][j], dp[k - 1][lastDoor - 1] + sum);
						curr += rotated[lastDoor];
						lastDoor--;
					}
				}
			}
			ans = Math.min(ans, dp[K - 1][N - 1]);
		}
		cout.println(ans);
		cout.close();
	}
}
