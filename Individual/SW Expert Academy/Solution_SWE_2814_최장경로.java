import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -최장 경로-
 * 1. DFS통한 최대로 이동할 수 있는 거리 출력
 */

/*
 * 메모리 : 29848KB 
 * 시간 : 115ms 
 * 코드길이 : 1434B 
 * 소요시간 : 40M
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GOPPaAeMDFAXB
public class Solution_SWE_2814_최장경로 {
	static boolean[] isVisited;
	static boolean[][] edge;
	static int N,M, u,v, maxTime;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			isVisited = new boolean[N+1];
			edge = new boolean[N+1][N+1];
			for(int m = 0; m < M; ++m) {
				st = new StringTokenizer(br.readLine(), " ");
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				
				edge[u][v] = true;
				edge[v][u] = true;
			}
			
			maxTime = 1;
			for(int tv = 1; tv <= N; ++tv) {
				dfs(tv, 1);
			}
			sb.append('#').append(t).append(' ').append(maxTime).append('\n');
		} //end TestCase
		System.out.print(sb.toString());
	}
	
	public static void dfs(int index, int count) {
		boolean goAble=false;
		//연결된 정점중 아무곳이나 들어가기
		for(int tv = 1; tv <= N; ++tv) {
			if(tv==index || isVisited[tv] || !edge[index][tv]) continue;
			isVisited[tv] = true;
			goAble = true;
			dfs(tv, count+1);
			isVisited[tv] = false;
		}
		
		// 더이상 진행할 수 없다면 count-1 저장.
		if(!goAble)
			maxTime = maxTime > count-1 ? maxTime : count-1;
	}
}
