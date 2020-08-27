import java.io.*;
import java.util.*;
public class spainting{
  static final long mod = (long) (1e9+7);
  public static void main(String[] args) throws IOException{
    BufferedReader sin = new BufferedReader(new FileReader("spainting.in"));
    PrintWriter sout = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
    StringTokenizer st = new StringTokenizer(sin.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    long[] s = new long[N+1];
    long a = 1;
    s[0] = 1;
    for(int i=1; i<K; i++){
      a = (a*M) % mod;
      s[i] = (s[i]+s[i-1]+a) % mod;
    }
    for(int i=K; i<=N; i++) s[i] = (M*s[i-1]) % mod - ((M-1)*s[i-K]) % mod;
    long ans = M;
    for(int i=1; i<N; i++) ans = (ans*M) % mod;
    if(ans>(s[N]-s[N-1])) ans = (ans-(s[N]-s[N-1])) % mod;
    else ans = (ans+mod-(s[N]-s[N-1])) % mod;
    if(ans<0) ans = ans + mod;
    sout.println(ans);
    sout.close();
  }
}
