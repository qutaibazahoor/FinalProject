package Inventory.GUI_Controllers;

import Inventory.Services.StoreService;
import Inventory.Services.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class stores extends JFrame {
    JLabel reportsLabel =new JLabel("Stores");
    ImageIcon img=new ImageIcon("C:\\Users\\Qutaiba Zahoor\\IdeaProjects\\InventoryManagementSystem\\src\\Inventory\\Resources\\vectorBackground.png");
    JLabel background =new JLabel("",img,JLabel.CENTER);
    JButton refresh =new JButton("Refresh");
    JMenuBar MenuBar=new JMenuBar();
    JMenu menuItem=new JMenu("Options");
    JLabel idColumn=new JLabel("       ID");
    JLabel nameColumn=new JLabel("     NAME");
    JLabel statusColumn=new JLabel("    QUANTITY");
    JMenuItem profile=new JMenuItem("Profile");
    JMenuItem items=new JMenuItem("Items");
    JMenuItem stores=new JMenuItem("Stores");
    JMenuItem reports=new JMenuItem("Reports");
    JMenuItem signOut=new JMenuItem("SignOut");
    JMenuItem Menu=new JMenuItem("Menu");
    JMenuItem AdminControls=new JMenuItem("AdminControls");
    JTextField search=new JTextField();
    JButton searchButton=new JButton("Search Item");
    JLabel searchLabel=new JLabel("Enter Id to search");
    public stores(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(383,100,600,468);

        reportsLabel.setBounds(375,50,300,50);
        reportsLabel.setFont(new Font(Font.MONOSPACED,Font.BOLD,44));

        background.setBounds(0,0,600,468);

        String[] names={"ID","NAME","QUANTITY"};
        StoreService storeService=new StoreService();
        DefaultTableModel model=new DefaultTableModel(storeService.populateTable(),names);
        JTable itemReport=new JTable(model);



        itemReport.setBounds(20,50,320,300);
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
        idColumn.setBounds(20,27,106,20);
        idColumn.setFont(new Font(Font.MONOSPACED,Font.BOLD,12));
        idColumn.setBackground(Color.WHITE);
        idColumn.setOpaque(true);

        nameColumn.setBounds(127,27,106,20);
        nameColumn.setFont(new Font(Font.MONOSPACED,Font.BOLD,12));
        nameColumn.setBackground(Color.WHITE);
        nameColumn.setOpaque(true);

        statusColumn.setBounds(234,27,106,20);
        statusColumn.setFont(new Font(Font.MONOSPACED,Font.BOLD,12));
        statusColumn.setBackground(Color.WHITE);
        statusColumn.setOpaque(true);
        if(UserService.user_role.equals("Admin")) {
            menuItem.add(AdminControls);
        }
        search.setSize(200,30);
        search.setLocation(370,239-20);

        searchButton.setSize(100,30);
        searchButton.setLocation(420,339-50);
        searchButton.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        searchButton.setOpaque(false);

        searchLabel.setSize(200,30);
        searchLabel.setLocation(370,195);
        searchLabel.setFont(new Font(Font.MONOSPACED,Font.ITALIC,12));

        refresh.setSize(100,30);
        refresh.setLocation(130,380);
        refresh.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        refresh.setOpaque(false);

        Menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Menu();
            }
        });
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new UserProfile();
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

                            itemReport.changeSelection(row, column, false, false);
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(new JFrame(),"No Record Found","Warning",JOptionPane.ERROR_MESSAGE);
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemReport.setModel(new DefaultTableModel(StoreService.populateTable(),names));
            }
        });

        add(idColumn);
        add(nameColumn);
        add(statusColumn);
        add(search);
        add(refresh);
        add(reportsLabel);
        add(searchButton);
        add(searchLabel);
        add(MenuBar);
        add(itemReport);
        add(background);
        setVisible(true);
    }
}
