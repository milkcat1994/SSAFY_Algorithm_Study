import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -바이러스-
 * UnionFind 알고리즘
 */

//출처 : https://www.acmicpc.net/problem/2251
public class Main_B_S2_2606_바이러스 {
	static int[] parents;
	
	static void initArray(int N) {
		parents=new int[N+1];
		Arrays.fill(parents, -1);
	}
	
	static int find(int n) {
		if(parents[n]==-1) return n;
		return parents[n]=find(parents[n]);
	}
	
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa==pb && pa!=-1)
			return false;
		
		parents[pb] = pa;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N=Integer.parseInt(br.readLine());
		initArray(N);
		
		int E=Integer.parseInt(br.readLine());
		for(int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		int res=0;
		int pa=find(1);
		for(int i = 1; i <= N; ++i) {
			if(find(i) == pa)
				res++;
		}
		System.out.print(res-1);
	}
}