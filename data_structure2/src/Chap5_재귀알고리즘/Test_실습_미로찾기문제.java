// 
package Chap5_재귀알고리즘;
/*
 * 미로 찾기 문제
 * 플라토의 미로 찾기 문제 설명 자료 학습
 * int input[12][15] 테이블에서 입구는 (0,0)이며 출구는 (11,14)임
 * 미로 테이블 (12,15)을 상하좌우 울타리를 친 maze[14][17]을 만들고 입구는 (1,1)이며 출구는(12,15)
 * stack을 사용한 backtracking 구현
 */

//23.2.16 수정
//23.2.24: 객체 배열 초기화, static 사용, 내부 클래스와 외부 클래스 사용 구분을 못하는 문제 => 선행 학습 필요
enum Directions {N, NE, E, SE, S, SW, W, NW}
class Items {
	int x;
	int y;
	int dir;

	Items(int x, int y, int dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

class Offsets {
	int a;
	int b;
	
	 Offsets(int a, int b) {
         this.a = a;
         this.b = b;
     }
}

public class Test_실습_미로찾기문제 {

	static Offsets[] moves = new Offsets[8];//static을 선언하는 이유를 알아야 한다
	// 이동할 수 있는 방향의 오프셋을 저장하는 배열
	// 프로그램 실행 중에 변하지 않고, 모든 인스턴스에서 동일한 값을 가지기를 원하기 때문에 static으로 선언

	public static void path(int[][] maze, int[][] mark, int ix, int iy) {

		mark[1][1] = 1;
		StackList st = new StackList(50); // 스택을 생성
		Items temp = new Items(0, 0, 0); //N :: 0
		temp.x = 1;
		temp.y = 1;
		temp.dir = 2; //E:: 2
		mark[temp.x][temp.y] = 2; //미로 찾기 궤적은 2로 표시
		st.push(temp);

		while (!st.isEmpty()) // stack not empty
		{
			Items tmp = st.pop(); // unstack
			int i = tmp.x;
			int j = tmp.y;
			int d = tmp.dir;
			mark[i][j] = 1;//backtracking 궤적은 1로 표시
			while (d < 8) // moves forward
			{
				// outFile << i << " " << j << " " << d << endl;
				int g = i + moves[d].a; // 새로운 위치 계산
	            int h = j + moves[d].b;
				if ((g == ix) && (h == iy)) { // reached exit
												// output path
					System.out.println("출구에 도달했습니다 !");
                    return;
				}
				if ((maze[g][h] == 0) && (mark[g][h] == 0)) { // 새로운 위치가 갈 수 있는 곳인지 확인
					Items newItem = new Items(g,h,0); // g는 새로운 x좌표, h는 y좌표, 0은 방향
					st.push(newItem);
					mark[g][h] = 2; // 2는 방문한 곳
					i = g; 
					j = h;
					d = 0;
					// push the old temp to the stack, but the direction changes.
					// Because the neighbor in the direction of d has been checked.

				} else
					// 다음 방향을 시도
					d++;
			}
		}
		System.out.println("길이 막혔습니다 !");
	}

	public static void main(String[] args) {
		int[][] maze = new int[14][17];
		int[][] mark = new int[14][17];

		int input[][] = { // 12 x 15
				{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
				{ 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
				{ 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
				{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
				{ 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 }};
		for (int ia = 0; ia < 8; ia++)
			moves[ia] = new Offsets(0, 0);//배열에 offsets 객체를 치환해야 한다.
		moves[0].a = -1;	moves[0].b = 0;
		moves[1].a = -1;	moves[1].b = 1;
		moves[2].a = 0;		moves[2].b = 1;
		moves[3].a = 1;		moves[3].b = 1;
		moves[4].a = 1;		moves[4].b = 0;
		moves[5].a = 1;		moves[5].b = -1;
		moves[6].a = 0;		moves[6].b = -1;
		moves[7].a = -1;	moves[7].b = -1;
		//Directions d;
		//d = Directions.N;
		//d = d + 1;//java는 지원안됨
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {
				//input[][]을 maze[][]로 복사

			}
		}
		System.out.println("maze[12,15]::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("mark::");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 16; j++) {
				System.out.print(mark[i][j] + " ");
			}
			System.out.println();
		}
		path(maze, mark, 12, 15);
		System.out.println("mark::");
		for (int i = 1; i <= 12; i++) {
			for (int j = 1; j <= 15; j++) {
				System.out.print(mark[i][j] + " ");
			}
			System.out.println();
		}

	}
}
