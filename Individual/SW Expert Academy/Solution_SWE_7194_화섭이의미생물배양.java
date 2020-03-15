import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -화섭이의 미생물 배양-
 * 1. 일반 BFS, DFS로 풀려 했지만 범위가 너무 커서 시간,메모리 초과
 * 2. 답안 코드 보고 설명 듣고 작성하였음
 * 
 * - 항상 배수로만 움직인다.
 * - 배수로 움직인 자리에서 a로만 이동 가능한지 확인한다
 * └─만약 a로만 이동 가능하다면 현재까지 곱해진 b로 나누어 이전 자리에서 움직일 수 있는 a칸의 개수를 구한다.
 * ──방식은 아래와 같다.
	 * 
	 * b : 2
	 * curMultiple : 현재까지 곱해진 값
	 * diffCnt : a로만 도착지점까지 도착 할 수 있는 횟수
	 * 
	 * curMultiple이 2이고 diffCnt가 15일때
	 * 현재 위치 이전에서 diffCnt/curMultiple_(7)칸 이동 후 (b)배수 이동을 한다면
	 * 더 적은 움직임으로 이동 할 수 있다.
	 * x -> bx -> bx+15a => (1+15번) > x -> x+7a -> bx+7ab -> bx+7ab+a => (7+1+1번)
	 *
 * 
 * 예외적으로
 * b가 1일 경우에는 a로만 이동 해야함으로 따로 예외 처리가 필요하다.
 */

/*
 * 메모리 : 20220KB 
 * 시간 : 95ms 
 * 코드길이 : 1169B 
 * 소요시간 : 3H+
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWksRe4KARQDFAVE&
public class Solution_SWE_7194_화섭이의미생물배양 {
	static int s,t,res,a,b;
	static final int MAX=Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			s=Integer.parseInt(st.nextToken());
			t=Integer.parseInt(st.nextToken());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			
			res=MAX;
			solve();
			System.out.println("#"+tc+" "+(res==MAX ? -1 : res));
		} //end TestCase
	}
	
	static void solve() {
		long cp=s;
		int time=0;

		//b가 1일때
		if(b==1) {
			if((t-cp)%a == 0) {
				res=(int) ((t-cp)/a);
			}
			return;
		}
		
		while(cp<=t) {
			//남은 거리가 a로만 이동 가능하다면 실행
			if((t-cp)%a == 0) {
				//남은 거리가 a로만 이동 가능하고 그때의 이동 횟수이다.
				int diffCnt = (int) ((t-cp)/a);
				//b배로 움직인 횟수
				int exCnt=time;
				//현재까지의 배수의 값
				int curMultiple=(int) (cp/s);
				while(curMultiple>0) {
					/*
					 * curMultiple이 2이고 diffCnt가 15일때
					 * 현재 위치 이전에서 diffCnt/curMultiple(7)칸 이동 후 배수 이동을 한다면
					 * 더 적은 움직임으로 이동 할 수 있다.
					 * x -> bx -> bx+15a == x -> x+7a -> bx+7ab -> bx+7ab+a
					 */
					exCnt+=diffCnt/curMultiple;
					/*
					 * diffCnt를 남은 거리 만큼 다시 조정해준다.
					 * 위의 예시에서는 diffCnt=1이 된다.
					 */
					diffCnt%=curMultiple;
					/*
					 * curMultiple도 현재 배수 이전의 값으로 다시 조정해준다.
					 * 거꾸로 올라가며 현재 배수를 확인 해야하므로 b로 나누어준다.
					 */
					curMultiple/=b;
				}
				res = res < exCnt ? res : exCnt;
			}
			
			time++;
			cp*=b;
		}
	}
}