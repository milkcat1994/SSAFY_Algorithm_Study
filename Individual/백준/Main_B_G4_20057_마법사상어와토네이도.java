import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -마법사 상어와 토네이도-
 * 1H 10M
 */

//출처 : https://www.acmicpc.net/problem/20057
public class Main_B_G4_20057_마법사상어와토네이도 {
	static int N;
	static Sand[][] board;
	static List<Percent>[] percent;
	static boolean[][] isVisited;
	
	// left, down, right, up
	static int[] drow = {0,1,0,-1};
	static int[] dcol = {-1,0,1,0};
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(move());
	}
	
	static int move() {
		int cr = N/2, cc = N/2, cd = 0, nr,nc, ti, res=0, origin;
		
		while(cr != 0 || cc != 0) {
			isVisited[cr][cc] = true;
			nr=cr+drow[cd]; nc=cc+dcol[cd];
			
			// 이동 시킬 총 모래 량
			origin = board[nr][nc].s;
			for(Percent tp : percent[cd]) {
				// 비율 구하여 원자리 모래는 빼주고, 해당 위치에 모래 량 더해준다.
				// 나갈 시는 res에 더해준다.
				ti = origin * tp.p / 100;
				board[nr][nc].s -= ti;
				if(isOut(nr+tp.r, nc+tp.c))
					res += ti;
				else
					board[nr+tp.r][nc+tp.c].s += ti;
			}
			
			// 'a'에 이동해야할 모래량은 res 또는 'a' 자리에 이동시킨다.
			if(isOut(nr+drow[cd], nc+dcol[cd]))
				res += board[nr][nc].s;
			else
				board[nr+drow[cd]][nc+dcol[cd]].s += board[nr][nc].s;
			board[nr][nc].s = 0;
			
			cr=nr; cc=nc;
			// 방향을 틀어야 되는지 확인한다.
			if(isCurve(cr, cc, cd))
				cd = (cd+1)%4;
		}
		return res;
	}
	
	// 나갔는지 확인
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
	
	// 현재 진행 사항에 따른 커브를 돌아야 하는지 판단
	static boolean isCurve(int cr, int cc, int d) {
		// 왼쪽 -> 아래
		// 아래 -> 우측
		// 우측 -> 위
		// 위쪽 -> 왼쪽
		int nr,nc;
		nr=cr+drow[(d+1)%4]; nc=cc+dcol[(d+1)%4];
		if(isVisited[nr][nc])
			return false;
		return true;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		isVisited = new boolean[N][N];
		board = new Sand[N][N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = new Sand(Integer.parseInt(st.nextToken()));
			}
		}
		
		percent = new ArrayList[4];
		for(int i=0; i<4; ++i) {
			percent[i] = new ArrayList<Percent>();
		}
		initPoint();
	}
	
	static class Sand {
		// 모래 총량
		int s;
		
		Sand(int s) {
			this.s = s;
		}
	}
	
	static class Percent {
		// 행 열, 모래 이동 비율
		int r,c, p;
		
		Percent(int r, int c, int p){
			this.r = r;
			this.c = c;
			this.p = p;
		}
	}

	// 4방향에 따른 모래 이동 비율
	static void initPoint() {
		// left
		percent[0].add(new Percent( 0,-2, 5));
		percent[0].add(new Percent(-1,-1,10));
		percent[0].add(new Percent(-1, 0, 7));
		percent[0].add(new Percent(-1, 1, 1));
		percent[0].add(new Percent( 1,-1,10));
		percent[0].add(new Percent( 1, 0, 7));
		percent[0].add(new Percent( 1, 1, 1));
		percent[0].add(new Percent(-2, 0, 2));
		percent[0].add(new Percent( 2, 0, 2));
		
		// down
		percent[1].add(new Percent( 2, 0, 5));
		percent[1].add(new Percent( 1,-1,10));
		percent[1].add(new Percent( 0,-1, 7));
		percent[1].add(new Percent(-1,-1, 1));
		percent[1].add(new Percent( 1, 1,10));
		percent[1].add(new Percent( 0, 1, 7));
		percent[1].add(new Percent(-1, 1, 1));
		percent[1].add(new Percent( 0, 2, 2));
		percent[1].add(new Percent( 0,-2, 2));
		
		// right
		percent[2].add(new Percent( 0, 2, 5));
		percent[2].add(new Percent( 1, 1,10));
		percent[2].add(new Percent( 1, 0, 7));
		percent[2].add(new Percent( 1,-1, 1));
		percent[2].add(new Percent(-1, 1,10));
		percent[2].add(new Percent(-1, 0, 7));
		percent[2].add(new Percent(-1,-1, 1));
		percent[2].add(new Percent( 2, 0, 2));
		percent[2].add(new Percent(-2, 0, 2));
		
		// up
		percent[3].add(new Percent(-2, 0, 5));
		percent[3].add(new Percent(-1, 1,10));
		percent[3].add(new Percent( 0, 1, 7));
		percent[3].add(new Percent( 1, 1, 1));
		percent[3].add(new Percent(-1,-1,10));
		percent[3].add(new Percent( 0,-1, 7));
		percent[3].add(new Percent( 1,-1, 1));
		percent[3].add(new Percent( 0,-2, 2));
		percent[3].add(new Percent( 0, 2, 2));
	}
}
