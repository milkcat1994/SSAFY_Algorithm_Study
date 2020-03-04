import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -햄스터-
 * 1. 시간 복잡도를 볼때 11^6*10 정도로 무난하다.
 * 2. 완전탐색 진행
 * 3. 앞에서 부터 작은 값으로 채워 나가며
 * 4. 총 햄스터의 수가 많을 때만 결과값을 갱신해나가는 식으로 진행
 */

/*
 * 메모리 : 33884KB 
 * 시간 : 652ms 
 * 코드길이 : 2216B 
 * 소요시간 : -
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWxQ310aOlQDFAWL
public class Solution_SWE_8275_햄스터 {
	//N, ln, M(1 ≤ N ≤ 6, 1 ≤ ln, M ≤ 10)
	//우리갯수, 우리당최고마릿수, 기록수
	static int N,ln, M;
	static int[] cage;
	
	public static class Condition {
		int si,ei,n;
		public Condition(int si, int ei, int n) {
			this.si = si;
			this.ei = ei;
			this.n = n;
		}
	}
	static List<Condition> list;
	static int maxRes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		// si, ei, n(1 ≤ si ≤ ei ≤ N, 0 ≤ n ≤ 60)
		//시작우리,종료우리, 총마릿수
		int si,ei,n;
		for(int t=1; t<=T; ++t) {
			maxRes = -1;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			ln = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			cage = new int[N+1];
			list = new ArrayList<>();
			for(int m = 0; m < M; ++m) {
				st = new StringTokenizer(br.readLine(), " ");
				si = Integer.parseInt(st.nextToken());
				ei = Integer.parseInt(st.nextToken());
				n = Integer.parseInt(st.nextToken());
				list.add(new Condition(si, ei, n));
			}
			permu(1, 0);
			
			sb.append('#').append(t).append(' ');
			if(maxRes != -1) {
				sb.append(ans);
			}
			else
				sb.append(maxRes);
			sb.append('\n');
		} //end TestCase
		System.out.print(sb.toString());
	}
	
	static String ans;
	public static void permu(int index, int sum) {
		//끝까지 다 돌았다면
		if(index == N+1) {
			if(isRight()) {
				if(maxRes < sum) {
					maxRes = sum;
					StringBuilder sb = new StringBuilder();
					for(int i = 1; i <= N; ++i) {
						sb.append(cage[i]).append(' ');
					}
					ans = sb.toString();
				}
			}
			return;
		}
		
		for(int n=0; n<=ln; ++n) {
			//넣고 다음 확인
			cage[index]=n;
			permu(index+1, sum+n);
		}
	}
	
	//기록된 수만큼 햄스터가 들어있는지
	public static boolean isRight() {
		for(Condition c : list) {
			int tmp = 0;
			for(int i = c.si; i<=c.ei; ++i) {
				tmp+=cage[i];
			}
			if(tmp != c.n)
				return false;
		}
		return true;
	}
}
