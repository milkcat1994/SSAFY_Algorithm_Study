import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -최소비용 구하기-
 * 1. 다익스트라 알고리즘 이용
 */

//출처 : https://www.acmicpc.net/problem/1916
public class Main_B_G5_1916_최소비용구하기 {
	static int N,M;
	static ArrayList<Point>[] edge;
	static boolean[] isVisted;
	static int[] dist;
	static final int INF = 987654321;
	
	static class Point implements Comparable<Point>{
		int v,w;
		Point(int v, int w){
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Point p) {
			return this.w-p.w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N+1];
		edge = new ArrayList[N+1];
		isVisted = new boolean[N+1];
		
		for(int i = 0; i <= N; ++i) {
			edge[i] = new ArrayList<Point>();
			dist[i] = INF;
		}
		
		int u,v,w;
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			edge[u].add(new Point(v, w));
		}
		
		st = new StringTokenizer(br.readLine()," ");
		int sIndex, eIndex;
		sIndex = Integer.parseInt(st.nextToken());
		eIndex = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		dist[sIndex] = 0;
		pq.offer(new Point(sIndex, 0));
		
		Point tp;
		int curv,tarv,tarw;
		while(!pq.isEmpty()) {
			tp = pq.poll();
			curv = tp.v;
			
			if(curv == eIndex) {
				System.out.println(dist[curv]);
				return;
			}
			
			if(isVisted[curv])
				continue;
			
			for(Point p : edge[curv]) {
				tarv = p.v;	tarw = p.w;
				//갱신 될 여지 있다면
				if(dist[tarv] > dist[curv]+tarw) {
					dist[tarv] = dist[curv]+tarw;
					pq.offer(new Point(tarv, dist[tarv]));
				}
			}
			
			isVisted[curv] = true;
		}
	}
}