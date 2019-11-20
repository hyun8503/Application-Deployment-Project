package Member_pro;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MemberAdd extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfPwd;
	private JTextField tfName;
	private JTextField tfEmail;
	private Vector cols;
	private MemberDTO dto;
	private MemberDAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberAdd frame = new MemberAdd();
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
	public MemberAdd() {
		
		
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 367, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(45, 31, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfId = new JTextField();
		tfId.setBounds(138, 28, 116, 21);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(45, 66, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		tfPwd = new JTextField();
		tfPwd.setBounds(138, 63, 116, 21);
		contentPane.add(tfPwd);
		tfPwd.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("이름");
		lblNewLabel_2.setBounds(45, 103, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		tfName = new JTextField();
		tfName.setBounds(138, 100, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("이메일");
		lblNewLabel_3.setBounds(45, 139, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(138, 136, 184, 21);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
	

		
	 		
		JButton btnNewButton = new JButton("가입하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userid = tfId.getText();
				String password = tfPwd.getText();
				String name = tfName.getText();
				String email = tfEmail.getText();
				
				MemberDTO dto = new MemberDTO(userid,password,name,email);
				MemberDAO dao = new MemberDAO();
				int result = dao.insertMember(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(MemberAdd.this, "회원가입되었습니다.");
				}
				dispose();
			
			}
		});
		btnNewButton.setBounds(115, 207, 124, 23);
		contentPane.add(btnNewButton);
	}
}
