package Inventory.GUI_Controllers;

import Inventory.Services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.*;

public class Login extends JFrame {
    ImageIcon img=new ImageIcon("C:\\Users\\Qutaiba Zahoor\\IdeaProjects\\InventoryManagementSystem\\src\\Inventory\\Resources\\vectorBackground.png");
    JLabel background =new JLabel("",img,JLabel.CENTER);

    JButton loginButton=new JButton("Login");
    JButton signUpButton =new JButton("SignUp");
    JTextField userName=new JTextField();
    JPasswordField password=new JPasswordField();
    JCheckBox showPassword=new JCheckBox("Show Password");
    JLabel txt=new JLabel();
    JLabel login =new JLabel("Login");
    JLabel usernameLabel =new JLabel("username");
    JLabel passwordLabel=new JLabel("password");


    public Login(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(383,100,600,468);

        background.setBounds(0,0,600,468);

        userName.setSize(200,30);
        userName.setLocation(200,159);

        password.setSize(200,30);
        password.setLocation(200,239-20);

        loginButton.setSize(100,30);
        loginButton.setLocation(250,339-30);
        loginButton.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        loginButton.setOpaque(false);

        signUpButton.setSize(100,30);
        signUpButton.setLocation(250,419-50);
        signUpButton.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        signUpButton.setOpaque(false);

        showPassword.setSize(150,30);
        showPassword.setLocation(200,289-30);
        showPassword.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        showPassword.setOpaque(false);

        JButton button=new JButton("Forgot Password?");
        button.setBounds(430,419-50,120,30);
        button.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        button.setOpaque(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new RecoverAccount();
            }
        });

        txt.setBounds(50,419-50,150,30);
        txt.setText("      Don't have an account?");
        txt.setFont(new Font(Font.SERIF,Font.ITALIC,12));

        login.setBounds(230,80,300,50);
        login.setFont(new Font(Font.MONOSPACED,Font.BOLD,44));

        usernameLabel.setBounds(130,159,200,30);
        usernameLabel.setFont(new Font(Font.SERIF,Font.ITALIC,16));

        passwordLabel.setBounds(130,219,200,30);
        passwordLabel.setFont(new Font(Font.SERIF,Font.ITALIC,16));

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new SignUp();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserService userService=new UserService();
                if(userName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(),"UserName and Password are required","Warning",JOptionPane.ERROR_MESSAGE);
                    return;
                }else if(password.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(),"UserName and Password are required","Warning",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean c=userService.verifyLogin(password.getText(),userName.getText());
                if(c){
                    setVisible(false);
                    new Menu();
                }
            }
        });

        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( password.getEchoChar() != '\u0000' ) {
                    password.setEchoChar('\u0000');
                } else {
                    password.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                }

            }
        });

        add(userName);
        add(button);
        add(password);
        add(usernameLabel);
        add(passwordLabel);
        add(showPassword);
        add(loginButton);
        add(signUpButton);
        add(txt);
        add(login);
        add(background);

        setVisible(true);
    }
}
