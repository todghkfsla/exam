package 자판기;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 

 

public class Machine extends JFrame implements MouseListener{
    private JPanel Border = new JPanel(new BorderLayout());
    private JPanel Flow = new JPanel(new FlowLayout());
   
    private JButton 라면 = new JButton("라면 : 3000");
    private JButton 햄버거 = new JButton("햄버거 : 3500");
    private JButton 콜라 = new JButton("콜라 : 2500");
    private JButton 결정 = new JButton("결정");
    
    
    private JTextField j1 = new JTextField("0");  //판매금액
    private JTextField j2 = new JTextField("0");  //라면 판매수
    private JTextField j3 = new JTextField("0");  //햄버거 판매수
    private JTextField j4 = new JTextField("0");  //콜라 판매수
 
    private JLabel JL1 = new JLabel("라면");
    private JLabel JL2 = new JLabel("햄버거");
    private JLabel JL3 = new JLabel("콜라");
    ImageIcon img = new ImageIcon("images/자판기.png"); 
	JLabel imageLabel = new JLabel(img);

    
    private static int Num1=0;       //라면 총개수
    private static int Num2=0;       //햄버거 총개수
    private static int Num3=0;       //콜라 총개수
    private static int Num4=0;       //총개수
    
    public Machine(){
          super("자판기");
          init();
          start();
    }
    
  
    public void init(){
          this.setSize(550,400);     
          
          Flow.add(라면);              //라면 버튼을 추가.
          Flow.add(햄버거);             //햄버거 버튼을 추가.
          Flow.add(콜라);              //콜라 버튼을 추가.
          Flow.add(결정);              //결정
          Flow.add(imageLabel);                           
          
          
          this.add("North",Flow);
          this.add("South",j1);

          this.setVisible(true);
    }
    
    public void start(){
         
          라면.addMouseListener(this);
          햄버거.addMouseListener(this);
          콜라.addMouseListener(this);
          결정.addMouseListener(this);
    }
    

    public void mouseClicked(MouseEvent e) {
         
          int val=0;
          if(e.getSource()==(JButton)라면){
                 val = Integer.parseInt(j1.getText());
                 
                 //가격에 라면+
                 val=val+3000;
                 //라면 개수
                 this.Num1=this.Num1+1;
                 
                 j2.setText(String.valueOf(Num1));
                 j1.setText(String.valueOf(val));
                 }
          			else if(e.getSource()==(JButton)햄버거){
          			val = Integer.parseInt(j1.getText());
          			//가격에 햄버거+
          			val=val+3500;
          			//햄버거 개수
          			this.Num2=this.Num2+1;
          			j3.setText(String.valueOf(Num2));
          			j1.setText(String.valueOf(val));
          			}
          			else if(e.getSource()==(JButton)콜라){
          			val = Integer.parseInt(j1.getText());
          			//가격에 콜라+
          			val=val+2500;
          			//콜라 개수
          			this.Num3=this.Num3+1;
          			j4.setText(String.valueOf(Num3));
          			j1.setText(String.valueOf(val));
                    }
          			else if(e.getSource()==(JButton)결정){
                	  this.Num4=this.Num4+1;
                	  j1.setText(String.valueOf(val)); //결정
              
          }     
        
    }

  
    public void mouseEntered(MouseEvent e) {
     
    }
    public void mouseExited(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {
   
    }
    public void mouseReleased(MouseEvent e) {
          
    }
    public static void main(String[] args) {
   
        Machine machine=new Machine();
    }

}