import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -스타일리쉬 들여쓰기-
 * 1. 모든 가능성을 봐도 될 입력 크기이다 20^3
 * 2. 각 가능성에 대해 해당 라인이 가질 수 있는 가능성 확인, 유일한 가능성이면 출력
 * 3. 유일하지 않거나 알 수 없다면 -1출력
 */

/*
 * 메모리 : 36248KB 
 * 시간 : 141ms 
 * 코드길이 : 3051B 
 * 소요시간 : 2H
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWD3nB5q3T0DFAUZ
public class Solution_SWE_3378_스타일리쉬들여쓰기 {
	static class Point {
		int r,c,s,d;
		public Point(int r, int c, int s, int d) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
		}
	}
	
	static int R,C,S;
	static List<Point> pList;
	static List<Point> qList;
	static List<int[]> res;
	static boolean[] isUsed;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			int P=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			int[] arr = new int[] {0,0,0};
			isUsed = new boolean[3];
			pList = new LinkedList<>();
			qList = new LinkedList<>();
			res = new LinkedList<>();
			
			//스타일리쉬가 사용한 '.'에 대한 r,c,s 갯수 구하기
			for(int p=0; p<P; ++p)
				getCode(pList, br.readLine(),arr);
			
			for(Point p : pList) {
				if(p.r!=0)
					isUsed[0]=true;
				if(p.c!=0)
					isUsed[1]=true;
				if(p.s!=0)
					isUsed[2]=true;
			}
			
			//내가 사용할 r,c,s 갯수 구하기
			arr = new int[] {0,0,0};
			for(int q=0; q<Q; ++q)
				getCode(qList, br.readLine(),arr);
			
			//가능한 모든 순열을 만들어 res에 저장할 예정이다.
			permu(0,arr);
			sb.append("#"+t+" ");
			int ci=0;
			//만일 모든 가능성에 대해 해당 r,c,s가 0이라면
			//해당 괄호는 알 수 없다.
			boolean[] isAble = new boolean[3];
			for(int[] ti: res) {
				if(ti[0]!=0)
					isAble[0]=true;
				if(ti[1]!=0)
					isAble[1]=true;
				if(ti[2]!=0)
					isAble[2]=true;
			}
			
			//모든 라인에 대해서 실행
			F:for(Point p : qList) {
				//한 줄에 대해 여러번 실행 해보기
				int ei=-1;
				//해당 괄호 알 수 없는데 1이상 해당 괄호가 있다면 해당 라인은 알 수 없음.
				if((p.r>0 && !isAble[0]) || (p.c>0 && !isAble[1]) || (p.s>0 && !isAble[2])) {
					sb.append(-1+" ");
					continue;
				}
				
				//될 수 있는 모두 가능성에 대해 확인
				for(int[] ti : res) {
					//만일 이전 값과 값이 다르다면 해당 줄은 유일한 값이 아니다.
					ci=p.r*ti[0]+p.c*ti[1]+p.s*ti[2];
					if(ei != -1) {
						if(ei != ci) {
							sb.append(-1+" ");
							continue F;
						}
					}
					else
						ei=ci;
				}
				sb.append(ci+" ");
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
	
	//스타일리쉬의 코드와 같은 가능성인지 확인
	static boolean isRight(int[] arr) {
		for(Point p : pList) {
			if(!(p.r*arr[0]+p.c*arr[1]+p.s*arr[2] == p.d))
				return false;
		}
		res.add(new int[] {arr[0], arr[1], arr[2]});
		return true;
	}
	
	static void permu(int index, int[] arr) {
		if(index==3) {
			if(isRight(arr)) {
				return;
			}
			return;
		}

		//해당 괄호가 사용되지 않았다면 0으로 넘긴다.
		if(!isUsed[index]) {
			arr[index]=0;
			permu(index+1, arr);
		}
		//모든 순열을 선택한다.
		else {
			for(int i=1; i<=20; ++i) {
				arr[index]=i;
				permu(index+1,arr);
			}
		}

		return;
	}
	
	static void getCode(List<Point> list, String str, int[] arr) {
		int r=arr[0],c=arr[1],s=arr[2], d=0, sl=str.length();
		boolean flag=false;	//true면 .이미 다받음
		char tc;
		for(int i=0; i<sl; ++i) {
			tc=str.charAt(i);
			if(!flag && tc!='.') flag=true;
			switch (tc) {
			//flag가 false면 맨 앞 dot이다.
			case '.': if(!flag) d++;	break;
			case '(': arr[0]++;	break;
			case ')': arr[0]--;	break;
			case '{': arr[1]++;	break;
			case '}': arr[1]--;	break;
			case '[': arr[2]++;	break;
			case ']': arr[2]--;	break;
			default:
				break;
			}
		}
		//이전 r,c,s에 대해 현재 나온 dot개수 저장
		list.add(new Point(r, c, s, d));
	}
}
