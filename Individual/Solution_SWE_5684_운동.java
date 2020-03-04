import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -운동-
 */

/*
 * 메모리 : -KB 
 * 시간 : -ms 
 * 코드길이 : -B 
 * 소요시간 : -
 */

//런타임 에러 떠서 중지
//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRxnnah2sDFAUo
public class Solution_SWE_5684_운동 {
	public static class Edge {
		int u, v, w;
		Edge(int u, int v, int w){
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	
	//0:미방문, 1:방문중, 2:방문종료
	static int[] isVisited;
	static List<Edge>[] list;
	static Stack<Edge> stack;
	static int minRes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <=T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());	//Vertex갯수
			int M = Integer.parseInt(st.nextToken());	//Edge갯수
			
			isVisited = new int[N+1];
			list = new ArrayList[N+1];
			stack = new Stack<>();
			minRes = Integer.MAX_VALUE;
			for(int i = 0; i <= N; ++i)
				list[i] = new ArrayList<Edge>();
			
			//간선 정보 저장
			int u;
			for(int m = 0; m < M; ++m) {
				st = new StringTokenizer(br.readLine(), " ");
				u = Integer.parseInt(st.nextToken());
				list[u].add(new Edge(u, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			for(int n = 1; n <= N; ++n) {
				if(isVisited[n] == 2) continue;
				isVisited[n] = 1;
				dfs(n);
				isVisited[n] = 2;
			}
			
			System.out.println("#"+t+" "+(minRes == Integer.MAX_VALUE ? -1 : minRes));
		} //end TestCase
	}
	
	public static void dfs(int u) {
		for(Edge e : list[u]) {
			//만약 이미 방문완료 된 좌표면 볼 필요 없다.
			if(isVisited[e.v]==2)
				continue;
			//만약 방문 중이라면 cycle이다.
			if(isVisited[e.v]==1) {
				stack.push(e);
				//길이 재기
				int tl = getLength(e.v);
				minRes = minRes < tl ? minRes : tl;
				stack.pop();
				continue;
			}
			isVisited[e.v] = 1;
			stack.push(e);
			dfs(e.v);
			isVisited[e.v] = 2;
			stack.pop();
		}
	}
	
	public static int getLength(int u) {
		boolean isFind = false;
		int length=0;
		for(Edge e : stack) {
			if(e.v == u && !isFind) {
				isFind = true;
				if(e.u == u)
					length+=e.w;
				continue;
			}
			if(isFind)
				length+= e.w;
		}
		return length;
	}
}
