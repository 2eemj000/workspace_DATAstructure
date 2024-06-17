package Chap6_정렬알고리즘;

import java.util.Random;
import java.util.Scanner;

interface MaxHeap {
	public void Insert(int x);
	public int DeleteMax();
}

class Heap implements MaxHeap {
	final int heapSize = 100;
	private int[] heap; 
	private int n; // MaxHeap의 현재 입력된 element 개수
	private int MaxSize; // heap의 최대 크기를 제한

	public Heap(int sz) {
		MaxSize = sz;
        heap = new int[MaxSize + 1];
        n = 0;
	}

	public void display() {//heap 배열을 출력한다. 배열 인덱스와 heap[]의 값을 출력한다.
		System.out.print("Heap: ");
        for (int i = 1; i <= n; i++) { // 인덱스 1부터 시작해야 i/2,2*i,2*i+1로 간단하게 표현가능
            System.out.print(heap[i] + " ");
        System.out.println();
	};
	}
	@Override
	public void Insert(int x) {//max heap이 되도록 insert한다. 삽입후 complete binary tree가 유지되어야 한다.
		int i; // 새로 삽입된 요소의 인덱스값
		if (n == MaxSize) {
			HeapFull();
			return;
		}
		n++;
		 for (i = n; i > 1 && x > heap[i / 2]; i /= 2) // 새로운 요소를 힙의 마지막에 추가함, x가 자신의 부모보다 큰 값일 때까지 반복
	            heap[i] = heap[i / 2]; // x를 힙의 마지막에 추가하고, 부모 노드와 비교하여 크기 순서대로 위치를 조정 (더 큰 자식노드로 이동)
	        heap[i] = x;
	}
	@Override
	public int DeleteMax() { //heap에서 가장 큰 값을 삭제하여 리턴한다. 
		int x;
		int i, j;
		if (n == 0) {
			HeapEmpty();
			int elm = 0; // 힙의 조건을 유지하기 위해 마지막 노드를 루트로 옮김
			return elm;
		}
		x = heap[1];
        int temp = heap[n--];
        for (i = 1, j = 2; j <= n;) {
            if (j < n && heap[j] < heap[j + 1])
                j++;
            if (temp >= heap[j])
                break;
            heap[i] = heap[j];
            i = j;
            j *= 2;
        }
        heap[i] = temp;
		return x;
	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}
public class heap_정렬_test {
	 static void showData(int[] d) {
		 for (int i = 0; i < d.length; i++)
	            System.out.print(" " + d[i]);
	        System.out.println();
	    }
	public static void main(String[] args) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(20);
	    final int count = 10;//난수 생성 갯수
	    int[] x = new int[count+1];//x[0]은 사용하지 않으므로 11개 정수 배열을 생성한다 
	    int []sorted = new int[count];//heap을 사용하여 deleted 정수를 배열 sorted[]에 보관후 출력한다

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1://난수를 생성하여 배열 x에 넣는다 > heap에 insert한다.
				for (int i = 1; i <= count; i++) {
                    x[i] = rnd.nextInt(100);
                    heap.Insert(x[i]);
                }
			     showData(x);
				break;
			case 2:	//heap 트리구조를 배열 인덱스를 사용하여 출력한다.
				heap.display();
				break;
			case 3://heap에서 delete를 사용하여 삭제된 값을 배열 sorted에 넣는다 > 배열 sorted[]를 출력하면 정렬 결과를 얻는다 
				for (int i = 0; i < count; i++) {
                    sorted[i] = heap.DeleteMax();
                }
                System.out.println("Sorted Array:");
                showData(sorted);
				break;

			case 4:
				return;
			}
		} while (select < 5);

		return;
	}
}

