package Chap4_스택과큐;
/*
 * 실습 6번: 원형 큐로서 큐에 Point 객체를 배열로 저장
 * 실습4_3_2 정수 원형 큐 배열을 객체 버젼으로 구현하는 것임
 */


import java.util.Random;
import java.util.Scanner;

class Point5 {
	private int ix;
	private int iy;
	
	 public Point5(int ix, int iy) {
	        this.ix = ix;
	        this.iy = iy;
	    }

	    @Override // 객체를 출력할 때 메모리 주소가 아닌 좌표값을 출력하도록 수정
	    public String toString() {
	        return "(" + ix + ", " + iy + ")";
	    }
}

class CircularQueue {
	private int QUEUE_SIZE = 0;
	private Point5[] que;//배열로 객체원형 큐 구현
	private int front, rear;
	private static boolean isEmptyTag;
	
	//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String msg) {
			super(msg);
		}
	}

	//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException(String msg) {
			super(msg);
		}
	}

	public CircularQueue(int count) {
		QUEUE_SIZE = count;
        que = new Point5[QUEUE_SIZE];
        front = rear = 0;
        isEmptyTag = true;
	}
	
	public void push(Point5 it) throws OverflowQueueException{
		if(isFull()) {
			throw new OverflowQueueException("push: circular queue overflow"); 
		}
		que[rear] = it;
		rear = (rear+1)% QUEUE_SIZE;
        isEmptyTag = false;
	}

	Point5 pop() throws EmptyQueueException{
		if(isEmpty()) {
			throw new EmptyQueueException("pop: queue empty"); 
		}
		Point5 temp = que[front];
		que[front] = null;
	    front = (front + 1)% QUEUE_SIZE;
	    isEmptyTag = (front == rear);
	    if (isEmptyTag) {
	        rear = front; // 큐가 비었을 때 rear도 front 위치로 갱신
	    }
	    return temp;
	}

	 void clear() throws EmptyQueueException{
		if(isEmpty()) {
				throw new EmptyQueueException("clear: queue empty"); 
		}		 
		front = rear = 0;
        isEmptyTag = true;
	}

	//--- 큐의 크기를 반환 ---//
		public int getCapacity() {
			return QUEUE_SIZE;
		}

	//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
		public int size() {//front, rear를 사용하여 갯수를 size로 계산
			if (isEmpty()) {
		            return 0;
			} else if (front <= rear) {
		        return rear - front;
		    } else {
		        return QUEUE_SIZE - front + rear;
		    }
		}
		
		//--- 원형 큐가 비어있는가? --- 수정 필요//
		public boolean isEmpty() {
			return isEmptyTag;
		}

	//--- 원형 큐가 가득 찼는가? --- 수정 필요//
		public boolean isFull() {
			return (rear+1)%QUEUE_SIZE == front;
		}

		public void dump() throws EmptyQueueException{
			if (isEmpty()) {
					throw new EmptyQueueException("dump: queue empty");
			}
			System.out.print("QUEUE : ");
			int idx = front;
	        do {
	            System.out.print(que[idx] + " ");
	            idx = (idx + 1) % QUEUE_SIZE;
	        } while (idx != rear);
	        System.out.println();
	    }
		
		public Point5 peek() throws EmptyQueueException {
			if (isEmpty()) {
				throw new EmptyQueueException("peek: queue empty"); // 큐가 비어있음
			}
		return que[front];
		}
}

public class 실습4_06_객체원형큐_배열 {
public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	CircularQueue oq = new CircularQueue(4); // 최대 4개를 인큐할 수 있는 큐
	Random random = new Random();
	int rndx = 0, rndy = 0;
	Point5 p = null;
	while (true) {
		System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
		System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
		System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5) clear  (0)종료: ");
		int menu = stdIn.nextInt();
		if (menu == 0)
			break;
		switch (menu) {
		case 1: // 인큐

			rndx = random.nextInt(20);

			rndy = random.nextInt(20);
			System.out.print("입력데이터: (" + rndx + ", " + rndy + ")");
			p = new Point5(rndx,rndy);
			try {
				oq.push(p);
				System.out.println("push: size() = "+ oq.size());
			} catch(CircularQueue.OverflowQueueException e) {
				System.out.println("queue이 full입니다." + e.getMessage());
				e.printStackTrace();
			}
			break;

		case 2: // 디큐
			try {
				p = oq.pop();
				System.out.println("pop: size() = "+ oq.size());
			} catch (CircularQueue.EmptyQueueException e) {
				System.out.println("queue이 비어있습니다." + e.getMessage());
				e.printStackTrace();
			}
			break;

		case 3: // 피크
			try {
				p = oq.peek();
				System.out.println("피크한 데이터는 " + p + "입니다.");
			} catch (CircularQueue.EmptyQueueException e) {
				System.out.println("queue이 비어있습니다." + e.getMessage());
				e.printStackTrace();
			}
			break;
		case 4: // 덤프

			break;
		case 5: //clear
			
			break;
	}
	}
}
}
	


