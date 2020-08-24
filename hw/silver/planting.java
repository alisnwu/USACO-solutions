import java.io.*;
import java.util.*;
public class planting{
	public static void main(String[] args) throws IOException{
    BufferedReader pin = new BufferedReader(new FileReader("planting.in"));
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
    StringTokenizer st = new StringTokenizer(pin.readLine());
		int N = Integer.parseInt(st.nextToken());
    int[] d = new int[100000];
    for(int i=1; i<N; i++){
      st = new StringTokenizer(pin.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      d[a-1]++;
      d[b-1]++;
    }
    int D = 0;
    for(int i=0; i<N; i++) if(d[i]>D) D = d[i];
    pout.println(D+1);
    pout.close();
  }
}
