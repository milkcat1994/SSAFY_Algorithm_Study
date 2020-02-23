import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -최단경로-
 * 1. 시작 vertex와 연결된 정점의 이동거리값 갱신
 * 2. 이동거리의 갱신이 이루어 진다면 해당 정점 우선순위큐에 넣기
 * 3. 연결된 정점 모두 확인 했다면 현재 정점은 방문했다고 표시
 * 4. 우선순위 큐에서 가장 작은 이동거리 값을 가지는 정점 부터 확인
 * 5. 해당 정점을 이용하여 1번부터 반복
 */

//출처 : https://www.acmicpc.net/problem/1753
public class Main_B_G5_1753_최단경로 {
	static int V,E;
	static ArrayList<ArrayList<Edge>> edge = new ArrayList<ArrayList<Edge>>();
	static Vertex[] vertex;
	static boolean[] isVisted;
	final static int MAX_VALUE = 7654321;
	
	public static class Edge {
		//연결된 정점, 가중치
		int v,w;
		Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	public static class Vertex implements Comparable<Vertex>{
		//현재 정점 번호, 자신의 거리
		int u,w;
		
		Vertex(int u){
			this.u = u;
			this.w = MAX_VALUE;
		}
		
		public boolean setMinW(int w) {
			if(this.w <= w)
				return false;
			this.w = w;
			return true;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.w-o.w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		//시작 index
		int sIndex = Integer.parseInt(br.readLine());
		int u,v,w;

		vertex = new Vertex[V+1];
		isVisted = new boolean[V+1];
		for(int i = 0; i <= V; ++i) {
			edge.add(new ArrayList<Edge>());
			vertex[i] = new Vertex(i);
		}
		vertex[sIndex].w = 0;
		
		for(int e = 0; e < E; ++e) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			edge.get(u).add(new Edge(v,w));
		}
		
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(vertex[sIndex]);
		
		//해당 vertex와 연결된 좌표 값 갱신
		Vertex cv;
		while(!pq.isEmpty()) {
			cv = pq.poll();
			if(isVisted[cv.u])
				continue;
			
			for(Edge te : edge.get(cv.u)) {
				//갱신이 된다면 갱신하고 해당 좌표 넣기
				if(vertex[te.v].setMinW(cv.w+te.w))
					pq.offer(vertex[te.v]);
			}
			isVisted[cv.u] = true;
		}
		
		for(int i = 1; i <= V; ++i) {
			if(vertex[i].w != MAX_VALUE)
				sb.append(vertex[i].w).append('\n');
			else
				sb.append("INF\n");
		}
		System.out.print(sb.toString());
	}
}