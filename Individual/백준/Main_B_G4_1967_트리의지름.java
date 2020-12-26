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
	static int N, max;
	static boolean[] isVisited;
	static List<Edge>[] list;
	
	public static void main(String[] args) throws Exception {
		init();
		
		for(int i=1; i<N+1; ++i) {
			Arrays.fill(isVisited, false);
			isVisited[i] = true;
			max = Math.max(max, find(i));
		}
		System.out.println(max);
	}
	
	static int find(int parent) {
		int res=0;
		for(Edge te : list[parent]) {
			if(isVisited[te.e]) continue;
			
			isVisited[te.e] = true; 
			res = Math.max(res, find(te.e) + te.w);
		}
		
		return res;
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
