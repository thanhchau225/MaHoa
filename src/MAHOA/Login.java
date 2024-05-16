package MAHOA;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tftUsername;
	private JPasswordField pwPasword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 224, 224));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		SignUp su = new SignUp();
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel_2);
		
		tftUsername = new JTextField();
		tftUsername.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(tftUsername);
		tftUsername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(lblNewLabel_1);
		
		pwPasword = new JPasswordField();
		panel.add(pwPasword);
		
		JCheckBox cb = new JCheckBox("Show password");
		cb.setBackground(new Color(255, 255, 255));
		cb.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(cb);
		cb.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (cb.isSelected()) {
		            pwPasword.setEchoChar((char) 0);
		        } else {
		            pwPasword.setEchoChar('•');
		        }
		    }
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setHgap(100);
		flowLayout.setVgap(20);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.setBackground(new Color(68, 162, 255));
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				su.setVisible(true);
				dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(btnSignUp);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBackground(new Color(68, 162, 255));
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn();
			}
		});
		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(btnSignIn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setHgap(50);
		flowLayout_1.setVgap(30);
		contentPane.add(panel_2, BorderLayout.WEST);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setHgap(50);
		contentPane.add(panel_3, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(68, 162, 255));
		contentPane.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setBackground(new Color(0, 128, 255));
		panel_4.add(lblNewLabel);
	}
	
	private void SignIn() {
		 String username = tftUsername.getText();
	     String password = new String(pwPasword.getPassword());
	     
        try {
        	String hashedPassword = PasswordEncryption.encrypt(password);
            Connection connection = ConnecttionDatabase.connect();
            java.sql.Statement statement = connection.createStatement();
            String query = "SELECT * FROM ACC WHERE Username = '" + username + "' AND Password = '" + hashedPassword + " '";
            ResultSet a = statement.executeQuery(query);
            if(a.next()) {
            	JOptionPane.showMessageDialog(this, "Wellcome");
            } else  {
            	JOptionPane.showMessageDialog(this, "Username or Password incorrect!");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
