import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -N번째 큰수-
 * 다 넣지 않고 배열에 저장하고 마지막 행만 pq에 넣는다.
 * 가장 최대인 수를 poll하면, 해당 열의 이전 행의 값을 넣으면 열의 항상 최대인 값만 pq에 들어가게 된다.
 */

//출처 : https://www.acmicpc.net/problem/2075
public class Main_B_G4_2075_N번째큰수 {
	static int N;
	static List<Integer> list;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(list.get(N-1));
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				list.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		
	}
}
