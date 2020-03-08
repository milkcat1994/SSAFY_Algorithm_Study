import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * -자기방으로 돌아가기-
 * 1. 각 방의 정보를 홀수일경우 +1해주어 짝수로 만들어 주며, 절반으로 나누어 index를 400이 아닌 200까지 사용한다.
 * 2. 시작과 끝의 대소 비교를 통해 for문을 다르게 선택하였다.
 * 3. 가장 많은 좌표를 뽑아내어 모두 이동할 수 있는 최소 시간을 구한다.
 * 
 * **홀수와 짝수 index를 맞춰주어야 하며, 시작방과 끝방이 오름차순이 아닌 내림차순으로도 주어질 수 있다는점.
 */

//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWNcJ2sapZMDFAV8
public class Solution_D4_4408_자기방으로돌아가기 {
	static int[] board = new int[201];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		int N, sp, ep, minres;
		for(int t = 1; t <= T; ++t) {
			minres = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			//학생 읽기
			for(int n = 0; n < N; ++n) {
				st = new StringTokenizer(br.readLine());
				sp = Integer.parseInt(st.nextToken());		//시작 방
				ep = Integer.parseInt(st.nextToken());		//도착 방
				
				//홀수라면 +1해주어 짝수로 만들어줌.
				if(sp%2 == 1)	sp+=1;
				if(ep%2 == 1)	ep+=1;
				sp/=2;	ep/=2;
				
				//해당 짝수 좌표의 선택 늘리기.
				//거꾸로 돌아갈수도있음.
				if(sp < ep) {
					for(int index = sp; index <= ep; ++index) {
						board[index]++;
					}
				}
				else {
					for(int index = sp; index >= ep; --index) {
						board[index]++;
					}
				}
			}
			//가장 많은 짝수 좌표 뽑아내기
			for(int i = 1; i < 201; ++i) {
				minres = minres > board[i] ? minres : board[i];
				board[i] = 0;
			}
			sb.append("#"+t+" "+minres+"\n");
		}
		System.out.println(sb.toString());
	}
}