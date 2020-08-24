import java.io.*;
import java.util.*;
public class cardgame{
  private static int N;
  private static boolean cards[];
  private static int elsie[][];
  private static int bessie[][];
  public static void main(String args[]) throws IOException{
    BufferedReader cin = new BufferedReader(new FileReader("cardgame.in"));
    PrintWriter cout = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
    N = Integer.parseInt(cin.readLine());
    elsie = new int[2][N/2];
    bessie = new int[2][N/2];
    cards = new boolean[2*N];
    for(int i=0; i<N/2; i++){
      elsie[0][i] = Integer.parseInt(cin.readLine());
      cards[elsie[0][i]-1] = true;
    }
    for(int i=0; i<N/2; i++){
      elsie[1][i] = Integer.parseInt(cin.readLine());
      cards[elsie[1][i]-1] = true;
    }
    Arrays.sort(elsie[0]);
    Arrays.sort(elsie[1]);
    for(int i=0,j=0,k=1; i<2*N; i++){
      if(!cards[i]){
        bessie[k][j] = i + 1;
        j++;
        if(j==N/2){
          j = 0;
          k = 0;
        }
      }
    }
    int count = 0;
    boolean a = false;
    boolean b = false;
    for(int i=0; i<N/2; i++){
      boolean aa = false;
      for(int j=0; !a&&j<N/2; j++){
        if(!cards[bessie[0][j]-1] && bessie[0][j]>elsie[0][i]){
          cards[bessie[0][j]-1] = true;
          count++;
          aa = true;
          break;
        }
      }
      if(!aa) a = true;
      boolean bb = false;
      for(int j=N/2-1; !b&&j>=0; j--){
        if(!cards[bessie[1][j]-1] && bessie[1][j]<elsie[1][N/2-i-1]){
          cards[bessie[1][j]-1] = true;
          count++;
          bb = true;
          break;
        }
      }
      if(!bb) b = true;
    }
    cout.println(count);
    cout.close();
  }
}
