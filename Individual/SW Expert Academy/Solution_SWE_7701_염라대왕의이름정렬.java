import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * -염라대왕의 이름 정렬-
 * 1. Set이용한 중복 제거
 * 2. List로 옮기고 정렬하여 출력
 */

/*
 * 메모리 : 151188KB 
 * 시간 : 714ms 
 * 코드길이 : 1102B 
 * 소요시간 : 10M
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWqU0zh6rssDFARG&
public class Solution_SWE_7701_염라대왕의이름정렬 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; ++t) {
			int N=Integer.parseInt(br.readLine().trim());
			Set<String> set = new HashSet<String>();
			for(int n=0; n<N; ++n) {
				set.add(br.readLine().trim());
			}
			
			List<String> list = new ArrayList<>();
			list.addAll(set);
			
			Collections.sort(list, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if(o1.length()==o2.length())
						return o1.compareTo(o2);
					return o1.length()-o2.length();
				}
			});
			sb.append('#').append(t).append('\n');
			for(String str : list)
				sb.append(str).append('\n');
		} //end TestCase
		System.out.print(sb.toString());
	}
}