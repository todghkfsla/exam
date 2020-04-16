import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class Project extends JFrame{

    public void main(){
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel northPanel = new JPanel(new GridLayout(0,3));
        northPanel.add(new MenuPanel("imagePath"));
        northPanel.add(new MenuPanel("imagePath"));
        northPanel.add(new MenuPanel("imagePath"));

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(northPanel,BorderLayout.NORTH);
        this.setVisible(true);
    }


    public class MenuPanel extends JPanel {
        JButton button1= new JButton("+");
        JButton button2= new JButton("-");

        public MenuPanel(String imagePath) {
            this.setLayout(new BorderLayout());
            JLabel label= new JLabel(imagePath);
            this.add(label,BorderLayout.NORTH);
            this.setBorder(new LineBorder(Color.BLACK));

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(button2);
            buttonPanel.add(button1);
            this.add(buttonPanel,BorderLayout.SOUTH);
        }



    }



    public static void main(String args[]) {

        Project project = new Project();
        project.main();

    }

}
/*
package 스윙;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class pp2  extends JFrame{

JTextField tf[]=new JTextField[8];

JTextField source=new JTextField();

JLabel la[]=new JLabel[8];

JButton Calcbtn=new JButton("구매");

JLabel 금액=new JLabel("투입금액");

Container contentPane;


pp2()

{

setBackground(Color.PINK);

setLayout(null);


// 금액 레이블

JLabel 금액 = new JLabel("투입금액");

금액.setHorizontalAlignment(JLabel.RIGHT);

금액.setSize(50, 20);

금액.setLocation(20, 20);

add(금액);


// 금액을 입력하는 JTextField

source = new JTextField(30);

source.setSize(100, 20);

source.setLocation(100, 20);

add(source);


// 계산 버튼

JButton calcBtn = new JButton("구매");

calcBtn.setSize(70, 20);

calcBtn.setLocation(210, 20);

add(calcBtn);



setTitle("Practice 3");

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

String[] text={"오만원","만원","천원","500원","100원","50원","10원","1원"};

int []unit={50000,10000,1000,500,100,50,10,1};

for(int i=0;i<unit.length;i++)

{

la[i]=new JLabel(text[i]);

la[i].setHorizontalAlignment(JLabel.RIGHT);

la[i].setSize(50,20);;

la[i].setLocation(50,50+i*20);;

add(la[i]);

tf[i]=new JTextField(30);

tf[i].setHorizontalAlignment(JLabel.RIGHT);

tf[i].setSize(70,20);;

tf[i].setLocation(120,50+i*20);

add(tf[i]);

}


calcBtn.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e){

String str=source.getText();

if(str.length()==0) return;

int money=Integer.parseInt(str);

int res;

for(int i=0;i<unit.length;i++)

{

res=money/unit[i];

tf[i].setText(Integer.toString(res));

if(res>0) money=money%unit[i];

}

}



});

setSize(300,300);

setVisible(true);

}

public static void main(String[] args)

{

new pp2();



}
}
*/



