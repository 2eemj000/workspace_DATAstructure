package Chap5_재귀알고리즘;

//해가 256개 확인 필요
import java.util.ArrayList;
import java.util.List;

import Chap5_재귀알고리즘.Stack4.EmptyGenericStackException;

//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp
//N Queen problem / backtracking
//모든 해가 나오는 버젼 만들기 
/*
 * 체스판은 8 x 8 체스의 기물: king/가로세로대각선 1칸만 이동, queen/가로세로 대각선/같은 편의 기물을 넘을 수 없다,
 * Rook/가로,세로 이동/다른 기물을 넘을 수없다, bishop/대각선, knight/1-2칸 이동/다른 기물을 넘을 수 있다,
 * pawn/처음 이동은 2칸까지 가능, 그 후 한칸만 가능, 잡을 때는 대각선 가능 체스판 최대 배치 문제 : king/16,
 * Queen/8, rook/8, bishop/?, knight/? rook 2개/a, h, knight 2개/b, g, bishop
 * 2개/c, f, queen 1개/black queen은 black 칸에, 폰 8개
 */
class Point { // 퀸의 위치를 나타내는 좌표를 저장하기 위한 클래스
	private int ix;
	private int iy;

	public Point(int x, int y) { // 좌표를 문자열로 반환함
		ix = x;
		iy = y;
	}
	
	public int getIx() {
	    return ix;
	}

	public int getIy() {
	    return iy;
	}

	@Override
	public String toString() { 
		return "(" + ix + ", " + iy + ")";
	}

	@Override
	public boolean equals(Object p) {
		if (p instanceof Point) {
            Point other = (Point) p;
            return this.ix == other.ix && this.iy == other.iy;
        }
        return false;
    }
}

class Stack4 { // Stack4 클래스는 스택을 구현합니다. 이 스택은 Point 객체를 저장
	// --- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyGenericStackException(String message) {
			super(message);
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException(String message) {
			super(message);
		}
	}

	private List<Point> data; // 스택용 배열
	// private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 생성자(constructor) ---//
	public Stack4(int capacity) {
		top = 0;
		this.capacity = capacity;
		try {
	        data = new ArrayList<Point>(capacity); // capacity를 지정하여 ArrayList를 생성
	    } catch(OutOfMemoryError e) { // OutOfMemoryError 처리
	        System.err.println("메모리 부족으로 스택을 생성할 수 없습니다.");
	        System.exit(1); // 프로그램 종료
	    }
	}

	// --- 스택에 x를 푸시 ---//
	public void push(Point x) throws OverflowGenericStackException {
		if (top >= capacity) // 스택이 가득 참 => 예외
            throw new OverflowGenericStackException("push: stack overflow");
        data.add(x);
        top++;
    }
	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Point pop() throws EmptyGenericStackException {
		if (top <= 0) // 스택이 비어있음 => 예외
            throw new EmptyGenericStackException("pop: stack empty");
        top--;
        return data.remove(top);
    }

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Point peek() throws EmptyGenericStackException {
		if (top <= 0) // 스택이 비어있음 => 예외
            throw new EmptyGenericStackException("peek: stack empty");
        return data.get(top - 1);
    }

	// --- 스택을 비움 ---//
	public void clear() {
		data.clear();
        top = 0;
	}
	
	// --- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(Point x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	// --- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	// --- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	// --- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

	// --- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() throws EmptyGenericStackException{
		if (top <= 0)
			throw new EmptyGenericStackException("stack:: dump - empty");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

