
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class SearchEngine extends javax.swing.JFrame {

    private String database;
    private String driver;
    private String url;
    private Connection conn;
    private Statement stm;
    private ResultSet rs;
    private String sql;
    Object cbCategory;
    private int selectedRowIndex;

    private DefaultTableModel model;

    public SearchEngine() {
        initComponents();

        bt_update.setEnabled(false);
        bt_delete.setEnabled(false);

        bt_add.setEnabled(false);
        bt_cancel.setEnabled(false);
        tf_name.setEnabled(false);
        tf_lastname.setEnabled(false);
        tf_address.setEnabled(false);

        lb_warningB.setText("");
        lb_warningA.setText("");

        model = (DefaultTableModel) tb_result.getModel();

        database = "SearchEngineDb";
        driver = "org.apache.derby.jdbc.ClientDriver";
        url = "jdbc:derby:" + database + ";create=true";

        try {
            //step 1 load driver
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("cannot load driver");
            System.exit(-1);
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ix) {
        }

        try {
            //step 2 connect to database
            conn = DriverManager.getConnection(url, "", ""); //user password
        } catch (SQLException ex) {
            System.out.println("cannot load database");
            System.exit(-1);
        }

        try {
            //step 3
            stm = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("cannot create statement");
            System.exit(-1);
        }

        /*
        try {
            //step 4 create table

            //sql = "CREATE TABLE profile(ID INTEGER PRIMARY KEY AUTO_INCREMENT,name VARCHAR(15),lastname VARCHAR(15),address VARCHAR(50))";
            sql = "CREATE TABLE profile(ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),name VARCHAR(15),lastname VARCHAR(15),address VARCHAR(50))";
            
            stm.execute(sql);

        } catch (SQLException ex) {
            System.out.println("cannot create table : "+ex);
            System.exit(-1);
        }

        try {
            sql = "INSERT INTO profile (name,lastname,address)" + "VALUES ( 'Paul', '32', 'California')";
            stm.execute(sql);

        } catch (SQLException ex) {
            System.out.println("cannot insert : " + ex);
            System.exit(-1);
        }
*/
        
        try {
            selectAllProfile();

        } catch (SQLException ex) {
            System.out.println("Can't show All Profile : " + ex);
            System.exit(-1);
        }

    }

    public void insertProfile(String name, String lastname, String address) throws SQLException {
        sql = "INSERT INTO profile (name,lastname,address) VALUES('" + name + "','" + lastname + "','" + address + "')";
        stm.executeUpdate(sql);

    }

    public void selectAllProfile() throws SQLException {
        model.setRowCount(0);

        sql = "SELECT * FROM profile";
        rs = stm.executeQuery(sql);
        try {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
        } catch (SQLException ex) {
            System.out.println("Can't show All Profile");
            System.exit(-1);
        }
    }

    public void searchProfile(String table, String msg) throws SQLException {
        model.setRowCount(0);

        bt_update.setEnabled(false);
        bt_delete.setEnabled(false);

        bt_add.setEnabled(false);
        bt_cancel.setEnabled(false);
        tf_name.setEnabled(false);
        tf_lastname.setEnabled(false);
        tf_address.setEnabled(false);

        sql = "SELECT * FROM profile where " + table + " like '%" + msg + "%'";
        rs = stm.executeQuery(sql);

        while (rs.next()) {
            model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
        }
    }

    public void deleteProfile(String field, String msg) throws SQLException {
        sql = "DELETE FROM profile WHERE " + field + " = '" + msg + "'";
        stm.executeUpdate(sql);
    }

    public void updateProfile(int index, String name, String lastname, String address) throws SQLException {
        sql = "UPDATE profile SET name = 'Somkid' WHERE name = 'Somchai'";
        stm.executeUpdate(sql);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        tf_search = new javax.swing.JTextField();
        bt_search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_result = new javax.swing.JTable();
        cb_category = new javax.swing.JComboBox<>();
        bt_insert = new javax.swing.JButton();
        bt_update = new javax.swing.JButton();
        bt_delete = new javax.swing.JButton();
        tf_name = new javax.swing.JTextField();
        tf_lastname = new javax.swing.JTextField();
        tf_address = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bt_add = new javax.swing.JButton();
        bt_cancel = new javax.swing.JButton();
        lb_warningA = new javax.swing.JLabel();
        lb_warningB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SearchEngine");

        tf_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_searchFocusGained(evt);
            }
        });

        bt_search.setText("SEARCH");
        bt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_searchActionPerformed(evt);
            }
        });

        tb_result.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "LASTNAME", "ADDRESS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_result.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_result.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tb_resultFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tb_resultFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(tb_result);

        cb_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Lastname", "Address" }));

        bt_insert.setText("INSERT");
        bt_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_insertActionPerformed(evt);
            }
        });

        bt_update.setText("UPDATE");
        bt_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_updateActionPerformed(evt);
            }
        });

        bt_delete.setText("DELETE");
        bt_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_deleteActionPerformed(evt);
            }
        });

        jLabel1.setText("Name");

        jLabel2.setText("Lastname");

        jLabel3.setText("Address");

        bt_add.setText("ADD");
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addActionPerformed(evt);
            }
        });

        bt_cancel.setText("Cancel");
        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });

        lb_warningA.setForeground(new java.awt.Color(255, 0, 0));
        lb_warningA.setText("jLabel4");

        lb_warningB.setForeground(new java.awt.Color(255, 0, 0));
        lb_warningB.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tf_name, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                            .addComponent(tf_lastname)
                            .addComponent(tf_address))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(tf_search, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_category, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_update, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_warningA)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_warningB)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_search)
                    .addComponent(cb_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_warningA, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_insert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_delete)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_warningB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_cancel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_searchActionPerformed
        lb_warningA.setText("");

        String msg = tf_search.getText();   //get search bar
        cbCategory = cb_category.getSelectedItem();

        if (!(tf_search.getText().equals(""))) {
            if (cbCategory != null) {
                String selectedItemStr = cbCategory.toString();

                System.out.println("test combobox : " + selectedItemStr);

                try {
                    searchProfile(selectedItemStr, msg);

                } catch (SQLException se) {
                    System.out.println("Error search : " + se);
                }

            }
        } else {
            lb_warningA.setText(" โปรดป้อนสิ่งที่ต้องการค้นหา");
        }


    }//GEN-LAST:event_bt_searchActionPerformed

    private void bt_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_updateActionPerformed

        //ด้านบน
        tf_search.setEnabled(false);
        cb_category.setEnabled(false);
        bt_search.setEnabled(false);
        //ด้านขวา
        bt_insert.setEnabled(false);
        bt_update.setEnabled(false);
        bt_delete.setEnabled(false);

        //jtable
        tb_result.setEnabled(false);

        //ด้านล่างขวา
        bt_add.setEnabled(true);
        bt_cancel.setEnabled(true);
        //ด้านล่าง
        tf_name.setEnabled(true);
        tf_lastname.setEnabled(true);
        tf_address.setEnabled(true);

        //ดู index
        selectedRowIndex = tb_result.getSelectedRow();

        //set ค่าในช่องว่างด้านล่าง
        tf_name.setText(model.getValueAt(selectedRowIndex, 0).toString());
        tf_lastname.setText(model.getValueAt(selectedRowIndex, 1).toString());
        tf_address.setText(model.getValueAt(selectedRowIndex, 2).toString());

        //model.setRowCount(0);
        tf_search.setText("");
        lb_warningA.setText("");
        bt_add.setText("UPDATE");


    }//GEN-LAST:event_bt_updateActionPerformed

    private void bt_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_insertActionPerformed

        //ด้านบน
        tf_search.setEnabled(false);
        cb_category.setEnabled(false);
        bt_search.setEnabled(false);
        //ด้านขวา
        bt_insert.setEnabled(false);
        bt_update.setEnabled(false);
        bt_delete.setEnabled(false);
        //jtable
        tb_result.setEnabled(false);
        //ด้านล่างขวา
        bt_add.setEnabled(true);
        bt_cancel.setEnabled(true);
        //ด้านล่าง
        tf_name.setEnabled(true);
        tf_lastname.setEnabled(true);
        tf_address.setEnabled(true);

        model.setRowCount(0);
        tf_search.setText("");
        lb_warningA.setText("");
        bt_add.setText("ADD");
    }//GEN-LAST:event_bt_insertActionPerformed

    private void bt_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addActionPerformed
        lb_warningB.setText("");

        String btadd_update = bt_add.getText().toString();

        if (tf_name.getText().equals("")) {
            lb_warningB.setText("ป้อนค่าในช่อง Name");
        } else if (tf_lastname.getText().equals("")) {
            lb_warningB.setText("ป้อนค่าในช่อง Lastname");
        } else if (tf_address.getText().equals("")) {
            lb_warningB.setText("ป้อนค่าในช่อง Address");
        } else {
            
            //ด้านบน
            tf_search.setEnabled(true);
            cb_category.setEnabled(true);
            bt_search.setEnabled(true);
            //ด้านขวา
            bt_insert.setEnabled(true);
            bt_update.setEnabled(false);
            bt_delete.setEnabled(false);
            //jtable
            tb_result.setEnabled(true);
            //ด้านล่างขวา
            bt_add.setEnabled(false);
            bt_cancel.setEnabled(false);
            //ด้านล่าง
            tf_name.setEnabled(false);
            tf_lastname.setEnabled(false);
            tf_address.setEnabled(false);
            
             

            if (btadd_update.equals("ADD")) {
                try {
                    //process
                    insertProfile(tf_name.getText(), tf_lastname.getText(), tf_address.getText());
                } catch (SQLException ex) {
                    System.out.println("Insert error : " + ex);
                }
            } else if (btadd_update.equals("UPDATE")) {

                DefaultTableModel model = (DefaultTableModel) tb_result.getModel();
                int selectedRowIndex = tb_result.getSelectedRow();

                System.out.println("index : " + selectedRowIndex);

                //model.setValueAt(tf_name.getText().toString(), selectedRowIndex, 0);
                /*
                    String nameU = model.getValueAt(selectedRowIndex, 0).toString();
                    
                    System.out.println("nameU : "+nameU);
                    String lastU = model.getValueAt(selectedRowIndex, 1).toString();
                    String addressU = model.getValueAt(selectedRowIndex, 2).toString();
                 */
                //updateProfile(tf_name.getText(), tf_lastname.getText(), tf_address.getText());
            }

            
            tf_name.setText("");
            tf_lastname.setText("");
            tf_address.setText("");
            selectedRowIndex = -1;
            
        }
    }//GEN-LAST:event_bt_addActionPerformed

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed

        //ดู index
        selectedRowIndex = tb_result.getSelectedRow();
        System.out.println("index : " + selectedRowIndex);

        tf_search.setText("");
        lb_warningA.setText("");

    }//GEN-LAST:event_bt_deleteActionPerformed

    private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
        //ด้านบน
        tf_search.setEnabled(true);
        cb_category.setEnabled(true);
        bt_search.setEnabled(true);
        //ด้านขวา
        bt_insert.setEnabled(true);
        bt_update.setEnabled(false);
        bt_delete.setEnabled(false);
        //jtable
        tb_result.setEnabled(true);
        //ด้านล่างขวา
        bt_add.setEnabled(false);
        bt_cancel.setEnabled(false);
        //ด้านล่าง
        tf_name.setEnabled(false);
        tf_lastname.setEnabled(false);
        tf_address.setEnabled(false);

        tf_name.setText("");
        tf_lastname.setText("");
        tf_address.setText("");

        lb_warningB.setText("");
        selectedRowIndex = -1;

    }//GEN-LAST:event_bt_cancelActionPerformed

    private void tf_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_searchFocusGained
        lb_warningA.setText("");
    }//GEN-LAST:event_tf_searchFocusGained

    private void tb_resultFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tb_resultFocusGained
        // ด้านขวา
        bt_insert.setEnabled(true);
        bt_update.setEnabled(true);
        bt_delete.setEnabled(true);
    }//GEN-LAST:event_tb_resultFocusGained

    private void tb_resultFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tb_resultFocusLost

    }//GEN-LAST:event_tb_resultFocusLost

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchEngine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchEngine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchEngine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchEngine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchEngine().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JButton bt_cancel;
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_insert;
    private javax.swing.JButton bt_search;
    private javax.swing.JButton bt_update;
    private javax.swing.JComboBox<String> cb_category;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lb_warningA;
    private javax.swing.JLabel lb_warningB;
    private javax.swing.JTable tb_result;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_lastname;
    private javax.swing.JTextField tf_name;
    private javax.swing.JTextField tf_search;
    // End of variables declaration//GEN-END:variables
}
