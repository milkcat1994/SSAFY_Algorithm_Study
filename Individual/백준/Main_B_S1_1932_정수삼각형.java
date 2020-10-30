import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -정수 삼각형-
 */

//출처 : https://www.acmicpc.net/problem/1932
public class Main_B_S1_1932_정수삼각형 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int total=N*(N+1)/2;
		int[] arr = new int[total];
		int[] res = new int[total];
		int si,ei;
		for(int i=1; i<=N; ++i) {
			si=(i-1)*i/2; ei=i*(i+1)/2;
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=si; j<ei; ++j) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int col = N*(N-1)/2; col<total; ++col)
			res[col] = arr[col];
			
		for(int row=N-1; row>0; --row) {
			si=(row-1)*row/2; ei=si+row;
			for(int col=si; col<ei; ++col) {
				res[col] = Math.max(res[col+row]+arr[col], res[col+row+1]+arr[col]);
			}
		}
		
		System.out.println(res[0]);
	}
}
