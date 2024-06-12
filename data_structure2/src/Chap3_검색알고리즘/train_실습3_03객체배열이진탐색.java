package Chap3_검색알고리즘;

/*
 * 3장 3번 실습과제 - 객체 배열의 정렬과 이진검색 - Comparable 구현
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData2 implements Comparable<PhyscData2>{
	String name;
	int height;
	double vision;
	public PhyscData2(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}
	@Override
	public String toString() {//[홍길동,162,0.3] 형태로 리턴한다 
		return name+" "+height+" "+vision;
	}
	@Override
	public int compareTo(PhyscData2 p) { // 추상 메소드
		int result = this.name.compareTo(p.name);
		if (result != 0)
			return result; // string 이면 compareTo 사용
		else {
			int result2 = this.height - p.height;
			if (result2 != 0)
				return result2;
			else 
				return (int)(this.vision - p.vision);
		}
	}
}
public class train_실습3_03객체배열이진탐색 {
	public static void main(String[] args) {
		PhyscData2[] data = {
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("나동", 164, 1.3),
				new PhyscData2("최길", 152, 0.7),
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("박동", 182, 0.6),
				new PhyscData2("박동", 167, 0.2),
				new PhyscData2("길동", 167, 0.5),
		};
		showData("정렬전", data);
		sortData(data);//213p 6장 06-4 단순 삽입 정렬 InsertionSort() 함수로 구현
		showData("정렬후", data);
		reverse(data);
		showData("역순 재배치후", data);
		Arrays.sort(data);//사용된다 그 이유는? 자바에서 제공하는 라이브러리, data는 배열, PhyscData2클래스에 compareTo가 있는지?
		showData("Arrays.sort() 정렬후", data);
		
		PhyscData2 key = new PhyscData2("길동", 167, 0.5);
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(<길동,167,0.5>): result index = " + resultIndex);
		
		key = new PhyscData2("박동", 167, 0.6);
		resultIndex = binarySearch(data, key);//comparable를 사용
		System.out.println("\nbinarySearch(<박동,167,0.6>): result index = " + resultIndex);
		
		key = new PhyscData2("나동", 164, 0.6);
		resultIndex = Arrays.binarySearch(data, key);//comparable를 사용 , 배열에 찾는 key값이 없으면 들어가야할 인덱스값을 알려줌
		System.out.println("\nArrays.binarySearch(<나동,164,0.6>): result index = " + resultIndex);
	}
	static void showData(String msg, PhyscData2[] data) {
		System.out.print(msg + ": ");
		for (PhyscData2 a : data) {
            System.out.print(a + " ");
        }
        System.out.println();
	}
	static void sortData(PhyscData2[] data) { // --- 단순 삽입 정렬 : 삽입할 요소와 원래 배열을 비교하면서 들어갈 자리를 찾음
		for (int i=1;i<data.length;i++) {
			int j;
			PhyscData2 tmp = data[i];
			for (j=i;j>0&&data[j-1].compareTo(tmp)>0;j--)
				data[j]=data[j-1];
			data[j]=tmp;
		}
	}
	
	static void reverse(PhyscData2[] data) { // 배열 역순 정렬
        for (int i = 0; i < data.length / 2; i++) {
            PhyscData2 tmp = data[i];
            data[i] = data[data.length - 1 - i];
            data[data.length - 1 - i] = tmp;
        }
    }

	
	static int linearSearch(PhyscData2[] data, PhyscData2 key) {
		for(int i=0;i<data.length;i++) {
			if (data[i].compareTo(key)==0) {
				return i;
			}
		} return -1;
	}
	static int binarySearch(PhyscData2[] data, PhyscData2 key) {
		int pl = 0; // 검색 범위의 첫 인덱스
		int pr = data.length-1; // 검색 범위의 마지막 인덱스
		while (pl<=pr) {
			int pc = (pl+pr)/2; // 중앙 요소의 인덱스
			int cmp = data[pc].compareTo(key);
            if (cmp == 0) 
                return pc;
            else if (cmp < 0) 
                pl = pc + 1; // 검색 범위를 뒤쪽 절반으로 좁힘
            else 
                pr = pc - 1; // 검색 범위를 앞쪽 절반으로 좁힘
        }
        return -1; // 검색 실패
    }
}
