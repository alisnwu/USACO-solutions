import java.io.*;
import java.util.*;
public class taming{
  public static void main(String[] args) throws IOException{
    BufferedReader tin = new BufferedReader(new FileReader("taming.in"));
    PrintWriter tout = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
    StringTokenizer st = new StringTokenizer(tin.readLine());
    int N = Integer.parseInt(st.nextToken());
    int[] a = new int[N];
    st = new StringTokenizer(tin.readLine());
    for(int i=0; i<N; i++) a[i] = Integer.parseInt(st.nextToken());
    int[][][] dp = new int[N+1][N+1][N+1];
    for(int i=0; i<N; i++)
      for(int j=0; j<=i; j++)
        for(int k=0; k<=N; k++) dp[i][j][k] = 1000;
    if(a[0]==0) dp[0][0][1] = 0;
    else dp[0][0][1] = 1;
    for(int i=1; i<N; i++){
      for(int j=0; j<=i; j++) for(int k=1; k<=i+1; k++){
        if(j<i) dp[i][j][k] = dp[i-1][j][k];
        else for(int j1=0; j1<i; j1++) dp[i][j][k] = Math.min(dp[i][j][k], dp[i-1][j1][k-1]);
        if(a[i]!=i-j) dp[i][j][k]++;
      }
    }
    for(int i=1; i<=N; i++){
      int ret = 1000;
      for(int j=0; j<N; j++) ret = Math.min(ret, dp[N-1][j][i]);
      tout.println(ret);
    }
    tout.close();
  }
}
