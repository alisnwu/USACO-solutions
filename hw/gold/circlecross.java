import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class circlecross{
  public static void main(String[] args) throws IOException{
    BufferedReader cin = new BufferedReader(new FileReader("circlecross.in"));
    PrintWriter cout = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
    int N = Integer.parseInt(cin.readLine());
    pair[] pairs = new pair[N];
    for(int i=0; i<2*N; i++){
      int ind = Integer.parseInt(cin.readLine());
      if(pairs[ind-1]!=null){
        pairs[ind-1].y = i;
      }
      else{
        pairs[ind-1] = new pair();
        pairs[ind-1].x = i;
      }
    }
    Arrays.sort(pairs);
    int count = 0;
    int[] bit = new int[2*N+1];
    for(int i=0; i<N; i++){
      int start = pairs[i].x;
      int end = pairs[i].y;
      count += query(bit,end) - query(bit,start);
      update(bit, end, 1);
    }
    cout.println(count);
    cout.close();
  }
  static private void update(int[] arr, int pos, int val){
    int len = arr.length;
    for (; pos < len; pos |= (pos+1)) arr[pos] += val;
  }
  static private int query(int[] arr, int pos){
    int sum = 0;
    for (; pos >= 0; pos = (pos&(pos+1))-1) sum += arr[pos];
    return sum;
  }
  static class pair implements Comparable<pair>{
    int x;
    int y;
    @Override
    public int compareTo(pair pair){
      return this.x-pair.x;
    }
  }
}
