package Member_pro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class BookEdit extends JFrame {

	private JPanel contentPane;
	private JTextField tfBookno;
	private JTextField tfBname;
	private JTextField tfPublisher;
	private JTextField tfPdate;
	private JTextField tfAuthor;
	private JTextField tfClass;
	private BookDTO dto;
	private BookDAO dao;
	private String bookno;
	private BookList parent;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BookEdit frame = new BookEdit();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	public BookEdit(BookList parent, String bookno) {
		this.parent=parent;
		this.bookno=bookno;
	
		setTitle("도서정보수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("도서번호");
		lblNewLabel.setBounds(56, 42, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfBookno = new JTextField();
		tfBookno.setBounds(148, 39, 116, 21);
		contentPane.add(tfBookno);
		tfBookno.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("도서명");
		lblNewLabel_1.setBounds(56, 83, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		tfBname = new JTextField();
		tfBname.setBounds(148, 83, 116, 21);
		contentPane.add(tfBname);
		tfBname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("출판사");
		lblNewLabel_2.setBounds(56, 123, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		tfPublisher = new JTextField();
		tfPublisher.setBounds(148, 123, 116, 21);
		contentPane.add(tfPublisher);
		tfPublisher.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("출판일");
		lblNewLabel_3.setBounds(56, 166, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		tfPdate = new JTextField();
		tfPdate.setBounds(148, 163, 116, 21);
		contentPane.add(tfPdate);
		tfPdate.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("저자");
		lblNewLabel_4.setBounds(56, 207, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		tfAuthor = new JTextField();
		tfAuthor.setBounds(148, 204, 116, 21);
		contentPane.add(tfAuthor);
		tfAuthor.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("분류");
		lblNewLabel_5.setBounds(56, 252, 57, 15);
		contentPane.add(lblNewLabel_5);
		
		tfClass = new JTextField();
		tfClass.setBounds(148, 252, 116, 21);
		contentPane.add(tfClass);
		tfClass.setColumns(10);
		
		dao = new BookDAO(); 
		dto = dao.viewBook(bookno);
		tfBookno.setText(dto.getBookno());
		tfBname.setText(dto.getBname());
		tfPublisher.setText(dto.getPublisher());
		tfPdate.setText(dto.getPdate()+"");
		tfAuthor.setText(dto.getAuthor());
		tfClass.setText(dto.getClassification());
				
		JButton btnEdit = new JButton("수정");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookno = tfBookno.getText();
				String bname = tfBname.getText();
				String publisher = tfPublisher.getText();
				Date pdate = Date.valueOf(tfPdate.getText());
		        String author = tfAuthor.getText();
				String classification = tfClass.getText();
				
				BookDTO dto = new BookDTO(bookno,bname,publisher,pdate,author,classification);
				int result = dao.updateBook(dto);
				if(result == 1) {
					JOptionPane.showMessageDialog(BookEdit.this, "수정되었습니다.");				
					parent.refreshTable();
					dispose();
				}
				
			}
		});
		btnEdit.setBounds(122, 322, 97, 23);
		contentPane.add(btnEdit);
	}

}
