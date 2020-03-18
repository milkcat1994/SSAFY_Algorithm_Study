import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -영역구하기-
 * 1. 직사각형의 자리는 true로 바꾸기
 * 2. false인 부분 BFS로 돌며 영역 넓이, 개수 확인하기
 */

//출처 : https://www.acmicpc.net/problem/2283
public class Main_B_S1_2283_영역구하기 {
	static boolean[][] board;
	static int ROW,COL;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		board = new boolean[ROW][COL];
		int ldr,ldc, rur,ruc;
		for (int k = 0; k < K; ++k) {
			st = new StringTokenizer(br.readLine(), " ");
			ldc = Integer.parseInt(st.nextToken());
			ldr = Integer.parseInt(st.nextToken());
			ruc = Integer.parseInt(st.nextToken());
			rur = Integer.parseInt(st.nextToken());
			quard(ldr, ldc, rur, ruc);
		}
		
		Queue<int[]> que = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		int cr,cc,nr,nc, count=0,area=0;
		int[] tIntArr;
		for (int r = 0; r < ROW; ++r) {
			for (int c = 0; c < COL; ++c) {
				if(!board[r][c]) {
					count++;
					que.offer(new int[] {r,c});
					board[r][c]=true;
					area=1;
					while(!que.isEmpty()) {
						tIntArr = que.poll();
						cr=tIntArr[0];	cc=tIntArr[1];
						for(int dir = 0; dir < 4; ++dir) {
							nr=cr+drow[dir];	nc=cc+dcol[dir];
							if(isOut(nr,nc) || board[nr][nc]) continue;
							que.offer(new int[] {nr,nc});
							board[nr][nc]=true;
							area++;
						}
					}
					list.add(area);
				}
			}
		}
		
		list.sort(null);
		System.out.println(count);
		for(Integer ti : list)
			System.out.print(ti+" ");
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=ROW || c>=COL)
			return true;
		return false;
	}
	
	//해당 사각형자리 true처리
	static void quard(int ldr, int ldc, int rur, int ruc) {
		for (int row = ldr; row < rur; ++row) {
			for (int col = ldc; col < ruc; ++col) {
				board[row][col] = true;
			}
		}
	}
}