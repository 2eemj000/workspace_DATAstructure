package Chap3_검색알고리즘;
/*
* 3장 1번 실습과제: 03-3 정수배열이진검색
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
* 3장 - 1번 실습 과제 > 2번 실습: 스트링 객체의 정렬과 이진 탐색 > 3번 실습: 객체 정렬과 이진 탐색
*/
import java.util.Arrays;
import java.util.Random;
public class train_실습3_01정수배열이진탐색 {

	public static void main(String[] args) {
		int []data = new int[30];
		inputData(data);// 구현 반복 숙달 훈련 - 100 이하 난수를 생성
		showList("정렬 전 배열[]:: ", data);
		Arrays.sort(data);
		showList("정렬 후 배열[]:: ", data);// 구현 반복 숙달 훈련
		Random random = new Random();
		int key1 = random.nextInt(31);
		int resultIndex1 = linearSearch(data, key1);//교재 99-100:실습 3-1 참조, 교재 102: 실습 3-2
		//Arrays 클래스에 linear search는 없기 때문에 구현해야 한다 
		System.out.println("\nlinearSearch : key1 = " + key1 + ", result = " + resultIndex1);

		int key2 = random.nextInt(31);
		/*
		 * 교재 109~113
		 */
		int resultIndex2 = binarySearch(data, key2);//함수 구현이 필요
		System.out.println("\nbinarySearch : key2 = " + key2 + ", result = " + resultIndex2);
		
		int key3 = random.nextInt(31);
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		int resultIndex3 = Arrays.binarySearch(data, key3); // 작성할 필요 없음, 표준 라이브러리로 제공
		System.out.println("\nArrays.binarySearch : key3 = "+ key3 +", result = " + resultIndex3);
	}
	
	public static void inputData(int[] data) {
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(30); // 0부터 100까지의 난수를 생성하여 배열에 저장
        }
    }
	
	static void showList(String msg, int[]data) {
		System.out.print(msg+": ");
		for (int i : data) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	static int linearSearch(int[]data, int key) { // --- 선형검색
		int i = 0;
		while(true) {
			if (i==data.length)
				return -1;
			if (data[i]==key)
				return i;
			i++;
		}
	}
	
	static int binarySearch(int[]data, int key) { // --- 이진검색
		int pl = 0; // 검색 범위의 첫 인덱스
		int pr = data.length-1; // 검색 범위의 마지막 인덱스
		while (pl<=pr) {
			int pc=(pl+pr)/2; // 검색 범위의 중간 인덱스
			if(data[pc]==key) {
				return pc;
			} else if (data[pc]<key) {
				pl=pc+1; // 검색 범위를 뒤쪽 절반으로 좁힘
			} else {
				pr=pc-1; // 검색 범위를 앞쪽 절반으로 좁힘
			}
		} ;
		return -1; // 검색 실패
	} 
}
