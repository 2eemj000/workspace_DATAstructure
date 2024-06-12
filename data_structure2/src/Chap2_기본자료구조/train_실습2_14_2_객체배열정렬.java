package Chap2_기본자료구조;
import java.util.Arrays;

//5번 실습 - 2장 실습 2-10를 수정하여 객체 배열의 정렬 구현 (86p)
class PhyscData implements Comparable<PhyscData>{ // 데이터 정의
	String name;
	int height;
	double vision;
	public PhyscData(String name, int height, double vision) { // 생성자
        this.name = name;
        this.height = height;
        this.vision = vision;
    }
	@Override
	public String toString() { // 문자열로 만들어 반환하는 메서드
		return name+" "+height+" "+vision;
	}
	@Override
	public int compareTo(PhyscData p) { // 비교해서 순서대로 정렬 (이름->키->시력 순으로)
		// (123p)
		if (this.name!=p.name) {
			return this.name.compareTo(p.name); // string 이면 compareTo 사용
		} else {
			if (this.height != p.height) {
                return Integer.compare(this.height, p.height);
		} else {
                return Double.compare(this.vision, p.vision);
			}
		}
	}
	public boolean equals(PhyscData p) {
		if (this==p) {
			return true;
		} else {
			return false;
		}
	}
}

public class train_실습2_14_2_객체배열정렬 {
	static void swap(String[] data, int idx1, int idx2) {
		String t = data[idx1]; data[idx1] = data[idx2] ; data[idx2] = t;
	}
	static void sortData(PhyscData[] data) {//객체 배열을 이름 순서로 정렬, 이름이 같으면 키 값을 정렬, 키 값이 같으면 시력으로 
		Arrays.sort(data);
	}
	public static void main(String[] args) {
		PhyscData[] data = {
				new PhyscData("홍길동", 162, 0.3),
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길동", 162, 0.7),
				new PhyscData("김홍길동", 172, 0.3),
				new PhyscData("이길동", 182, 0.6),
				new PhyscData("이길동", 167, 0.2),
				new PhyscData("최길동", 169, 0.5),
		};
		showData("정렬전",data);
		sortData(data); //bubblesort 수정해서, string 비교할때는 compare to 쓰기
		showData("정렬후", data);
		PhyscData[] newData= insertObject(data, new PhyscData("이기자", 179, 1.5));//배열의 사이즈를 1개 증가시킨후 insert되는 객체 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 객체 배열을 리턴
		showData("삽입후", newData);
	}
	static void showData(String msg, PhyscData[] data) {
		System.out.print(msg + ": ");
		for (PhyscData a : data) {
            System.out.print(a + " ");
        }
        System.out.println();
	}
	static PhyscData[] insertObject(PhyscData[] data, PhyscData value){
		PhyscData[] newData = Arrays.copyOf(data, data.length + 1); 
		int i = data.length - 1; 
		while (i>=0 && data[i].compareTo(value)>0) { 
			newData[i+1] = newData[i]; 
			i--;
		}
		newData[i+1] = value; 
		return newData;
	}

}
