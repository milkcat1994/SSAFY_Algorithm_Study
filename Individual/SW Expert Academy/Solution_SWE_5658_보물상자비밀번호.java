import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo
public class Solution_SWE_5658_보물상자비밀번호 {
	static int N,K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int size = N/4;
			String str = br.readLine();
			Set<Integer> set = new HashSet<>();
			//3번만 돌려보면 됨.
			for(int j = 0; j < size; ++j) {
				for(int i = 0; i < N; i+=size) {
					int num = 0;
					for(int k = 0; k < size; ++k) {
						int a1 = 0;
						char a = str.charAt((i+j+k)%N);
						if('A' <= a) a1=(a-'A')+10;
						else a1=(a-'0');
						num = num+ a1*(int)(Math.pow(16, size-k-1));
					}
					set.add(num);
				}
			}
			List<Integer> list = new ArrayList<>();
			list.addAll(set);
//			Collections.sort(list, new Comparator<Integer>() {
//				@Override
//				public int compare(Integer o1, Integer o2) {
//					return o2-o1;
//				}
//			});
			Collections.sort(list, Collections.reverseOrder());
			sb.append("#"+t+" "+list.get(K-1)+"\n");
		}
		System.out.print(sb.toString());
	}
}
