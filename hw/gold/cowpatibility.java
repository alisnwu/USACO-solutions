import java.io.*;
import java.util.*;
public class cowpatibility{
  static final int maxn = 50100;
  public static void main(String[] args) throws IOException{
    BufferedReader cin = new BufferedReader(new FileReader("cowpatibility.in"));
    PrintWriter cout = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
    int N = Integer.parseInt(cin.readLine());
    int[][] a = new int[maxn][6];
    HashMap<Integer,BitSet> mp = new HashMap<Integer,BitSet>();
    for(int i=1; i<=N; i++){
      StringTokenizer st = new StringTokenizer(cin.readLine());
      for(int j=1; j<=5; j++){
        a[i][j] = Integer.parseInt(st.nextToken());
        if(mp.containsKey(a[i][j])){
          BitSet bs = mp.get(a[i][j]);
          bs.set(i);
          mp.put(a[i][j],bs);
        }
        else{
          BitSet bs = new BitSet(maxn);
          bs.set(i);
          mp.put(a[i][j],bs);
        }
      }
    }
    BitSet p = new BitSet(maxn);
    long ret = 0;
    for(int i=1; i<=N; i++){
      p.clear();
      for(int j=1; j<=5; j++){
        BitSet cur = mp.get(a[i][j]);
        p.or(cur);
      }
      ret += N - p.cardinality();
    }
    cout.println(ret/2);
    cout.close();
  }
}
