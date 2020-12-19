import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -회의실 배정-
 */

//출처 : https://www.acmicpc.net/problem/1931
public class Main_B_S2_1931_회의실배정 {
	static int N;
	static PriorityQueue<Conference> pq;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(find());
	}
	
	static int find() {
		int res = 0, te=0;
		
		Conference tc;
		while(!pq.isEmpty()) {
			tc = pq.poll();
			if(te <= tc.s) {
				te = tc.e;
				res++;
			}
		}
		
		return res;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		pq = new PriorityQueue<>();
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			pq.offer(new Conference(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
	}
	
	static class Conference implements Comparable<Conference>{
		int s,e;
		
		Conference(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Conference o) {
			if(this.e == o.e) {
				return this.s - o.s;
			}
			return this.e - o.e;
		}
	}
}