public class Test_QueenEight_구현과제_최종수정본 { //백트래킹 알고리즘
	// 가능한 모든 퀸의 배치를 탐색하고, 유효한 배치를 찾으면 그 위치를 스택에 저장
	public static void EightQueen(int[][] d) throws EmptyGenericStackException {
		int count = 0;// 퀸 배치 갯수
		int numberSolutions = 0;
		int ix = 0, iy = 0;// 행 ix, 열 iy
		Stack4 st = new Stack4(100); // 100개를 저장할 수 있는 스택을 만들고 (가능한 퀸의 위치를 저장)
		Point p = new Point(ix, iy);// 현 위치를 객체로 만들고 해당 위치에 퀸을 배치
		d[ix][iy] = 1;// 현 위치에 queen을 넣었다는 표시를 하고
		count++;
		ix++;
		st.push(p);// 스택에 현 위치 객체를 push
		while (true) {
		    ix = nextMove(d, ix, iy); // 다음 퀸을 배치할 행 인덱스 찾기
		    if (ix < 0) { // 다음 행이 없는 경우
		        if (st.isEmpty()) { // 스택이 비어있으면 모든 해를 찾은 것
		            break;
		        } else { // 스택이 비어있지 않은 경우
		            Point lastQueen = st.pop(); // 스택에서 마지막에 저장된 퀸 위치 가져오기
		            ix = lastQueen.getIx(); // 행 인덱스 설정
		            iy = lastQueen.getIy(); // 열 인덱스 설정
		            d[ix][iy] = 0; // 퀸 위치 초기화
		            iy++; // 다음 열로 이동
		        }
		    } else { // 다음 행이 있는 경우
		        d[ix][iy] = 1; // 퀸 배치
		        st.push(new Point(ix, iy)); // 스택에 퀸의 위치 저장
		        iy++; // 다음 열로 이동
		        if (iy >= 8) { // 모든 열에 퀸을 배치한 경우
		        	numberSolutions++; // 해의 수 증가
		            System.out.println("Solution " + numberSolutions + ":");
		            showQueens(d); // 퀸의 위치 출력
		            iy--; // 이전 열로 이동하여 백트래킹 수행
		        }
		    }
		}
	}
	
		public static boolean checkRow(int[][] d, int crow) { //배열 d에서 행 crow에 퀸을 배치할 수 있는지 조사
			 for (int i = 0; i < d.length; i++)
		            if (d[crow][i] == 1)
		                return false;
		        return true;
		    }

		public static boolean checkCol(int[][] d, int ccol) {//배열 d에서 열 ccol에 퀸을 배치할 수 있는지 조사
			 for (int i = 0; i < d.length; i++)
		            if (d[i][ccol] == 1)
		                return false;
		        return true;
		    }
		
		//배열 d에서 행 cx, 열 cy에 퀸을 남서, 북동 대각선으로 배치할 수 있는지 조사
		public static boolean checkDiagSW(int[][] d, int cx, int cy) { // x++, y-- or x--, y++ where 0<= x,y <= 7
			
			int x = cx, y =cy;
			while ( x >=  0 && x < d.length &&  y >=  0 && y < d.length ) {
				if (d[x][y] == 1)
					return false;
				x++;y--;
			}
			x = cx; y =cy;
			while ( x >=  0 && x < d.length &&  y >=  0 && y < d.length ) {
				if (d[x][y] == 1)
					return false;
				x--;y++;
			}

	    }

		//배열 d에서 행 cx, 열 cy에 퀸을 남동, 북서 대각선으로 배치할 수 있는지 조사
		public static boolean checkDiagSE(int[][] d, int cx, int cy) {// x++, y++ or x--, y--
			 for (int i = cx, j = cy; i >= 0 && j >= 0; i--, j--)
		            if (d[i][j] == 1)
		                return false;
		        return true;
		    }
		
		//배열 d에서 (x,y)에 퀸을 배치할 수 있는지  조사(가로,세로,대각선을 모두 확인 - 앞에 함수들을 부름)
		public static boolean checkMove(int[][] d, int x, int y) {// (x,y)로 이동 가능한지를 check
			  return checkRow(d, x) && checkCol(d, y) && checkDiagSW(d, x, y) && checkDiagSE(d, x, y);
	    }
		
		//배열 d에서 현재 위치(row,col)에 대하여 다음에 이동할 위치 nextCol을 반환, 이동이 가능하지 않으면 -1를 리턴(다음줄에서 가능한 자리를 찾아주는 것)
		public static int nextMove(int[][] d, int row, int col) {// 현재 row, col에 대하여 이동할 col을 return
			int i;
			for (i = col; i < d.length; i++) {
	            if (checkMove(d, row, i)) { // 현재 열에 퀸을 놓을 수 있는지 확인
	                return i; // 퀸을 놓을 수 있는 행 인덱스 반환
	            }
	        }
	        return -1; // 이동할 행이 없는 경우
	    }
	
	static void showQueens(int[][] data) {// 배열 출력
		for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++)
                System.out.print(data[i][j] + " ");
            System.out.println();
        }
    }

	public static void main(String[] args) throws EmptyGenericStackException {
		int row = 8, col = 8;
		int[][] data = new int[8][8];
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++)
				data[i][j] = 0;

		EightQueen(data);
	}
}