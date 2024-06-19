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
		if (left == right) 
			return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
	}

	public static void main(String[] args) {
		int resultLen = 0;
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
		ShowPolynomial(Polynomialx,Polynomialx.length );
		ShowPolynomial(Polynomialy, Polynomialy.length);
		MergeSort(Polynomialx, 0, Polynomialx.length - 1); // 배열 x를 퀵정렬
		MergeSort(Polynomialy, 0, Polynomialy.length - 1); // 배열 x를 퀵정렬
		ShowPolynomial(Polynomialx, Polynomialx.length);
		ShowPolynomial(Polynomialy, Polynomialy.length);
		 // 다항식 덧셈을 위한 결과 배열
        Term[] Polynomialz = new Term[20];
        
		resultLen = AddPolynomial(Polynomialx,Polynomialy,Polynomialz);//다항식 덧셈 z = x + y
		
		ShowPolynomial(Polynomialz, resultLen);
	}
	static int AddPolynomial(Term[] Polynomialx,Term[] Polynomialy,Term[] Polynomialz) {
		System.out.println("다항식 덧셈 : ");
	
		int i = 0, j = 0, k = 0;
		while (i<Polynomialx.length && j<Polynomialy.length) { 
			if (Polynomialx[i] != null && Polynomialy[j] != null) {
			if (Polynomialx[i].exp>Polynomialy[j].exp) { // 다항식 x의 지수가 가장 낮은 항과 다항식 y의 지수가 가장 낮은 항을 비교
				Polynomialz[k++] = new Term(Polynomialx[i].coef, Polynomialx[i].exp); 
				i++;
			} else if (Polynomialx[i].exp<Polynomialy[j].exp) {
				Polynomialz[k++] = new Term(Polynomialx[i].coef, Polynomialx[i].exp); 
				j++;
			} else { // 지수가 같은 경우
				double sumCoefficients = Polynomialx[i].coef + Polynomialy[j].coef; // sumCoefficients : 지수가 같을 때 계수를 합한 변수 
	            if (sumCoefficients != 0) {
	                Polynomialz[k++] = new Term(sumCoefficients, Polynomialx[i].exp);
	            }
	            i++;
	            j++;
			}
            } else if (Polynomialx[i] != null) {
                Polynomialz[k++] = new Term(Polynomialx[i].coef, Polynomialx[i].exp);
                i++;
            } else if (Polynomialy[j] != null) {
                Polynomialz[k++] = new Term(Polynomialy[j].coef, Polynomialy[j].exp);
                j++;
            }
        }
		 // Polynomialx 배열에 남은 항을 추가
//		while (i < Polynomialx.length && Polynomialx[i] != null) {
//	            Polynomialz[k++] = new Term(Polynomialx[i].coef, Polynomialx[i].exp);
//	            i++;
//	    }
//	    // Polynomialy 배열에 남은 항을 추가
//	 	while (j < Polynomialy.length && Polynomialy[j] != null) {
//	            Polynomialz[k++] = new Term(Polynomialy[j].coef, Polynomialy[j].exp);
//	            j++;
//	    }
		return k;
	}
	static void MultiplyPolynomial(Term[] Polynomialx,Term[] Polynomialy,Term[] Polynomialz) {
		System.out.println("다항식 곱셈 : ");
		int k = 0;
		for (int i=0; i<Polynomialx.length; i++) {
			for (int j=0; j<Polynomialy.length; j++) {
				double coef = Polynomialx[i].coef *Polynomialy[j].coef;
				int exp = Polynomialx[i].exp+Polynomialy[j].exp;
				
				Polynomialz[k++] = new Term(coef, exp);
			}
		}
		ShowPolynomial(Polynomialz, k);
	}
	static int EvaluatePolynomial(Term[] Polynomialz, int n) {
		System.out.println("다항식의 값 구하기 : ");
		
		int result = 0;
	    for (Term term : Polynomialz) {
	        result += term.coef * Math.pow(n, term.exp);
	    }
	    return result;
	}
	
	private static void ShowPolynomial(Term[] polynomialx, int len) {
		// TODO Auto-generated method stub
		System.out.println("다항식 출력 : ");
		for (int i = 0; i < len; i++) {
            System.out.print(polynomialx[i].coef + "x^" + polynomialx[i].exp);
            if (i < len) {
                System.out.print(" + ");
            }
        }
        System.out.println();
    }
}
