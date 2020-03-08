import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -스타트와 링크-
 * 1. A팀은 1번이 무조건 들어가는 전제로 중복을 뽑지 않는다.
 * 2. 현재 뽑은 번호 뒤부터 확인하며, 뽑거나 뽑지 않고 다음 번호를 뽑는다.
 * 3. 절반의 사람을 뽑았다면 selected 배열을 통해 1팀인지 2팀인지 확인하고 각각의 합을 구해준다.
 * └──A_ij + A_ji를 구해야 함으로 미리 두개를 합쳐 배열을 구성해놓는다면 최종 연산은 절반이 될 것이다.
 */

// 출처 : https://www.acmicpc.net/problem/14889
public class Main_B_S3_14889_스타트와링크 {
	static int BOARD_SIZE;
	// 몇명이 한팀인지
	static int pickNum;
	static int[][] board;
	// 1팀으로 해당 선수가 뽑혔는지
	static boolean[] selected;
	// 각 팀 숫자
	static int aTeamNum = 0;
	static int bTeamNum = 0;
	// 각 팀 뽑힌 선수 저장
	static int[] aTeam;
	static int[] bTeam;
	
	static int minResult = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 배열 크기 저장 4<= N <=20
		BOARD_SIZE = Integer.parseInt(br.readLine());
		board = new int[BOARD_SIZE][BOARD_SIZE];
		selected = new boolean[BOARD_SIZE];
		pickNum = BOARD_SIZE/2;
		aTeam = new int[pickNum];
		bTeam = new int[pickNum];
		
		for(int row = 0; row < BOARD_SIZE; ++row) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < BOARD_SIZE; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1번은 무조건 선택된다고 가정 -> 중복되는 계산 하지 않기위해
        // nCr = n-1Cr + n-1Cr-1
		selected[0] = true;
		aTeam[0] = 0; aTeamNum++;
		pickTeam(1, 1);

		System.out.println(minResult);
	}
	
	// index : 현재 뽑기 시작할 위치
	private static void pickTeam(int index, int curPickNum) {
		// 다 뽑았다면
		if(curPickNum == pickNum) {
			int tempInt=0;
			bTeamNum=0;
			for(int i = 0; i<BOARD_SIZE; ++i) {
				if(!selected[i])
					bTeam[bTeamNum++] = i;
			}
			// aTeam돌면서 확인
			for(int i = 0; i < aTeamNum; ++i) {
				// 해당 사람 선택됬다면 해당 사람과의 관계 구하기
				for(int j = i+1; j < aTeamNum; ++j) {
					tempInt += (board[aTeam[i]][aTeam[j]] + board[aTeam[j]][aTeam[i]]);
				}
			}
			// bTeam돌면서 값 저장
			for(int i = 0; i < bTeamNum; ++i) {
				// 해당 사람 선택됬다면 해당 사람과의 관계 구하기
				for(int j = i+1; j < bTeamNum; ++j) {
					tempInt -= (board[bTeam[i]][bTeam[j]] + board[bTeam[j]][bTeam[i]]);
				}
			}
			if(minResult > Math.abs(tempInt))
				minResult =  Math.abs(tempInt);
			return;
		}
		
		// 총 뽑을 수는 pickNum
		// 현재 뽑기 시작할 위치 뒤로 확인
		for(int pick = index; pick < BOARD_SIZE; ++pick) {
			selected[pick] = true;
			aTeam[aTeamNum] = pick; aTeamNum++;
			
			// 현재 뽑은 위치 다음 위치부터 다시 탐색
			pickTeam(pick+1, curPickNum+1);
			
			selected[pick] = false;
			aTeamNum--;
		}
	}
}