import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -네트워크 연결-
 * 크루스칼 알고리즘 이용한 MST
 */

//출처 : https://www.acmicpc.net/problem/1922
public class Main_B_G4_1922_네트워크연결 {
	static class Edge {
		int s,e,w;
		Edge(int s, int e, int w){
			this.s=s;
			this.e=e;
			this.w=w;
		}
	}
	
	static int[] parents;
	
	static void initParents(int N) {
		parents = new int[N];
		Arrays.fill(parents, -1);
	}
	
	static int getParent(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = getParent(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);
		
		if(pa == pb) return false;
		
		parents[pa] = pb;
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		initParents(N+1);
		
		st = new StringTokenizer(br.readLine(), " ");
		int E = Integer.parseInt(st.nextToken());
		
		List<Edge> list = new ArrayList<>();
		for(int e=0; e<E; ++e) {
			st = new StringTokenizer(br.readLine(), " ");
			list.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(list, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.w-o2.w;
			}
		});
		
		int a,b, res=0;
		for(Edge e : list) {
			a=e.s; b=e.e;
			
			if(union(a,b)) {
				res+=e.w;
			}
		}
		
		System.out.println(res);
	}
}
