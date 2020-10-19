import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -청소년 상어-
 */

//출처 : https://www.acmicpc.net/problem/19236
public class Main_B_G3_19236_청소년상어 {
	
	public static class Fish{
		int r,c,d,n;
		Fish(Fish fish){
			this.r = fish.r;
			this.c = fish.c;
			this.d = fish.d;
			this.n = fish.n;
		}
		
		Fish(int r, int c, int d, int n){
			this.r = r;
			this.c = c;
			this.d = d;
			this.n = n;
		}
		
		void move(int nr, int nc, int nd) {
			this.r = nr;
			this.c = nc;
			this.d = nd;
		}
		
		void move(int nr, int nc) {
			this.r = nr;
			this.c = nc;
		}
	}
	
	// 물고기 번호 담는 판
	static Fish[] fishArr = new Fish[16];
	static int[] drow = {-1,-1,0,1,1,1,0,-1};
	static int[] dcol = {0,-1,-1,-1,0,1,1,1};
	static int maxResult = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] board = new int[4][4];
		for (int row = 0; row < 4; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < 4; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken())-1;
				fishArr[board[row][col]] = new Fish(row, col, Integer.parseInt(st.nextToken())-1, board[row][col]);
			}
		} //end for
		
		//무조건 0,0 부터 시작
		fishArr[board[0][0]].n = -1;
		dfs(new Fish(0, 0, fishArr[board[0][0]].d, -1), board, board[0][0]+1, fishArr);
		System.out.println(maxResult);
	}
	
	public static void dfs(Fish shark, int[][] copyBoard, int totalCnt, Fish[] fish) {
		int nr,nc, fcr,fcc,fcd, fnr,fnc;
		nr = shark.r+drow[shark.d];	nc=shark.c+dcol[shark.d];
		
		int[][] tempBoard = new int[4][4];
		Fish[] tempFish = new Fish[16];
		Fish ts;
		boolean isMoved = false;
		int targetIdx;
		
		for(int r = 0; r < 4; ++r)
			for(int c = 0; c < 4; ++c)
				tempBoard[r][c] = copyBoard[r][c];
		
		for(int r = 0; r<16; ++r)
			tempFish[r] = new Fish(fish[r]);

		for(int idx = 0; idx < 16; ++idx) {
			if(tempFish[idx].n < 0) continue;
			fcr=tempFish[idx].r; fcc=tempFish[idx].c; fcd=tempFish[idx].d;
			
			for(int d = fcd; d < 8+fcd; ++d) {
				fnr=fcr+drow[d%8]; fnc=fcc+dcol[d%8];
				
				if(isOut(fnr, fnc) || !moveAble(fnr, fnc, shark.r, shark.c))
					continue;
				// 각 물고기 좌표 교환
				tempFish[idx].move(fnr, fnc, d%8);
				tempFish[tempBoard[fnr][fnc]].move(fcr, fcc);
				tempBoard[fcr][fcc] = tempBoard[fnr][fnc];
				tempBoard[fnr][fnc] = idx;
				break;
			}// end for(d)
		} // end for(idx)
		
		
		while(!isOut(nr, nc)) {
			targetIdx = tempBoard[nr][nc];
			//이미 먹힌 자리라면 패스
			if(tempFish[targetIdx].n < 0) {
				nr+=drow[shark.d];
				nc+=dcol[shark.d];
				continue;
			}
			isMoved = true;
			//새로운 좌표 이동 및 해당 위치 물고기 n값 변경
			ts = new Fish(nr, nc, tempFish[targetIdx].d, -1);
			tempFish[targetIdx].n = -1;
			dfs(ts, tempBoard, totalCnt+targetIdx+1, tempFish);
			tempFish[targetIdx].n = targetIdx;
			
			nr = ts.r+drow[shark.d]; nc=ts.c+dcol[shark.d];
		}
		
		//상어가 움직일 수 없다면 최대값 갱신
		if(!isMoved) {
			maxResult = maxResult > totalCnt ? maxResult : totalCnt;
		}
		return;
	}
	
	public static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=4 || c>=4)
			return true;
		return false;
	}
	
	// 물고기 이동 가능한지
	public static boolean moveAble(int r, int c, int sr, int sc) {
		//상어라면 이동 불가
		if(r == sr && c == sc)
			return false;
		return true;
	}
	
}
