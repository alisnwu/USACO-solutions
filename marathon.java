import java.io.*;
import java.util.*;
public class marathon{
  public static void main(String[] args) throws IOException{
    BufferedReader min = new BufferedReader(new FileReader("marathon.in"));
    PrintWriter mout= new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
    int N = Integer.parseInt(min.readLine());
    int[] speed = new int[N];
    for(int i=0; i<N; i++){
      StringTokenizer st = new StringTokenizer(min.readLine());
      speed[i] = Integer.parseInt(st.nextToken());
      speed[i] = Integer.parseInt(st.nextToken());
    }
    int groups = N;
    int smin = speed[N-1];
    for(int i=N-1; i>-1; i--){
      if(speed[i]>smin) groups--;
      else smin = speed[i];
    }
    mout.println(groups);
    mout.close();
    System.exit(0);
  }
