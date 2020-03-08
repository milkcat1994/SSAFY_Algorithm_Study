import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -사칙연산 유효성 검사-
 * 1. 한줄씩 입력을 받아 2,3,4개의 token에 따라 다르게 처리한다.
 * 2. flag==true라면 나머지 입력을 모두 받는다.
 * 3. 2개 : 숫자이어야한다. 문자가 들어온다면 실패로 간주
 * 4. 3개 : 자식이 1개라면 수식이 될 수 없어 실패로 간주
 * 5. 4개 : 연산자이어야한다. 숫자가 들어온다면 실패로 간주
 */

//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV141176AIwCFAYD
public class Solution_D4_1233_사칙연산유효성검사 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		int Length;
		String[] tempStringArr;
		boolean flag;
		for(int t = 1; t <= T; ++t) {
			flag = false;
			Length = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < Length; ++i) {
				tempStringArr = br.readLine().split(" ");
				if(flag)
					continue;
				if(tempStringArr.length == 2) {
					if(tempStringArr[1].equals("+") ||tempStringArr[1].equals("-") ||
							tempStringArr[1].equals("*") ||tempStringArr[1].equals("/")) {
						flag = true;
						System.out.println("#" +t+" "+0);
						continue;
					}
				}
				else if(tempStringArr.length == 3) {
					System.out.println("#" +t+" "+0);
					flag = true;
					continue;
				}
				else if(tempStringArr.length == 4) {
					if(tempStringArr[1].equals("+") ||tempStringArr[1].equals("-") ||
							tempStringArr[1].equals("*") ||tempStringArr[1].equals("/")) {
						continue;
					}
					else {
						System.out.println("#" +t+" "+0);
						flag = true;
						continue;
					}
				}
			} //end for(i)
			//사칙연산이 가능하다면 1출력
			if(!flag)
				System.out.println("#" +t+" "+1);
		} //end TestCase;
	}
}
