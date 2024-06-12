package Chap2_기본자료구조;

/*
 * 3번째 실습 
 * 교재 83 - 배열 처리 + function parameter 전달 숙달 훈련 
 */

import java.util.Arrays;
import java.util.Random;
public class train_실습2_06다차원배열 {

	public static void main(String[] args) {
		double [][]A = new double[2][3];
		double [][]B = new double[3][4];
		double [][]C = new double[2][4];

		inputData(A); inputData(B);
		showData("A[2][3] = ",A);
		showData("B[3][4] = ",B);
		C = multiplyMatrix(A,B);//행렬 곱셈
		showData("C[2][4] = ",C);
		double [][]D = A.clone();//교재83 - 배열 복제
		showData("D[2][3] = ",D);
		double [][]E = addMatrix(A,D);//행렬 덧셈
		showData("E[2][3] = ",E);
		double [][]F = transposeMatrix(A);//전치 행렬
		showData("F[3][2] = ",F);
		boolean result = equals(A, D);//행렬 동등 비교
		System.out.println(" equals(A,D) = " + result);
		System.out.println("F = " + Arrays.deepToString(F));//2차원 배열 처리 
	}
	
	static void inputData(double[][]data) {//double 난수 0.0 ~ 1.0 생성
		Random rnd = new Random();
		for (int i=0;i<data.length;i++) {
			for (int j=0;j<data[i].length;j++) {
				data[i][j] = rnd.nextDouble();
			}
		}
	}
	static void showData(String msg, double[][]data) {//주어진 문자열을 출력하고 배열을 2차원 형태로 출력
		System.out.println(msg);
		for (int i=0;i<data.length;i++) {
			for (int j=0;j<data[i].length;j++) {
				System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}
	}
	static boolean equals(double[][]A, double[][]B) {//두 행렬의 사이즈가 같고 값도 모두 같아야 행렬 2개는 equals을 true로 리턴
		if (A.length != B.length || A[0].length != B[0].length) {
			return false;
		}
		for (int i=0;i<A.length;i++) {
			for (int j=0;j<A[i].length;j++) {
				if (A[i][j] != B[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	static double[][] addMatrix(double[][]A, double[][]B) {//행렬 덧셈 결과를 리턴
		double[][]C = new double[2][3];
		//코딩
		for (int i=0;i<A.length;i++) {
			for (int j=0;j<A[i].length;j++) {
				C[i][j] = A[i][j]+B[i][j];
			}
		}
		return C;
	}
	static double[][] multiplyMatrix(double[][]A, double[][]B) {//행렬 곱셈 결과를 리턴
		//코딩 (행렬 크기가 달라서 이렇게 하면 안됨)
//		for (int i=0;i<A.length;i++) {
//			for (int j=0;j<A[i].length;j++) {
//				C[i][j] = A[i][j]*B[i][j];
//			}
//		}
		int ARow = A.length;
		int ACol = A[0].length;
		int BCol = B[0].length;
		double[][]C = new double[ARow][BCol]; 
		for (int i=0;i<ARow;i++) { // 결과행렬의 행
			for (int j=0;j<BCol;j++) { // 결과행렬의 열
				for (int k=0;k<ACol;k++) { // 곱셈합산을 위한 반복문
					C[i][j] += A[i][k]*B[k][j];
				}
			}
		}
		return C;
	}
	static double[][] transposeMatrix(double[][]A) {//전치 행렬을 리턴
		// 행렬 내 원소를 대각선축을 기준으로 서로 위치를 바꿈
		double[][]F = new double[3][2];
		for (int i=0;i<A.length;i++) {
			for (int j=0;j<A[i].length;j++) {
				F[j][i] = A[i][j];
			}
		}
		return F;
	}
}
