package Chap6_정렬알고리즘;
/*
 * 6장 구현 실습과제1 
 */

class PhyscData implements Comparable<PhyscData>{
    String name;              // 이름
    int    height;            // 키
    double vision;            // 시력
    public PhyscData(String name, int height, double vision) {
    	this.name = name;
    	this.height = height;
    	this.vision = vision;
    }
	@Override
	public int compareTo(PhyscData o) { // 키를 기준으로 비교
		return Integer.compare(this.height,o.height);
	}
}
public class Test_객체merge정렬 {
		// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(PhyscData[] a, int lefta, int righta, int leftb, int rightb ) {
		int left = lefta;
        int right = rightb;
        int mid = righta;  // mid == leftb - 1와 같은 의미

		// 임시배열(newarray)을 하나 만들어서 왼쪽 배열, 오른쪽 배열 정렬한 걸 합침
        PhyscData[] newarray = new PhyscData[right-left+1];
        int newarrayIndex = 0;
        
        // 두 배열을 비교하면서 병합
        while (left<=righta && leftb<=rightb) {
        	if(a[left].compareTo(a[leftb])<=0){ // 두 부분 배열의 첫 번째 요소를 비교
        		newarray[newarrayIndex++] = a[left++];
        	} else {
        		newarray[newarrayIndex++] = a[leftb++];
        	}
        }
        while (left <= righta) {
        	newarray[newarrayIndex++] = a[left++];
        }
        while (leftb <= rightb) {
        	newarray[newarrayIndex++] = a[leftb++];
        }
        // 임시 배열을 원래 배열로 복사
        System.arraycopy(newarray, 0, a, lefta, newarray.length);
    }
        
	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(PhyscData[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);  // 왼쪽 부분 배열을 정렬
		MergeSort(a, mid+1, right); // 오른쪽 부분 배열을 정렬
		merge(a, left, mid, mid+1, right); // 정렬된 두 부분 배열을 병합
		return;
	}

	public static void main(String[] args) {
		PhyscData[] x = {
		         new PhyscData("강민하", 162, 0.3),
		         new PhyscData("김찬우", 173, 0.7),
		         new PhyscData("박준서", 171, 2.0),
		         new PhyscData("유서범", 171, 1.5),
		         new PhyscData("이수연", 168, 0.4),
		         new PhyscData("장경오", 171, 1.2),
		         new PhyscData("황지안", 169, 0.8),
		     };
		int nx = x.length;

		     MergeSort(x, 0, nx - 1); // 병합 정렬로 배열 x를 정렬
			 System.out.println("오름차순으로 정렬했습니다.");
		     System.out.println("■ 신체검사 리스트 ■");
		     System.out.println(" 이름     키  시력");
		     System.out.println("------------------");
		     for (int i = 0; i < x.length; i++)
		         System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
