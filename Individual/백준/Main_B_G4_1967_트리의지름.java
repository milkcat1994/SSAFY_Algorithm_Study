import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -트리의 지름-
 */

//출처 : https://www.acmicpc.net/problem/1967
public class Main_B_G4_1967_트리의지름 {
	static int N, max, maxLength, farVertex;
	static boolean[] isVisited;
	static List<Edge>[] list;
	
	public static void main(String[] args) throws Exception {
		init();

		findFarVertex(1, 0);
		
		Arrays.fill(isVisited, false);
		System.out.print(find(farVertex));
	}
	
	// 지름 이루는 하나의 점에서 가장 긴 길이를 반환 하는 함수
	static int find(int parent) {
		int res=0;
		
		isVisited[parent]= true;
		for(Edge te : list[parent]) {
			if(isVisited[te.e]) continue;
			
			res = Math.max(res, find(te.e) + te.w);
		}
		
		return res;
	}
	
	// 임의의 점에서 갖아 먼 좌표 찾기 -> 지름을 이루는 두 점 중 하나가 나온다.
	static void findFarVertex(int parent, int length) {
		if(length > maxLength) {
			maxLength = length;
			farVertex = parent;
		}
		
		isVisited[parent]= true;
		for(Edge te : list[parent]) {
			if(isVisited[te.e]) continue;
			
			findFarVertex(te.e, length + te.w);
		}
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		isVisited = new boolean[N+1];
		for(int i=0; i<=N; ++i) {
			list[i] = new ArrayList<Edge>();
		}
		
		int p,c,w;
		for(int i=0; i<N-1; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			p = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			list[p].add(new Edge(p,c,w));
			list[c].add(new Edge(c,p,w));
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
