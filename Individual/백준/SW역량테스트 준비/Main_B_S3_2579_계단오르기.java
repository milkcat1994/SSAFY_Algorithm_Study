import java.io.BufferedReader;
import java.io.InputStreamReader;

//출처 : https://www.acmicpc.net/problem/2579
public class Main_B_S3_2579_계단오르기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] point = new int[N+1];
		int[][] res = new int[N+1][3];
		for(int i = 1; i <= N; ++i) {
			point[i] = Integer.parseInt(br.readLine());
		}
		
		res[1][1] = res[1][2] = point[1];
		
		//각 계단마다 1칸으로 올라왔을때 최대값, 2칸으로 올라왔을 때 최대값을 가지고있는다.
		for(int i = 2; i <= N; ++i) {
			//현재칸을 1칸으로 올라올 수 있는경우는 바로 이전 계단을 2칸으로 올라왔을 경우이다.
			res[i][1] = point[i] + res[i-1][2];
			//현재칸을 2칸으로 올라오기 위해 2칸 이전 계단 값에서 최대값으로 올라온다.
			res[i][2] = point[i] + Math.max(res[i-2][1], res[i-2][2]);
		}
		System.out.print(Math.max(res[N][1], res[N][2]));
	}
}