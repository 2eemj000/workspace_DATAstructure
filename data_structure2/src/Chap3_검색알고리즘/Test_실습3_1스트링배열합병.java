package Chap3_검색알고리즘;

/*
 * 3장 과제1: 스트링 배열 합병 정렬
 */
import java.util.Arrays;
public class Test_실습3_1스트링배열합병 {
    public static void main(String[] args) {
	String[] s1 = {"홍길동", "강감찬", "을지문덕", "계백", "김유신", "최치원" };
	String[] s2 = {"독도", "울릉도", "한산도", "영도", "오륙도", "동백섬"};
	Arrays.sort(s1);
	Arrays.sort(s2);
	
	showList("s1배열 = ", s1);
	showList("s2배열 = ", s2);

	String[] s3 = mergeList(s1,s2);//정렬된 s1[], s2[]을 합병하여 다시 정렬된 결과를 만드는 함수 구현
	showList("스트링 배열 s3 = s1 + s2:: ", s3);
}

	private static void showList(String string, String[] s3) {
		// TODO Auto-generated method stub
		System.out.println(string+":");
    	for (String merge : s3) {
    		System.out.print(merge+" ");
    	}
    	System.out.println();
    }

	private static String[] mergeList(String[] s1, String[] s2) {
		// TODO Auto-generated method stub
		String[]s3 = new String[s1.length+s2.length];
		int i=0,j=0,k=0;
		// 두 배열의 원소를 하나씩 비교하면서 작은 값을 결과 배열에 추가
		while (i < s1.length && j < s2.length) {
            if (s1[i].compareTo(s2[j]) <= 0) {
                s3[k++] = s1[i++];
            } else {
                s3[k++] = s2[j++];
            }
        }
		// 한 배열의 모든 원소를 결과 배열에 추가한 후, 다른 배열에 남은 원소들을 결과 배열에 추가
        while (i < s1.length) {
            s3[k++] = s1[i++];
        }
        while (j < s2.length) {
            s3[k++] = s2[j++];
        }
		return s3;
	}
}
