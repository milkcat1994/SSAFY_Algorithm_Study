import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -DFS와 BFS-
 */

//출처 : https://www.acmicpc.net/problem/1260
public class Main_B_S2_1260_DFS와BFS {
	static int N, M, V;
	static List<ArrayList<Integer>> list;
	static boolean[] isVisited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		init();
		
		dfs(V);
		sb.append('\n');
		Arrays.fill(isVisited, false);
		
		bfs();
		System.out.print(sb.toString());
	}
	
	static void dfs(int ori) {
		sb.append(ori+" ");
		isVisited[ori] = true;
		
		for(Integer ti : list.get(ori)) {
			if(isVisited[ti]) continue;			
			dfs(ti);
		}
	}
	
	static void bfs() {
		Queue<Integer> que = new LinkedList<>();
		isVisited[V] = true;
		que.offer(V);
		
		int ori;
		while(!que.isEmpty()) {
			ori = que.poll();
			sb.append(ori+" ");
			for(Integer ti : list.get(ori)) {
				if(isVisited[ti]) continue;
				isVisited[ti] = true;
				que.offer(ti);
			}
		}
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[N+1];
		list = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<N+1; ++i) {
			list.add(new ArrayList<Integer>());
		}
		
		int s,e;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			list.get(s).add(e);
			list.get(e).add(s);
		}
		
		for(int i=1; i<N+1; ++i)
			Collections.sort(list.get(i));
	}
	
}
