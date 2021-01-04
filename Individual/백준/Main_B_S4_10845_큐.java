import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -큐-
 */

//출처 : https://www.acmicpc.net/problem/10845
public class Main_B_S4_10845_큐 {
	static int N;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		init();
		
		solve();
		
		System.out.print(sb.toString());
	}
	
	static void solve() throws Exception {
		int ti, start, end;
		int[] arr = new int[N];
		
		start = 0;
		end = 0;
		
		StringTokenizer st;
		while(--N >= 0) {
			st = new StringTokenizer(br.readLine(), " ");
			switch (st.nextToken()) {
			case "push":
				ti = Integer.parseInt(st.nextToken());
				arr[end++] = ti;
				break;
				
			case "pop":
				if(start == end) {
					sb.append("-1");
				}
				else{
					ti = arr[start++];
					sb.append(ti);
				}
				sb.append('\n');
				break;
				
			case "size":
				sb.append(end-start+"\n");
				break;
				
			case "empty":
				if(start == end)
					sb.append('1');
				else
					sb.append('0');
				sb.append('\n');
				break;
				
			case "front":
				if(start == end)
					sb.append("-1");
				else
					sb.append(arr[start]);
				sb.append('\n');
				break;
				
			case "back":
				if(start == end)
					sb.append("-1");
				else
					sb.append(arr[end-1]);
				sb.append('\n');
				break;
			}
		}
	}
	
	static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
	}
}
