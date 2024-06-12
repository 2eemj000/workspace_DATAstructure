package Chap3_검색알고리즘;

/*
 * 3장 2번 실습과제 - 스트링 배열의 정렬과 이진검색 
* 교재 121 실습 3-6 
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;
public class train_실습3_02스트링배열정렬이진탐색 {

	public static void main(String[] args) {
		String []data = {"사과","포도","복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외"};
		showData("정렬전 : ", data);
		sortData(data);//올림차순으로 정렬 교재211-212 단순 선택 정렬 알고리즘으로 구현
		showData("정렬후 : ", data);

		String key = "포도";
		int resultIndex = linearSearch(data, key);//교재 100 페이지 seqSearch() 함수로 구현
		System.out.println("\nlinearSearch : key = " + key + ", result = " + resultIndex);

		key = "배";
		resultIndex = binarySearch(data, key);//교재 109 페이지 binSearch() 함수로 구현
		System.out.println("\nbinarySearch : key = " + key + ",  result = " + resultIndex);
		key = "산딸기";
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch : key = " + key + ", result = " + resultIndex);
	}
	static void showData(String msg, String[]data) {
		System.out.print(msg+": ");
		for (String i : data) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	static void sortData(String[]data) { // --- 단순 선택 정렬 : 아직 정렬되지 않은 부분에서 가장 앞순서로 가야할 요소의 인덱스를 저장
		for (int i=0;i<data.length-1;i++) { // - 첫번째요소를 단순하게 선택해서
			int min = i;  
			for (int j = i+1;j<data.length;j++) // - 뒤에 있는 요소들 중 가장 작은 요소랑 swap
				if (data[j].compareTo(data[min])<0)
					min=j;
			swap(data,i,min); // 아직 정렬되지 않은 부분의 첫요소와 가장 앞순서로 가야할 요소를 교환
		}
	}
	static void swap(String[] data, int idx1, int idx2) {//스트링의 맞교환 함수로 sortData()에서 호출됨
		String t = data[idx1]; data[idx1] = data[idx2] ; data[idx2] = t;
	}
	static int linearSearch(String[]data, String key) {
		int i = 0;
		while(true) {
			if (i==data.length)
				return -1;
			if (data[i]==key)
				return i;
			i++;
		}
	}
	static int binarySearch(String[]data, String key) {
		int pl = 0; // 검색 범위의 첫 인덱스
		int pr = data.length-1; // 검색 범위의 마지막 인덱스
		do {
			int pc = (pl+pr)/2; // 중앙 요소의 인덱스
			if (data[pc]==key) 
				return pc;
			else if (data[pc].compareTo(key)<0)
				pl = pc +1; // 검색 범위를 뒤쪽 절반으로 좁힘
			else
				pr = pc-1; // 검색 범위를 앞쪽 절반으로 좁힘
			} while (pl<=pr);
		return -1; // 검색 실패
	}
	} 
