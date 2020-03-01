import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -Z-
 * 1.분할 정복 이용한 위치 찾기
 */

//출처 : https://www.acmicpc.net/problem/1074
public class Main_B_S1_1074_Z {
	static int N,r,c,res=0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		divide(0, 0, (int)Math.pow(2, N-1));
		
		System.out.println(res);
	}
	
	public static void divide(int row, int col, int dist) {
		if(row == r && col == c) return;
		if(row<=r && r<row+dist) {
			//1
			if (col<=c && c<col+dist) {
				divide(row, col, dist/2);
			}
			//2
			else {
				res += dist*dist;
				divide(row, col+dist, dist/2);
			}
		}
		else {
			//3
			if (col<=c && c<col+dist) {
				res += dist*dist*2;
				divide(row+dist, col, dist/2);
			}
			//4
			else {
				res += dist*dist*3;
				divide(row+dist, col+dist, dist/2);
			}
		}
	}
}
