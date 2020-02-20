import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -톱니바퀴-
 * 1. 현재 톱니 형태를 "0"+이진수 형태로 저장하여 양수로 저장한다.
 * └──int의 앞자리를 0으로 모두 채우기 위함
 * 2. 회전 정보에 따라 해당 index에서 좌,우측을 확인해 나간다.
 * 3. rotate()함수의 마지막 인자로 좌,우측 확인을 선택한다.
 * 4. 좌,우측 임에 따라 각각 확인할 비트가 다르므로 and연산으로 해당 비트의 값을 확인해주어 진행여부 결정
 * 5. shift 연산을 이용하여 톱니를 회전 시키며
 * 6. 마지막으로 12방향만을 확인하며 결과값을 도출한다.
 */

// 출처 : https://www.acmicpc.net/problem/14891
public class Main_B_S1_14891_톱니바퀴 {
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