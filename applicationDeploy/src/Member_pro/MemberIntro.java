package Member_pro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class MemberIntro extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfPwd;
	private MemberDTO dto;
	private MemberDAO dao;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberIntro frame = new MemberIntro();
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
	
	
	public MemberIntro() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 311, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(39, 71, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfId = new JTextField();
		tfId.setBounds(118, 68, 116, 21);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(39, 113, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		tfPwd = new JTextField();
		tfPwd.setBounds(118, 110, 116, 21);
		contentPane.add(tfPwd);
		tfPwd.setColumns(10);
			
		
		
		JButton btnSave = new JButton("회원가입");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   MemberAdd frm = new MemberAdd();
			   frm.setVisible(true);
			   frm.setLocation(200,200);
			}
		});
		btnSave.setBounds(39, 207, 97, 23);
		contentPane.add(btnSave);
		
		dto = new MemberDTO(); 
		dao = new MemberDAO();
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userid = tfId.getText();
				String password = tfPwd.getText();

				
 				if(userid.equals("") && password.equals("")) {
				JOptionPane.showMessageDialog(MemberIntro.this, "아이디 또는 비밀번호를 확인하세요.");
 				}else { 
					boolean existLogin = dao.logintest(userid, password);
				      if(existLogin == true) {
				    	  JOptionPane.showMessageDialog(MemberIntro.this, "로그인되었습니다."); 
				    	  BookList frm = new BookList();		    	  
				    	  frm.setVisible(true);
				    	  frm.setLocation(550,100);} 
				      else { 
				    	  JOptionPane.showMessageDialog(MemberIntro.this, "아이디 또는 비밀번호를 확인하세요.");
				      }
					}
			password = null;
			
			}
		});
		
		btnLogin.setBounds(162, 207, 97, 23);
		contentPane.add(btnLogin);
}
	
	
}