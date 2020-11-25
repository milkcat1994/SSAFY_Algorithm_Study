import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * -문자열 지옥에 빠진 호석-
 * 미리 확인해야하는 문자열은 HashMap에 담아둔다.
 * 방문 여부를 확인하지 않고 8방향 dfs를 하며,
 * 한번 방문마다 해당 문자열이 HashMap에 있는지 확인하며, 경우의 수를 더해준다.
 * 
 * 추가적으로 확인해야하는 문자열의 최대 크기를 구하여 되도록 최소한 실행 하도록 하였다.
 */

//출처 : https://www.acmicpc.net/problem/20166
public class Main_B_G5_20166_문자열지옥에빠진호석 {
	static int N,M,K;
	static char[][] board;
	static int[] drow = {-1,-1,-1,0,0,1,1,1};
	static int[] dcol = {-1,0,1,-1,1,-1,0,1};
	
	static Map<String, Integer> hm;
	static int maxSize;
	static StringBuilder sb;
	static String tempStr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		hm = new HashMap<String, Integer>();
		
		String str;
		for (int row = 0; row < N; ++row) {
			str = br.readLine();
			for (int col = 0; col < M; ++col) {
				board[row][col] = str.charAt(col);
			}
		}
		
		int s;
		List<String> list = new ArrayList<>();
		for(int i=0; i<K; ++i) {
			str = br.readLine();
			list.add(str);
			hm.put(str, 0);
			s = str.length();
			if(s > maxSize)
				maxSize = s;
		}
		
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < M; ++col) {
				dfs(row,col,1);
			}
		}
		
		for(String ts : list) {
			sb.append(hm.get(ts)).append('\n');
		}
		System.out.print(sb.toString());
	}
	
	static void dfs(int r, int c, int len) {
		sb.append(board[r][c]);
		tempStr = sb.toString();
		if(hm.containsKey(tempStr)) {
			hm.replace(tempStr, hm.get(tempStr)+1);
		}
		if(len >= maxSize) {
			sb.setLength(sb.length()-1);
			return;
		}
		int nr,nc;
		for(int d=0; d<8; ++d) {
			nr =(r+drow[d]+N)%N; nc=(c+dcol[d]+M)%M;
			dfs(nr, nc, len+1);
		}
		
		sb.setLength(sb.length()-1);
	}
}
