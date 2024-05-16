package MAHOA;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Color;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tftUsername;
	private JPasswordField pwPassword;
	private JPasswordField tftCP;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public SignUp() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(lblNewLabel_3);
		
		tftUsername = new JTextField();
		tftUsername.setBackground(new Color(210, 255, 255));
		panel.add(tftUsername);
		tftUsername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(lblNewLabel_2);
		
		pwPassword = new JPasswordField();
		pwPassword.setBackground(new Color(210, 255, 255));
		panel.add(pwPassword);
		
		JLabel lblNewLabel_1 = new JLabel("Confirm Password");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(lblNewLabel_1);
		
		tftCP = new JPasswordField();
		tftCP.setBackground(new Color(210, 255, 255));
		panel.add(tftCP);
		
		JCheckBox cb = new JCheckBox("Show password");
		cb.setBackground(new Color(255, 255, 255));
		cb.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(cb);
		cb.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (cb.isSelected()) {
		            pwPassword.setEchoChar((char) 0);
		        } else {
		            pwPassword.setEchoChar('•');
		        }
		        if (cb.isSelected()) {
		        	tftCP.setEchoChar((char) 0);
		        } else {
		        	tftCP.setEchoChar('•');
		        }
		    }
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setHgap(70);
		contentPane.add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(70);
		flowLayout.setVgap(40);
		contentPane.add(panel_2, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setVgap(20);
		flowLayout_2.setHgap(50);
		contentPane.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.setBackground(new Color(68, 162, 255));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUp();
				;
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_3.add(btnSignUp);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(68, 162, 255));
		contentPane.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("CREATE ACCOUNT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_4.add(lblNewLabel);
	}
	
	private void signUp() {
        String username = tftUsername.getText();
        String password = new String(pwPassword.getPassword());
        String confirmPassword = new String(tftCP.getPassword());

        if (password.equals(confirmPassword)) {
            String hashedPassword = PasswordEncryption.encrypt(password);

            try {
            	Connection connection = ConnecttionDatabase.connect();
                java.sql.Statement statement = connection.createStatement();
                String insertQuery = "INSERT INTO ACC (Username, Password) VALUES ('" + username + "', '" + hashedPassword + "')";
                statement.executeUpdate(insertQuery);
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
			dispose();
			Login l = new Login();
			l.setVisible(true);
        } else {
        	JOptionPane.showMessageDialog(this, "Passwords do not match!");
        }
    }
}
