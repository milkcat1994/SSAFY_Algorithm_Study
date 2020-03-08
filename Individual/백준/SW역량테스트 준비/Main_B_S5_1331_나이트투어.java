import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -나이트 투어-
 * 1. 한번 방문한 곳을 다시 방문하는지 확인
 * 2. 나이트의 움직임이 맞는지 확인
 * 3. 다시 시작점으로 돌아올 수 있는지 확인
 */

//출처 : https://www.acmicpc.net/problem/1331
public class Main_B_S5_1331_나이트투어 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ts;
		boolean[][] isVisited = new boolean[7][7];
		ts = br.readLine();
		int sr,sc;
		sc = ts.charAt(0)-'A';
		sr = ts.charAt(1)-'1';
		isVisited[sr][sc]=true;
		
		int er=sr,ec=sc,cr=0,cc=0;
		for(int i = 1; i < 36; ++i) {
			ts = br.readLine();
			cc = ts.charAt(0)-'A';
			cr = ts.charAt(1)-'1';
			if(isVisited[cr][cc]) {
				System.out.print("Invalid");
				return;
			}
			isVisited[cr][cc] = true;
			//맞는 이동
			if(Math.abs(er-cr)==2 && Math.abs(ec-cc)==1 || (Math.abs(er-cr)==1 && Math.abs(ec-cc)==2)) {
				er=cr;	ec=cc;
				continue;
			}
			System.out.print("Invalid");
			return;
		}
		
		if(Math.abs(sr-cr)==2 && Math.abs(sc-cc)==1 || (Math.abs(sr-cr)==1 && Math.abs(sc-cc)==2)) {
			System.out.print("Valid");
			return;
		}
		System.out.print("Invalid");
	}
}