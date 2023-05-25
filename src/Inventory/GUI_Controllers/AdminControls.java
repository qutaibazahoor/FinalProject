package Inventory.GUI_Controllers;

import Inventory.Services.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.SystemColor.text;

public class AdminControls extends JFrame {
    ImageIcon img=new ImageIcon("C:\\Users\\Qutaiba Zahoor\\IdeaProjects\\InventoryManagementSystem\\src\\Inventory\\Resources\\vectorBackground.png");
    JLabel background =new JLabel("",img,JLabel.CENTER);
    UserService userService=new UserService();
    JTextField search=new JTextField();
    JButton remove=new JButton("Remove");
    JButton searchButton=new JButton("Search User");
    JLabel searchLabel =new JLabel("Enter user id");
    JLabel label1 =new JLabel("Admin");
    JLabel label2 =new JLabel("Controls");
    JMenuBar MenuBar=new JMenuBar();
    JMenu menuItem=new JMenu("Options");
    JMenuItem profile=new JMenuItem("Profile");
    JMenuItem items=new JMenuItem("Items");
    JMenuItem stores=new JMenuItem("Stores");
    JMenuItem reports=new JMenuItem("Reports");
    JMenuItem signOut=new JMenuItem("SignOut");
    JMenuItem Menu=new JMenuItem("Menu");
    JMenuItem AdminControls=new JMenuItem("AdminControls");
    JLabel idColumn=new JLabel("    ID");
    JLabel nameColumn=new JLabel("    NAME");
    JLabel roleColumn =new JLabel("     ROLE");
    JLabel userNameColumn=new JLabel("  USERNAME");



    public AdminControls(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(383,100,600,468);

        background.setBounds(0,0,600,468);

        remove.setSize(100,30);
        remove.setLocation(130,380);
        remove.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        remove.setOpaque(false);

        search.setSize(200,30);
        search.setLocation(370,239-20);

        searchButton.setSize(100,30);
        searchButton.setLocation(420,339-50);
        searchButton.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        searchButton.setOpaque(false);

        searchLabel.setSize(200,30);
        searchLabel.setLocation(370,195);
        searchLabel.setFont(new Font(Font.MONOSPACED,Font.ITALIC,12));

        label1.setBounds(400,50,300,50);
        label1.setFont(new Font(Font.MONOSPACED,Font.BOLD,44));
        label2.setBounds(365,100,300,50);
        label2.setFont(new Font(Font.MONOSPACED,Font.BOLD,44));
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

        idColumn.setBounds(20,27,79,20);
        idColumn.setFont(new Font(Font.MONOSPACED,Font.BOLD,12));
        idColumn.setBackground(Color.WHITE);
        idColumn.setOpaque(true);

        nameColumn.setBounds(100,27,79,20);
        nameColumn.setFont(new Font(Font.MONOSPACED,Font.BOLD,12));
        nameColumn.setBackground(Color.WHITE);
        nameColumn.setOpaque(true);

        roleColumn.setBounds(260,27,79,20);
        roleColumn.setFont(new Font(Font.MONOSPACED,Font.BOLD,12));
        roleColumn.setBackground(Color.WHITE);
        roleColumn.setOpaque(true);

        userNameColumn.setBounds(180,27,79,20);
        userNameColumn.setFont(new Font(Font.MONOSPACED,Font.BOLD,12));
        userNameColumn.setBackground(Color.WHITE);
        userNameColumn.setOpaque(true);

        String [] header={"id","name","username","role"};
        DefaultTableModel model = new DefaultTableModel(userService.populateTable(),header);
        JTable userList = new JTable(model);
        userList.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        userList.setBounds(20,50,320,300);

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = userList.getSelectedRow();
                String value = userList.getModel().getValueAt(row, column).toString();
                userService.delete(Integer.valueOf(value));
                userList.setModel(new DefaultTableModel(userService.populateTable(),header));
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer.parseInt(search.getText());

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(new JFrame(),"Please Enter id only","Warning",JOptionPane.ERROR_MESSAGE);
                    return;
                }

            int rowCount = model.getRowCount();

                for (int row = 0; row < rowCount; row++) {
                    int columnCount = model.getColumnCount();

                    for (int column = 0; column < columnCount; column++) {
                        Object cellValue = model.getValueAt(row, column);

                        if (cellValue != null && cellValue.toString().equals(search.getText())) {

                            userList.changeSelection(row, column, false, false);
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(new JFrame(),"No Record Found","Warning",JOptionPane.ERROR_MESSAGE);
            }
        });

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
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new UserProfile();
            }
        });
        items.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new items();
            }
        });



        add(idColumn);
        add(userNameColumn);
        add(nameColumn);
        add(roleColumn);
        add(MenuBar);
        add(label1);
        add(label2);
        add(remove);
        add(userList);
        add(search);
        add(searchButton);
        add(searchLabel);
        add(background);
        setVisible(true);
    }


}