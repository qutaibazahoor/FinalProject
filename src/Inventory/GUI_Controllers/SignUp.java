package Inventory.GUI_Controllers;

import Inventory.Models.UserModel;
import Inventory.Services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame {
    JTextField name=new JTextField();
    JTextField userName=new JTextField();
    JPasswordField password=new JPasswordField();
    String[] roles={"Admin","User"};
    JComboBox<String> role=new JComboBox<>(roles);
    JButton signup=new JButton("SignUp");
    JLabel nameLabel=new JLabel("Name");
    JLabel userNameLabel=new JLabel("username");
    JLabel passwordLabel=new JLabel("password");
    JLabel roleLabel=new JLabel("role");
    JLabel signUp=new JLabel("Signup");
    ImageIcon img=new ImageIcon("C:\\Users\\Qutaiba Zahoor\\IdeaProjects\\InventoryManagementSystem\\src\\Inventory\\Resources\\vectorBackground.png");
    JLabel background =new JLabel("",img,JLabel.CENTER);
    JButton back=new JButton("<<back");

    JCheckBox showPassword=new JCheckBox("Show Password");

    public SignUp(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(383,100,600,468);

        background.setBounds(0,0,600,468);

        name.setSize(200,30);
        name.setLocation(200,99);

        userName.setSize(200,30);
        userName.setLocation(200,159);

        role.setSize(200,30);
        role.setLocation(200,219);

        password.setSize(200,30);
        password.setLocation(200,279);

        signup.setSize(100,30);
        signup.setLocation(250,339);
        signup.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        signup.setOpaque(false);

        signUp.setBounds(220,30,300,50);
        signUp.setFont(new Font(Font.MONOSPACED,Font.BOLD,44));

        userNameLabel.setBounds(130,159,200,30);
        userNameLabel.setFont(new Font(Font.SERIF,Font.ITALIC,16));

        nameLabel.setBounds(130,99,200,30);
        nameLabel.setFont(new Font(Font.SERIF,Font.ITALIC,16));

        roleLabel.setBounds(130,219,200,30);
        roleLabel.setFont(new Font(Font.SERIF,Font.ITALIC,16));

        passwordLabel.setBounds(130,279,200,30);
        passwordLabel.setFont(new Font(Font.SERIF,Font.ITALIC,16));

        back.setSize(100,30);
        back.setLocation(20,390);
        back.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        back.setOpaque(false);

        showPassword.setSize(150,30);
        showPassword.setLocation(200,309);
        showPassword.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        showPassword.setOpaque(false);

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(name.getText().isEmpty()||userName.getText().isEmpty()||password.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(),"None of the fields can be null ","Warning",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                UserModel userModel=new UserModel();
                userModel.setName(name.getText());
                userModel.setPassword(password.getText());
                userModel.setRole(role.getSelectedItem().toString());
                userModel.setUserName(userName.getText());
                UserService userService=new UserService();
                userService.save(userModel);
                setVisible(false);
                new Login();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login();
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
        add(name);
        add(password);
        add(showPassword);
        add(role);
        add(signup);
        add(roleLabel);
        add(passwordLabel);
        add(nameLabel);
        add(userNameLabel);
        add(signUp);
        add(back);
        add(background);

        setVisible(true);

    }

}
