import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * -움직이는 미로 탈출-
 * 1. 개선안 : 맵을 움직이지 않고 사람이 이동한 시간을 이용하여 맵의 이동 좌표를 구할 수 있음.
 */

//출처 : https://www.acmicpc.net/problem/16954
public class Main_B_G5_16954_움직이는미로탈출 {
	static final int N = 8;
	static final int D = 9;
	static int[] drow = {0,-1,-1,0,1,1,1,0,-1};
	static int[] dcol = {0,0,-1,-1,-1,0,1,1,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[][] board = new char[N][N];
		char[][] tempBoard = new char[N][N];
		String str;
		for (int row = 0; row < N; ++row) {
			str = br.readLine();
			for (int col = 0; col < N; ++col) {
				board[row][col] = str.charAt(col);
			}
		}
		Queue<int[]> que = new LinkedList<>();
		Set<int[]> set = new HashSet<>();
		
		que.offer(new int[] {7,0});
		set.add(new int[] {7,0});
		int cr,cc,nr,nc, size;
		int[] ti, nti;
		while(!que.isEmpty()) {
			size = que.size();
			tempBoard = moveBoard(board);
			if(isNonWall(board)) {
				System.out.println(1);
				return;
			}
			while(--size >= 0) {
				ti = que.poll();
				set.remove(ti);
				cr=ti[0]; cc=ti[1];
				for(int d=0; d<D; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d];
					//나가거나 벽이라면 이동 할 수 없다. + 깔릴 예정이라면 패스
					if(isOut(nr, nc) || board[nr][nc] == '#'|| tempBoard[nr][nc] == '#') continue;
					if(nr==0 && nc==7) {
						System.out.println(1);
						return;
					}
					//새로운 위치 넣기
					nti = new int[] {nr,nc};
					if(!set.contains(nti))
						que.offer(nti);
				}
			}
			board = copyBoard(tempBoard);
		}
		System.out.println(0);
	}
	
	static char[][] copyBoard(char[][] board) {
		char[][] tempBoard = new char[N][N];
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				tempBoard[row][col] = board[row][col];
			}
		}
		return tempBoard;
	}
	
	//board이동
	static char[][] moveBoard(char[][] board) {
		char[][] tempBoard = new char[N][N];
		for (int row = N-2; row >= 0; --row) {
			for (int col = 0; col < N; ++col) {
				tempBoard[row+1][col] = board[row][col];
			}
		}
		for(int col = 0; col<N; ++col)
			tempBoard[0][col] = '.';
		return tempBoard;
	}
	
	//벽이 있는지 테스트
	static boolean isNonWall(char[][] board) {
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if(board[row][col] == '#') return false;
			}
		}
		return true;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
}
