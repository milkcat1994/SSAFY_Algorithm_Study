import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -집합의 표현-
 */

//출처 : https://www.acmicpc.net/problem/1717
public class Main_B_G4_1717_집합의표현 {
	static int[] parent;
	
	static void init(int N) {
		parent = new int[N];
		Arrays.fill(parent, -1);
	}
	
	static int findSet(int x) {
		if(parent[x] == -1) return x;
		return parent[x] = findSet(parent[x]);
	}
	
	static boolean union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		
		if(pa == pb) return false;
		
		parent[pa] = pb;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N+1);
		
		while(--M>=0) {
			st = new StringTokenizer(br.readLine(), " ");
			
			switch (Integer.parseInt(st.nextToken())) {
			case 0:
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				break;
			case 1:
				if(findSet(Integer.parseInt(st.nextToken())) == findSet(Integer.parseInt(st.nextToken()))) {
					sb.append("YES\n");
				}
				else {
					sb.append("NO\n");
				}
				break;
			}
		}
		System.out.print(sb.toString());
	}
}
