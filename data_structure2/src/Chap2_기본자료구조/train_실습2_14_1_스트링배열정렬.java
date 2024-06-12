package Chap2_기본자료구조;
import java.util.Arrays;

public class train_실습2_14_1_스트링배열정렬 {
	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental melon"};
		showData("정렬전 : ", data);
		sortData(data);
		showData("정렬후 : ", data);
		String[] newdata = insertString(data, "banana");
		showData("삽입후 : ", newdata);
	}
	
	static void showData(String msg, String[] data) {//확장된 for 문으로 출력 
		System.out.print(msg);
		for (String a :data) {
			System.out.print(a+" ");
		}
		System.out.println();
	}

	static void swap(String[] data, int idx1, int idx2) {//스트링의 맞교환 함수로 sortData()에서 호출됨
		String t = data[idx1]; data[idx1] = data[idx2] ; data[idx2] = t;
	}
	
	static void sortData(String[] data) {//올림차순으로 정렬
		Arrays.sort(data);
	}
	
	// 메서드 내에서는 배열을 반환해야함 -> void가 아니라 String[]으로 선언
	static String[] insertString(String[] data, String value){
		//배열의 사이즈를 1개 증가시킨 후 insert되는 스트링 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 스트링 배열을 리턴
		String[] newdata = Arrays.copyOf(data, data.length + 1); // 배열의 사이즈를 1 증가시킴
		int i = data.length - 1; // i = 배열의 마지막 인덱스
		while (i>=0 && data[i].compareTo(value)>0) { // compareTo()로 string 비교
			newdata[i+1] = newdata[i]; // value가 들어가기 전까지의 값들을 한칸 오른쪽으로 이동시킴
			i--;
		}
		newdata[i+1] = value; 
		return newdata;
	}
}
