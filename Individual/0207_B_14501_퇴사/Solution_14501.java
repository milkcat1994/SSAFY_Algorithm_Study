import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -퇴사-
 * 순열을 이용하여 각 상담 일을 뽑아나간다.
 * 그러나 상담을 완료하는데 걸리는 시간이 있으므로 이를 한계점으로 두었다.
 * 현재 뽑고자 하는 상담이 한계점보다 낮은 위치라면 해당 상담은 진행하지 못한다.
 */

//출처 : https://www.acmicpc.net/problem/14501
public class Solution_14501 {
	static int N;
	static Info[] infoArr;
	
	static int maxResult = Integer.MIN_VALUE;
	
	public static class Info{
		int time, price;
		
		/**
		 * @param time		상담하는데 걸리는 시간
		 * @param price		상담가격
		 */
		Info(int time, int price){
			this.time = time;
			this.price = price;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		infoArr = new Info[N+1];
		
		//정보 받기
		for(int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			infoArr[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// 1번째 뽑고, 안뽑고
		findMax(1, infoArr[1].time, infoArr[1].price);
		findMax(1, 1, 0);
		
		System.out.println(maxResult);
	}
	
	/**
	 * @param index		지금 확인하는 위치
	 * @param limit		다른 상담으로 인해 상담을 하지 못하는 끝 구간
	 * @param result	고른 상담의 가격
	 */
	public static void findMax(int index, int limit, int result) {
		// 상담일정이 퇴사 일자 넘어간다면 여긴 더 볼 필요 없음.
		if(limit > N) {
			return;
		}
		
		// 현재 index가 limit보다 작다면 현재것은 뽑지 못하고 넘겨야한다.
		if(index < limit && index != -1) {
			findMax(index+1, limit, result);
			return;
		}
		
		// 다돌았을 경우
		if(index >= N) {
			maxResult = maxResult > result ? maxResult : result;
			return;
		}
		
		// index로 들어온 걸 상담 해봤다가 하지 않았다가 할것임
		findMax(index+1, limit+infoArr[index+1].time, result + infoArr[index+1].price);
		findMax(index+1, index+1, result);
	}
}
