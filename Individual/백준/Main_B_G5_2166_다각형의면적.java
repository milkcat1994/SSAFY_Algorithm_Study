import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -다각형의 면적-
 * 1. 다각형을 여러개의 삼각형으로 쪼갠다.
 * 2. 백터의 외적을 이용하여 삼각형의 넓이를 구한다.
 * 
 */

//출처 : https://www.acmicpc.net/problem/2166
public class Main_B_G5_2166_다각형의면적 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	static List<Point> pointList;
	static List<Vector> vectorList;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.printf("%.1f", Math.abs(solve()));
	}
	
	public static double solve() {
		int vectorSize = vectorList.size();
		double areaSize = 0;
		
		Vector vector1;
		Vector vector2;
		
		for(int i=0; i<vectorSize-1; ++i) {
			vector1 = vectorList.get(i);
			vector2 = vectorList.get(i+1);
			
			areaSize += ((double)(vector1.r * vector2.c) - (double)(vector2.r * vector1.c)) / 2;
		}
		
		return areaSize;
	}
	
	static class Point {
		int r,c;
		
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}

	static class Vector {
		long r,c;
		
		Vector(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static void init() throws Exception {
		pointList = new ArrayList<Point>();
		vectorList = new ArrayList<Vector>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		int r, c;
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			pointList.add(new Point(r,c));
		}

		Point mainPoint = pointList.get(0);
		Point tempPoint;
		for(int i=1; i<N; ++i) {
			tempPoint = pointList.get(i);
			
			vectorList.add(new Vector(tempPoint.r - mainPoint.r, tempPoint.c - mainPoint.c));
		}
		
	}
	
}
