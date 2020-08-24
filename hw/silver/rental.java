import java.io.*;
import java.util.*;
public class rental{
	public static void main(String[] args) throws IOException{
		BufferedReader rin = new BufferedReader(new FileReader("rental.in"));
		PrintWriter rout = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
		StringTokenizer st = new StringTokenizer(rin.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[] milkProduced = new int[N];
		for(int i=0; i<N; i++){
			milkProduced[i] = Integer.parseInt(rin.readLine());
		}
		sort(milkProduced);
		Shop[] shops = new Shop[M];
		for(int i=0; i<M; i++){
			st = new StringTokenizer(rin.readLine());
			shops[i] = new Shop(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(shops);
		long[] maxProfit = new long[N+1];	{
			int index = 0;
			for(int i=0; i<N; i++){
				maxProfit[i+1] = maxProfit[i];
				while(index<M && milkProduced[i]>0){
					int use = Math.min(milkProduced[i], shops[index].quantity);
					maxProfit[i+1] += use*(long)shops[index].price;
					milkProduced[i] -= use;
					shops[index].quantity -= use;
					if(shops[index].quantity==0){
						index++;
					}
				}
			}
		}
		int[] rental = new int[R];
		for(int i=0; i<R; i++){
			rental[i] = Integer.parseInt(rin.readLine());
		}
		sort(rental);{
			int a = N-1;
			int rI = 0;
			long rentalSoFar = 0;
			while(a>=0 && rI<R){
				rentalSoFar += rental[rI];
				maxProfit[a] += rentalSoFar;
				rI++;
				a--;
			}
		}
		long ret = 0;
		for(long out: maxProfit){
			ret = Math.max(ret, out);
		}
		rout.println(ret);
		rout.close();
	}
	public static void sort(int[] l){
		Arrays.sort(l);
		for(int i=0; i<l.length-1-i; i++){
			l[i] ^= l[l.length-1-i];
			l[l.length-1-i] ^= l[i];
			l[i] ^= l[l.length-1-i];
		}
	}
	static class Shop implements Comparable<Shop>{
		public int quantity, price;
		public Shop(int a, int b){
			quantity=a;
			price=b;
		}
		public int compareTo(Shop s){
			return s.price - price;
		}
	}
}
