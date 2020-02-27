import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -다리만들기2-
 * 1. BFS이용하여 땅 구역 다르게 번호 부여 : divideLand()
 * 2. 해당 땅에서 다른 땅까지의 거리 구하기 : getDist
 * 3. 크루스칼 알고리즘 이용한 최소 연결 거리 도출
 * └──Union-Find 구현
 */

//출처 : https://www.acmicpc.net/problem/17472
public class Main_B_G3_17472_다리만들기2 {
	static final int INF = (1<<15);
	static int N,M;
	static int[][] board;
	static boolean[][] isVisited;	//섬 구역 나눌때 사용
	static int[][] edge;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static List<ArrayList<int[]>> landList;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	//Union-Find
	static int[] parents;
	public static void initUnion() {
		Arrays.fill(parents, -1);
	}
	
	public static int find(int index) {
		//자기 자신이 부모라면 index return
		if(parents[index] == -1) return index;
		return parents[index] = find(parents[index]);
	}
	
	public static boolean union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		if(aP == bP) return false;
		
		parents[bP] = aP;
		return true;
	}
	//Union-Find
	
	static class Edge implements Comparable<Edge>{
		int u,v,w;
		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < M; ++col) {
				switch (Integer.parseInt(st.nextToken())) {
				case 0:
					board[row][col] = 0;
					break;
				case 1:
					board[row][col] = -1;
					break;
				} 
			}
		}
		
		//bfs로 각자 땅 좌표 List알아두기
		isVisited = new boolean[N][M];
		landList = new ArrayList<ArrayList<int[]>>();
		landList.add(new ArrayList<int[]>());

		int landCount = 0;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < M; ++col) {
				//섬일 경우 해당 섬으로 부터 List담기
				if(board[row][col] == -1 && !isVisited[row][col]) {
					divideLand(row,col, ++landCount);
				}
			}
		} //end for(row)
		
		edge = new int[landCount+1][landCount+1];
		for(int row = 0; row <= landCount; ++row)
			Arrays.fill(edge[row], INF);
		//각 땅 돌면서 다른 땅과의 거리 구하기
		for(int clc = 1; clc <= landCount; ++clc) {
			getDist(clc);
		}
		
		
		for (int row = 0; row <= landCount; ++row) {
			for (int col = 0; col < row; ++col) {
				if(edge[row][col] == INF) continue;
				pq.offer(new Edge(row, col, edge[row][col]));
			}
		}
		
		//하나의 섬도 연결되지 않을 때
		if(pq.isEmpty()) {
			System.out.println(-1);
			return;
		}
		
		//모두 연결 되는지 확인하기.
		if(!isConnected(pq.peek().u, landCount)) {
			//만약 연결 될 수 없다면 종료
			System.out.println(-1);
			return;
		}
		
		//크루스칼 알고리즘
		Edge te;
		int u,v,w,sum=0;
		parents = new int[landCount+1];
		initUnion();
		while(!pq.isEmpty()) {
			te = pq.poll();
			u = te.u;	v = te.v;	w = te.w;
			//합쳐 질 수 있다면 sum에 더하기
			if(union(u,v)) {
				sum+=w;
			}
		}
		
		System.out.println(sum);
	} //end main
	
	//모든 땅이 연결 될 수 있다면 true
	public static boolean isConnected(int row, int toc) {
		boolean[] isSelected = new boolean[toc+1];
		Queue<Integer> q = new LinkedList<>();
		q.offer(row);
		int tr, tc = 1;
		isSelected[row] = true;
		
		while(!q.isEmpty()) {
			tr = q.poll();
			for(int c = 1; c <= toc; ++c) {
				if(edge[tr][c] == INF || isSelected[c]) continue;
				
				q.offer(c);
				isSelected[c] = true;
				tc++;
			}
		}
		return tc == toc;
	}
	
	public static void getDist(int index) {
		int cr,cc,nr,nc;
		//해당 땅 에서 사방 펼쳐나가기
		for(int[] point : landList.get(index)) {
			cr = point[0];	cc = point[1];
			//4방향 확인
			for(int dir = 0; dir < 4; ++dir) {
				//해당 방향 계속 직진 해보기
				for(int dist = 1; dist <= 10; ++dist) {
					nr = cr+drow[dir]*dist;	nc = cc+dcol[dir]*dist;
					//나가거나, 자신의 땅이라면 그만.
					if(isOut(nr, nc) || board[nr][nc] == index) break;
					
					//만일 다른 땅을 만났다면 해당 땅과의 거리 좌표 넣어주기.
					//거리가 1이라면 넣지 않기
					if(board[nr][nc] > 0) {
						if(dist <= 2) break;
						//현재 저장된 정보보다 지금 들어오는것이 작다면 다시 저장.
						if(edge[index][board[nr][nc]] > dist-1) {
							edge[index][board[nr][nc]] = dist-1;
							edge[board[nr][nc]][index] = dist-1;
						}
						break;
					}
				} //end for(dist)
			} //end for(dir)
		} // end for(list)
	}
	
	//분리된 땅 표시하기
	public static void divideLand(int row, int col, int landCount) {
		int cr,cc,nr,nc;
		int[] tIntArr;
		Queue<int[]> que = new LinkedList<>();

		landList.add(new ArrayList<int[]>());
		landList.get(landCount).add(new int[] {row, col});
		
		isVisited[row][col] = true;
		que.offer(new int[] {row, col});
		board[row][col] = landCount;
		
		//BFS시작
		while(!que.isEmpty()) {
			tIntArr = que.poll();
			cr = tIntArr[0];	cc = tIntArr[1];
			for(int dir = 0; dir < 4; ++dir) {
				nr = cr+drow[dir];	nc = cc+dcol[dir];
				//나가거나 바다라면 Pass
				if(isOut(nr, nc) || board[nr][nc] == 0 || isVisited[nr][nc]) continue;
				
				isVisited[nr][nc] = true;
				que.offer(new int[] {nr, nc});
				landList.get(landCount).add(new int[] {nr, nc});
				board[nr][nc] = landCount;
			}
		}
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=N || col>=M)
			return true;
		return false;
	}
}
