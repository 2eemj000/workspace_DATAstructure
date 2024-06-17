package Chap6_정렬알고리즘;

class Term implements Comparable<Term>{
    double coef;           // 계수
    int    exp;            // 지수
    public Term(double coef, int exp) {
    	this.coef = coef;
    	this.exp = exp;
    }
	@Override
	public int compareTo(Term o) {
	    // 먼저 지수를 비교
	    int expComparison = Integer.compare(this.exp, o.exp);
	    
	    // 만약 지수가 다르다면 지수 비교 결과를 반환
	    if (expComparison != 0) {
	        return expComparison;
	    }
	    
	    // 지수가 같으면 계수를 비교
	    return Double.compare(this.coef, o.coef);
	}
}
public class Test_다항식merge연산 {

	static void merge(Term[] a, int lefta, int righta, int leftb, int rightb ) {
		int left = lefta;
		int right = rightb;
		int mid = rightb;
		
		Term[] newarray = new Term[right-left+1];
		int newarrayIndex = 0;
		
		while (left<=righta && leftb<=rightb) {
			if (a[left].compareTo(a[leftb])<=0) {
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
	static void MergeSort(Term[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
	}

	public static <Polynomial> void main(String[] args) {
		Term[] Polynomialx = { // 먼저 지수로 정렬필요
		         new Term(1.5, 3), // (계수, 지수)
		         new Term(2.5, 7),
		         new Term(3.3, 2),
		         new Term(4.0, 1),
		         new Term(2.2, 0),
		         new Term(3.1, 4),
		         new Term(3.8, 5),
		     };
		Term[] Polynomialy = {
		         new Term(1.5, 1),
		         new Term(2.5, 2),
		         new Term(3.3, 3),
		         new Term(4.0, 0),
		         new Term(2.2, 4),
		         new Term(3.1, 5),
		         new Term(3.8, 6),
		     };
		int nx = Polynomialx.length;


		ShowPolynomial(Polynomialx);
		ShowPolynomial(Polynomialy);
		MergeSort(x, 0, x.length - 1); // 배열 x를 퀵정렬
		MergeSort(y, 0, y.length - 1); // 배열 x를 퀵정렬
		ShowPolynomial(x);
		ShowPolynomial(y);
		Polynomial[] z = new Polynomial[20];
		AddPolynomial(x,y,z);//다항식 덧셈 z = x + y
		ShowPolynomial(z);
		ShowPolynomial(y);
		MultiplyPolynomial(x,y,z);//다항식 곱셈 z = x * y
		ShowPolynomial(y);
		int result = EvaluatePolynomial(z, 10);//다항식 값 계산 함수 z(10) 값 계산한다 
		System.out.println(" result = " + result );
	}

	private static void ShowPolynomial(Term[] polynomialx) {
		// TODO Auto-generated method stub
		System.out.println("다항식 출력 : ");
		for (int i = 0; i < polynomial.length; i++) {
            System.out.print(polynomial[i].coef + "x^" + polynomial[i].exp);
            if (i < polynomial.length - 1) {
                System.out.print(" + ");
            }
        }
        System.out.println();
    }
}
