import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -도시분할 계획-
 * 크루스칼 알고리즘 이용하여 마지막 연결선은 연결하지 않는 방법
 */

//출처 : https://www.acmicpc.net/problem/1647
public class Main_B_G4_1647_도시분할계획 {
	static class Point implements Comparable<Point> {
		int s,e,w;
		Point(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Point o) {
			return this.w-o.w;
		}
	}
	
	static int[] parents;
	static void init(int N) {
		parents = new int[N];
		Arrays.fill(parents, -1);
	}
	
	static int getSet(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = getSet(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getSet(a);
		int pb = getSet(b);
		
		if(pa == pb) return false;
		
		parents[pa] = pb;
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		init(N+1);
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			pq.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		int res=0, k=N;
		Point tp;
		while(k-2>0) {
			tp = pq.poll();
			if(union(tp.s, tp.e)) {
				res += tp.w;
				k--;
			}
		}
		
		System.out.println(res);
	}
}
