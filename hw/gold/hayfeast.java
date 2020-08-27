import java.io.*;
import java.util.*;
public class hayfeast{
	public static void main(String[] args) throws IOException{
    BufferedReader hin = new BufferedReader(new FileReader("hayfeast.in"));
		PrintWriter hout = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
		StringTokenizer st = new StringTokenizer(hin.readLine());
		int N = Integer.parseInt(st.nextToken());
		long need = Long.parseLong(st.nextToken());
		long[] f = new long[N];
		long[] s = new long[N];
		for(int i=0; i<N; i++){
			st = new StringTokenizer(hin.readLine());
			f[i] = Long.parseLong(st.nextToken());
			s[i] = Long.parseLong(st.nextToken());
		}
		int left = 0;
		long ret = Long.MAX_VALUE;
		TreeMap<Long, Integer> seen = new TreeMap<Long, Integer>();
		long flavor = 0;
		for(int i=0; i<N; i++){
			flavor += f[i];
			update(seen, s[i], 1);
			while(flavor-f[left]>=need){
				update(seen, s[left], -1);
				flavor -= f[left++];
			}
			if(flavor>=need) ret = Math.min(ret, seen.lastKey());
		}
		hout.println(ret);
		hout.close();
	}
	private static void update(Map<Long, Integer> m, long k, int v){
		if(!m.containsKey(k)) m.put(k, 0);
		int nv = m.get(k) + v;
		if(nv==0) m.remove(k);
		else m.put(k, nv);
	}
}
