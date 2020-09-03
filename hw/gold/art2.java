import java.io.*;
import java.util.*;
public class art2{
	public static void main(String[] args) throws IOException{
		BufferedReader ain = new BufferedReader(new FileReader("art2.in"));
		PrintWriter aout = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));
		int N = Integer.parseInt(ain.readLine());
		int[] painting = new int[N + 2];
		int[][] startEnd = new int[N + 1][2];
		for(int i=0; i<N+1; i++) for(int j=0; j<2; j++) startEnd[i][j] = -1;
		for(int i=0; i<N; i++) painting[i + 1] = Integer.parseInt(ain.readLine());
		for (int i=0; i<N+2; i++){
			if(startEnd[painting[i]][0]==-1){
				startEnd[painting[i]][0] = i;
				startEnd[painting[i]][1] = i;
			}
      else{
				startEnd[painting[i]][1] = i;
			}
		}
		Stack<Integer> stack = new Stack<Integer>();
		int maxLayers = 0;
		for(int i=0; i<N+2; i++){
			int color = painting[i];
			if(color==0 && i>0 && painting[i-1]!=0){
				if(startEnd[painting[i-1]][1]!=i-1){
					maxLayers = 0;
					break;
				}
			}
			int start = startEnd[color][0];
			int end = startEnd[color][1];
			if(i==start) stack.push(color);
			maxLayers = Math.max(maxLayers, stack.size());
			if(i==end){
				if(stack.peek()==color){
					stack.pop();
				}
        else{
					maxLayers = 0;
					break;
				}
			}
		}
		aout.println(maxLayers - 1);
		aout.close();
	}
}
