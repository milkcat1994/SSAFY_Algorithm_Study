import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -가장 긴 증가하는 부분 수열 2-
 * 1. LIS 길이 구하는 공식 이용하기
 * 2. 작은 숫자 들어갈 자리 찾는 방법을 이분탐색으로 수정
 * 3. 이분탐색 bfs가 아닌 while문 이용하면 더 빠르게 될 것으로 예상
 */

//출처 : https://www.acmicpc.net/problem/12015
public class Main_B_G2_12015_가장긴증가하는부분수열2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		List<Integer> list = new ArrayList<>();
		
		int cur=0;
		while(st.hasMoreTokens()) {
			cur = Integer.parseInt(st.nextToken());
			if(list.isEmpty() || list.get(list.size()-1) < cur) {
				list.add(cur);
			}
			else {
				list.set(findIdx(0, list.size()-1, list, cur), cur);
			}
		}
		System.out.print(list.size());
	}
	
	public static int findIdx(int s, int e, List<Integer> list, int target) {
		if(s==e)
			return s;
		
		if(list.get((s+e)/2) < target) {
			return findIdx((s+e)/2+1, e, list, target);
		}
		else {
			return findIdx(s, (s+e)/2, list, target);
		}
	}
}
