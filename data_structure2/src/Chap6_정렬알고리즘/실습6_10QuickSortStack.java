package Chap6_정렬알고리즘;

//퀵 정렬(비재귀 버전) - 교재 버젼으로 stack을 2개 사용하는 문제가 있다 

import java.util.Scanner;

class IntStack3 {
	private int[] stk; // 스택용 배열
	private int capacity; // 스택의 크기
	private int ptr; // 스택 포인터

//--- 실행시 예외: 스택이 비어있음 -> 예외발생시키기 ---//
//	자바에서 기본적으로 제공하는 RuntimeException을 상속받아 EmptyIntStackException을 내가 정의함
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException(String message) {
			super(message); // 상위클래스의 생성자를 부름(메시지를 주면 출력함)
		}
	}

//--- 실행시 예외: 스택이 가득 참 -> 예외발생시키기 ---//
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException(String message) {
			super(message);
		}
	}

//--- 생성자(constructor) ---//
	public IntStack3(int maxlen) {
		ptr = 0;
		capacity = maxlen;
		try {
			stk = new int[capacity];
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}
	}

//--- 스택에 x를 푸시 ---//
// 예외를 발생시키려면 예외를 던져주기 위해 throws OverflowIntStackException 해줘야함
	public int push(int x) throws OverflowIntStackException {
		if (ptr >= capacity) // 스택이 가득 참
			throw new OverflowIntStackException("push: stack overflow");
		return stk[ptr++] = x;
	}

//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public int pop() throws EmptyIntStackException {
		if (ptr<=0) // 스택이 비어있음
			 throw new EmptyIntStackException("pop: stack empty");
		return stk[--ptr];
	}

//--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public int peek() throws EmptyIntStackException {
		if (ptr<=0)
			throw new EmptyIntStackException("peek: stack empty");
		return stk[ptr-1];
	}

//--- 스택을 비움 ---//
	public void clear() {
		ptr = 0;
	}

//--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(int x) {
		for (int i = ptr-1;i>=0;i--)
			if (stk[i]==x)
				return i; // 검색성공
		return -1; // 검색실패
	}

//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return ptr;
	}

//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return ptr <= 0;
	}

//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return ptr >= capacity;
	}
	
//--- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 (모든 데이터를 출력함) ---//
	public void dump() throws EmptyIntStackException{
		if (ptr<=0)
			System.out.println("스택이 비어있습니다");
		else {
			for (int i=0;i<ptr;i++)
				System.out.println(stk[i]+" ");
			System.out.println();
		}
	}
}

public class 실습6_10QuickSortStack {
 //--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
 static void swap(int[] a, int idx1, int idx2) {
     int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
 }

 //--- 퀵 정렬(비재귀 버전)---//
 static void quickSort(int[] a, int left, int right) {
	 IntStack3 lstack = new IntStack3(right - left + 1); // 스택의 생성, (right - left + 1)는 스택의 크기
	 IntStack3 rstack = new IntStack3(right - left + 1);

     lstack.push(left); // 나눌 배열의 첫요소의 인덱스를 push
     rstack.push(right); // 나눌 배열의 끝요소의 인덱스를 push

     while (!lstack.isEmpty()) { // 이때 스택에 들어있는건 분할할 배열의 범위
         int pl = left  = lstack.pop();        // 왼쪽 커서 --- 0 --- 5
         int pr = right = rstack.pop();        // 오른쪽 커서 --- 8 --- 8
         int x = a[(left + right) / 2];        // 피벗은 가운데 요소
         System.out.println("피벗 : "+x);
         do {
             while (a[pl] < x) pl++;
             while (a[pr] > x) pr--;
             if (pl <= pr)
                 swap(a, pl++, pr--);
         } while (pl <= pr);
         showData(a);
         System.out.println();
         if (left < pr) {
        	 System.out.println("left = " + left + ", pr = " + pr);
             lstack.push(left);           // 왼쪽 그룹 범위의 --- 0 --- 5
             rstack.push(pr);             // 인덱스를 푸시 --- 4 --- 6
         }
         if (pl < right) {
          	 System.out.println("pl = " + pl + ", right = " + right);
             lstack.push(pl);             // 오른쪽 그룹 범위의 --- 5 --- 7
             rstack.push(right);          // 인덱스를 푸시 --- 8 --- 8
         }
     }
 }
 static void showData(int[] d) {
	 System.out.println();
     for (int i = 0; i < d.length; i++)
         System.out.print(d[i] + " ");
 }
 public static void main(String[] args) {
     Scanner stdIn = new Scanner(System.in);

     System.out.println("퀵 정렬");
     System.out.print("요솟수: ");
     int nx = stdIn.nextInt();
     int[] x = new int[nx];

     for (int i = 0; i < nx; i++) {
		double d = Math.random();
		x[i] = (int) (d * 20);
         //System.out.print("x[" + i + "]: ");
         //x[i] = stdIn.nextInt();
     }
     showData(x);

     quickSort(x, 0, nx - 1);            // 배열 x를 퀵정렬

     System.out.println("오름차순으로 정렬했습니다.");
     showData(x);
 }
}
