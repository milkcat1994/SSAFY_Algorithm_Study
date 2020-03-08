import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -퇴사-
 * 1. 조합 이용하여 상담 선택
 * 2. 상담에 걸리는 시간을 현재 선택하고자 하는 상담 index에 접목
 */

//출처 : https://www.acmicpc.net/problem/14501
public class Main_B_S4_14501_퇴사 {
	static int N;
	static Info[] infoArr;
	static int maxResult = Integer.MIN_VALUE;
	
	public static class Info{
		int time, price;
		//상담 걸리는 시간, 가격
		Info(int time, int price){
			this.time = time;
			this.price = price;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		infoArr = new Info[N];
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			infoArr[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		findMax(0, 0);
		System.out.println(maxResult);
	}
	
	public static void findMax(int index, int price) {
		// 상담일정이 퇴사 일자 넘어간다면 여긴 더 볼 필요 없음.
		if(index > N)
			return;
		
		if(maxResult < price)
			maxResult = price;
		
		for(int i = index; i < N; ++i) {
			findMax(i+infoArr[i].time, price + infoArr[i].price);
		}
	}
}
