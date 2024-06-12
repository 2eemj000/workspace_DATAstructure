package Chap4_스택과큐;
/*
 * 실습 4번 - 정수 배열 원형 큐
 * 교재 148 ~ 157 페이지
 * 교재 소스 코드를 보지 않고 구현 완성 연습 필요 
 */
import java.util.Random;
/*
 * 큐 1번 실습 코드 - 정수들의 큐
 */
import java.util.Scanner;

import Chap4_스택과큐.IntQueue3.EmptyIntQueue3Exception;
import Chap4_스택과큐.IntQueue3.OverflowIntQueue3Exception;

//int형 고정 길이 큐

class IntQueue3 {
	private int[] que; // 큐용 배열
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서

//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyIntQueue3Exception extends RuntimeException {
		public EmptyIntQueue3Exception(String msg) {
			super(msg);
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowIntQueue3Exception extends RuntimeException {
		public OverflowIntQueue3Exception(String msg) {
			super(msg);
		}
	}

//--- 생성자(constructor) ---//
	public IntQueue3(int maxlen) {
		que = new int[maxlen];
		capacity = maxlen; // 큐의 크기
		front = 0; // 맨 처음 요소 커서
		rear = 0; // 맨 끝 요소 커서
		// front rear 값이 같은 건 없다는 표시 (둘다-1로 표시하기도함)
		// num = 0; // 현재 데이터 개수
	}

//--- 큐에 데이터를 인큐 ---//
	public int enque(int x) throws OverflowIntQueue3Exception {
		if (isFull())
            throw new OverflowIntQueue3Exception("inque overflow"); // 큐가 가득 찼을 때 예외 발생
        rear = (rear + 1) % capacity; // 원형 큐 구현을 위해 rear 값을 갱신
        que[rear] = x;
        return 1;
	}

//--- 큐에서 데이터를 디큐 ---//
	public int deque() throws EmptyIntQueue3Exception {
		//if (front==rear&&front==0) // front==rear : 비어도, 가득차도 다 해당됨 / if문 걸어서 그 전단계 확인
		 if (isEmpty())
	            throw new EmptyIntQueue3Exception("deque empty"); // 큐가 비어 있을 때 예외 발생
	        int x = que[front];
	        front = (front + 1) % capacity; // 원형 큐 구현을 위해 front 값을 갱신
	        return x;
	    }

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public int peek() throws EmptyIntQueue3Exception {
		 if (isEmpty())
	            throw new EmptyIntQueue3Exception("peek empty"); // 큐가 비어 있을 때 예외 발생
	        return que[front];
	    }

//--- 큐를 비움 ---//
	public void clear() {
		front = 0;
        rear = -1;
	}

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(int x) {
		 for (int i = 0; i < capacity; i++) {
	            int index = (front + i) % capacity;
	            if (que[index] == x)
	                return index;
	        }
	        return -1; // 찾지 못한 경우 -1 반환
	    }

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		  if (rear >= front)
	            return rear - front + 1;
	        else
	            return capacity - (front - rear - 1);
	    }

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return size() == 0;
    }

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		 return size() == capacity;
    }
	
//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
	    if (isEmpty())
            System.out.println("큐가 비어 있습니다.");
        else {
            for (int i = 0; i < size(); i++) {
                System.out.print(que[(front + i) % capacity] + " ");
            }
            System.out.println();
        }
    }

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntQueue3 oq = new IntQueue3(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, p = 0;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(0)종료: ");
			int menu = stdIn.nextInt();
			switch (menu) {
			case 1: // 인큐
				rndx = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx +")");
				try {
					oq.enque(rndx);
				} catch(OverflowIntQueue3Exception e) {
					System.out.println("stack이 가득찼있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (EmptyIntQueue3Exception e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (EmptyIntQueue3Exception e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 4: // 덤프
				oq.dump();
				break;
			default:
				break;
			}
		}
	}

}