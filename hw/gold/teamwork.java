import java.io.*;
import java.util.*;
public class teamwork{
  public static void main(String[] args) throws IOException{
    BufferedReader tin = new BufferedReader(new FileReader("teamwork.in"));
    PrintWriter tout = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
    StringTokenizer st = new StringTokenizer(tin.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] a = new int[10000];
    int[] dp = new int[10000];
    for(int i=0; i<N; i++) a[i] = Integer.parseInt(tin.readLine());
    dp[0] = a[0];
    for(int i=1; i<N; i++){
      int max = a[i];
      for(int j=i; j>=0 && i+1-j<=K; j--){
        int r = i + 1 - j;
        if(r>K) continue;
        max = Math.max(max, a[j]);
        if(j==0) dp[i] = Math.max(dp[i], max*r);
        else dp[i] = Math.max(dp[i], dp[j-1]+max*r);
      }
    }
    tout.println(dp[N-1]);
    tout.close();
  }
}
