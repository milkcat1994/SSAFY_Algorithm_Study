import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -종구의 딸이름 짓기-
 * 1. 현재 위치들에서 갈 수 있는 곳 중 가장 작은 알파벳을 가지는 곳의 좌표 모두 Queue에 넣어주어야한다.
 * 2. List를 만들어 가장 작은 알파벳 위치만 넣어주었음
 */

/*
 * 메모리 : 109572KB 
 * 시간 : 327ms 
 * 코드길이 : 2053B 
 * 소요시간 : 50M
 */

//150P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWm8hNu6llcDFASj
public class Solution_SWE_7396_종구의딸이름짓기 {
	static int ROW,COL;
	static char[][] board;
	static boolean[][] isVisited;
	static int[] drow = {1,0};
	static int[] dcol = {0,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			ROW = Integer.parseInt(st.nextToken());
			COL = Integer.parseInt(st.nextToken());
			board = new char[ROW][COL];
			isVisited = new boolean[ROW][COL];
			String ts;
			for (int row = 0; row < ROW; ++row) {
				ts = br.readLine();
				for (int col = 0; col < COL; ++col) {
					board[row][col] = ts.charAt(col);
				}
			}
			
			System.out.println("#"+t+" "+bfs());
		}
	}
	
	static String bfs() {
		Queue<int[]> que = new LinkedList<>();
		List<int[]> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		
		int cr,cc, nr,nc, qs;
		char minc, cchar;
		int[] tIntArr;
		
		que.offer(new int[] {0, 0});
		while(!que.isEmpty()) {
			tIntArr = que.peek();
			sb.append(board[tIntArr[0]][tIntArr[1]]);
			qs=que.size();
			minc='z'+1;
			while(--qs>=0) {
				tIntArr = que.poll();
				cr=tIntArr[0];	cc=tIntArr[1];
				if(cr == ROW-1 && cc == COL-1) {
					return sb.toString();
				}
				//현재 위치에서 갈 수 있는 곳에서 가장 작은 알파벳 위치만 담기
				for(int dir = 0; dir < 2; ++dir) {
					nr=cr+drow[dir];	nc=cc+dcol[dir];
					if(isOut(nr,nc) || isVisited[nr][nc]) continue;
					isVisited[nr][nc]=true;
					cchar=board[nr][nc];
					if(minc >= cchar) {
						//새로운 작은 것을 발견했다면 list날려서 다시 저장하기
						if(minc != cchar)
							list.clear();
						list.add(new int[] {nr,nc});
						minc=cchar;
					}
				}
			}
			//작은 알파벳 위치 모두 que에 넣기
			que.addAll(list);
			list.clear();
		}
		
		return sb.toString();
	}
	
	static boolean isOut(int row, int col) {
		if(row>=ROW || col>=COL)
			return true;
		return false;
	}
}
