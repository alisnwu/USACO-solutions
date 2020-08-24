import java.io.*;
import java.util.*;
public class split{
	static long totalArea;
	public static void main(String[] args) throws IOException{
		BufferedReader sin = new BufferedReader(new FileReader("split.in"));
		PrintWriter sout = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
		int N = Integer.parseInt(sin.readLine());
		State[] list = new State[N];
		for(int i=0; i<N; i++){
			StringTokenizer st = new StringTokenizer(sin.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list[i] = new State(x, y);
		}
		long ans = solve(list);
		transpose(list);
		ans = Math.min(ans, solve(list));
		sout.println(totalArea-ans);
		sout.close();
	}

	public static long solve(State[] list){
		Arrays.sort(list);
		TreeMap<Integer, Integer> rhs = new TreeMap<Integer, Integer>();
		for(State curr: list){
			update(rhs, curr.y, 1);
		}
		long ret = totalArea = (list[list.length-1].x-list[0].x) * (long)(rhs.lastKey()-rhs.firstKey());
		TreeMap<Integer, Integer> lhs = new TreeMap<Integer, Integer>();
		for(int i=0; list[i].x<list[list.length-1].x;){
			int j = i+1;
			while(j<list.length && list[i].x==list[j].x) {
				j++;
			}
			for(int k=i; k<j; k++){
				update(rhs, list[k].y, -1);
				update(lhs, list[k].y, 1);
			}
			ret = Math.min(ret, (list[i].x-list[0].x) * (long)(lhs.lastKey()-lhs.firstKey())+(list[list.length-1].x-list[j].x) * (long)(rhs.lastKey()-rhs.firstKey()));
			i = j;
		}
		return ret;
	}

	public static void update(Map<Integer, Integer> m, int k, int v){
		if(!m.containsKey(k)) m.put(k, 0);
		int next = m.get(k) + v;
		if(next==0) m.remove(k);
		else m.put(k, next);
	}

	public static void transpose(State[] list){
		for(State curr: list) curr.transpose();
	}

	static class State implements Comparable<State>{
		public int x,y;
		public State(int a, int b){
			x=a;
			y=b;
		}
		public void transpose(){
			int t = x;
			x = y;
			y = t;
		}
		public int compareTo(State s){
			return x - s.x;
		}
	}
}
