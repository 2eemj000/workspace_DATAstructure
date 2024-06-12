package Chap3_검색알고리즘;
/*
 * 3장 5번 실습과제 - 스트링 리스트 정렬
 * 리스트를 배열로 변환후 중복제거후 다시 리스트 만들기 등 실습
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class train_실습3_05스트링리스트정렬 {

	public static String[] removeElement1(String[] arr, String item) {
        // 특정 요소를 배열에서 제거하는 메서드 (사용되지 않음)
        return Arrays.stream(arr)
                     .filter(e -> !e.equals(item))
                     .toArray(String[]::new);
    }
	    
	    static void getList(List<String> list) {
			list.add("서울");	list.add("북경");
			list.add("상해");	list.add("서울");
			list.add("도쿄");	list.add("뉴욕");


			list.add("런던");	list.add("로마");
			list.add("방콕");	list.add("북경");
			list.add("도쿄");	list.add("서울");

			list.add(1, "LA");
	    }
	    
	    static void showList(String topic, List<String> list) {
	    	System.out.println(topic+":");
	    	for (String city : list) {
	    		System.out.print(city+" ");
	    	}
	    	System.out.println();
	    }
	    
	    static void sortList(List<String> list) { // 리스트라는 걸 염두하고 코드 짜야함 
	    	// 리스트를 배열로 변환
	    	String[] array = list.toArray(new String[0]);
	    	// 중복제거후 (배열을 정렬)
	    	Arrays.sort(array);
	    	// 배열을 다시 리스트 만들기
	    	list.clear();
	        list.addAll(Arrays.asList(array));
	    	}
	    
	    static String[] removeDuplicateList(List<String> list) {
	    	String[] cities = list.toArray(new String[0]);
		    int n = cities.length;
	        if (n == 0) {
	            return cities; // 비어 있는 경우 그대로 반환
	        }
	        
	        Arrays.sort(cities); // 배열 정렬
	        
	        List<String> uniqueCities = new ArrayList<>();
	        uniqueCities.add(cities[0]); // 첫 번째 요소 추가
	        
	        for (int i = 1; i < n; i++) {
	            if (!cities[i].equals(cities[i - 1])) {
	                uniqueCities.add(cities[i]);
	            }
	        }
	        
	        return uniqueCities.toArray(new String[0]);
	    }
	    
		public static void main(String[] args) {
			ArrayList<String> list = new ArrayList<>(); // 앞과 같으면 <>으로 생략, 갯수도 10개 default, ArrayList가 generic으로 되어있음
			getList(list);
			showList("입력후", list);
			//sort - 오름차순으로 정렬, 내림차순으로 정렬, 중복 제거하는 코딩
			//배열의 정렬
		    Collections.sort(list); // 리스트니까 Array가 아니라 Collections(list,map,set) 써야함
		    showList("정렬후", list);
// 배열에서 중복제거
		    System.out.println();
		    System.out.println("중복제거::");
		    String[] cities = removeDuplicateList(list);
	        ArrayList<String> lst = new ArrayList<>(Arrays.asList(cities));
		    showList("중복제거후", lst);
		}
	}

