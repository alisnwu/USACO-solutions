import java.io.*;
import java.util.*;
public class lemonade{
  public static void main(String args[]) throws IOException{
    BufferedReader lin = new BufferedReader(new FileReader("lemonade.in"));
    PrintWriter lout = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
    int[] pitcher = new int[3];
    int[] lem = new int[3];
    for(int i=0; i<3; i++){
      StringTokenizer st = new StringTokenizer(lin.readLine());
      pitcher[i] = Integer.parseInt(st.nextToken());
      lem[i] = Integer.parseInt(st.nextToken());
    }
    for(int i=0; i<33; i++){
      for(int k=0; k<2; k++){
        if(lem[k]+lem[k+1]>pitcher[k+1]){
          lem[k] = lem[k] - (pitcher[k+1]-lem[k+1]);
          lem[k+1] = pitcher[k+1];
        }
        else if(lem[k]+lem[k+1]<pitcher[k+1]){
          lem[k+1] = lem[k+1] + lem[k];
          lem[k] = 0;
        }
        else{
          lem[k+1] = pitcher[k+1];
          lem[k] = 0;
        }
      }
      if(lem[2]+lem[0]>pitcher[0]){
        lem[2] = lem[2] - (pitcher[0]-lem[0]);
        lem[0] = pitcher[0];
      }
      else if(lem[2]+lem[0]<pitcher[0]){
        lem[0] = lem[0] + lem[2];
        lem[2] = 0;
      }
      else{
        lem[0] = pitcher[0];
        lem[2] = 0;
      }
    }
    if(lem[0]+lem[1]>pitcher[1]){
      lem[0] = lem[0] - (pitcher[1]-lem[1]);
      lem[1] = pitcher[1];
    }
    else if(lem[0]+lem[1]<pitcher[1]){
      lem[1] = lem[1] + lem[0];
      lem[0] = 0;
    }
    else{
      lem[1] = pitcher[1];
      lem[0] = 0;
    }
    for(int element: lem){
      lout.println(element);
    }
    lout.close();
  }
}
