package com.ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -서로소집합-
 * 상호배타 집합-트리 로 구현
 */

//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr
public class Solution_D4_3289_서로소집합_양지용 {
	
	static int[] parents;
	
	//해당 대표 원소 찾기
	static int findSet(int a) {
		//자신이 대표자일때
		if(parents[a] == -1)
			return a;
		return parents[a] = findSet(parents[a]);
	}
	
	//같은 집합인지 파악
	static boolean equals(int a, int b) {
		int aParent = findSet(a);
		int bParent = findSet(b);
		if(aParent == bParent)
			return true;
		return false;
	}
	
	//두개 집합 합치기
	static boolean unionSet(int a, int b) {
		int aParent = findSet(a);
		int bParent = findSet(b);
		
		//합치기 진행 (왼쪽에 우측 달기)
		if(aParent != bParent) {
			parents[bParent] = aParent;
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int n,m;
		int flag, a,b;
		
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			parents = new int[n+1];
			Arrays.fill(parents, -1);
			
			
			sb.append("#").append(t).append(" ");
			for(int i = 0; i < m; ++i) {
				st = new StringTokenizer(br.readLine()," ");
				flag = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				switch (flag) {
				//첫인자 0이라면 합집합
				case 0:
					unionSet(a,b);
					break;
					//첫인자 1이라면 집합에 포함인지 반환
				case 1:
					if(equals(a,b))
						sb.append(1);
					else
						sb.append(0);
					break;
				}
			}
			sb.append("\n");
		} //end TestCase
		System.out.print(sb.toString());
	}
}
