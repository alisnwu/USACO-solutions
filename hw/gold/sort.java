import java.io.*;
import java.util.*;
public class sort{
  private static int N;
  private static int[] nums;
  private static int[] sortnums;
  private static int[] moves;
  private static HashMap<Integer, Integer> track;
  public static void main(String[] args) throws IOException{
    BufferedReader sin = new BufferedReader(new FileReader("sort.in"));
    PrintWriter sout = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
    N = Integer.parseInt(sin.readLine());
    nums = new int[N];
    sortnums = new int[N];
    moves = new int[N-1];
    track = new HashMap<>();
    for(int i=0; i<N; i++){
      nums[i] = Integer.parseInt(sin.readLine());
      sortnums[i] = nums[i];
    }
    Arrays.sort(sortnums);
    for(int i=0; i<N; i++){
      int si = binarySearch(i);
      int a = Math.min(si, i);
      int b = Math.max(si, i);
      for (int j=a; j<b; j++) moves[j]++;
    }
    int max = 0;
    for(int i=0; i<N-1; i++) max = Math.max(max, moves[i]);
    if(max==0) max = 2;
    sout.println((max + 1) / 2);
    sout.close();
  }
  public static int binarySearch(int i){
    if(track.containsKey(nums[i])){
      track.put(nums[i], track.get(nums[i]) + 1);
      int si = Arrays.binarySearch(sortnums, nums[i] - 1);
      if(si < 0) return -si - 2 + track.get(nums[i]);
      else return si + track.get(nums[i]);
    }
    else{
      track.put(nums[i], 1);
      return Arrays.binarySearch(sortnums, nums[i]);
    }
  }
}
