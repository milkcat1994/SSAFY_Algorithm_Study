import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -마법사 상어와 파이어스톰-
 * 입력 받는 q의 크기에 따라 사각형들을 쪼개고
 * 해당 사각형의 크기를 줄여가며 시계방향으로 한줄씩 회전시킨다.
 */

//출처 : https://www.acmicpc.net/problem/20058
public class Main_B_G4_20058_마법사상어와파이어스톰 {
	static int N,Q;
	static Ice[][] board;
	static boolean[][] isVisited;
	
	static Ice[] ices;
	static int[] qList;
	
	static int[] drow = {0,1,0,-1};
	static int[] dcol = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception {
		init();
		
		for(int i=0; i<Q; ++i) {
			divide(qList[i]);
			sub();
		}
		
		System.out.println(getTotal());
		System.out.println(getMaxGroup());
	}
	
	// q에 따른 사각형 분할
	static void divide(int q) {
		int powQ=pow(q);
		for (int row = 0; row < N; row += powQ) {
			for (int col = 0; col < N; col += powQ) {
				// 왼쪽 상단 좌표 제공
				for(int i=0; i<powQ/2; ++i) {
					rotate(row+i, col+i, powQ-i*2);
				}
			}
		}
	}
	
	// q 크기에 따른 모든 사각형 회전
	static void rotate(int r, int c, int q) {
		int powQ = q-1;
		
		for(int i=0; i<powQ; ++i) {
			ices[i] = board[r][c+i];
		}
		
		// 왼쪽 -> 위
		for(int i=0; i<powQ; ++i) {
			board[r][c+i] = board[r+powQ-i][c];
			board[r+powQ-i][c].move(r, c+i);
		}
		
		// 아래 -> 왼쪽
		for(int i=0; i<powQ; ++i) {
			board[r+powQ-i][c] = board[r+powQ][c+powQ-i];
			board[r+powQ][c+powQ-i].move(r+powQ-i, c);
		}
		
		// 오른쪽 -> 아래
		for(int i=0; i<powQ; ++i) {
			board[r+powQ][c+powQ-i] = board[r+i][c+powQ];
			board[r+i][c+powQ].move(r+powQ, c+powQ-i);
		}
		
		// 위 -> 오른쪽
		for(int i=0; i<powQ; ++i) {
			board[r+i][c+powQ] = ices[i];
			ices[i].move(r+i, c+powQ);
		}
	}
	
	// 3개 인접 하지 않으면 얼음 녹이기
	static void sub() {
		List<Ice> list = new ArrayList<>();
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if(getSide(row, col) < 3 && board[row][col].n > 0)
					list.add(board[row][col]);
			}
		}
		
		for(Ice ice : list) {
			board[ice.r][ice.c].n--;
		}
	}
	
	// 주변 얼음 개수 반환
	static int getSide(int r, int c) {
		int res=0, nr,nc;
		for(int d = 0; d<4; ++d) {
			nr=r+drow[d]; nc=c+dcol[d];
			if(isOut(nr, nc) || board[nr][nc].n==0) continue;
			res++;
		}
		return res;
	}
	
	// 총 얼음량 구하기
	static int getTotal() {
		int res = 0;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				res += board[row][col].n;
			}
		}
		return res;
	}
	
	// 최대 얼음 덩어리 크기 구하기
	static int getMaxGroup() {
		int res=0, tres;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if(isVisited[row][col] || board[row][col].n == 0) continue;
				tres = bfs(row, col);
				if(tres > res) res = tres;
			}
		}
		return res;
	}
	
	// 최대 얼음 덩어리 구하기 위한 bfs
	static int bfs(int r, int c) {
		int res=1, nr, nc;
		Queue<Ice> que = new LinkedList<>();
		que.offer(board[r][c]);
		isVisited[r][c] = true;
		
		Ice tIce;
		while(!que.isEmpty()) {
			tIce = que.poll();
			for(int d = 0; d<4; ++d) {
				nr=tIce.r+drow[d]; nc=tIce.c+dcol[d];
				if(isOut(nr, nc) || isVisited[nr][nc] || board[nr][nc].n == 0) continue;
				
				que.offer(board[nr][nc]);
				isVisited[nr][nc] = true;
				res++;
			}
		}
		
		return res;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
	
	// 2의 제곱 수
	static int pow(int q) {
		int res=1;
		for(int i = 0; i < q; ++i)
			res *= 2;
		return res;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = pow(Integer.parseInt(st.nextToken()));
		Q = Integer.parseInt(st.nextToken());
		
		ices = new Ice[N];
		board = new Ice[N][N];
		isVisited = new boolean[N][N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = new Ice(row, col, Integer.parseInt(st.nextToken()));
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		qList = new int[Q];
		for(int i = 0; i < Q; ++i) {
			qList[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	static class Ice {
		int r, c, n;

		Ice(int r, int c, int n){
			this.r = r;
			this.c = c;
			this.n = n;
		}
		
		void move(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}