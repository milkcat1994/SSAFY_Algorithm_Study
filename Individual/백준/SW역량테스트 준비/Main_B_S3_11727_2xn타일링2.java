import java.io.BufferedReader;
import java.io.InputStreamReader;

//출처 : https://www.acmicpc.net/problem/11727
public class Main_B_S3_11727_2xn타일링2 {
	static final int MOD = 10007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//짝수 홀수 나눠서 계산
		int div = (n+1)/2;
		int flag = 0;
		int res;
		if(n%2 == 0) {
			flag = -1;
			res = 3;
		}
		else {
			flag = 1;
			res = 1;
		}

		for(int i = 1; i < div; ++i) {
			res = (res *4 +flag) % MOD;
		}
		System.out.println(res);
	}
}
