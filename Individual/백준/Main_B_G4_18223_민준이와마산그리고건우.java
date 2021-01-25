import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -민준이와 마산 그리고 건우-
 * 1. Edge를 그대로 입력 받아 각 Vertex idx에 따른 Edge List를 가지는 edges변수 생성
 * 2. 마산까지의 이동거리를 moveMinJun() 함수를 통해 구한다.
 * 3. 마산까지 거리는 minJunToGoal변수에 건우까지 거리는 gunWooToGoal에 더해준다. -> 건우까지 거리는 2번에서 미리 구해둔다.
 * 4. 건우위치에서 마산까지거리를 moveGunWoo() 함수를 통해 구한다.
 * 
 * 메모리 : 22304KB
 * 시간 : 296ms
 * 풀이 시간 : 30M
 */

//출처 : https://www.acmicpc.net/problem/18223
public class Main_B_G4_18223_민준이와마산그리고건우 {
	// vertex, edge, 건우 위치
	static int V, E, P;
	// 민준 -> 도착지, 민준 -> 건우 -> 도착지
	static int minJunToGoal, gunWooToGoal;
	
	static List<List<Edge>> edges;
	
	public static void main(String[] args) throws Exception {
		init();
		
		minJunToGoal = moveMinJun();
		
		gunWooToGoal += moveGunWoo();
		
		System.out.print(minJunToGoal < gunWooToGoal ? "GOOD BYE" : "SAVE HIM");
	}
	
	static int moveMinJun() {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(1,0));
		
		int[] weights = new int[V+1];
		Arrays.fill(weights, Integer.MAX_VALUE);
		weights[1] = 0;
		
		Vertex tv;
		while(!pq.isEmpty()) {
			tv = pq.poll();
			
			for(Edge te : edges.get(tv.idx)) {
				if(weights[te.e] > tv.w + te.w) {
					weights[te.e] = tv.w + te.w;
					pq.offer(new Vertex(te.e, weights[te.e]));
				}
			}
		}
		
		gunWooToGoal += weights[P];
		return weights[V];
	}

	static int moveGunWoo() {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(P,0));
		
		int[] weights = new int[V+1];
		Arrays.fill(weights, Integer.MAX_VALUE);
		weights[P] = 0;
		
		Vertex tv;
		while(!pq.isEmpty()) {
			tv = pq.poll();
			
			for(Edge te : edges.get(tv.idx)) {
				if(weights[te.e] > tv.w + te.w) {
					weights[te.e] = tv.w + te.w;
					pq.offer(new Vertex(te.e, weights[te.e]));
				}
			}
		}
		return weights[V];
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<List<Edge>>();
		for(int i=0; i<=V; ++i)
			edges.add(new ArrayList<Edge>());
		
		int s,e,w;
		for(int i=0; i<E; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			edges.get(s).add(new Edge(e,w));
			edges.get(e).add(new Edge(s,w));
		}
	}
	
	static class Edge {
		int e, w;
		
		Edge(int e, int w){
			this.e = e;
			this.w = w;
		}
	}
	
	static class Vertex implements Comparable<Vertex>{
		int idx, w;
		
		Vertex(int idx, int w){
			this.idx = idx;
			this.w = w;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.w - o.w;
		}
	}
}
