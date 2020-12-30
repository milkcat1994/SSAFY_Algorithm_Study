import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -문제집-
 * PriorityQueue이용한 위상정렬
 */

//출처 : https://www.acmicpc.net/problem/1766
public class Main_B_G2_1766_문제집 {
	static int N,M;
	static List<PriorityQueue<Integer>> list;
	static int[] exNeed;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(solve());
	}
	
	static String solve() {
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=1; i<=N; ++i) {
			if(exNeed[i] == 0)
				pq.offer(i);
		}
		
		int ti;
		while(!pq.isEmpty()) {
			ti = pq.poll();
			
			for(Integer integer : list.get(ti)) {
				exNeed[integer]--;
				if(exNeed[integer] == 0) {
					pq.offer(integer);
				}
			}
			
			sb.append(ti+" ");
		}
		
		return sb.toString();
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<PriorityQueue<Integer>>();
		for(int i=0; i<=N; ++i)
			list.add(new PriorityQueue<>());
		
		exNeed = new int[N+1];
		int a,b;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			exNeed[b]++;
			list.get(a).offer(b);
		}
	}
}
