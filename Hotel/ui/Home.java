package ui;

import entity.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.System.*;

public class Home {
    private Hotel hotel;
    private List<Customer> customers; // 고객 목록
    private Scanner sc;

    public Home() {
        hotel = new Hotel();
        customers = new ArrayList<>();
        sc = new Scanner(in);
    }

    public void start() {
        while(true){
            Customer customer = registerCustomer();

            out.println("-----------------------");
            boolean login = true;
            while(login) {
                int choice = displayOptions();
                switch(choice){
                    // 예약하기
                    case 1:
                        makeReservation();
                        break;

                    // 예약 확인
                    case 2:
                        checkReservation();
                        break;

                    // 예약 취소
                    case 3:
                        cancelReservation();
                        break;

                    // 나가기
                    case 4:
                        login = false;
                        break;
                }
            }
            out.println("-----------------------");
        }

    }

    public Customer registerCustomer(){
        out.println("[Hotel 로그인 화면]");
        String name = askName();
        String phoneNum = askPhoneNum();
        float wallet = askWallet();
        sc.nextLine();

        Customer customer = new Customer(name, phoneNum, wallet);
        customers.add(customer);
        return customer;
    }

    public String askName(){
        out.print("이름 : ");
        return sc.nextLine();
    }

    public String askPhoneNum() {
        out.print("전화번호 (ex. 012-3456-7890): ");
        String phoneNum = sc.nextLine();

        String regex_phoneNum = "^01([0|1|6|7|8|9])-?([0-9]{4})-?([0-9]{4})$";
        while(!Pattern.matches(regex_phoneNum, phoneNum)){
            out.println("전화번호의 형식이 일치하지 않습니다. 다시 입력해주세요.");
            out.print("전화번호 (ex. 012-3456-7890): ");
            phoneNum = sc.nextLine();
        }
        return phoneNum;
    }

    public float askWallet() {
        out.print("얼마를 소지하고 계신가요? :");
        return sc.nextFloat();
    }

    public int displayOptions(){
        out.println("Hotel에 오신걸 환영합니다.");
        out.println("1. 예약하기 \t2. 예약 확인\t3. 예약 취소\t4. 나가기");

        int choice;
        out.print("번호를 입력하세요 : ");
        while((choice = sc.nextInt()) > 4){
            out.println("유효하지 않은 번호입니다.");
            out.print("번호를 입력하세요 : ");
        }
        sc.nextLine();
        return choice;
    }

    public void makeReservation() {
        // Hotel이 보유한 객실 출력 -- 이미 예약된 객실은 제외

        // 객실 선택 -- 객실 번호를 제대로 입력할 때까지 반복

        // 객실 가격과 고객 소지금을 비교하여 고객 소지금이 더 적다면 실패 메시지 띄우고 return

        // 고객 소지금이 더 많다면 해당 객실을 예약 상태로 바꾸고, 고객 소지금에서 객실 가격을 빼고, 객실 가격만큼을 호텔의 보유자산에 추가

        // 예약 번호(id <- uuid) 생성

        // Reservation을 생성 후 Hotel과 Customer의 예약 목록에 각각 추가

        // 예약 완료 메시지 출력
    }

    public void checkReservation() {
        // 고객의 예약 목록(List<Reservation>) 불러오기

        // 예약 번호(id) 입력 후 예약 목록에서 해당 예약 정보를 받아오기
    }

    public void cancelReservation() {
        // 고객의 예약 목록(List<Reservation>) 불러오기

        // 예약 번호(id) 입력 후 예약 목록에서 해당 예약 정보 삭제하기

        // 객실의 상태를 예약 가능으로 바꾸고, 호텔의 보유자산을 객실 가격 만큼 빼고, 고객의 소지금에 다시 추가한다
    }
}