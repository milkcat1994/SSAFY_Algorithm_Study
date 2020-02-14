package com.ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14uWl6AF0CFAYD&categoryId=AV14uWl6AF0CFAYD&categoryType=CODE
public class Solution_D3_1225_암호생성기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = 10;
		Queue<Integer> que;
		boolean isRight = false;
		int tempInt;
		
		// Testcase 시작
		for(int t = 1; t <= T; ++t) {
			que = new LinkedList<Integer>();
			//테스트 케이스 번호 무시
			br.readLine();
			//모든 숫자 받아오기
			st = new StringTokenizer(br.readLine());
			
			//Queue에 넣어주기
			for(int i = 0; i < 8; ++i) {
				que.offer(Integer.parseInt(st.nextToken()));
			}
			
			// 만약 아직 찾지 못했다면 
			while(!isRight) {
				for(int i = 1; i <=5; ++i) {
					tempInt = que.poll() - i;
					tempInt = tempInt > 0 ? tempInt : 0;
					que.offer(tempInt);
					if(tempInt == 0) {
						isRight = true;
						break;
					}
				}
			} //end while(!isRight)
			
			sb.append("#" + t + " ");
			for(int var : que) {
				sb.append(var + " ");
			}
			sb.append("\n");
			
			isRight = false;
		} //end TestCase
		System.out.println(sb.toString());
	}
}