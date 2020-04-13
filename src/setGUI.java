
        package JavaBasic;
        import java.awt.*;
        import javax.swing.*;



        class Jmain {

        static class setGUI extends JFrame{

        // 생성자를 통해 GUI 초기 세팅을 해준다.
        setGUI(){

        // 윈도우 제목(Title)을 생성
        setTitle("Songdo Matjib");

        // 종료 버튼 생성
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 이 부분부터 원하는 버튼, 레이블, 콤보박스 등등 설정

        // Windows 배경색을 지정하기 위해
        // 페인(pane)을 getContentPane()으로 구한다.
        // pane을 이용하면 this.~를 c.~로 변경이 가능하다.

        Container c = this.getContentPane();

        // 회색으로 배경을 지정한다.
        c.setBackground(Color.YELLOW);

        // FlowLayout 레이아웃을 지정한다.
        this.setLayout(new FlowLayout());

        JButton btn1 = new JButton("버튼1");
        btn1.setBackground(Color.BLACK);
        btn1.setForeground(Color.MAGENTA);
        this.add(btn1);

        JButton btn2 = new JButton("버튼2");
        // 폰트 :: 맑은고딕, 볼드체, 크기는 30
        btn2.setFont(new Font("맑은고딕", Font.BOLD, 30));

        // 버튼2 위에 마우스를 올리면 대기중인 커서 모양으로 되도록 설정한다.
        btn2.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        this.add(btn2);

        JButton btn3 = new JButton("버튼3");
        // 3번 버튼은 비활성화 시킨다.
        btn3.setEnabled(false);
        this.add(btn3);


        // 윈도우 창 크기 설정(가로, 세로)
        setSize(500, 500);

        // 이 메소드를 이용해야 윈도우 창이 나타난다.
        setVisible(true);
        }
        }


        public static void main(String[] args){

        new setGUI();
        }
        }

//