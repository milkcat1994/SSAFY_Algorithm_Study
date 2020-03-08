import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -가스관-
 * 1. M에서 가스관을 따라가며 끊어진 부분을 찾는다.
 * 2. 해당 좌표에서 주변으로 뚫린 방향을 찾는다.
 * 3. 이후 필요한 가스관 모양 도출한다.
 * 4. canGo : 현재 방향성으로 해당 좌표 들어갈 수 있는지 판단하는 함수
 * 5. find : 주변 좌표를 확인하여 필요한 가스관 모양 도출하는 함수
 */

//출처 : https://www.acmicpc.net/problem/2931
public class Main_B_G3_2931_가스관 {
	static int ROW,COL;
	static int sr,sc, er,ec;
	static int[] drow={-1,1,0,0};
	static int[] dcol={0,0,-1,1};
	static char[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW=Integer.parseInt(st.nextToken());
		COL=Integer.parseInt(st.nextToken());
		board=new char[ROW+1][COL+1];
		
		String str;
		for (int row = 1; row <= ROW; ++row) {
			str = br.readLine();
			for (int col = 1; col <= COL; ++col) {
				board[row][col] = str.charAt(col-1);
				switch (board[row][col]) {
				case 'M':
					sr=row;	sc=col;
					break;
				case 'Z':
					er=row;	ec=col;
					break;
				}
			}
		}
		int cr=sr,cc=sc,cd=0, nr,nc;
		boolean isFind=false;
		//'M'주위 확인해서 이동 가능한 파이프 확인하기
		for(int dir = 0; dir < 4; ++dir) {
			nr=cr+drow[dir];	nc=cc+dcol[dir];
			if(isOut(nr, nc) || (cd=canGo(board[nr][nc], dir)) == -1) continue;
			//갈수 있다면 해당 방향 기억하고 중단
			cr=nr;	cc=nc;
			isFind=true;
			break;
		}
		
		//주변에 바로 끊어졌음.
		if(!isFind) {
			//4방향모두 4방향 확인하기
			for(int dir = 0; dir < 4; ++dir) {
				nr=cr+drow[dir];	nc=cc+dcol[dir];
				char resC = find(nr,nc);
				//찾지 못했다면 다음 방향 확인
				if(resC == '.') continue;
				
				//찾았다면 해당 문자와 위치 출력
				System.out.print(nr+" "+nc+" "+resC);
				return;
			}
		}
		
		//끊어진 땅으로 도착하면 멈춘다.
		while(board[cr][cc]!='.') {
			//해당 방향 이동 시키기
			nr=cr+drow[cd];		nc=cc+dcol[cd];
			cd=canGo(board[nr][nc], cd);
			cr=nr;	cc=nc;
		}
		
		//해당 좌표에서 상하좌우 살피고 들어갈 수 있는 방향, 갯수 파악하고 값 도출
		System.out.print(cr+" "+cc+" "+find(cr,cc));
	}
	
	//현재 좌표에서 상하좌우 살피기
	static char find(int r, int c) {
		int cr=r,cc=c, nr,nc;
		int dirBit=0;
		for(int dir = 0; dir < 4; ++dir) {
			nr=cr+drow[dir];	nc=cc+dcol[dir];
			if(isOut(nr, nc) || canGo(board[nr][nc], dir) == -1) continue;
			//들어 갈 수 있는 방향 기억.
			dirBit |= (1<<(3-dir));
		}
		
		switch (dirBit) {
		case 0b1111:
			return '+';
		case 0b1100:
			return '|';
		case 0b0011:
			return '-';
		case 0b0101:
			return '1';
		case 0b1001:
			return '2';
		case 0b1010:
			return '3';
		case 0b0110:
			return '4';
		default :
			return '.';
		}
	}
	
	//0,1,2,3 -> 상하좌우
	//이동 가능하면 이동 후 다음 이동 방향 뱉기
	static int canGo(int c, int dir) {
//		'|'(아스키 124), '-','+','1','2','3','4'
		switch (c) {
			// │
		case '|':
			//이동 방향 그대로 이동
			if(dir==0 || dir==1) return dir;
			return -1;
			//─
		case '-':
			if(dir==2 || dir==3) return dir;
			return -1;
			//┼
		case '+':
			//모든 방향 통과 가능 -> 이전 방향 그대로 가야됨
			return dir;
			//┌
		case '1':
			if(dir==0) return 3;
			if(dir==2) return 1;
			return -1;
			//└
		case '2':
			if(dir==1) return 3;
			if(dir==2) return 0;
			return -1;
			//┘
		case '3':
			if(dir==1) return 2;
			if(dir==3) return 0;
			return -1;
			//┐
		case '4':
			if(dir==0) return 2;
			if(dir==3) return 1;
			return -1;
		default:
			return -1;
		}
	}
	
	static boolean isOut(int r, int c) {
		if(r<1 || c<1 || r>ROW || c>COL)
			return true;
		return false;
	}
}
