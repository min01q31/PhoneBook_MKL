package kr.tjeit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import kr.tjeit.datas.Person;

public class MainDrive {
	
	//static Person[] peopleList = new Person[100];
	static List<Person> peopleList = new ArrayList<>();
	
	public static void main(String[] args) {
		//0을 누르기 전까지는 반복적으로 메뉴를 출력
		
		printMenu();
	}
	static void printMenu() {
		Scanner scan = new Scanner(System.in);
		int userInput = 0;
		while(true) {
		System.out.println("*****메뉴 목록*****");
		System.out.println("1. 전화번호 추가");
		System.out.println("2. 전화번호 목록 조회");
		System.out.println("3. 총 인원수 확인");
		System.out.println("0. 프로그램 종료");
		System.out.println("================");
		System.out.print("번호를 입력하세요 : ");
		userInput = scan.nextInt();
		
		//0을 안눌렀다면? 프로그램이 계속 켜져있게됨.
		
		if(userInput == 0) {
			System.out.println("프로그램을 종료합니다.");
			break;
		}
		else if (userInput == 1) {
			//전화번호 추가 메뉴 코드 작성
			addPhoneNum();
		}
		else if (userInput == 2) {
			//전화번호 목록 조회 코드 작성
			printPhoneNumList();
		}
		else if (userInput == 3) {
			//전화번호부에 몇명이 저장되어 있는지 확인.
			showPeopleCount();
			
		}
		else {
			System.out.println("잘못된 입력입니다.");
		}
		}
		}
	
	static void addPhoneNum() {
		//정보를 입력받아서 텍스트파일에 추가
		Scanner scan = new Scanner(System.in);
		System.out.print("이름을 입력해 주세요. ");
		String name = scan.nextLine();
		
		System.out.print("폰번을 입력해 주세요. ");
		String phoneNum = scan.nextLine();
		
		System.out.print("이름 : " + name);
		System.out.print("폰번 : " + phoneNum);
		//파일 출력 부분 작성 시작.
		File f = new File("C:/temp/phonebook.csv");
		try {
			FileWriter fw = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			//bw를 이용해서 기록할 사항: 이민규 01040758184 2018-12-02 오후 12:07
			
			//Calendar 변수를 하나 만들어서 현재시간 저장
			Calendar cal = Calendar.getInstance();
			//SimpleDateFormat을 통해 양식 지정
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD a h:mm");
			
			//만들어진 양식을 String으로 변형
			String dateStr = sdf.format(cal.getTime());
			
			//String으로 변형된 시간양식 출력
			System.out.println(dateStr);
			
			//"이름,연락처,일시" 묶어서 bw를 이용해 파일에 기록.
			bw.write(name + "," + phoneNum +"," + dateStr);
			//줄바꿈이 일어나지 않으니, 수동으로 한줄 강제 변경
			bw.newLine();
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void printPhoneNumList() {
		File f = new File("C:/temp/phonebook.csv");
		
		//데이터를 비우고 다시 시작.
		peopleList.clear();
		
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				//읽어온 line을 가지고 가공해서  Person 클래스로 추가.
				
				//,에 따라 배열 대입
				String[] infos = line.split(",");
				
				Person p = new Person();
				p.setName(infos[0]);
				p.setPhoneNum(infos[1]);
				
				SimpleDateFormat parseSdf = new SimpleDateFormat("yyyy-MM-dd a h:mm");
				Calendar c = Calendar.getInstance();
				c.setTime(parseSdf.parse(infos[2]));
				
				p.setCreatedAt(c);
				
				
				peopleList.add(p);
				
				
				p.printMyInfo();
				//peopleList 배열에 하나하나 대입
				//Person 배열 생성 => 추가
				//System.out.println(line);
			}
			
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void showPeopleCount() {
		int peopleCount = peopleList.size();
		System.out.println("전화번호부는 총 "+peopleCount + "명이 등록되어 있습니다.");
	}

}

