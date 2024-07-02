// 교재 398페이지보고 채우기
package Chap10_Hashing;

import java.util.Scanner;
//체인법에 의한 해시
//--- 해시를 구성하는 노드 ---//

class Node {
    int key;                 // 키값
    Node next;        // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)

    // 생성자
    public Node(int key) {
    	this.key = key;
    	this.next = null;
    }
    // 키값 반환 메서드
	public int getkey() {
		// TODO Auto-generated method stub
		return key;
	}
}

class SimpleChainHash {
 private int    size;              // 해시 테이블의 크기
 private Node[] table;        // 해시 테이블
 
//생성자
 public SimpleChainHash(int size) {
     this.size = size;
     this.table = new Node[size];
 }

 //해시함수
 public int hashValue(int key) {
		return (key*key*31)%size; // 최대한 home address가 다양하게 분포되게 함 (randomizing) -> 성능저하되지않게
	}
	 
 //--- 키값이 key인 요소를 검색(존재 여부 데이터를 반환) ---//
 public int search(int key) {
	 int hash = hashValue(key); // 검색할 데이터의 해시값
	 Node p = table[hash]; // 선택노드
	 while (p != null) {
         if (p.key == key)
             return 1;    // 키값이 존재하는 경우 1 반환
         p = p.next;
     }
     return -1;   // 키값이 존재하지 않는 경우 -1 반환
 }

 //--- 키값이 key인 데이터를 data의 요소(해시테이블)로 추가 ---//
 public int add(int key) {
	 int hash = hashValue(key);
	 Node newNode = new Node(key); // 새로운 노드 생성
	    if (table[hash] == null) {
	        table[hash] = newNode; // 해당 해시 버킷이 비어있으면 새로운 노드를 바로 추가
	        return 0; // 새로운 노드 추가 후 성공을 나타내는 0 반환
	    } else {
	    	Node p = table[hash]; // 6이면 결과값에 해당하는 노드를 가리키는 참조변수
	    	while (p.next !=null) {
	    		if (p.getkey() == key) {
	    			System.out.println("동일한 키값 존재 : "+key);
	    			return 1; // 이미 같은 키값이 존재하는 경우
	    		}
	    		p = p.next;
	    	}
	    	// 마지막 노드에 새로운 노드 추가
	    	p.next = newNode;
	    	table[hash] = p; // 노드 삽입
	    	return 0; 
	    }
		
 }

 //--- 키값이 key인 요소를 삭제 ---//
 public int delete(int key) {
	 int hash = hashValue(key);
	 Node p = table[hash];
	 Node pp = null;
	 while (p != null) {
		 if (p.getKey() == key) {
			 if (pp == null)
				 table[hash] = p.next;
		 }
	 }
 }

 //--- 해시 테이블을 덤프(dump) ---//
 public void dump() {

 }
}

public class Test_실습10_1정수체인해시 {

	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), Show("출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}
		// --- 메뉴 선택 ---//
		static Menu SelectMenu() {
			Scanner sc = new Scanner(System.in);
			int key;
			do {
				for (Menu m : Menu.values()) {
					System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
					if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
						System.out.println();
				}
				System.out.print(" : ");
				key = sc.nextInt();
			} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
			return Menu.MenuAt(key);
		}


//체인법에 의한 해시 사용 예
 public static void main(String[] args) {
	 	Menu menu;
		SimpleChainHash hash = new SimpleChainHash(11);
		Scanner stdIn = new Scanner(System.in);
		int select = 0, result = 0, val = 0;
		final int count = 15;
		do {
			switch (menu = SelectMenu()) {
			case Add:

				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					double d = Math.random();
					input[ix] = (int) (d * 20);
					System.out.print(" " + input[ix]);
				}
				for (int i = 0; i < count; i++) {
					if ((hash.add(input[i])) == 0)
						System.out.println("Insert Duplicated data");
				}
				break;
			case Delete:
				// Delete
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.delete(val);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;
			case Search:
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.search(val);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;

			case Show:
				hash.dump();
			break;
		}
	} while (menu != Menu.Exit);

	}
}
