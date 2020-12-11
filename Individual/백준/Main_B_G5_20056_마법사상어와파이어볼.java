import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -마법사 상어와 파이어볼-
 * 50M
 */

//출처 : https://www.acmicpc.net/problem/20056
public class Main_B_G5_20056_마법사상어와파이어볼 {
	static int N,M,K;
	static Board[][] board;
	
	static int[] drow = {-1,-1,0,1,1,1,0,-1};
	static int[] dcol = {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws Exception {
		init();
		
		while(--K >= 0) {
			move();
			
			done();
		}
		
		print();
	}
	
	// 공의 속력, 방향 대로 움직이기
	static void move() {
		List<Fire> list = new ArrayList<>();
		
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				for(Fire fire : board[row][col].list) {
					list.add(new Fire(getPoint(fire.r, fire.s * drow[fire.d]), getPoint(fire.c, fire.s * dcol[fire.d]), fire.m, fire.s, fire.d));
				}
				board[row][col].list.clear();
			}
		}
		
		// 움직여진 파이어볼을 정해진 판에 다시 넣기
		for(Fire fire : list) {
			board[fire.r][fire.c].list.add(fire);
		}
	}
	
	// 이동 후 위치 반환 (모든 행열이 연결되어있다)
	static int getPoint(int rc, int v) {
		return ((rc + v) + (N * 10000)) % N;
	}
	
	// 움직임 끝남
	static void done() {
		boolean isSame;
		int mod, tm, ts, m, s, size;
		
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				isSame = true;
				mod = -1; tm = 0; ts = 0; size=board[row][col].list.size();
				for(Fire fire : board[row][col].list) {
					tm+=fire.m;
					ts+=fire.s;
					
					// 방향성 설정
					if(isSame) {
						if(mod == -1) {
							mod = fire.d % 2;
						}
						else if(mod == 0) {
							if(fire.d % 2 == 1) {
								isSame = false;
							}
						}
						else if(mod == 1) {
							if(fire.d % 2 == 0) {
								isSame = false;
							}
						}
					}
				}
				
				// 1개 이상인 파이어볼이 있다면 나눌 것이다.
				if(size > 1) {
					board[row][col].list.clear();
					m = tm/5; s = ts/size;
					if(m != 0) {
						for(int d = isSame ? 0 : 1; d<8; d+=2) {
							board[row][col].list.add(new Fire(row, col, m, s, d));
						}
					}
				}
			}
		}
	}
	
	// 결과 출력
	static void print() {
		int res=0;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				for(Fire fire : board[row][col].list) {
					res += fire.m;
				}
			}
		}
		System.out.println(res);
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new Board[N][N];
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				board[row][col] = new Board();
			}
		}
		
		int r,c,m,s,d;
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			board[r][c].list.add(new Fire(r, c, m, s, d));
		}
	}
	
	static class Board {
		List<Fire> list;
		
		Board(){
			list = new ArrayList<>();
		}
	}
	
	static class Fire {
		// 행, 열, 질량, 속도, 방향
		int r,c,m,s,d;
		
		Fire(int r, int c, int m, int s, int d){
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
