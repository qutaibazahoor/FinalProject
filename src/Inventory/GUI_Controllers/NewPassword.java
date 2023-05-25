package Inventory.GUI_Controllers;

import Inventory.Services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPassword extends JFrame {
    ImageIcon img=new ImageIcon("C:\\Users\\Qutaiba Zahoor\\IdeaProjects\\InventoryManagementSystem\\src\\Inventory\\Resources\\vectorBackground.png");
    JLabel background =new JLabel("",img,JLabel.CENTER);

    JLabel pass =new JLabel("New Password");
    JTextField password=new JTextField();
    JButton enter=new JButton("Enter");

    public NewPassword(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(383,100,600,468);

        background.setBounds(0,0,600,468);

        password.setSize(200,30);
        password.setLocation(200,159);

        pass.setBounds(140,80,400,50);
        pass.setFont(new Font(Font.MONOSPACED,Font.BOLD,44));

        enter.setSize(100,30);
        enter.setLocation(250,200);
        enter.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        enter.setOpaque(false);

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(password.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(),"password is required","Warning",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                UserService userService=new UserService();
                boolean check=userService.updatePassword(RecoverAccount.var,password.getText());
                if(check){
                    setVisible(false);
                    new Login();
                }else {
                    JOptionPane.showMessageDialog(new JFrame(),"Error while updating","Error",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

            }
        });

        add(pass);
        add(enter);
        add(password);
        add(background);
        setVisible(true);
    }

}
