package com.ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_J_1175_주사위던지기2 {
	static int N, SUM;
	// 선택된 주사위 값 저장
	static int[] intArr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tempString = br.readLine().split(" ");
		// 주사위를 던진 횟수
		N = Integer.parseInt(tempString[0]);
		// 눈의 합
		SUM = Integer.parseInt(tempString[1]);
		
		intArr = new int[N];
		
		// 주사위 던질 횟수, 쌓일결과값
		throwDice(N, 0);
		System.out.print(sb.toString());
	}
	
	// 주사위를 던진 횟수
	private static void throwDice(int count, int result) {
		// 주사위 횟수를 다 소모하였고, 합이 딱 맞다면 해당 순열 출력 후 종료
		if(count == 0) {
			if(SUM == result) {
				for(int num : intArr) {
					sb.append(num+" ");
				}
				sb.append("\n");
			}
			// 주사위 횟수만 다 소모했을 경우 종료
			return;
		}
		
		// 1부터 6까지 선택
		for(int i = 1; i <= 6; ++i) {
			// 해당 선택으로 합을 넘었다면 해당 값 선택 x
			if(result +i>SUM) continue;
			intArr[N-count] = i;
			// 해당 선택으로 합을 넘지 않았다면 출력
			throwDice(count-1, result + i);
		}
		
	}
}
