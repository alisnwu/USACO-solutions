import java.io.*;
import java.util.*;
public class talent{
	public static void main(String[] args) throws IOException{
		BufferedReader tin = new BufferedReader(new FileReader("talent.in"));
		PrintWriter tout = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
    StringTokenizer st = new StringTokenizer(tin.readLine());
    int N = Integer.parseInt(st.nextToken());
    int W = Integer.parseInt(st.nextToken());
    int[] a = new int[N];
    int[] b = new int[N];
    int s = 0;
    for(int i=0; i<N; i++){
      st = new StringTokenizer(tin.readLine());
      a[i] = Integer.parseInt(st.nextToken());
      b[i] = Integer.parseInt(st.nextToken());
      s += b[i];
    }
    int[] dp = new int[s+1];
    Arrays.fill(dp,25000000);
    dp[0] = 0;
    for(int i=0; i<N; i++){
      for(int j=s; j>=b[i]; j--) dp[j] = Math.min(dp[j], dp[j-b[i]]+a[i]);
    }
    int res = 0;
    for(int i=1; i<=s; i++){
      if(dp[i]<W) continue;
      res = Math.max(res, (1000*i)/dp[i]);
    }
    tout.println(res);
    tout.close();
  }
}
