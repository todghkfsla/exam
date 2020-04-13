import javax.swing.JFrame;

class TestJFrame extends JFrame{ //jframe 상속받는다.

    public TestJFrame() { //생성자를 만든다.

        setTitle("TEST JFRAME"); //창 제목

        setSize(300, 300); //창 사이즈

        setVisible(true); //보이기

    }



    public static void main(String[] args) {

        // TODO Auto-generated method stub

        new TestJFrame(); //생성자 호출

    }

}