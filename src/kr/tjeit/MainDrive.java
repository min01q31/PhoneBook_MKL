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
		//0�� ������ �������� �ݺ������� �޴��� ���
		
		printMenu();
	}
	static void printMenu() {
		Scanner scan = new Scanner(System.in);
		int userInput = 0;
		while(true) {
		System.out.println("*****�޴� ���*****");
		System.out.println("1. ��ȭ��ȣ �߰�");
		System.out.println("2. ��ȭ��ȣ ��� ��ȸ");
		System.out.println("3. �� �ο��� Ȯ��");
		System.out.println("0. ���α׷� ����");
		System.out.println("================");
		System.out.print("��ȣ�� �Է��ϼ��� : ");
		userInput = scan.nextInt();
		
		//0�� �ȴ����ٸ�? ���α׷��� ��� �����ְԵ�.
		
		if(userInput == 0) {
			System.out.println("���α׷��� �����մϴ�.");
			break;
		}
		else if (userInput == 1) {
			//��ȭ��ȣ �߰� �޴� �ڵ� �ۼ�
			addPhoneNum();
		}
		else if (userInput == 2) {
			//��ȭ��ȣ ��� ��ȸ �ڵ� �ۼ�
			printPhoneNumList();
		}
		else if (userInput == 3) {
			//��ȭ��ȣ�ο� ����� ����Ǿ� �ִ��� Ȯ��.
			showPeopleCount();
			
		}
		else {
			System.out.println("�߸��� �Է��Դϴ�.");
		}
		}
		}
	
	static void addPhoneNum() {
		//������ �Է¹޾Ƽ� �ؽ�Ʈ���Ͽ� �߰�
		Scanner scan = new Scanner(System.in);
		System.out.print("�̸��� �Է��� �ּ���. ");
		String name = scan.nextLine();
		
		System.out.print("������ �Է��� �ּ���. ");
		String phoneNum = scan.nextLine();
		
		System.out.print("�̸� : " + name);
		System.out.print("���� : " + phoneNum);
		//���� ��� �κ� �ۼ� ����.
		File f = new File("C:/temp/phonebook.csv");
		try {
			FileWriter fw = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			//bw�� �̿��ؼ� ����� ����: �̹α� 01040758184 2018-12-02 ���� 12:07
			
			//Calendar ������ �ϳ� ���� ����ð� ����
			Calendar cal = Calendar.getInstance();
			//SimpleDateFormat�� ���� ��� ����
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD a h:mm");
			
			//������� ����� String���� ����
			String dateStr = sdf.format(cal.getTime());
			
			//String���� ������ �ð���� ���
			System.out.println(dateStr);
			
			//"�̸�,����ó,�Ͻ�" ��� bw�� �̿��� ���Ͽ� ���.
			bw.write(name + "," + phoneNum +"," + dateStr);
			//�ٹٲ��� �Ͼ�� ������, �������� ���� ���� ����
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
		
		//�����͸� ���� �ٽ� ����.
		peopleList.clear();
		
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				//�о�� line�� ������ �����ؼ�  Person Ŭ������ �߰�.
				
				//,�� ���� �迭 ����
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
				//peopleList �迭�� �ϳ��ϳ� ����
				//Person �迭 ���� => �߰�
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
		System.out.println("��ȭ��ȣ�δ� �� "+peopleCount + "���� ��ϵǾ� �ֽ��ϴ�.");
	}

}

