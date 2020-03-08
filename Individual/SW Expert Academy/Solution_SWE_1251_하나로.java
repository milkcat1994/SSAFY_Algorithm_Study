import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -하나로-
 * 1. 크루스칼 알고리즘 이용 풀이
 */

/*
 * 메모리 : 105296KB 
 * 시간 : 317ms 
 * 코드길이 : 2245B 
 * 소요시간 : 30M
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD
public class Solution_SWE_1251_하나로 {
	static int N;
	static double E;
	
	static class Vertex {
		int r,c;
		
		public Vertex(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public double getW(Vertex v) {
			return E*(Math.pow(Math.abs(this.r-v.r),2) + Math.pow(Math.abs(this.c-v.c),2));
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int u,v;
		double w;
		
		public Edge(int u, int v, double w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.w>o.w)
				return 1;
			return -1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		int[] xArr = new int[1000];
		Vertex[] vArr = new Vertex[1000];
		for(int t=1; t<=T; ++t) {
			N = Integer.parseInt(br.readLine());
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			//x좌표
			st = new StringTokenizer(br.readLine(), " ");
			for(int n=0; n<N; ++n) {
				xArr[n] = Integer.parseInt(st.nextToken());
			}
			//y좌표
			st = new StringTokenizer(br.readLine(), " ");
			for(int n=0; n<N; ++n) {
				vArr[n] = new Vertex(xArr[n], Integer.parseInt(st.nextToken()));
			}
			
			E = Double.parseDouble(br.readLine());
			//각 거리 구하기
			for (int u = 0; u < N-1; ++u) {
				for (int v = 1; v < N; ++v) {
					pq.offer(new Edge(u, v, vArr[u].getW(vArr[v])));
				}
			}
			initUnion();
			
			double minRes=0;
			int u,v, count=0;
			Edge te;
			while(!pq.isEmpty()) {
				te=pq.poll();
				u=te.u;	v=te.v;
				//연결이 될 경우
				if(union(u,v)) {
					minRes+=te.w;
					count++;
					if(count==N-1)
						break;
				}
			}
			System.out.println("#"+t+" "+Math.round(minRes));
		} //end TestCase
	}
	
	//Union Find
	static int[] parents;
	static void initUnion() {
		parents = new int[N+1];
		Arrays.fill(parents, -1);
	}
	
	static int find(int index) {
		if(parents[index]==-1) return index;
		return parents[index]=find(parents[index]);
	}
	
	static boolean union(int a, int b) {
		int ap=find(a);
		int bp=find(b);
		
		if(ap!=-1 && ap==bp) return false;
		
		parents[bp]=ap;
		
		return true;
	}
}