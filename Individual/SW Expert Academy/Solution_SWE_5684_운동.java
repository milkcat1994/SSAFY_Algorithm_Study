import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * -운동-
 * 1. DFS이용해서 Cycle나오는 정점 모두 저장
 * 2. EdgeList 가중치 오름차순으로 정렬, BFS에 넣을 Vertex는 해당 Vertex까지 필요한 거리 오름차순으로 정렬
 * 3.Cycle에 나오는 정점 모두 돌면서 BFS로 최소 Cycle길이 파악
 * 4. BFS는 PriorityQueue로 확인하였음.
 * 
 * BurfferedReader쓰면 런타임에러 Scanner쓰면 통과되었던 문제.. -> 이거 찾느라 1시간 사용한듯.
 * +뭔가 그리디 적인 접근이라 다 맞진 않을 듯 하다.
 */

/*
 * 메모리 : 116676KB 
 * 시간 : 658ms 
 * 코드길이 : 2391B 
 * 소요시간 : 3H+
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRxnnah2sDFAUo
public class Solution_SWE_5684_운동 {
	public static class Edge implements Comparable<Edge>{
		int u, v, w;
		Edge(int u, int v, int w){
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
	}
	public static class Vertex implements Comparable<Vertex>{
		int u, sum;
		
		public Vertex(int u, int sum) {
			this.u = u;
			this.sum = sum;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.sum-o.sum;
		}
		
	}
	
	//0:미방문, 1:방문중, 2:방문종료
	static int[] isVisited;
	static List<Edge>[] list;
	static List<Integer> cycleList;
	static int minRes;
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1; t <=T; ++t) {
			N = sc.nextInt();	//Vertex갯수
			int M = sc.nextInt();	//Edge갯수
			
			isVisited = new int[N+1];
			list = new ArrayList[N+1];
			cycleList = new ArrayList<>();
			minRes = Integer.MAX_VALUE;
			for(int i = 0; i <= N; ++i)
				list[i] = new ArrayList<Edge>();
			
			//간선 정보 저장
			int u,v,w;
			for(int m = 0; m < M; ++m) {
				u = sc.nextInt();
				v = sc.nextInt();
				w = sc.nextInt();
				list[u].add(new Edge(u, v, w));
			}
			
			//오름차순 정렬
			for(int n = 1; n <= N; ++n)
				list[n].sort(null);
			
			//Cycle존재하는 정점 모두 저장.
			for(int n = 1; n <= N; ++n) {
				if(isVisited[n] == 2) continue;
				isVisited[n] = 1;
				dfs(n);
				isVisited[n] = 2;
			}
			
			//cycleList 정점 모두 BFS로 최소 거리 찾기
			PriorityQueue<Vertex> que = new PriorityQueue<>();
			Vertex tv;
			for(Integer ti : cycleList) {
				que.offer(new Vertex(ti,0));
				Arrays.fill(isVisited, 123456789);
				isVisited[ti]=0;
				W:while(!que.isEmpty()) {
					tv = que.poll();
					for(Edge e : list[tv.u]) {
						//도착지가 목적지이고 현재 값이 최소값이라면 갱신시키기
						if(e.v==ti) {
							minRes = minRes < isVisited[tv.u]+e.w ? minRes : isVisited[tv.u]+e.w;
							break W;
						}
						//목적지가 지금까지의 합보다 최소값이라면 볼필요 없다.
						//목적지 방문하지 않은 곳이라면 갱신필요
						if(isVisited[e.v] < isVisited[tv.u] + e.w)
							continue;
						
						//갱신 될 수 있다면 해당 목적지 최소값으로 갱신
						isVisited[e.v]=isVisited[tv.u] + e.w;
						que.offer(new Vertex(e.v,isVisited[e.v]));
					}
				}
			}
			
			System.out.println("#"+t+" "+(minRes == Integer.MAX_VALUE ? -1 : minRes));
		} //end TestCase
		sc.close();
	}
	
	//Cycle 판단 dfs
	public static void dfs(int u) {
		for(Edge e : list[u]) {
			if(isVisited[e.v]==2)
				continue;
			//만약 방문 중이라면 cycle이다.
			if(isVisited[e.v]==1) {
				cycleList.add(e.v);
				//길이 재기
				continue;
			}
			isVisited[e.v] = 1;
			dfs(e.v);
			isVisited[e.v] = 2;
		}
	}
}