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

public class BookSave extends JFrame {

	private JPanel contentPane;
	private JTextField tfBookno;
	private JTextField tfBname;
	private JTextField tfPublisher;
	private JTextField tfPdate;
	private JTextField tfAuthor;
	private JTextField tfClass;
	private BookList bs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSave frame = new BookSave();
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
	public BookSave(BookList bs) {
		this();
		this.bs=bs;
	}
		
		public BookSave() {
		setTitle("도서추가");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("도서번호");
		lblNewLabel.setBounds(51, 33, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfBookno = new JTextField();
		tfBookno.setBounds(162, 30, 116, 21);
		contentPane.add(tfBookno);
		tfBookno.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("도서명");
		lblNewLabel_1.setBounds(51, 70, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		tfBname = new JTextField();
		tfBname.setBounds(162, 67, 116, 21);
		contentPane.add(tfBname);
		tfBname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("출판사");
		lblNewLabel_2.setBounds(51, 108, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		tfPublisher = new JTextField();
		tfPublisher.setBounds(162, 105, 116, 21);
		contentPane.add(tfPublisher);
		tfPublisher.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("출판일");
		lblNewLabel_3.setBounds(51, 146, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		tfPdate = new JTextField();
		tfPdate.setBounds(162, 143, 116, 21);
		contentPane.add(tfPdate);
		tfPdate.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("저자");
		lblNewLabel_4.setBounds(51, 184, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		tfAuthor = new JTextField();
		tfAuthor.setBounds(162, 184, 116, 21);
		contentPane.add(tfAuthor);
		tfAuthor.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("분류");
		lblNewLabel_5.setBounds(51, 224, 57, 15);
		contentPane.add(lblNewLabel_5);
		
		tfClass = new JTextField();
		tfClass.setBounds(162, 221, 116, 21);
		contentPane.add(tfClass);
		tfClass.setColumns(10);
		
		JButton btnAdd = new JButton("추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bookno = tfBookno.getText();
				String bname = tfBname.getText();
				String publisher = tfPublisher.getText();
				Date pdate = Date.valueOf(tfPdate.getText());
		        String author = tfAuthor.getText();
				String classification = tfClass.getText();
				
				BookDTO dto = new BookDTO(bookno,bname,publisher,pdate,author,classification);
				BookDAO dao = new BookDAO();
				int result = dao.insertBook(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(BookSave.this, "저장되었습니다.");
					bs.refreshTable();
					dispose();
				}
				
			}
		});
		btnAdd.setBounds(120, 294, 97, 23);
		contentPane.add(btnAdd);
	}

}
