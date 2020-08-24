import java.io.*;
import java.util.*;
class sleepy{
   public static int[] bits;
   public static int N;
   public static void main(String[] args) throws IOException{
      BufferedReader sin = new BufferedReader(new FileReader("sleepy.in"));
      PrintWriter sout = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
      N = Integer.parseInt(sin.readLine());
      StringTokenizer st = new StringTokenizer(sin.readLine());
      int[] array = new int[N];
      for(int i=0; i<N; i++){
        array[i] = Integer.parseInt(st.nextToken());
      }
      bits = new int[N+1];
      int t = N-1;
      update(array[t],1);
      while(t >= 1 && array[t] > array[t-1]){
        t--;
        update(array[t],1);
      }
      System.out.println(t);
      sout.println(t);
      for(int i=0; i<t; i++){
         int ans = t - i - 1 + psum(array[i]);
         System.out.print(ans + " ");
         sout.print(ans);
         if(i < t-1) sout.print(" ");
         update(array[i],1);
      }
      sout.println();
      sout.close();
   }
   public static void update(int i, int x){
      for(; i<=N; i+=i&-i){
         bits[i]+=x;
      }
   }
   public static int psum(int i){
      int sum = 0;
      for(; i>0; i-=i&-i){
         sum += bits[i];
      }
      return sum;
   }
}
