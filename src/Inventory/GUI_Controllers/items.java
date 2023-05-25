package Inventory.GUI_Controllers;

import Inventory.Models.ItemModel;
import Inventory.Services.ItemService;
import Inventory.Services.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class items extends JFrame {
    JLabel itemLabel =new JLabel("Items");
    JTextField name=new JTextField();
    String[] st={"available","unavailable"};
    JComboBox<String> status=new JComboBox<>(st);
    JButton add=new JButton("add");
    JButton delete=new JButton("delete");
    JButton update=new JButton("update");
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

    JLabel nameLabel=new JLabel("Enter Item Name");
    JLabel statusLabel=new JLabel("Select Item Status");

    ItemService itemService=new ItemService();

    JLabel idColumn=new JLabel("       ID");
    JLabel nameColumn=new JLabel("     NAME");
    JLabel statusColumn=new JLabel("    STATUS");


    public items(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(383,100,600,468);

        background.setBounds(0,0,600,468);
        name.setSize(200,30);
        name.setLocation(370,149);

        status.setSize(200,30);
        status.setLocation(370,239-20);

        add.setSize(100,30);
        add.setLocation(420,339-50);
        add.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        add.setOpaque(false);

        update.setSize(100,30);
        update.setLocation(420,419-70);
        update.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        update.setOpaque(false);

        delete.setSize(100,30);
        delete.setLocation(130,380);
        delete.setFont(new Font(Font.SERIF,Font.ITALIC,11));
        delete.setOpaque(false);

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
        itemLabel.setBounds(400,50,300,50);
        itemLabel.setFont(new Font(Font.MONOSPACED,Font.BOLD,44));

        nameLabel.setSize(200,30);
        nameLabel.setLocation(370,124);
        nameLabel.setFont(new Font(Font.MONOSPACED,Font.ITALIC,12));

        statusLabel.setSize(200,30);
        statusLabel.setLocation(370,195);
        statusLabel.setFont(new Font(Font.MONOSPACED,Font.ITALIC,12));

        String names[]={"id","Name","Status"};
        DefaultTableModel model = new DefaultTableModel(itemService.populateTable(),names);
        JTable itemsTable = new JTable(model);
        itemsTable.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        itemsTable.setBounds(20,50,320,300);

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

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = itemsTable.getSelectedRow();
                String value = itemsTable.getModel().getValueAt(row, column).toString();
                itemService.delete(Integer.valueOf(value));
                itemsTable.setModel(new DefaultTableModel(itemService.populateTable(),names));
            }
        });



        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemModel itemModel=new ItemModel();
                itemModel.setItemName(name.getText());
                if(name.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(),"Name cannot be null","Warning",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                itemModel.setItemStatus(String.valueOf(status.getSelectedItem()));
                itemService.save(itemModel);
                itemsTable.setModel(new DefaultTableModel(itemService.populateTable(),names));
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = itemsTable.getSelectedRow();
                String value1 = itemsTable.getModel().getValueAt(row, 0).toString();
                String value2 = itemsTable.getModel().getValueAt(row, 1).toString();
                String value3 = itemsTable.getModel().getValueAt(row, 2).toString();

                ItemModel itemModel=new ItemModel(Integer.parseInt(value1),value2,value3);
                itemService.update(itemModel);
                itemsTable.setModel(new DefaultTableModel(itemService.populateTable(),names));
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
        AdminControls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AdminControls();
            }
        });
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new UserProfile();
            }
        });


        add(itemsTable);
        add(idColumn);
        add(statusColumn);
        add(nameColumn);
        add(name);
        add(status);
        add(update);
        add(delete);
        add(add);
        add(MenuBar);
        add(itemLabel);
        add(nameLabel);
        add(statusLabel);
        add(background);
        setVisible(true);
    }


}
