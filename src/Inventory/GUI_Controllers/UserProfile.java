package Inventory.GUI_Controllers;

import Inventory.Services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfile extends JFrame {
    JLabel userProfileLabel =new JLabel("Your Profile");
    ImageIcon img=new ImageIcon("C:\\Users\\Qutaiba Zahoor\\IdeaProjects\\InventoryManagementSystem\\src\\Inventory\\Resources\\vectorBackground.png");
    JLabel background =new JLabel("",img,JLabel.CENTER);
    JMenuBar MenuBar=new JMenuBar();
    JMenu menuItem=new JMenu("Options");
    JMenuItem profile=new JMenuItem("Profile");
    JMenuItem items=new JMenuItem("Items");
    JMenuItem stores=new JMenuItem("Stores");
    JMenuItem reports=new JMenuItem("Reports");
    JMenuItem signOut=new JMenuItem("SignOut");
    JMenuItem Menu=new JMenuItem("Menu");
    JMenuItem AdminControls=new JMenuItem("AdminControls");

    JTextField name=new JTextField();
    JTextField userName=new JTextField();
    JTextField role=new JTextField();
    JPasswordField password=new JPasswordField();
    JButton update=new JButton("update");
    JLabel nameLabel=new JLabel("Name");
    JLabel userNameLabel=new JLabel("username");
    JLabel passwordLabel=new JLabel("password");
    JLabel roleLabel=new JLabel("role");
    public UserProfile(){
        UserService userService=new UserService();
        String data[]=userService.getData();
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(383,100,600,468);

        userProfileLabel.setBounds(135,30,400,50);
        userProfileLabel.setFont(new Font(Font.MONOSPACED,Font.BOLD,44));

        background.setBounds(0,0,600,468);

        MenuBar.setBounds(0,0,600,20);
        MenuBar.add(menuItem);
        MenuBar.setOpaque(false);
        MenuBar.setBorderPainted(false);

        menuItem.add(profile);
        menuItem.add(reports);
        menuItem.add(items);
        menuItem.add(stores);
        menuItem.add(signOut);
        menuItem.add(Menu);
        if(UserService.user_role.equals("Admin")) {
            menuItem.add(AdminControls);
        }
        name.setSize(200,30);
        name.setLocation(200,99);
        name.setText(data[0]);

        userName.setSize(200,30);
        userName.setLocation(200,159);
        userName.setText(data[1]);
        System.out.println();

        role.setSize(200,30);
        role.setLocation(200,219);
        role.setText(data[2]);

        password.setSize(200,30);
        password.setLocation(200,279);
        password.setText(data[3]);

        userNameLabel.setBounds(130,159,200,30);
        userNameLabel.setFont(new Font(Font.SERIF,Font.ITALIC,16));

        nameLabel.setBounds(130,99,200,30);
        nameLabel.setFont(new Font(Font.SERIF,Font.ITALIC,16));

        roleLabel.setBounds(130,219,200,30);
        roleLabel.setFont(new Font(Font.SERIF,Font.ITALIC,16));

        passwordLabel.setBounds(130,279,200,30);
        passwordLabel.setFont(new Font(Font.SERIF,Font.ITALIC,16));

        update.setBounds(250,339,100,30);
        update.setFont(new Font(Font.SERIF,Font.ITALIC,11));

        Menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Menu();
            }
        });
        stores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new stores();
            }
        });
        signOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login();
            }
        });
        reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Reports();
            }
        });
        AdminControls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AdminControls();
            }
        });
        items.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new items();
            }
        });

        add(update);
        add(userName);
        add(name);
        add(role);
        add(nameLabel);
        add(userNameLabel);
        add(roleLabel);
        add(password);
        add(passwordLabel);
        add(userProfileLabel);
        add(MenuBar);
        add(background);
        setVisible(true);
    }
}
