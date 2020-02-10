import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -상호의 배틀필드-
 * 1. 입력에 따라 2차원배열에 char형으로 지도를 그려준다.
 * 2. 사용자의 명령은 Queue에 담아 순서대로 실행할 수 있도록한다.
 * 3. 이동 명령시 방향은 무조건 바뀌며, 이동시 자신의 자리를 평지로 초기화한다.
 * 4. 포탄 발사시 밖으로 나가거나 강철 벽이면 중단한다.
 * 	  └벽돌벽이라면 해당자리 평지로 전환
 */

//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc
public class Solution_1873 {
	//사용자 명령 input담을 Queue
	static Queue<Character> inputque = new LinkedList<Character>();
	
	static char[][] board;
	static int ROW, COL;
	
	//상하좌우
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static Player p;
	
	static int nr,nc;
	public static class Player{
		int row,col,dir;
		Player(int row, int col, int dir){
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
		
		//방향 전환은 무조건 실행
		void move(int dir) {
			this.dir = dir;
			nr = this.row + drow[dir];
			nc = this.col + dcol[dir];
			//이동 할 수 있다면
			if(!isOut(nr,nc) && board[nr][nc] == '.') {
				//자신의 자리 평지로 초기화
				board[this.row][this.col]= '.'; 
				this.row = nr;
				this.col = nc;
				
			}
			//이동 불가면 현재자리 nr,nc는 그자리 그대로.
			else {
				nr = this.row;
				nc = this.col;
			}
			
			//현재 방향 따라 해당 위치에 플레이어 표시
			switch (this.dir) {
			case 0:
				board[nr][nc] = '^';
				break;
			case 1:
				board[nr][nc] = 'v';
				break;
			case 2:
				board[nr][nc] = '<';
				break;
			case 3:
				board[nr][nc] = '>';
				break;
			}
		} //end move()
	}
	
	//범위 밖으로 벗어났는지
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL)
			return true;
		return false;
	}
	
	//포탄 발사
	public static void shoot(int row, int col, int dir) {
		nr = row += drow[dir];
		nc = col += dcol[dir];
		
		// 밖으로 나간다면 false, 강철벽이면 false
		// 벽돌 벽이면 해당 칸 평지로 전환
		while(!(isOut(nr,nc) || board[nr][nc] == '#')) {
			if(board[nr][nc] == '*') {
				board[nr][nc] = '.';
				break;
			}
			nr = row += drow[dir];
			nc = col += dcol[dir];
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		int N;
		String tempString;
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			ROW = Integer.parseInt(st.nextToken());
			COL = Integer.parseInt(st.nextToken());
			
			board = new char[ROW][COL];
			
			/*
			 * . 평지
			 * * 벽돌벽_포탄에 깨짐
			 * # 강철벽_포탄 반응없음
			 * - 물
			 * ^	위쪽을 바라보는 전차(아래는 평지이다.)
			 * v	아래쪽을 바라보는 전차(아래는 평지이다.)
			 * <	왼쪽을 바라보는 전차(아래는 평지이다.)
			 * >	오른쪽을 바라보는 전차(아래는 평지이다.)
			 */
			for (int row = 0; row < ROW; ++row) {
				tempString = br.readLine();
				for (int col = 0; col < COL; ++col) {
					board[row][col] = tempString.charAt(col);
					switch(board[row][col]) {
					//평지, 지나갈수있음.
					case '^' :
						p = new Player(row,col,0);
						break;
					case 'v' :
						p = new Player(row,col,1);
						break;
					case '<' :
						p = new Player(row,col,2);
						break;
					case '>' :
						p = new Player(row,col,3);
						break;
						}
				}
			} //end for(row)
			
			//사용자가 넣을 입력의 개수
			N = Integer.parseInt(br.readLine());
			tempString = br.readLine();
			for(int i = 0; i < N; ++i) {
				inputque.offer(tempString.charAt(i));
			}
			
			//명령 하나씩 뺴기
			while(!inputque.isEmpty()) {
				switch (inputque.poll()) {
				//바라보는 방향 위로 전환, 위쪽 평지면 위로이동
				case 'U':
					p.move(0);
					
					break;
				case 'D':
					p.move(1);
					
					break;
				case 'L':
					p.move(2);
					
					break;
				case 'R':
					p.move(3);
					
					break;
					//현재 바라보고 있는 방향으로 포탄 발사.
				case 'S':
					shoot(p.row,p.col,p.dir);
					break;

				default:
					break;
				}
			} //end while(!inputque.isEmpty())
			
			sb.append("#"+t+" ");
			for (int row = 0; row < ROW; ++row) {
				for (int col = 0; col < COL; ++col) {
					sb.append(board[row][col]);
				}
				sb.append("\n");
			}
			
			
		} //end TestCase
		
		System.out.println(sb.toString());
	}
}