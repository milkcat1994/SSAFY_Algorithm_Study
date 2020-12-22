import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -ABCDE-
 */

//출처 : https://www.acmicpc.net/problem/13023
public class Main_B_G5_13023_ABCDE {
	static int N, M;
	static List<Integer>[] list;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		init();
		
		for(int i=0; i<N; ++i) {
			if(dfs(i, 1)) {
				System.out.print(1); 
				return;
			}
		}
		
		System.out.print(0);
	}
	
	static boolean dfs(int idx, int cnt) {
		if(isSelected[idx]) return false;
		isSelected[idx] = true;
		if(cnt >= 5) return true;
		
		for(Integer ti : list[idx]) {
			if(dfs(ti, cnt+1)) return true;
		}
		
		isSelected[idx] = false;
		return false;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isSelected = new boolean[N];
		list = new ArrayList[N];
		for(int i=0; i<N; ++i)
			list[i] = new ArrayList<>();
		
		int s,e;
		
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			list[s].add(e);
			list[e].add(s);
		}
	}
}
