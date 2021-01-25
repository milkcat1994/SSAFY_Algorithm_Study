import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -특정 거리의 도시 찾기-
 * 1. BFS 이용한 최소 거리가 K 인 위치 찾기.
 * 2. 출력은 오름차순으로.
 * 
 * 메모리 : 250500KB
 * 시간 : 900ms
 * 풀이 시간 : 30M
 */

//출처 : https://www.acmicpc.net/problem/18352
public class Main_B_S2_18352_특정거리의도시찾기 {
	static int N,M,K,X;
	
	static boolean[] isVisited;
	static List<ArrayList<Integer>> edges;
	static Queue<Integer> que;
	public static void main(String[] args) throws Exception {
		init();
		
		solve();
		
		print();
	}

	static void solve() {
		que = new LinkedList<>();
		que.offer(X);
		isVisited[X] = true;
		
		int start, time=0, qs;
		while(!que.isEmpty()) {
			
			qs = que.size();
			while(--qs>=0) {
				start = que.poll();
				for(Integer end : edges.get(start)) {
					if(isVisited[end]) continue;
					
					que.offer(end);
					isVisited[end] = true;
				}
			}
			time++;
			if(time == K) break;
		}
	}
	
	static void print() {
		StringBuilder sb = new StringBuilder();
		if(que.isEmpty()) {
			sb.append(-1);
			System.out.print(sb.toString());
			return;
		}
		else{
			List<Integer> list = new ArrayList<>();
			list.addAll(que);
			Collections.sort(list);
			for(Integer ti : list)
				sb.append(ti+"\n");
			System.out.print(sb.toString());
		}
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[N+1];
		edges = new ArrayList<>();
		for(int i=0; i<=N; ++i)
			edges.add(new ArrayList<Integer>());
		
		
		int s,e;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			edges.get(s).add(e);
		}
	}
}
