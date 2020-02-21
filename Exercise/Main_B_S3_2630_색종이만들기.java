package com.ssafy.divide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * -색종이 만들기-
 */

//출처 : https://www.acmicpc.net/problem/2630
public class Main_B_S3_2630_색종이만들기 {
	static int N;
	static int[][] board;
	static int blue, white;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		blue=0; white=0;
		
		for(int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		int res = divided(0,0,N,N);
		
		if(white == 0 && blue == 0) {
			res = res==0 ? white++ : blue++;
		}
		System.out.print(white+"\n"+blue);
	}
	
	public static int divided(int sr, int sc, int er, int ec) {
		//한칸 짜리라면
		if(er-sr == 1) {
			return board[sr][sc];
		}
		int arr[] = new int[4];
		
		int sumr=sr+er, sumc=sc+ec;
		arr[0] = divided(sr, sc, sumr/2, sumc/2);
		arr[1] = divided(sumr/2, sc, er, sumc/2);
		arr[2] = divided(sr, sumc/2, sumr/2, ec);
		arr[3] = divided(sumr/2, sumc/2, er, ec);
		
		//4개 다 같으면 해당 색상 그러나 -1은 아니어야 한다.
		if(arr[0]==arr[1] && arr[2]==arr[3] && arr[1]==arr[2] && arr[0] != -1) {
			return board[sr][sc];
		}
		//4개 다 같지 않으면 a,b,c,d의 색상 값으로
		//0이면 white 1이면 블루 -1이면 더하지 않기
		//return은 -1로
		else {
			for(int i = 0; i < 4; ++i) {
				switch (arr[i]) {
					//white
				case 0:
					white++;
					break;
					//blue
				case 1:
					blue++;
					break;
					//색상 섞임
				case -1:
					break;
				}
			}
			return -1;
		}
	}
}
