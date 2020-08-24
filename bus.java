import java.io.*;
import java.util.*;
public class bus{
  public static void main(String args[]) throws IOException{
    BufferedReader bin = new BufferedReader(new FileReader("bus.in"));
    PrintWriter bout = new PrintWriter(new BufferedWriter(new FileWriter("bus.out")));
    int N = Integer.parseInt(bin.readLine());
    int[] x = new int[N];
    int[] y = new int[N];
    for(int i=0; i<N; i++){
      StringTokenizer st = new StringTokenizer(bin.readLine());
      x[i] = Integer.parseInt(st.nextToken());
      y[i] = Integer.parseInt(st.nextToken());
    }
    int totalD = 0;
    for(int i=0; i<N; i++){
      totalD += Math.abs(x[i]-x[i-1]) + Math.abs(y[i]-y[i-1]);
    }
    int minD = totalD;
    for(int i=0; i<N-1; i++){
      int d1 = Math.abs(x[i]-x[i-1]) + Math.abs(y[i]-y[i-1]) + Math.abs(x[i+1]-x[i]) + Math.abs(y[i+1]-y[i]);
      int d2 = Math.abs(x[i+1]-x[i-1]) + Math.abs(y[i+1]-y[i-1]);
      minD = Math.min(minD, totalD-(d1-d2));
    }
    bout.println(minD);
    bout.close();
    System.exit(0);
  }
}
