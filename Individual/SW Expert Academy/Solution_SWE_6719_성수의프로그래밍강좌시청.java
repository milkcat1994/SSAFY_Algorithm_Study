import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * -성수의 프로그래밍 강좌 시청-
 * 1. (A+M)/2 의 식을
 * (A + (2^0)*M1 + (2^1)*M2 .... (2^k-1)*Mk ) / 2^k
 * 로 두고
 * 가장 큰값을 우측에 넣어간다.
 * 10^-6 까지 오차 허용이므로 중간 값이 10^-7 *5 보다 작다면 그 이하 값들의 합은 10^-6이 될 수 없으므로
 * 확인 하지 않는다.
 */

/*
 * 메모리 : 54200KB 
 * 시간 : 207ms 
 * 코드길이 : 855B 
 * 소요시간 : 20M
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWd7sgDatsMDFAUh
public class Solution_SWE_6719_성수의프로그래밍강좌시청 {
	static final double LIMIT = 0.0000005;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			List<Integer> list = new LinkedList<>();
			for(int i = 0; i < N; ++i) {
				list.add(sc.nextInt());
			}
			
			Collections.sort(list, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2-o1;
				}
			});;
			
			double res = 0;
			long div = 2;
			double ci;
			for(int k = 0; k < K; ++k) {
				ci = list.get(k);
				ci/=div;
				if(ci <= LIMIT)
					break;
				res += ci;
				div*=2;
			}
			System.out.printf("#%d %.6f\n",t,res);
		}
	}
}