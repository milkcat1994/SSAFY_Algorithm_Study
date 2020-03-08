import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -주사위 굴리기-
 */

//출처 : https://www.acmicpc.net/problem/14499
public class Main_B_G5_14499_주사위굴리기 {
	static int[][] dice = new int[4][3];
	static int[][] board;
	static int ROW,COL;

	//우좌상하
	static int[] drow= {0,0,0,-1,1};
	static int[] dcol= {0,1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		int cr = Integer.parseInt(st.nextToken());
		int cc = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());	//명령 수
		Queue<Integer> que = new LinkedList<Integer>();
		
		board = new int[ROW][COL];
		String tempStr;
		for (int row = 0; row < ROW; ++row) {
			tempStr = br.readLine();
			for (int col = 0; col < COL; ++col) {
				board[row][col] = tempStr.charAt(col*2)-'0';
			}
		}
		
		tempStr = br.readLine();
		for(int i = 0; i < K; ++i)
			que.offer(tempStr.charAt(i*2) -'0');
		
		int dir;
		//이동 명령 모두 수행
		while(!que.isEmpty()) {
			dir = que.poll();
			if(isOut(cr+drow[dir], cc+dcol[dir]))
				continue;
			
			cr = cr+drow[dir];
			cc = cc+dcol[dir];
			
			switch (dir) {
				//우측
			case 1:	right();
				break;
				// 좌측
			case 2:	left();
				break;
				//위
			case 3:	up();
				break;
				//아래
			case 4:	down();
				break;
			}
			
			copyValue(cr, cc);
			sb.append(dice[1][1]).append('\n');
		}
		System.out.print(sb.toString());
	} //end main()

	//(3,1) -> down
	public static void copyValue(int cr, int cc) {
		//board 0이라면 주사위 바닥을 board로 복사
		if(board[cr][cc] == 0) {
			board[cr][cc] = dice[3][1];
		}
		//칸의 값이 주사위 바닥으로 복사
		else {
			dice[3][1] = board[cr][cc];
			board[cr][cc] = 0;
		}
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL)
			return true;
		return false;
	}
	
	/*
	 * (1,x) -> u,r,l
	 * (3,1) -> d
	 */
	public static void right() {
		int ti;
		ti = dice[1][0];
		dice[1][0] = dice[3][1];
		dice[3][1] = dice[1][2];
		dice[1][2] = dice[1][1];
		dice[1][1] = ti;
	}
	
	public static void left() {
		int ti;
		ti = dice[1][0];
		dice[1][0] = dice[1][1];
		dice[1][1] = dice[1][2];
		dice[1][2] = dice[3][1];
		dice[3][1] = ti;
	}
	
	/*
	 * (x,1) -> b,u,f,d
	 */
	public static void up() {
		int ti;
		ti = dice[3][1];
		dice[3][1] = dice[0][1];
		dice[0][1] = dice[1][1];
		dice[1][1] = dice[2][1];
		dice[2][1] = ti;
	}
	
	public static void down() {
		int ti;
		ti = dice[3][1];
		dice[3][1] = dice[2][1];
		dice[2][1] = dice[1][1];
		dice[1][1] = dice[0][1];
		dice[0][1] = ti;
	}
}
