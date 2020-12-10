import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -어른 상어-
 * 1H 20M
 */

//출처 : https://www.acmicpc.net/problem/19237
public class Main_B_G3_19237_어른상어 {
	static int N,M,K;
	static Board[][] board;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static int[][][] dir;
	
	public static void main(String[] args) throws Exception {
		int time = 0;
		init();
		
		while(time <= 1000) {
			spread();
			
			move();

			removeShark();

			removeSmall();
			
			time++;
			
			if(getSharksNum() == 1)
				break;
		}
		System.out.print(time == 1001 ? -1 : time);
	}
	
	// 현재 위치에 냄새 추가
	static void spread() {
		Shark ts;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				ts = board[row][col].shark;
				if(ts.n > -1) {
					board[row][col].small.n = ts.n;
					board[row][col].small.k = K;
				}
			}
		}
	}
	
	// 상어 이동
	static void move() {
		Shark ts;
		int d;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				ts = board[row][col].shark;
				if(ts.n > -1) {
					d = findDir(row, col, ts.n, ts.d);
					// 상어 방향 전환 및 해당 위치에 상어 추가
					ts.d = d;
					board[row+drow[d]][col+dcol[d]].list.add(ts);
					board[row][col].shark = new Shark();
				}
			}
		}
	}

	// 이동 가능한 방향 찾기
	static int findDir(int row, int col, int n, int cd) {
		int nr,nc;
		int res = -1;
		for (int d = 0; d < 4; ++d) {
			nr = row+drow[dir[n][cd][d]];
			nc = col+dcol[dir[n][cd][d]];
			if(isOut(nr, nc)) continue;
			// 빈 곳이라면 바로 리턴
			if(board[nr][nc].small.n == -1)
				return dir[n][cd][d];
			// 빈 곳 못찾았다면 자신의 냄새쪽 기억
			if(res == -1)
				if(board[nr][nc].small.n == n)
					res = dir[n][cd][d];
		}
		return res;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
	
	// 겹친 상어 제거
	static void removeShark() {
		int n;
		Shark shark;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				n = N*N;
				shark = null;
				for(Shark ts : board[row][col].list) {
					if(n > ts.n) {
						n = ts.n;
						shark = ts;
					}
				}
				if(shark != null) {
					board[row][col].shark = shark;
					board[row][col].list.clear();
				}
			}
		}
	}
	
	// 냄새 시간 감소
	static void removeSmall() {
		Small small;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				small = board[row][col].small;
				if(small.n != -1) {
					small.k--;
					if(small.k == 0) {
						small.n = -1;
					}
				}
			}
		}
	}

	// 상어 갯수 반환
	static int getSharksNum() {
		int res = 0;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if(board[row][col].shark.n > -1) {
					res++;
				}
			}
		}
		return res;
	}
	
	// 입력받기
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new Board[N][N];
		// 상어번호 / 현재 방향 / 우선순위
		dir = new int[M][4][4];
		
		int ti;
		// 상어 현재 방향 넣기 위한 배열
		List<Shark> list = new ArrayList<>();
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				ti = Integer.parseInt(st.nextToken());
				if(ti == 0) {
					board[row][col] = new Board();
				}
				else {
					board[row][col] = new Board(ti-1, K);
					list.add(board[row][col].shark);
				}
			}
		}
		
		// 상어의 현재 방향 설정
		String str = br.readLine();
		for(Shark shark : list) {
			shark.d = str.charAt(shark.n*2) -'0' -1;
		}
		
		// 상어 번호와 현재 방향에 따른 방향 우선순위
		for (int num = 0; num < M; ++num) {
			for (int cur = 0; cur < 4; ++cur) {
				str = br.readLine();
				for(int d = 0; d < 4; ++d) {
					dir[num][cur][d] = str.charAt(d*2) - '0' -1;
				}
			}
		}
	}

	static class Board {
		Shark shark;
		// 이동 완료된 현재 위치의 상어리스트
		List<Shark> list;
		Small small;
		Board(){
			list = new ArrayList<>();
			shark = new Shark();
			small = new Small();
		}
		
		Board(int n, int k){
			list = new ArrayList<>();
			shark = new Shark();
			shark.n = n;
			small = new Small(n, k);
		}
	}
	
	static class Shark {
		//상어 번호, 방향
		int n, d;
		
		Shark(){
			n = -1;
			d = -1;
		}
		
		Shark(int n, int d){
			this.n = n;
			this.d = d;
		}
	}
	
	static class Small {
		// 상어 번호, 남은 시간
		int n, k;
		
		Small(){
			n = -1;
			k = -1;
		}
		
		Small(int n, int k){
			this.n = n;
			this.k = k;
		}
	}
}
