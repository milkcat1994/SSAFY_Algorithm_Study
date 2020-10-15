import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -회전초밥-
 * 1. 완탐
 */

//출처 : https://www.acmicpc.net/problem/2531
public class Main_B_S1_2531_회전초밥 {
	static int N,d,k,c;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 접시의 수, 가짓수, 연속해서 먹는 접시의 수, 쿠폰번호
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i <N; ++i)
			list.add(Integer.parseInt(br.readLine()));
		
		Set<Integer> set = new HashSet<>();
		
		int idx, maxResult = 0, tr, target;
		boolean isC = false;
		for(int i = 0; i < N; ++i) {
			for(int tidx = i; tidx < i+k; ++tidx) {
				idx = tidx % N;
				//Set에 집어넣기
				target = list.get(idx);
				set.add(target);
				if(target == c)
					isC = true;
			}
			//set 개수 최대값 갱신 -> c가 있었느냐 없었느냐에 따른 추가적 처리 필요
			tr = set.size();
			if(!isC)
				tr++;
			
			maxResult = maxResult > tr ? maxResult : tr;
			isC = false;
			set.clear();
		}
		System.out.println(maxResult);
	}
}
