import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -트리의 부모 찾기-
 */

//출처 : https://www.acmicpc.net/problem/11725
public class Main_B_S2_11725_트리의부모찾기 {
	static int N;
	static List<Integer>[] list;
	static boolean[] isVisited;
	static int[] res;
	
	public static void main(String[] args) throws Exception {
		init();
		
		find();
		
		print();
	}
	
	static void find() {
		Queue<Integer> que = new LinkedList<>();
		
		que.offer(1);
		isVisited[1] = true;
		
		int parent;
		while(!que.isEmpty()) {
			parent = que.poll();
			for(Integer ti : list[parent]) {
				if(isVisited[ti]) continue;
				que.offer(ti);
				isVisited[ti] = true;
				res[ti] = parent;
			}
		}
	}
	
	static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=2; i<=N; ++i)
			sb.append(res[i]+"\n");
		System.out.print(sb.toString());
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[N+1];
		list = new ArrayList[N+1];
		res = new int[N+1];
		for(int i=0; i<=N; ++i)
			list[i] = new ArrayList<>();
		
		int a,b;
		for(int i=0; i<N-1; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
	}
}
