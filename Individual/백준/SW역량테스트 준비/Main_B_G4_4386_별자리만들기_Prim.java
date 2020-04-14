import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//출처 : https://www.acmicpc.net/problem/4386
public class Main_B_G4_4386_별자리만들기_Prim {
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
		boolean[] isSelected = new boolean[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		//시작지점 0 제외 넣어 가장 짧은 것 찾기
		isSelected[0] = true;
		for(int i = 1; i < N; ++i) {
			pq.offer(new Edge(0, i, getDist(point[0][0], point[0][1], point[i][0], point[i][1])));
		}
		
		double res = 0;
		Edge te;
		int count = 0;
		while(!pq.isEmpty()) {
			te = pq.poll();
			if(isSelected[te.v])
				continue;
			
			isSelected[te.v] = true;
			count++;
			res+=te.w;
			for(int i = 0; i < N; ++i) {
				if(isSelected[i])
					continue;
				pq.offer(new Edge(te.v, i, getDist(point[te.v][0], point[te.v][1], point[i][0], point[i][1])));
			}
			
			if(count == N-1)
				break;
		}
		
		System.out.printf("%.2f", res);
	}
	
	static double getDist(double x1,double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
	
}
