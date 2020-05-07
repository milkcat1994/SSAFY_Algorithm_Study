import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * -아기상어-
 * BFS통해 먹을 수 있는 먹이 찾기, 우선순위 큐에 해당 먹이 담기
 * 우선순위 큐에서 먹을 수 있는 1마리 찾아서 해당 좌표로 이동.
 */

// 출처 : https://www.acmicpc.net/problem/16236
public class Main_B_G5_16236_아기상어_재풀이 {
	static int[][] board;
	static boolean[][] isVisited;
	static int N;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};

	static class Shark{
		int r,c,size,feed;
		
		public Shark(int r, int c, int size, int feed) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.feed = feed;
		}
		
		public void eat() {
			this.feed--;
			if(this.feed == 0) {
				this.size++;
				this.feed = this.size;
			}
		}
		
		public void move(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static Shark shark;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		isVisited = new boolean[N][N];
		String str;
		for (int row = 0; row < N; ++row) {
			str = br.readLine();
			for (int col = 0; col < N; ++col) {
				board[row][col] = str.charAt(col*2)-'0';
				if(board[row][col] == 9) {
					shark = new Shark(row, col, 2, 2);
				}
			}
		}
		
		Queue<int[]> que = new LinkedList<>();
		//row가 작은것, 만약 같다면 col이 작은것을 앞으로 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] > o2[0]) {
					return 1;
				}
				else if(o1[0] == o2[0]) {
					if(o1[1] > o2[1]) {
						return 1;
					}
					else {
						return -1;
					}
				}
				return -1;
			}
		});
		
		int[] tia;
		int cr,cc, nr,nc, qs, dist,time=0;
		boolean find;
		do{
			find = false;
			dist = 0;
			initVisited();
			que.offer(new int[] {shark.r, shark.c});
			isVisited[shark.r][shark.c] = true;
			while(!que.isEmpty()) {
				//만일 먹이를 찾았다면 탐색 중지
				if(find) {
					que.clear();
					break;
				}
				qs = que.size();
				while(qs-->0) {
					tia = que.poll();
					cr=tia[0];	cc=tia[1];
					for(int dir = 0; dir < 4; ++dir) {
						nr = cr+drow[dir];	nc = cc+dcol[dir];
						//밖으로 나가거나, 방문 했거나, 자기보다 크기가 크다면 못지나간다.
						if(isOut(nr, nc) || isVisited[nr][nc] || board[nr][nc] > shark.size) continue;
						//빈 곳이 아니고 상어보다 크기 작다면 pq(먹이)에 담기
						if(board[nr][nc] != 0 && board[nr][nc] < shark.size) {
							pq.offer(new int[] {nr,nc});
							find = true;
						}
						que.offer(new int[] {nr,nc});
						isVisited[nr][nc] = true;
					}
				}
				//BFS 탐색 거리 증가
				dist++;
			} //end While(!Que.isEmpty)
			//먹이를 찾았다면 먹고, 이동, 거리를 time에 더하기
			if(find) {
				board[shark.r][shark.c] = 0;
				shark.eat();
				nr = pq.peek()[0];	nc = pq.peek()[1];
				shark.move(nr, nc);
				board[nr][nc] = 0;
				isVisited[nr][nc] = true;
				pq.clear();
				time+=dist;
			}
			//찾았다면 다음 먹이도 찾기
		}while(find);
		System.out.print(time);
	}
	
	static void initVisited() {
		for(int r = 0; r < N; ++r)
			Arrays.fill(isVisited[r], false);
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
}
