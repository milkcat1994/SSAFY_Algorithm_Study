import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//출처 : https://www.acmicpc.net/problem/4386
public class Main_B_G4_4386_별자리만들기_Kruskal {
	static class Edge implements Comparable<Edge>{
		int u,v;
		double w;
		
		public Edge(int u, int v, double w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		double[][] point = new double[N][2];
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			point[i][0] = Double.parseDouble(st.nextToken());
			point[i][1] = Double.parseDouble(st.nextToken());
		}
		init(N+1);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for(int i = 0; i < N-1; ++i) {
			for(int j = i+1; j < N; ++j) {
				pq.offer(new Edge(i,j,getDist(point[i][0], point[i][1], point[j][0], point[j][1])));
			}
		}
		
		double res = 0;
		Edge te;
		while(!pq.isEmpty()) {
			te = pq.poll();
			if(!union(te.u,te.v)) continue;
			res+=te.w;
		}
		
		System.out.printf("%.2f", res);
	}
	
	static double getDist(double x1,double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
	
	static int[] parents;
	
	static void init(int N) {
		parents = new int[N];
		Arrays.fill(parents, -1);
	}
	
	static int find(int x) {
		if(parents[x]==-1) return x;
		return parents[x] = find(parents[x]);
	}
	
	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px == py && px != -1) return false;
		
		parents[px] = py;
		return true;
	}
}
