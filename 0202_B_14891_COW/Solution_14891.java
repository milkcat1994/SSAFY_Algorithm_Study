import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * -입력-
 * 첫째 줄에 1번 톱니바퀴의 상태, 둘째 줄에 2번 톱니바퀴의 상태, 셋째 줄에 3번 톱니바퀴의 상태, 넷째 줄에 4번 톱니바퀴의 상태가 주어진다.
 * 상태는 8개의 정수로 이루어져 있고, 12시방향부터 시계방향 순서대로 주어진다. N극은 0, S극은 1로 나타나있다.
 * 다섯째 줄에는 회전 횟수 K(1 ≤ K ≤ 100)가 주어진다. 다음 K개 줄에는 회전시킨 방법이 순서대로 주어진다.
 * 각 방법은 두 개의 정수로 이루어져 있고, 첫 번째 정수는 회전시킨 톱니바퀴의 번호, 두 번째 정수는 방향이다.
 * 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향이다.
 */

/* -출력-
 * 총 K번 회전시킨 이후에 네 톱니바퀴의 점수의 합을 출력한다. 점수란 다음과 같이 계산한다.
 * 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
 * 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
 * 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
 * 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
 */
// 출처 : https://www.acmicpc.net/problem/14891
public class Solution_14891 {
	static int[] gear;
	static int rotateNum;
	static int[][] rotateArr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		gear = new int[6];
		
		// 1~4번째 톱니 형태
		gear[1] = Integer.parseInt("0"+br.readLine(),2);
		gear[2] = Integer.parseInt("0"+br.readLine(),2);
		gear[3] = Integer.parseInt("0"+br.readLine(),2);
		gear[4] = Integer.parseInt("0"+br.readLine(),2);
		
		// 회전 정보 저장
		rotateNum = Integer.parseInt(br.readLine());
		rotateArr = new int[rotateNum][2];
		for(int i = 0; i < rotateNum; ++i) {
			st = new StringTokenizer(br.readLine());
			rotateArr[i][0] = Integer.parseInt(st.nextToken());
			rotateArr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 회전 횟수만큼 해당 번호 회전
		for(int i = 0; i < rotateNum; ++i) {
			// 마지막 인자 0을 넣어 좌우측을 모두 확인
			rotate(rotateArr[i][0], rotateArr[i][1], true, 0);
		}
		
		int result = 0;
		// 톱니 바퀴 점수 계산
		for(int i = 1; i < 5; ++i) {
			// 12시 방향이 S극이라면
			if((gear[i] & 0b10000000) > 0) {
				result += Math.pow(2, i-1);
			}
		}
		System.out.println(result);
	} //end main()
	
	// 몇번을 돌릴지, 어느 방향으로 회전할지, 해당 번호와 현재 번호가 다른 극이라 회전이 가능한지, 좌측(-1)인지 우측(1)인지
	private static void rotate(int curNum, int curRotate, boolean isRotate, int flag) {
		int temp;
		// 만일 왼쪽 톱니번호가 0이거나 5라면 돌릴수 없다.
		// 돌릴 수 없다면 return
		if(curNum == 0 || curNum == 5 || !isRotate) {
			return;
		}
		
		// 왼쪽 톱니와 현재것 비교 -> 왼쪽3시(32), 현재9시(2)
		if(flag >= 0)
		rotate(curNum-1, curRotate*-1,
				((gear[curNum-1]>>5)&1) != ((gear[curNum]>>1)&1), 1);
		
		// 우측 톱니와 현재것 비교 -> 현재3시(32), 우측9시(2)
		if(flag <= 0)
		rotate(curNum+1, curRotate*-1,
				((gear[curNum]>>5)&1) != ((gear[curNum+1]>>1)&1), -1);
		
		// 톱니를 돌릴 수 있다면 현재 번호 돌리기
		// 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향이다.
		if(isRotate) {
			switch (curRotate) {
			// 반시계
			case -1:
				// 첫번째 bit(12시방향)을 마지막bit로 가져온다.
				temp = ((gear[curNum] & (1<<7)) >>> 7);
				gear[curNum] = ((gear[curNum]<<1) | temp);
				break;
				// 시계
			case 1:
				// 마지막 bit(11시방향)을 1번째bit로 가져온다.
				temp = ((gear[curNum] & (1<<0)) << 7);
				gear[curNum] = ((gear[curNum]>>1) | temp);
				break;
			}
		}
	}
}
