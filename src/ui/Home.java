package ui;

import entity.*;

import java.util.Scanner;

public class Home {
    private Scanner sc;
    private MemoList memoList;

    public Home() {
        sc = new Scanner(System.in);
        memoList = new MemoList();
    }

    public void start() {
        while(true) {
            // 0. 메모장 옵션 출력
            printOptions();
            // 옵션 번호 입력받기
            int choice = selectNum();


            switch(choice) {
                // 1. 입력 (구현)
                case 1 -> {
                    // 이름, 비밀번호, 메모 입력받기
                    Memo memo = writeMemo();
                    // 글 생성 후 메모리스트에 저장
                    memoList.addMemo(memo);
                }

                // 2. 목록 보기 (구현)
                case 2 -> {
                    // 작성된 메모 최신순으로 출력하기
                    memoList.printMemoList();
                }

                // 3. 수정 (구현)
                case 3 -> {
                    // (수정할) 글 번호 입력 받기
                    // 존재 시 수정, 아닐 시 메시지 출력
                    while((choice = selectNum()) > memoList.getCount())
                        System.out.println("번호에 맞는 글이 존재하지 않습니다.");

                    // 비밀 번호 확인. 일치 시, 내용 수정. 불일치 시, 메시지 출력
                    memoList.editMemo(choice);
                }

                // 4. 삭제 (구현)
                case 4 -> {
                    // (삭제할) 글 번호 입력 받기
                    // 존재하지 않을 시, 아닐 시 메시지 출력
                    while((choice = selectNum()) > memoList.getCount())
                        System.out.println("번호에 맞는 글이 존재하지 않습니다.");

                    // 비밀 번호 확인. 일치 여부 판단 후 기능 실행
                    memoList.deleteMemo(choice);
                }

                // 5. 종료
                case 5 -> {
                    System.out.println("메모장을 종료합니다.");
                    System.exit(0);
                }
            }
        }
    }

    // 메모장 옵션 출력
    public void printOptions() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------\n");
        sb.append("\t[메모장 시작 페이지]\t\n");
        sb.append("1. 입력\n2. 목록 보기\n3. 수정\n4. 삭제\n5. 종료\n");
        sb.append("-------------------------\n");
        System.out.print(sb);
    }

    // 번호 입력받기
    public int selectNum() {
        System.out.print("번호를 입력해주세요: ");
        return sc.nextInt();
    }

    // 메모 작성하기 (구현)
    public Memo writeMemo() {
        // (1) 글 번호 불러오기
        // (2) 이름 입력
        // (3) 비밀번호 입력
        // (4) 내용 입력
        // (5) 입력된 내용 Memo 생성자로 전달 후 반환
        return null;
    }
}
