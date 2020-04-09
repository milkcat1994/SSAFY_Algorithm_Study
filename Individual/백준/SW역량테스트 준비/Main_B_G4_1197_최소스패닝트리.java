import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//출처 : https://www.acmicpc.net/problem/1197
public class Main_B_G4_1197_최소스패닝트리 {
	
	static class Edge implements Comparable<Edge>{
		int u,v,w;

		public Edge(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		init(V);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			pq.offer(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		int cnt = 0, res = 0;
		Edge te;
		while(!pq.isEmpty()) {
			te = pq.poll();
			if(!union(te.u, te.v)) continue;
			res += te.w;
			if(++cnt == V-1) break;
		}
		System.out.print(res);
	}
	
	static int[] parents;
	
	static void init(int v) {
		parents = new int[v+1];
		Arrays.fill(parents, -1);
	}
	
	static int find(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = find(parents[x]);
	}
	
	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px==py && px != -1) return false;
		
		parents[px] = py;
		return true;
	}
}
