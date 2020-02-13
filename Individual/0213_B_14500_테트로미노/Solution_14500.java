import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -테트로미노-
 * 1. 테트로미노로 나올 수 있는 모양 전부를 3차원 배열을 이용하여 작성하였다.
 * 2. 이후 해당 좌표가 밖으로 나가는지만 판별하며 모든 좌표를 탐색한다.
 * 3. 밖으로 나가지 않았다면 선택된 구역의 최대값을 갱신해준다.
 */

//출처 : https://www.acmicpc.net/problem/14500
public class Solution_14500 {
	//'ㅡ'
	static int[][][] one = new int[][][] {
			{
				{0,0},{0,1},{0,2},{0,3}
			},
			{
				{0,0},{1,0},{2,0},{3,0}
			},
	};
	
	//'ㅁ'
	static int[][][] two = new int[][][] {
		{
			{0,0},{0,1},{1,0},{1,1}
		},
	};

	//'L'
	static int[][][] three = new int[][][] {
		{
			{0,0},{-1,0},{-2,0},{0,1}
		},
		{
			{0,0},{0,1},{0,2},{1,0}
		},
		{
			{0,-1},{0,0},{1,0},{2,0}
		},
		{
			{0,0},{0,-1},{0,-2},{-1,0}
		},
		{
			{0,0},{0,-1},{-1,0},{-2,0}
		},
		{
			{0,0},{-1,0},{0,1},{0,2}
		},
		{
			{0,0},{0,1},{1,0},{2,0}
		},
		{
			{0,0},{0,-1},{0,-2},{1,0}
		}
	};
	
	//ㄴ
	//ㄱ
	static int[][][] four = new int[][][] {
		{
			{0,0},{-1,0},{0,1},{1,1}
		},
		{
			{0,0},{0,1},{1,0},{1,-1}
		},
		{
			{0,0},{1,0},{0,1},{-1,1}
		},
		{
			{0,0},{0,-1},{1,0},{1,1}
		},
	};
	
	//'ㅜ'
	static int[][][] five = new int[][][] {
		{
			{0,0},{0,-1},{0,1},{1,0}
		},
		{
			{0,0},{0,-1},{-1,0},{1,0}
		},
		{
			{0,0},{0,-1},{0,1},{-1,0}
		},
		{
			{0,0},{-1,0},{0,1},{1,0}
		}
	};
	
	//각 테트로미노를 4차원 배열로 관리한다.
	static int[][][][] tetro = new int[][][][]{one, two, three, four, five};
	
	static int[][] board;
	static int N,M;
	static final int TETRO_SIZE = 5;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for(int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < M; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxResult = Integer.MIN_VALUE;
		
		//현재 좌표, 임시 결과값
		int cr,cc,tr;
		//다돌면서 나간것 판단, 합계 갱신
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < M; ++col) {
				for(int prod = 0; prod < TETRO_SIZE; ++prod) {
					D:for(int dir = 0; dir < tetro[prod].length; ++dir) {
						tr = 0;
						for(int spot = 0; spot < 4; ++spot) {
							cr = row + tetro[prod][dir][spot][0];
							cc = col + tetro[prod][dir][spot][1];
							//나가면 다음 방향확인
							if(isOut(cr,cc))
								continue D;
							//나가지 않았다면 해당 좌표의 값 더해서 합계 갱신
							tr += board[cr][cc];
						}
						maxResult = Math.max(maxResult, tr);
					} //end D:for(dir)
				} //end for(prod) -> 각각 사각형
			}
		}
		
		System.out.println(maxResult);
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=N || col>=M)
			return true;
		return false;
	}
}