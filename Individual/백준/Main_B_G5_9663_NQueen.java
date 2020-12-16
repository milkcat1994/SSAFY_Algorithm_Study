import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -N-Queen-
 */

//출처 : https://www.acmicpc.net/problem/9663
public class Main_B_G5_9663_NQueen {
	static int N, res;
	static boolean[] isSelectedCol;
	static boolean[][] isSelected;
	
	static int[] drow = {-1,-1,1,1};
	static int[] dcol = {-1,1,-1,1};
	
	public static void main(String[] args) throws Exception {
		init();
		
		for(int col=0; col < N; ++col) {
			putQueen(0, col);
		}
		System.out.print(res);
	}
	
	static void putQueen(int r, int c) {
		// 해당 r,c에 놓을 수 있는지 확인
		if(!isAble(r, c)) return;
		
		// 끝 row 까지 다 보았다면 갯수 증가 시키고 return
		if(r >= N-1) {
			res++;
			return;
		}
		
		isSelectedCol[c] = true;
		isSelected[r][c] = true;
		
		for(int col = 0; col < N; ++col) {
			if(isSelectedCol[col]) continue;
			putQueen(r+1, col);
		}
		isSelected[r][c] = false;
		isSelectedCol[c] = false;
	}
	
	static boolean isAble(int r, int c) {
		// '열'은 isSelectedCol로 확인중이라 확인 불필요.
		// '행'은 항상 증가하므로 확인 불필요.
		
		// '대각선' 확인 필요
		int nr, nc;
		// 왼쪽 위, 우측 위, 왼쪽 아래, 우측 아래
		for(int d = 0; d<4; ++d) {
			nr=r; nc=c;
			while(!isOut(nr +=drow[0], nc += dcol[0])) {
				if(isSelected[nr][nc])
					return false;
			}
		}
		return true;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isSelectedCol = new boolean[N];
		isSelected = new boolean[N][N];
	}
}
