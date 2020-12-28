import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -중량제한-
 * 다익스트라 알고리즘 이용한 최소 간선 가중치 구하기
 * DFS + 이분 탐색?
 */

//출처 : https://www.acmicpc.net/problem/1939
public class Main_B_G4_1939_중량제한 {
	static int N, M, S, E;
	static PriorityQueue<Edge> pq;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(dijkstra());
	}
	
	static int dijkstra(){
		
		int pa,pb, max=Integer.MAX_VALUE;
		Edge te;
		while(!pq.isEmpty()) {
			te = pq.poll();
			union(te.s, te.e);
			max = Math.min(max, te.w);
			pa = getParent(S);
			pb = getParent(E);
			if(pa == pb)
				return max;
		}
		return -1;
	}
	
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		initParents(N+1);
		
		pq = new PriorityQueue<>();
		int s,e,w;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(s,e,w));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
	}
	
	static void initParents(int size) {
		parents = new int[size];
		Arrays.fill(parents, -1);
	}
	
	static int getParent(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		
		if(pa == pb) return false;
		
		parents[pa] = pb;
		
		return true;
	}
	
	
	static class Edge implements Comparable<Edge>{
		int s,e,w;
		
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return o.w - this.w;
		}
	}
}
