import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -ATM-
 */

//출처 : https://www.acmicpc.net/problem/11399
public class Main_B_S3_11399_ATM {
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		init();
		
		Arrays.sort(arr);
		
		System.out.println(find());
	}
	
	static int find() {
		int res=0, n=N;
		
		for(int i=0; i<N; ++i) {
			res += arr[i] * n;
			n--;
		}
		return res;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; ++i)
			arr[i] = Integer.parseInt(st.nextToken());
	}
}
