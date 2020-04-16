package 자판기;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VendingMachine extends JFrame{
   private TitleLabel tl = new TitleLabel(); //자판기 메인 타이틀(라면 그림 디자인의 패널)
   private MoneyHole mh = new MoneyHole();   //돈 투입 패널
   private FoodButtons fm = new FoodButtons();      //메뉴 버튼 패널
   private Chages ch = new Chages();    //잔돈 반환 패널
   
   private MyDialog dialog;   //비밀번호를 입력하는 다이얼로그 설정
   
   static int UserMoneySum = 0;  //이용자 돈 초기화
   private int [] foodPrice = {1500,1500,1500,1700,1700,1700,1900,1900,1900};  //각 라면의 가격
   private int [] foodNum = {10,10,10,10,10,10,10,10,10};  //라면의 각 개수를 10개로 초기화
   private int MoneySum = 0;  //총 매출 초기화
   private int [] cell = {0,0,0,0,0,0,0,0,0}; //라면 판매 갯수 초기화

   static JLabel sumLabel = new JLabel("     Now " + UserMoneySum + "  won" );   //다양한 패널들에게 전체적으로 유저의 현재돈이 표시되도록 static 설정
   
   static ImageIcon [] images = { new ImageIcon("images\\신라면봉지.png"), new ImageIcon("images\\안성탕면봉지.png"),new ImageIcon("images\\열라면봉지.png")
         ,new ImageIcon("images\\너구리봉지.png"),new ImageIcon("images\\짜파게티봉지.png"),new ImageIcon("images\\불닭볶음면봉지.png")
         ,new ImageIcon("images\\진짬뽕봉지.png"),new ImageIcon("images\\무파마봉지.png"),new ImageIcon("images\\참께라면봉지.png")
   };  // 자판기 메뉴에 나타나는 라면 봉지 이미지
   static ImageIcon [] imagesF = { new ImageIcon("images\\신라면음식.png"), new ImageIcon("images\\안성탕면음식.png"),new ImageIcon("images\\열라면음식.png")
         ,new ImageIcon("images\\너구리음식.png"),new ImageIcon("images\\짜파게티음식.png"),new ImageIcon("images\\불닭볶음면음식.png")
         ,new ImageIcon("images\\진짬뽕음식.png"),new ImageIcon("images\\무파마음식.png"),new ImageIcon("images\\참께라면음식.png")
   };  // 자판기 메뉴의 선택 시 나타나는 완성된 라면 이미지
   
   private String passWord = "123456";  //매출 및 판매현황 메뉴에 들어가기 위한 비밀번호 설정
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   public VendingMachine() 
   {
      setTitle("라면 자판기");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container c = getContentPane();
      c.setLayout(null);
      
      createMenu();
      
      setSize(800,800);
      
      tl.setBounds(0, 0, getWidth(), 100);           //각  패널들을 프레임의 크기에 맞게 설정함.
      mh.setBounds(0, 100, getWidth(), 50);
      fm.setBounds(0, 150, getWidth(), 500);
      ch.setBounds(0, 680, getWidth(), 300);
      
      dialog = new MyDialog(this, "비밀번호 입력");          //다이얼로그 설정
      
      c.add(tl);
      c.add(mh);
      c.add(fm);
      c.add(ch);
      
      setVisible(true);
   }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   class MyDialog extends JDialog {      //비밀번호 입력을 위한 다이얼 로그
      private JTextField tf = new JTextField(10);          //다이얼로그 입력 길이 (10)
      private JButton okButton = new JButton("OK");    //OK버튼 부착

      public MyDialog(JFrame frame, String title) {
         super(frame,title);
         setLayout(new FlowLayout());
         add(tf);
         add(okButton);
         setSize(200, 100);

         okButton.addActionListener(new ActionListener() {        //OK버튼을 누르면 반응함
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
               if(passWord.equals( dialog.getInput()))         //설정한 패스워드(line 32)와 다이얼로그 텍스트 필드에 입력한 값이 같으면 매출 및 판매현황이 보임.
               {
                  new Sales();
                  tf.setText("");       //다이얼 로그에 입력후 입력창(레이블)을 빈칸으로 리셋
               }
               else if(tf.getText().length() == 0)
               {
                  JOptionPane.showMessageDialog(null, "비밀번호를 입력하지 않았습니다. 입력해 주세요", "비밀번호 오류!", JOptionPane.ERROR_MESSAGE);   //비밀번호를 입력하지 않으면 경고메세지 출력
                   setVisible(true);
                   tf.setText("");      //다이얼 로그에 입력후 입력창(레이블)을 빈칸으로 리셋
               }
               else
                {
                   JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다. 다시 입력해 주세요", "비밀번호 오류!", JOptionPane.ERROR_MESSAGE);   //틀리면 경고메세지 출력
                   setVisible(true);
                   tf.setText("");      //다이얼 로그에 입력후 입력창(레이블)을 빈칸으로 리셋
                }
            }
         });
      }
      
      public String getInput() {
         if(tf.getText().length() == 0) return null;    //다이얼로그에 입력받는 값을 호출 할 수 있도록 메소드 설정
         else return tf.getText();
      }

   }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   

   private void createMenu()    // "매출 및 판매현황" , "나가기" 두가지 메뉴를 만드는 메소드
   {
      JMenuBar mb = new JMenuBar();
      JMenuItem [] menuItem = new JMenuItem[2];
      String [] itemTitle = {"매출 및 판매현황" , "나가기"};         
      JMenu screenMenu = new JMenu("메뉴");
      
      MenuActionListener listener = new MenuActionListener();
      for(int i=0; i<menuItem.length; i++)              //각 메뉴아이템을 메뉴와 리스너에 달기 
      {
         menuItem[i] = new JMenuItem(itemTitle[i]);   
         menuItem[i].addActionListener(listener);     //리스너달기
         screenMenu.add(menuItem[i]);                 //메뉴에 부착
      }
      mb.add(screenMenu);      //메뉴바에 부착
      setJMenuBar(mb);
   }
   
   class MenuActionListener implements ActionListener {
       public void actionPerformed(ActionEvent e)
       {
          String cmd = e.getActionCommand();
          switch(cmd)
          {
             case "매출 및 판매현황" :                     //해당 메뉴를 선택하면 바로 매출 및 판매현황이 나타나지 않고 다이얼로그가 실행되어 <비밀번호>를 입력 할 수 있음.
                dialog.setVisible(true);
                break;
                
             case "나가기" :
                System.exit(0);         //프로그램 종료
                break;
          }
       }
   }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
   class Sales extends JFrame {
      private JPanel jp1 = new JPanel();       //자판기 메뉴의 각 사진과 재고 및 판매량 (패널)
      private JPanel jp2 = new JPanel();       //재고 초기화 및 총 매출 현황 (패널)
      private JLabel [] num =  new JLabel[9]; 
      private JLabel money;
      private JButton reset;
      
      public Sales() 
      {
         setTitle("매출 및 판매현황");
         setLayout(new BorderLayout(0,20));
         jp1.setLayout(new GridLayout(3, 3, 5,5));
         for(int i = 0 ; i<9; i++ ) 
         {
            num[i] = new JLabel("<html>판매 개수: " +cell[i]+ "  개" + "<br><br>   재고: " + foodNum[i]+ "  개", images[i] , SwingConstants.CENTER);   
            jp1.add(num[i]);      //판매개수 와 재고 현황 등을 일목요연하게 확인 할 수 있음  <html>/ <br>이 소스코드는 라벨 내의 텍스트를 한줄 띄워줌.
         }
         //
         reset = new JButton("재고 초기화 (10개)");
         reset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               for(int i = 0 ; i<9; i++ ) 
               {
               foodNum[i] = 10;    //재고를 10개로 초기화 시켜주는 코드
               num[i].setText("<html>판매 갯수: " +cell[i]+ "  개" + "<br><br>   재고: " + foodNum[i]+ "  개");
               }
            }             //재고를 모두 10개로 리셋 시켜주며/ 리셋과 동시에 (리셋상태로)텍스트 수정
         });
         //
         
         money = new JLabel("          총 판매 매출은  " + MoneySum + "  입니다.          ");     //총 판매매출을 보여주는 라벨
         money.setOpaque(true);
         money.setBackground(Color.LIGHT_GRAY);
         
         jp2.add(reset);     
         jp2.add(money);    //아래 패널에 리셋버튼과 매출현황 라벨을 추가 시킨다.
         
         add(jp1,BorderLayout.CENTER);
         add(jp2,BorderLayout.SOUTH);
      
         setSize(1100,600);
         setVisible(true);
      }
      
   }

   
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   class TitleLabel extends JPanel {
      
      private ImageIcon icon = new ImageIcon("images\\타이틀.png");          //라면 자판기 디자인이 되어있는 이미지 (자판기 가장 상단 패널에 부착)
      private Image img = icon.getImage();
      
      public void paintComponent(Graphics g)
      {
         super.paintComponent(g);
         g.drawImage(img, 0, 0, getWidth(), getHeight(), this);  
      }
      
   }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   class MoneyHole extends JPanel {
   
      private JButton [] btn = { new JButton("10,000 won"), new JButton("5,000 won"), new JButton("1,000 won"),new JButton("500 won")};   //돈을 투입할 수 있도록 각 금액별 버튼을 설정
      
      public MoneyHole() {
         MyMoneyListener listener = new MyMoneyListener();
         for(int i=0; i<4; i++)              //돈 투입 버튼 (10,000원 , 5,000원, 1,000원 , 500원) 
         {
            btn[i].setBackground(Color.ORANGE);
            btn[i].setFont(new Font("Arial", Font.BOLD, 20));
            add(btn[i]);
            btn[i].addActionListener(listener);
         }
         sumLabel.setFont(new Font("Arial", Font.BOLD, 20));
         add(sumLabel);    //이용자의 돈이 표시되는 라벨 부착
         
         setBackground(Color.YELLOW);
      }
      
      class MyMoneyListener implements ActionListener {        // 금액 버튼을 누르면 해당 금액 만큼 이용자의 돈이 올라간다.
         
         public void actionPerformed(ActionEvent e)
         {
            JButton b = (JButton)e.getSource();
            if(b == btn[0])
            {
               UserMoneySum += 10000;     //10000원 투입
            }
            else if(b == btn[1])
            {
               UserMoneySum += 5000;     //5000원 투입
            }
            else if(b == btn[2])
            {
               UserMoneySum += 1000;     //1000원 투입
            }
            else
            {
               UserMoneySum += 500;     //500원 투입
            }
            sumLabel.setText("     Now " + UserMoneySum + "  won");      //이용자의 현재 투입금액을 볼 수 있도록 설정
         }
      }
      
   }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   class FoodButtons extends JPanel {
      private JButton [] btn = new JButton[9];
      private JLabel [] la = new JLabel[9];
      
      public FoodButtons() 
      {
         setLayout(new GridLayout(3, 3, 5, 5));
         int price = 1500;
         MyMenuListener listener = new MyMenuListener();
         
         for(int i = 0 ; i<9; i++ )       // 자판기에 판매되는 라면(이미지)을 정렬 부착 
         {
            if(i>2) price = 1700;     //가장 상위 칸 1500원, 두번째 칸 1700원, 세번째 칸1900원
            if(i>5) price = 1900;
            
            btn[i] = new JButton(price+"원", images[i]);   
            add(btn[i]);
            btn[i].addActionListener(listener);    //자판기 메뉴 버튼에 리스너 달기
         }
         
         setBackground(Color.ORANGE);
         }
      class MyMenuListener implements ActionListener{
         
         public void actionPerformed(ActionEvent e)
         {
            
            for(int i = 0 ; i<9; i++ )
            {
               if(UserMoneySum >= foodPrice[i])
               {
                  JButton b = (JButton)e.getSource();
                  if(b == btn[i])
                     {
                     if(foodNum[i] > 0) 
                        {
                        Foods fd = new Foods();         //메뉴 버튼을 누르면 해당 라면의 완성품이 새로운 프레임과 함께 나오도록 설정
                        fd.add(new JLabel(imagesF[i]));  //선택한 라면의 완성품 이미지
                        
                        UserMoneySum -= foodPrice[i];    //이용자의 돈이 선택한 메뉴의 액수만큼 차감
                        foodNum[i]--;                 //재고 반영
                        MoneySum += foodPrice[i];         //매출 반영
                        cell[i]++;                 //판매 갯수 반영
                        
                        sumLabel.setText("     Now " + UserMoneySum + "  won");        //이용자의 돈이 새롭게 set(설정)
                        break;
                        }
                     else 
                        {
                        JOptionPane.showMessageDialog(null, "재고가 없습니다. 물품을 채워 넣으세요.", "재고 부족!", JOptionPane.ERROR_MESSAGE);
                        break; //재고가 없으면 채워넣으라는 경고 메세지 다이얼로그
                        }
                     }      
               }
               else 
               {
                  JOptionPane.showMessageDialog(null, "금액이 부족합니다! 돈을 더 투입하세요", "잔액 부족!", JOptionPane.ERROR_MESSAGE);
                  break;  //금액이 부족하면 돈을 투입하라는 경고 메세지 다이얼로그
               }
            }   
            
         }
      }
}
   
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   class Foods extends JFrame {           //선택한 라면의 완성품이 이 프레임에서 나옴
      public Foods() 
      {
         setTitle("라면 완성!  맛있게 드세요~!");
         Container c = getContentPane();
         c.setLayout(new FlowLayout());
         
         setSize(400,300);
         setVisible(true);
      }
   }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   class Chages extends JPanel {
      private JButton btn = new JButton("잔돈 반환");              // 자판기 가장 하단부 패널에 잔돈 반환 버튼
      private JLabel la = new JLabel("");
      public Chages() 
      {   
         btn.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) 
            {
               la.setFont(new Font("Arial", Font.ITALIC, 20));
               
               if(UserMoneySum > 0)      //이용자의 금액이 0원보다 많으면 해당 금액이 반환되었다는 문구 출력
               {
                  la.setText(UserMoneySum + " won returned!  Thank you for using...");      
                  UserMoneySum=0;     //문구 출력과 함께 자판기에 보이는 이용자 금액은 0원으로 리셋
                  sumLabel.setText("     Now " + UserMoneySum + "  won");
               }
               else 
                  la.setText("NO MONEY!   Please use after confirmation...");       //자판기에 이용자의 돈이 없으면 돈이 없으니 확인후 이용해 달라는 문구 출력
            }
         });
         
         btn.setBackground(Color.LIGHT_GRAY);
         setBackground(Color.GRAY);
         add(btn); add(la);
      }
      
      
   }
   
////////////////////////////////////////////////////////  
   public static void main(String[] args) {
      new VendingMachine();

   }

}
