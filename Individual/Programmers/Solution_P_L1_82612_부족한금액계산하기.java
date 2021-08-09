/*
 * -부족한 금액 계산하기-
 * 
 * 
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/82612
public class Solution_P_L1_82612_부족한금액계산하기 {
	
	public long solution(int price, int money, int count) {
		return Math.max((long)count*(count+1)*price/2 - money, 0L);
	}

	public static void main(String[] args) {
		Solution_P_L1_82612_부족한금액계산하기 sol = new Solution_P_L1_82612_부족한금액계산하기();
		int price = 3;
		int money = 20;
		int count = 4;
		long answer = sol.solution(price, money, count);
		System.out.println(answer);
	}
}