package Member_pro;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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


public class BookList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfSearch;
	private Vector<String> cols;
	private BookDAO dao;
	private BookDTO dto;
	private DefaultTableModel model;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookList frame = new BookList();
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

	public BookList() {
		
		dao = new BookDAO();
		cols = new Vector<String>();
		cols.add("도서번호");
		cols.add("도서명");
		cols.add("출판사");
		cols.add("출판일");
		cols.add("저자");
		cols.add("분류");
		
		setTitle("도서관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("검색할 도서의 이름을 입력하세요");
		panel.add(lblNewLabel);
		
		//검색
		tfSearch = new JTextField();
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();	
			}
		});
		panel.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = table.getSelectedRow();
				String bookno= (String) table.getValueAt(idx, 0);
				int result=0;
				int response=JOptionPane.showConfirmDialog(BookList.this, "삭제하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteBook(bookno);
				}if(result==1){
					JOptionPane.showMessageDialog(BookList.this, "삭제되었습니다.");
					refreshTable();	
				}
			}
		});
			
			JButton btnEdit = new JButton("수정");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int idx = table.getSelectedRow();
					System.out.println(idx);
					if(idx == -1) {
						JOptionPane.showMessageDialog(BookList.this, "편집할 도서 이름을 선택하세요.");
						return; 
					}else {
					String bookno= (String) table.getValueAt(idx, 0);
					BookEdit frm = new BookEdit(BookList.this, bookno);
					frm.setVisible(true);
					frm.setLocation(500,100);
				}
				}
			});
			panel.add(btnEdit);
			
			
			JButton btnAdd = new JButton("추가");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BookSave frm = new BookSave(BookList.this); //여기에서 테이블을 표시하는 값이 없었던 것(널포인트 오류)
					frm.setVisible(true);
					frm.setLocation(200, 200);
				}
			});
			panel.add(btnAdd);
			panel.add(btnDelete);
			
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		refreshTable();
	}
	
		

public void refreshTable() {
		DefaultTableModel model = new DefaultTableModel(dao.listBook(), cols);
		table.setModel(model);
		}

public void search() {
	String bname=tfSearch.getText();
	model=new DefaultTableModel(dao.searchBook(bname),cols) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;					
		}
	};
	table.setModel(model);
}//search()


}