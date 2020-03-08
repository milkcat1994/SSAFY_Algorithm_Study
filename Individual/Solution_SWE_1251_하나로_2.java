import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -하나로-
 * 1. 프림 알고리즘 이용 풀이
 */

/*
 * 메모리 : 95148KB 
 * 시간 : 332ms 
 * 코드길이 : 2065B 
 * 소요시간 : 30M
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD
public class Solution_SWE_1251_하나로_2 {
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
		boolean[] isSelected = new boolean[1000];
		
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
			//시작점에서 가장 가까운 정점 찾기
			for (int v = 1; v < N; ++v) {
				pq.offer(new Edge(0, v, vArr[0].getW(vArr[v])));
			}
			isSelected[0]=true;
			
			double minRes=0;
			int v, count=0;
			Edge te;
			while(!pq.isEmpty()) {
				te=pq.poll();
				v=te.v;
				//도착점이 이미 선택 되었다면 pass
				if(isSelected[v]) continue;
				isSelected[v]=true;
				minRes+=te.w;
				count++;
				
				//현재 도착점과 연결되는 모든 좌표 더해주기 -> 0은 확정이라 확인x
				for (int nv = 1; nv < N; ++nv) {
					//선택 되었다면 넣지 않기
					if(isSelected[nv]) continue;
					pq.offer(new Edge(v, nv, vArr[v].getW(vArr[nv])));
				}
				
				if(count==N-1)
					break;
			}
			System.out.println("#"+t+" "+Math.round(minRes));
			Arrays.fill(isSelected, false);
		} //end TestCase
	}
}