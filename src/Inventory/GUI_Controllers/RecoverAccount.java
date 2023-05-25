package Inventory.GUI_Controllers;

import Inventory.Services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecoverAccount extends JFrame {
    ImageIcon img=new ImageIcon("C:\\Users\\Qutaiba Zahoor\\IdeaProjects\\InventoryManagementSystem\\src\\Inventory\\Resources\\vectorBackground.png");
    JLabel background =new JLabel("",img,JLabel.CENTER);

    JButton enter=new JButton("Enter");
    JTextField uuid=new JTextField();

    JLabel recover =new JLabel("Recover Account");
    JLabel uid=new JLabel("Enter UUID");

    JButton back=new JButton("back");
    public static String var;

    public RecoverAccount(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(383,100,600,468);

        background.setBounds(0,0,600,468);

        uuid.setSize(200,30);
        uuid.setLocation(200,189);

        enter.setSize(100,30);
        enter.setLocation(250,239);
        enter.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        enter.setOpaque(false);


        recover.setBounds(100,80,400,50);
        recover.setFont(new Font(Font.MONOSPACED,Font.BOLD,44));

        uid.setBounds(100,189,200,30);
        uid.setFont(new Font(Font.SERIF,Font.ITALIC,16));

        back.setSize(100,30);
        back.setLocation(20,390);
        back.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        back.setOpaque(false);

        
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(uuid.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(),"field cannot be empty","Warning",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                UserService userService=new UserService();
                var=userService.accountRecovery(uuid.getText());
                if(!var.equals("")){
                    setVisible(false);
                    new NewPassword();
                }
        }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login();
            }
        });



        add(uuid);
        add(uid);
        add(back);
        add(recover);
        add(enter);
        add(background);

        setVisible(true);
    }
}
