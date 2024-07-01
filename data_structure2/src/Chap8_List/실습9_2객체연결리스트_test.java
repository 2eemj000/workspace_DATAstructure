package Chap8_List;
/*
 * 정수 리스트 > 객체 리스트: 2번째 실습 대상
 */
import java.util.Comparator;
import java.util.Scanner;


class SimpleObject5 {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?

	private String no; // 회원번호
	private String name; // 이름
	private String expire;//  유효기간 필드를 추가

	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + no + ") " + name;
	}
	public SimpleObject5() {
		no = null;name = null;
	}
	
	// --- 데이터를 읽어 들임 ---//
	 void scanData(String guide, int sw) {
	        Scanner sc = new Scanner(System.in);
	        System.out.println(guide + "할 데이터를 입력하세요.");

	        if ((sw & NO) == NO) {
	            System.out.print("번호: ");
	            if (sc.hasNext()) {
	                no = sc.next(); // 번호 입력 처리
	            }
	        }
	        if ((sw & NAME) == NAME) {
	            System.out.print("이름: ");
	            if (sc.hasNext()) {
	                name = sc.next(); // 이름 입력 처리
	            }
	        }
	    }

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject5> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no)<0) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject5> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return d1.name.compareTo(d2.name);
		}
	}
}

class Node2 {
	SimpleObject5 data;
	Node2 link;
	public Node2(SimpleObject5 element) {
		data = element;
		link = null;
	}
}

class LinkedList2 {
	Node2 first;
	public LinkedList2() {
		first = null;
	}

	public int Delete(SimpleObject5 element, Comparator<SimpleObject5> cc)
	//전달된 element를 찾을 때 comparator 객체를 사용한다
	{	Node2 current = first;
		Node2 q = null; // current는 리스트순회를 위한 현재노드
		while (current != null) {
			if (cc.compare(element, current.data)>0) {
				q=current;
				current = current.link;
			} else if (cc.compare(current.data, element)==0) {
				if (q == null) {
					first = current.link;
				} else 
					q.link = current.link;
				return 1; // 삭제성공
			} else {
				break;
			}
		} 
		return -1;
	}
		
	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node2 p = first;
		while (p != null) {
			System.out.print(p.data+" ");
			p = p.link;
		}
		System.out.println();
	}
	
	public void Add(SimpleObject5 element, Comparator<SimpleObject5> cc) 
	//임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{
		Node2 newNode = new Node2(element);
		if (first == null) //insert into empty list
		{
			first = newNode;
			return;
		}
		// 리스트가 정렬되도록 위치를 찾아서 삽입
		Node2 p = first;
		Node2 q = null;
		
		while(p != null) {
			if (cc.compare(p.data, element)<0) {
				q = p;
				p = p.link;
			} 
			else {
				if (q == null) {
					first = newNode;
				}
				else 
					q.link = newNode;
				newNode.link = p;
				return;
			}
		}
		q.link = newNode;
	}
	
	public boolean Search(SimpleObject5 element, Comparator<SimpleObject5> cc) { 
		// 전체 리스트를 올림차순 순서대로 출력한다.
		Node2 current = first;
		while (current != null) {
			if (cc.compare(current.data, element)==0) {
				return true; // 데이터가 일치하는 경우 true 반환
			}
			current = current.link;
		}
		return false;
	}
	
	void Merge(LinkedList2 b, Comparator<SimpleObject5> cc) {
		/*
		 * 연결리스트 a,b에 대하여 a = a + b
		 * merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지 않고 합병하는 알고리즘 구현
		 * 난이도 등급: 최상급
		 * 회원번호에 대하여 a = (3, 5, 7), b = (2,4,8,9)이면 a = (2,3,4,5,8,9)가 되도록 구현하는 코드
		 */
		Node2 pa = first;
		Node2 pb = b.first;
		Node2 prev = null;
		
		while (pb != null) {
			while (pa != null && cc.compare(pa.data, pb.data)<0) {
				prev = pa;
				pa = pa.link;
			}
			Node2 nextB = pb.link;
			pb.link = pa;
			if (prev == null) {
				this.first = pb;
			} else {
				prev.link = pb;
			}
			prev = pb;
			pb = nextB;
		}
	}
}

public class 실습9_2객체연결리스트_test {

	enum Menu {
		Add( "삽입"), Delete( "삭제"), Show( "인쇄"), Search( "검색"), Merge("합병"), Exit( "종료");

		private final String message;                // 표시할 문자열

		static Menu MenuAt(int idx) {                // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) {                        // 생성자(constructor)
			message = string;
		}

		String getMessage() {                        // 표시할 문자열을 반환
			return message;
		}
	}

	//--- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 &&
						m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() ||key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu;                               
		LinkedList2 l = new LinkedList2();
		LinkedList2 l2 = new LinkedList2();
		Scanner sc = new Scanner(System.in);
		int count = 3; //3개의 객체 입력 개수
		do {
			switch (menu = SelectMenu()) {
			case Add :                          
				SimpleObject5 data = new SimpleObject5();
		        data.scanData("입력", 3); // 이름 입력

			    l.Add(data, SimpleObject5.NO_ORDER); // LinkedList2 객체에 번호로 정렬된 순서로 데이터를 추가합니다.
			    break;
			case Delete :                         
				SimpleObject5 dataDelete = new SimpleObject5();
				dataDelete.scanData("삭제", SimpleObject5.NO);
				int num = l.Delete(dataDelete, SimpleObject5.NO_ORDER);//회원번호 조건 비교하여 삭제 
				System.out.println("삭제된 데이터 성공은 " + num);
				break;
			case Show :                           
				l.Show();
				break;
			case Search : // 회원 번호 검색
				SimpleObject5 dataSearch = new SimpleObject5();
				dataSearch.scanData("탐색", SimpleObject5.NO);
				boolean result = l.Search(dataSearch, SimpleObject5.NO_ORDER);//회원번호로 검색
				if (result)
					System.out.println("검색 성공 = " + result );
				else
					System.out.println("검색 실패 = " + result);
				break;
			case Merge://난이도 상
				for (int i = 0; i < count; i++) {//3개의 객체를 연속으로 입력받아 l2 객체를 만든다 
					SimpleObject5 mergeData = new SimpleObject5();
					mergeData.scanData("병합", 3);
					l2.Add(mergeData, SimpleObject5.NO_ORDER );				
				}
				System.out.println("리스트 l::");
				l.Show();
				System.out.println("리스트 l2::");
				l2.Show();
				l.Merge(l2, SimpleObject5.NO_ORDER);
				//merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
				System.out.println("병합 리스트 l::");
				l.Show();
				break;
			case Exit :                           // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
		 // Scanner 객체 닫기
        sc.close();
	}
}


