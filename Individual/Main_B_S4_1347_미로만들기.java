import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * -미로 만들기-
 * 1. 큰 배열내에서 움직인 최소,최대 상하좌우 위치 기억.
 * 2. 해당 사각형 배열 출력
 */

//출처 : https://www.acmicpc.net/problem/1347
public class Main_B_S4_1347_미로만들기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String ts = br.readLine();
		
		char[][] board = new char[101][101];
		for(int i=0; i<101; ++i)
			Arrays.fill(board[i], '#');
		
		int cr=50,cc=50,dir=0;
		//남서북동
		int[] drow = {1,0,-1,0};
		int[] dcol= {0,-1,0,1};
		int ur=cr,dr=cr,lc=cc,rc=cc;
		board[cr][cc]='.';
		
		for(int i = 0; i < N; ++i) {
			switch (ts.charAt(i)) {
			case 'R':
				dir=(dir+4+1)%4;
				break;
			case 'L':
				dir=(dir+4-1)%4;
				break;
			case 'F':
			default:
				cr+=drow[dir];	cc+=dcol[dir];
				board[cr][cc]='.';
				if(cr>ur) ur=cr;
				else if(cr<dr) dr=cr;
				else if(cc>rc) rc=cc;
				else if(cc<lc) lc=cc;
				break;
			}
		}
		
		for (int row = dr; row <= ur; ++row) {
			for (int col = lc; col <= rc; ++col) {
				sb.append(board[row][col]);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}
