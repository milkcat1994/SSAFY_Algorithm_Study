import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -행성연결-
 * 정점의 개수가 적으므로 프림 알고리즘 이용하였음.
 * V(Log_E)
 * 
 * 1 <= C_ij <= 100_000_000 이기에 답은 Long형 선언
 * 
 * ps) 정점 배열을 생성하여 정점 배열을 이동 가능 여부에 따라 갱신해 나간다면 더 빨리 진행이 될수도 있을듯.
 */

//출처 : https://www.acmicpc.net/problem/16398
public class Main_B_G4_16398_행성연결 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	static ArrayList<ArrayList<Edge>> edgeList;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(prim());
	}
	
	static long prim() {
		long distance = 0;
		boolean[] isVisited = new boolean[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);
		Queue<Integer> que = new LinkedList<>();
		que.add(0);
		
		Edge curEdge;
		int s;
		while(!que.isEmpty()) {
			s = que.poll();
			isVisited[s] = true;
			
			for(Edge edge : edgeList.get(s)) {
				if(isVisited[edge.e]) continue;
				pq.offer(edge);
			}
			
			while(!pq.isEmpty()) {
				curEdge = pq.poll();
				if(isVisited[curEdge.e]) continue;
				
				que.offer(curEdge.e);
				isVisited[curEdge.e] = true;
				distance += curEdge.w;
				break;
			}
		}
		return distance;
	}

	static void init() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		edgeList = new ArrayList<>();
		
		int w;
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			edgeList.add(new ArrayList<Edge>());
			for (int col = 0; col < N; ++col) {
				w = Integer.parseInt(st.nextToken());
				if(row == col) continue;
				edgeList.get(row).add(new Edge(row, col, w));
			}
		}
	}
	
	static class Edge {
		int s,e,w;
		
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	
}
