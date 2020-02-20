import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -1,2,3더하기-
 * 1. 순열생성 알고리즘과 비슷하게 작성하였다.
 * 2. 각 자리에서 1,2,3을 선택하고 총 합계가 sum이 된다면 가짓수+1을 해준다.
 */

//출처 : https://www.acmicpc.net/problem/9095
public class Main_B_S3_9095_일이삼더하기 {
	static int N, result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; ++t) {
			N = Integer.parseInt(br.readLine());
			result = 0;
			
			solution(0);
			sb.append(result).append('\n');
		}
		System.out.print(sb.toString());
	}
	
	public static void solution(int sum) {
		if(sum == N) {
			result++;
			return;
		}
		else if(sum > N) {
			return;
		}
		
		for(int i = 1; i < 4; ++i) {
			solution(sum+i);
		}
	}
}
