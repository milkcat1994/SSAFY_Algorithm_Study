import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -마법사 상어와 파이어볼-
 * 50M
 * List<Fire> fire 를 통해 임시로 저장 하던 것을
 * fireCnt의 2차원 배열을 통해 정해진 횟수만큼 돌게 하여 시간, 공간 절약하도록 리펙토링
 */

//출처 : https://www.acmicpc.net/problem/20056
public class Main_B_G5_20056_마법사상어와파이어볼 {
	static int N,M,K;
	static Board[][] board;
	static int[][] fireCnt;
	
	static int[] drow = {-1,-1,0,1,1,1,0,-1};
	static int[] dcol = {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws Exception {
		init();
		
		while(--K >= 0) {
			move();
			
			updateCnt();
			
			done();
		}
		
		print();
	}
	
	// 공의 속력, 방향 대로 움직이기
	static void move() {
		Fire fire;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				for(int k = 0; k<fireCnt[row][col]; ++k) {
					fire = board[row][col].que.poll();
					fire.r = getPoint(fire.r, fire.s * drow[fire.d]); fire.c = getPoint(fire.c, fire.s * dcol[fire.d]);
					board[fire.r][fire.c].que.offer(new Fire(fire.r, fire.c, fire.m, fire.s, fire.d));
				}
			}
		}
	}
	
	// 이동 후 위치 반환 (모든 행열이 연결되어있다)
	static int getPoint(int rc, int v) {
		return ((rc + v) + (N * 10000)) % N;
	}
	
	// 파이어볼 개수 초기화
	static void updateCnt() {
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				fireCnt[row][col] = board[row][col].que.size();
			}
		}
	}
	
	// 움직임 끝남
	static void done() {
		boolean isSame;
		int mod, tm, ts, m, s, size;
		Fire fire;
		
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				isSame = true;
				mod = -1; tm = 0; ts = 0; size=fireCnt[row][col];
				
				// 1개 이상인 파이어볼이 있다면 나눌 것이다.
				if(size > 1) {
					for(int k=0; k<size; ++k) {
						fire = board[row][col].que.poll();
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
				
					m = tm/5; s = ts/size;
					if(m != 0) {
						for(int d = isSame ? 0 : 1; d<8; d+=2) {
							board[row][col].que.offer(new Fire(row, col, m, s, d));
						}
						fireCnt[row][col] = 4;
					}
					else {
						fireCnt[row][col] = 0;
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
				for(Fire fire : board[row][col].que) {
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
		
		fireCnt = new int[N][N];
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
			board[r][c].que.offer(new Fire(r, c, m, s, d));
			fireCnt[r][c]++;
		}
	}
	
	static class Board {
		Queue<Fire> que;
		
		Board(){
			que = new LinkedList<>();
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
