package Chap5_재귀알고리즘;
/*
 * //실습 5-5  재귀 알고리즘
 * 재귀에 대한 이해를 돕는 순수 재귀 메서드 - 직접 입력하여 학습
 * stack frame을 이해하는 것이 필요 :구글링 - "스택 프레임(stack frame)", TCP School 참조 
 */

import java.util.Scanner;

class 실습5_3Recur {
 //--- 순수 재귀 메서드 ---//
 static void recur(int n) {
     if (n > 0) { // 재귀 호출의 종료 조건
    	 System.out.println("recur(" + n + " - 1) 호출됨");
         recur(n - 1); // 끝나면 밑에 n=1부터 출력
         System.out.println("------------");
         System.out.println("n = " + n);
         // recur(0)까지 호출되면서 종료 조건에 해당하는 부분이 실행됨
         // 이후에는 재귀 호출이 역순으로 종료
         System.out.println("recur(" + n + " - 2) 호출됨");
         recur(n - 2);
     }
 }
// 이러한 과정이 반복되다보면 recur(0)까지 호출되고, 이후에는 종료 조건이 만족되어 재귀 호출이 역순으로 종료
 
 public static void main(String[] args) {
     Scanner stdIn = new Scanner(System.in);
     // 처음에는 n = 2,3에 대하여 실행한다 다음에 5에 대하여 
     System.out.print("정수를 입력하세요 : ");
     int x = stdIn.nextInt();

     recur(x);
 }
}
