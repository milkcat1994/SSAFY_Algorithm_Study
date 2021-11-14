import java.util.Arrays;

//출처 : https://programmers.co.kr/learn/courses/30/lessons/12907
public class Solution_P_L3_12907_거스름돈 {
	int MOD = 1000000007;
	public int solution(int n, int[] money) {
		int moneyLength = money.length;
		int[] memo = new int[n+1];
		Arrays.sort(money);
		
		for(int i=0; i<moneyLength; ++i) {
			for(int j=1; j<=n; ++j) {
				if(j == money[i] || money[i] == 1) {
					memo[j]++;
				}
				else if(j > money[i]){
					memo[j] += (memo[j-money[i]]) % MOD;
				}
			}
		}
		
		return memo[n];
	}
	
	public static void main(String[] args) {
		Solution_P_L3_12907_거스름돈 sol = new Solution_P_L3_12907_거스름돈();
		int n = 5;
		int[] money = {1,2,5};
		int answer = sol.solution(n, money);
		System.out.println(answer);
	}
}