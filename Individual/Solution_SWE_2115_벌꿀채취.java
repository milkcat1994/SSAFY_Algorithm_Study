import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -벌꿀채취-
 * 1. 벌A 조합으로 고르기
 * 2. 벌B 조합으로 벌A제외 고르기
 * └─벌A,B는 가로로만 꿀통 채취 가능
 * 3. 벌A,B다 골랐다면 해당 꿀통을 조합이용하여 최대값 구하기
 * 4. 각각 최대값 합하여 최대값 갱신
 */

//200P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
public class Solution_SWE_2115_벌꿀채취 {
	public static int N,M,C,tN;
	public static int[] board;					//꿀량 저장위한 1차원 배열
	public static boolean[] isBeeSelected;		//벌 선택 위한 방문배열
	public static boolean[] isHoneySelected;	//꿀통 최대값 위한 방문배열
	public static Stack<Integer> stack = new Stack<>();
	public static int[][] bee;	//벌이 선택한 꿀통 번호 저장
	static int maxResult=0, tempRes=0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int beeASum, beeBSum;
		
		for(int t = 1; t <= T; ++t) {
			maxResult = 0; tempRes = 0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			tN = N*N;
			isHoneySelected = new boolean[M];
			
			board = new int[tN];
			isBeeSelected = new boolean[tN];
			for (int row = 0; row < N; ++row) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int col = 0; col < N; ++col) {
					board[row*N + col] = Integer.parseInt(st.nextToken());
				}
			}
			bee = new int[2][M];
			
			//첫번째 벌 선택
			for(int i = 0; i < tN; ++i) {
				if(isOut(i)) continue;

				//첫번째 벌
				for(int j = 0; j < M; ++j) {
					isBeeSelected[i+j] = true;
					bee[0][j] = i+j;
				}
				
				getMax(0, 0, 0);
				beeASum = tempRes;
				tempRes = 0;
				for(int j = i+M; j < tN; ++j) {
					if(isOut(j)) continue;
					//두번째 벌 선택
					selectedBeeB(j);
					
					//A,B다 선택 했다면 해당 정보로 가장 큰 값 고르기
					getMax(1, 0, 0);
					beeBSum = tempRes;
					tempRes = beeASum + beeBSum;
					maxResult = maxResult > tempRes ? maxResult : tempRes;
					tempRes = 0;
				}
				//선택 취소
				for(int j = 0; j < M; ++j) {
					isBeeSelected[i+j] = false;
				}
			}
			System.out.println("#"+t+" "+maxResult);
		} //end TestCase
	}

	public static int getHoneySum() {
		int sum = 0;
		for(Integer i : stack) {
			sum += i*i;
		}
		return sum;
	}
	
	public static void getMax(int beeN, int index, int count) {
		//max값 갱신
		if(count <= C) {
			int temp = getHoneySum();
			tempRes = tempRes > temp ? tempRes : temp;
		}
		
		for(int i = index; i < M; ++i) {
			if(isHoneySelected[i]) continue;				//이미 선택되었다면 Pass
			if(count + board[bee[beeN][i]] > C) continue;	//가질수 있는 꿀 량 넘는다면 선택X
			isHoneySelected[i] = true;
			stack.push(board[bee[beeN][i]]);
			getMax(beeN, i+1, count+board[bee[beeN][i]]);
			isHoneySelected[i] = false;
			stack.pop();
		}
	}
	
	//해당 index부터 벌 선택할 것이다.
	public static void selectedBeeB(int index) {
		//두번째 벌
		//해당 index에서 M개 뽑을 것임.
		for(int i = index; i < tN; ++i) {
			if(isBeeSelected[i]) continue;
			
			for(int j = 0; j < M; ++j) {
				bee[1][j] = i+j;
			}
			return;
		}
	}
	
	//현재 index에서 M개 뽑을시 다음 row로 넘어가는지 확인
	public static boolean isOut(int index) {
		if(index/N == (index+M-1)/N) return false;
		return true;
	}
	
}
