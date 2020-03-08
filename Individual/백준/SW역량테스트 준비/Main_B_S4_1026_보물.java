import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -보물-
 * 1. 하나는 오름차순, 하나는 내림차순 정렬
 * 2. 각 index끼리 곱, 합.
 */

//출처 : https://www.acmicpc.net/problem/1026
public class Main_B_S4_1026_보물 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		List<Integer> aList = new ArrayList<>();
		List<Integer> bList = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int j=0; j<N; ++j) {
			aList.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine(), " ");
		for(int j=0; j<N; ++j) {
			bList.add(Integer.parseInt(st.nextToken()));
		}
		aList.sort(null);
		bList.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		int sum=0;
		for(int i=0; i<N; ++i) {
			sum+=aList.get(i)*bList.get(i);
		}
		System.out.println(sum);
	}
}
