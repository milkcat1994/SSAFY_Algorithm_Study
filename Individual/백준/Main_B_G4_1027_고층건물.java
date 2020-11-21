import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -고층건물-
 * ccw 알고리즘
 * 
 * https://code0xff.tistory.com/40
 * 2차원 평면상에 각각 x, y 좌표를 갖고 있는 세점 (p1, p2, p3)가 존재한다고 했을 때, CCW 공식은 아래와 같다.
 * s = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.y * p2.x + p2.y * p3.x + p3.y * p1.x)
 * 
 * 만일 결과값인 s가 0보다 작다면(s < 0) p1, p2 벡터를 기준으로 했을 때 p3는 오른쪽에 존재하는 정점이 되고,
 * s가 0보다 크다면(s > 0)  p1, p2 벡터를 기준으로 했을 때 p3는 왼쪽쪽에 존재하는 정점이 된다.
 * 마지막으로 s가 0이라면(s == 0) 세 정점은 한 직선 위에 존재하게 된다.
 */

//출처 : https://www.acmicpc.net/problem/1027
public class Main_B_G4_1027_고층건물 {
	static int[] arr;
	static int[] res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		res = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; ++i)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int max = 0;
		boolean isVisible;
		for (int start = 0; start < N; ++start) {
			for (int end = start+1; end < N; ++end) {
				isVisible=true;
				for(int i = start+1; i < end; ++i) {
					if(!ccw(start, end, i)) {
						isVisible=false;
						break;
					}
				}
				if(isVisible) {
					res[start]++;
					res[end]++;
				}
			}
		}
		
		for(int i = 0; i<N; ++i)
			max = res[i] < max ? max : res[i];
		
		System.out.println(max);
	}
	
	static boolean ccw(int s, int t, int i) {
		long sl,tl,il, ss;
		sl=arr[s]; tl=arr[t]; il=arr[i];
		
		ss = (s*tl + t*il + i*sl) - (sl*t + tl*i + il*s);
		if(ss >= 0)
			return false;
		return true;
	}
}
