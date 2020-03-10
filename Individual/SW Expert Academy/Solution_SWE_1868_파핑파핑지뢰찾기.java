import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * -파핑파핑 지뢰찾기-
 * 크기가 크지 않아서 쉽게 3번 완전 탐색 하였다.
 * 입력받을 때 지뢰라면 8방향에 +1을 해준다.
 * 1. 0인 값에 대해 8방향 탐색하며 클릭++
 * 2. 0이 아니고 2번에서 확인 되지 않은 숫자 개수 세어 클릭++
 */

/*
 * 메모리 : 49724KB 
 * 시간 : 223ms 
 * 코드길이 : 1858B 
 * 소요시간 : 25M
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LwsHaD1MDFAXc
public class Solution_SWE_1868_파핑파핑지뢰찾기 {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] tia, drow={-1,1,0,0, -1,-1,1,1}, dcol={0,0,-1,1, -1,1,-1,1};
		for(int t=1; t<=T; ++t) {
			N = Integer.parseInt(br.readLine());
			char[][] board = new char[N][N];
			int[][] sum = new int[N][N];
			boolean[][] isVisited = new boolean[N][N];
			int cr,cc, nr,nc, res=0;
			
			String str;
			char tc;
			for (int r = 0; r < N; ++r) {
				str=br.readLine();
				for (int c = 0; c < N; ++c) {
					tc = str.charAt(c);
					board[r][c] = tc;
					//지뢰 일경우 8방향 +1더해주기
					if(tc=='*') {
						for(int d=0; d<8; ++d) {
							nr=r+drow[d];	nc=c+dcol[d];
							if(isOut(nr, nc)) continue;
							sum[nr][nc]++;
						}
					}
				}
			}
			
			Queue<int[]> que = new LinkedList<>();
			//지뢰 갯수 0인것에서 8방향 BFS탐색하기 -> 한번의 클릭으로 밝혀진다.
			for (int r = 0; r < N; ++r) {
				for (int c = 0; c < N; ++c) {
					if(board[r][c] =='*' || isVisited[r][c] || sum[r][c]!=0) continue;
					res++;
					que.offer(new int[] {r,c});
					while(!que.isEmpty()) {
						tia=que.poll();
						cr=tia[0];	cc=tia[1];
						for(int d=0; d<8; ++d) {
							nr=cr+drow[d];	nc=cc+dcol[d];
							if(isOut(nr, nc) || isVisited[nr][nc] || board[nr][nc]=='*') continue;
							isVisited[nr][nc]=true;
							if(sum[nr][nc]==0)
								que.offer(new int[] {nr,nc});
						}
					}
				}
			}
			
			//0이 아닌, 위에서 확인 되지 않은 공간 갯수 세기
			for (int r = 0; r < N; ++r) {
				for (int c = 0; c < N; ++c) {
					if(sum[r][c]!=0 && board[r][c]!='*' && !isVisited[r][c]) {
						res++;
					}
				}
			}
			
			System.out.println("#"+t+" "+res);
		} //end TestCase
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
}