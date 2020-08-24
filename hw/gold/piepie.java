import java.io.*;
import java.util.*;
public class piepie{
  public static void main(String[] args) throws IOException{
    BufferedReader pin = new BufferedReader(new FileReader("piepie.in"));
		PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
    StringTokenizer st = new StringTokenizer(pin.readLine());
    int num = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());
    State[] b = new State[num];
    State[] e = new State[num];
    int[] t = new int[num];
    int[] d = new int[num];
    boolean[] vb = new boolean[num];
    boolean[] ve = new boolean[num];
    for(int i=0; i<num; i++){
      st = new StringTokenizer(pin.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      b[i] = new State(x,y,i,0);
    }
    for(int i=0; i<num; i++){
      st = new StringTokenizer(pin.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      e[i] = new State(y,x,i,1);
    }
    LinkedList<State> bfs = new LinkedList();
    Arrays.sort(b);
    Arrays.sort(e);
    for(int i=0; i<num; i++){
      if(b[i].y==0){
        bfs.add(b[i]);
        t[b[i].o] = 1;
        vb[b[i].o] = true;
      }
    }
    for(int i=0; i<num; i++){
      if(e[i].y==0){
        bfs.add(e[i]);
        d[e[i].o] = 1;
        ve[e[i].o] = true;
      }
    }
    while(!bfs.isEmpty()){
      State cow = bfs.poll();
      if(cow.t==0){
        int found = lwrBound(e,cow.x);
        while(found>=0 && e[found].y>=cow.x-D){
          if(!ve[e[found].o]){
            ve[e[found].o] = true;
            d[e[found].o] = t[cow.o]+1;
            bfs.add(e[found]);
          }
          found--;
        }
      }
      else{
        int found = lwrBound(b,cow.x);
        while(found>=0 && b[found].y>=cow.x-D){
          if(!vb[b[found].o]){
            vb[b[found].o] = true;
            t[b[found].o] = d[cow.o]+1;
            bfs.add(b[found]);
          }
          found--;
        }
      }
    }
    for(int i=0; i<t.length; i++) pout.println((t[i]==0)?-1:t[i]);
    pout.close();
  }
  static class State implements Comparable<State>{
    int x, y, o, t;
    public State(int x, int y, int o, int t){
      this.x = x;
      this.y = y;
      this.o = o;
      this.t = t;
    }
    public int compareTo(State s){
      return y - s.y;
    }
  }
  public static int lwrBound(State arr[], int tgt){
    int min = 0;
    int max = arr.length;
    int middle =- 1;
    boolean flag = false;
    while(min<max){
      middle = (min+max)/2;
      if(arr[middle].y==tgt){
        flag=true;
        break;
      }
      else if(arr[middle].y<tgt) min = middle + 1;
      else max = middle;
    }
    if(flag){
      int i = middle;
      Boolean f = false;
      for(i=middle+1; i<max; i++) if(arr[i].y>tgt) break;
      return i-1;
    }
    else{
      if(min>=arr.length) return arr.length-1;
      else return min-1;
    }
  }
}
