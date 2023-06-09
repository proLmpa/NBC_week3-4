package ui;

import entity.*;

import java.util.*;
import java.util.regex.Pattern;

public class Home {
    private Hotel hotel;
    private Scanner sc;

    public Home() {
        hotel = new Hotel();
        sc = new Scanner(System.in);
    }

    public void start() {
        Master master = new Master(hotel);
        Client client = new Client(hotel);

        while(true) {
            printMainMenu();

            System.out.print("번호를 입력해주세요 (종료: 0): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    master.start();
                    break;

                case 2:
                    // customer 등록
                    Customer customer = registerCustomer();

                    // client 시작, 종료 시 customer 정보 가져오기
                    customer = client.start(customer);

                    // 종료 시 customer 정보를 기존 고객 정보에 저장하기
                    updateCustomer(customer);
                    break;
                case 0:
                    System.out.println("호텔 예약 프로그램을 종료합니다.");
                    return;
            }

        }
    }

    public void printMainMenu() {
        System.out.println("[Hotel 메인 메뉴]");
        System.out.println("1. Master \t 2. Client");
    }

    public Customer registerCustomer(){
        System.out.println("--------------------------------");
        System.out.println("[Hotel 로그인 화면]");
        String name = askName();            // 이름 묻기
        String phoneNum = askPhoneNum();    // 전화번호 묻기
        Customer customer = new Customer(name, phoneNum);

        float wallet = askWallet(customer); // 소지금 묻기
        customer.setWallet(customer.getWallet() + wallet);  // 기존 소지금에 새 소지금을 추가하기

        return customer;
    }

    public void updateCustomer(Customer customer) {
        String key = customer.getName()+customer.getPhoneNum();
        hotel.putCustomerWallet(key, customer.getWallet());
        hotel.setCustomerReservations(key, customer.getReservations());
    }

    public String askName(){
        System.out.print("이름: ");
        return sc.nextLine();
    }

    public String askPhoneNum() {
        System.out.print("전화번호 (ex. 012-3456-7890): ");
        String phoneNum = sc.nextLine();

        String regex_phoneNum = "^01([0-9])-?([0-9]{4})-?([0-9]{4})$";
        while(!Pattern.matches(regex_phoneNum, phoneNum)){
            System.out.println("전화번호의 형식이 일치하지 않습니다. 다시 입력해주세요.");
            System.out.print("전화번호 (ex. 012-3456-7890): ");
            phoneNum = sc.nextLine();
        }
        return phoneNum;
    }

    public float askWallet(Customer customer) {
        String key = customer.getName() + customer.getPhoneNum();
        if(hotel.hasHistory(key)){
            customer.setWallet(hotel.getCustomerWallet(key));
            customer.setReservations(hotel.getCustomerReservations(key));

            System.out.printf("*기존에 가입한 계정이 있습니다. 소지 금액은 %.0f 입니다*\n", customer.getWallet());
            System.out.print("얼마의 소지금을 추가하시겠습니까? : ");
            return sc.nextFloat();
        } else {
            System.out.print("얼마를 소지하고 계신가요?: ");
            return sc.nextFloat();
        }
    }
}