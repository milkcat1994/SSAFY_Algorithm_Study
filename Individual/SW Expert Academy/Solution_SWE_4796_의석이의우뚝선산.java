import java.util.Scanner;

/*
 * -의석이의 우뚝 선 산-
 * 1. 높이가 높아지는 중이라면 왼쪽값 +1
 * 2. 높이가 낮아지는 중이라면 오른쪽값 +1
 * 3. 높이가 높아졌으나 오른쪽 값이 존재한다면 (왼쪽*오른쪽) 각 값을 곱하여 경우의 수를 더해준다.
 * 4. 그리고 l,r은 초기화
 * 5. 마지막으로 지금 까지 저장된 값으로 경우의 수를 구하여 결과에 합하기.
 */

/*
 * 메모리 : 113424KB 
 * 시간 : 629ms 
 * 코드길이 : 568B 
 * 소요시간 : 10M
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWS2h6AKBCoDFAVT
public class Solution_SWE_4796_의석이의우뚝선산 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; ++t) {
			int res=0;
			int N = sc.nextInt();
			int exh = sc.nextInt(), ch;
			//왼쪽, 오른쪽 (선 산) 이 될 수있는 값들.
			int l=0,r=0;
			for(int n = 1; n < N; ++n) {
				ch = sc.nextInt();
				//현재 높이가 높아진다면 r이 0인지 확인
				// 0이라면 l을 하나 증가
				if(exh < ch) {
					if(r==0)
						l++;
					//0이 아니라면 l,r의 경우의 수 를 곱해 res에 더해준다.
					//그리고 l,r은 초기화
					else {
						res+=l*r;
						l=1; r=0;
					}
				}
				//현재 높이가 이전 높이보다 낮다면 r++;
				else {
					r++;
				}
				exh=ch;
			}
			res+=l*r;
			System.out.println("#"+t+" "+ res);
		} //end TestCase
		sc.close();
	}
}
