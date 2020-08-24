import java.io.*;
import java.util.*;
public class feast{
	public static void main(String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("feast.in"));
    PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
		StringTokenizer st = new StringTokenizer(fin.readLine());
		int T = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		boolean[][] seen = new boolean[2][T+1];
		seen[0][0] = true;
		for(int a=0; a<seen.length; a++){
			for(int i=0; i<seen[a].length; i++){
				if(!seen[a][i]) continue;
				if(i+X<=T) seen[a][i+X] = true;
				if(i+Y<=T) seen[a][i+Y] = true;
				if(a+1<seen.length) seen[a+1][i/2] = true;
			}
		}
		int ret = T;
		while(!seen[1][ret]) ret--;
		fout.println(ret);
		fout.close();
	}
}
