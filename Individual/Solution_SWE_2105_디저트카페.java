import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -디저트 카페-
 * 1. 시작점, 좌,우 길이 선택하는 4중 for문 작성
 * 2. 경계선 따라다니며 디저트 선택, selected 배열 로 재선택 판단
 * 3. 
 */

/*
 * 메모리 : 32204KB 
 * 시간 : 198ms 
 * 코드길이 : 2099B 
 * 소요시간 : 35M
 */

//200P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu
public class Solution_SWE_2105_디저트카페 {
	static int N;
	static int[][] board;
	static boolean[] isSelected;
	static int maxRes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; ++t){
			maxRes = -1;
			N = Integer.parseInt(br.readLine());
			
			board = new int[N][N];
			isSelected = new boolean[101];
			for (int row = 0; row < N; ++row) {
				st= new StringTokenizer(br.readLine(), " ");
				for (int col = 0; col < N; ++col) {
					board[row][col] = Integer.parseInt(st.nextToken());
				}
			} 
			
			int tempRes;
			for (int row = 0; row < N-2; ++row) {
				for (int col = 1; col < N-1; ++col) {
					//왼쪽 이동 길이
					for (int l = 1; l <= N-2; ++l) {
						//오른쪽 이동 길이
						for (int r = 1; r <= N-2; ++r) {
							if(isOut(row+l, col-l) ||isOut(row+r, col+r) || isOut(row+l+r, col-l+r)) continue;
							tempRes = getMax(row, col, l, r);
							maxRes = maxRes > tempRes ? maxRes : tempRes;
							Arrays.fill(isSelected, false);
						}
					}
				}
			}
			System.out.println("#"+t+" "+maxRes);
		} //end TestCase
	}
	
	public static int getMax(int row, int col, int l, int r) {
		int tempRes=0, cr,cc;
		//왼쪽아래
		cr=row; cc=col;
		for(int sl=0; sl<l; ++sl) {
			if(isSelected[board[cr][cc]]) return -1;
			tempRes++;
			isSelected[board[cr][cc]]=true;
			cr++;	cc--;
		}
		//우측아래
		for(int sr=0; sr<r; ++sr) {
			if(isSelected[board[cr][cc]]) return -1;
			tempRes++;
			isSelected[board[cr][cc]]=true;
			cr++;	cc++;
		}
		//우측 위
		for(int sl=0; sl<l; ++sl) {
			if(isSelected[board[cr][cc]]) return -1;
			tempRes++;
			isSelected[board[cr][cc]]=true;
			cr--;	cc++;
		}
		//좌측 위
		for(int sr=0; sr<r; ++sr) {
			if(isSelected[board[cr][cc]]) return -1;
			tempRes++;
			isSelected[board[cr][cc]]=true;
			cr--;	cc--;
		}
		return tempRes;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=N || col>=N)
			return true;
		return false;
	}
}
