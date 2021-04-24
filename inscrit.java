package Forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import ConnectionBase.BDconnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inscrit extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTypeUser;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldEmail;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JLabel Labelimg;
	String imagePth=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inscrit frame = new inscrit();
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
	//redimensionner l'image
	
	public ImageIcon resizeIcon(String picPath)
	{
		
		ImageIcon myImag =new ImageIcon(picPath);
		Image img = myImag.getImage().getScaledInstance(Labelimg.getWidth(), Labelimg.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon myPic = new ImageIcon(img); 
		return myPic;
	}
	
	public boolean isUserNameExist(String un)
	{
		boolean exist=false;
		Connection c=BDconnection.getConnection();
		
		PreparedStatement ps;
		ResultSet res;
		try {
			ps=c.prepareStatement("SELECT * FROM `user` WHERE `Email`=?");
			ps.setString(1, textFieldEmail.getText());
			
			res=ps.executeQuery() ;
			if(res.next()) 
			{
				exist=true;
				
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		
		return exist;
	}
	
	// verififier si les champs est vides ou nn
	
	public boolean verifData()
	{
		//if type - first name - last name - user name - password are empty
		if(textFieldTypeUser.getText().equals("")&& textFieldNom.getText().equals("") && textFieldPrenom.getText().equals("")
				|| textFieldEmail.getText().equals("") ||  passwordField1.getText().equals("")  ) 
		{
			JOptionPane.showMessageDialog(null, " one or more fild are empty");
			return false;
			
		}
		//if pass1 is different of pass2
		else if(!passwordField1.getText().equals(passwordField2.getText())) 
		{
			JOptionPane.showMessageDialog(null, " incorrect password");
			return false;
		}
		//if no image was selected
		else if(imagePth==null)
		{
			JOptionPane.showMessageDialog(null, "you must select an image");
			return false;
		}
		//if every thing is ok
		else
		{
			return true;
		}
		
		
	}
	
	public inscrit() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(54, 118, 51));
		panel.setBounds(0, 0, 450, 32);
		contentPane.add(panel);
		
		JLabel lblCreateAccount = new JLabel("Create account");
		lblCreateAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateAccount.setForeground(new Color(255, 255, 240));
		lblCreateAccount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCreateAccount.setBounds(0, 1, 131, 21);
		panel.add(lblCreateAccount);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 240));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(418, 0, 32, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("-");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(255, 255, 240));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(395, 0, 32, 21);
		panel.add(lblNewLabel_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(0, 32, 450, 468);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel(" Inscrivez-vous ");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_2.setBounds(10, 28, 183, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel LabelT = new JLabel("Type user:");
		LabelT.setBounds(26, 65, 84, 14);
		panel_1.add(LabelT);
		
		textFieldTypeUser = new JTextField();
		textFieldTypeUser.setBackground(new Color(245, 245, 245));
		textFieldTypeUser.setBounds(161, 62, 168, 20);
		panel_1.add(textFieldTypeUser);
		textFieldTypeUser.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setBounds(26, 93, 84, 14);
		panel_1.add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBackground(new Color(245, 245, 245));
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(161, 90, 168, 20);
		panel_1.add(textFieldNom);
		
		JLabel lblPrenom = new JLabel("Prenom:");
		lblPrenom.setBounds(26, 128, 84, 14);
		panel_1.add(lblPrenom);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBackground(new Color(245, 245, 245));
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(161, 125, 168, 20);
		panel_1.add(textFieldPrenom);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(26, 160, 84, 14);
		panel_1.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBackground(new Color(245, 245, 245));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(161, 157, 168, 20);
		panel_1.add(textFieldEmail);
		
		JLabel lblpass1 = new JLabel("Mot de passe:");
		lblpass1.setBounds(26, 197, 84, 14);
		panel_1.add(lblpass1);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBackground(new Color(245, 245, 245));
		passwordField1.setBounds(161, 188, 168, 20);
		panel_1.add(passwordField1);
		
		JLabel lblpass2 = new JLabel("Repeter mot de passe:");
		lblpass2.setBounds(26, 229, 137, 14);
		panel_1.add(lblpass2);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBackground(new Color(245, 245, 245));
		passwordField2.setBounds(161, 226, 168, 20);
		panel_1.add(passwordField2);
		
		JLabel lblimg = new JLabel("Photo:");
		lblimg.setBounds(26, 277, 46, 14);
		panel_1.add(lblimg);
		
		Labelimg = new JLabel("");
		Labelimg.setBackground(new Color(245, 245, 245));
		Labelimg.setOpaque(true);
		Labelimg.setBounds(181, 277, 137, 71);
		panel_1.add(Labelimg);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser file=new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				// file extension
				FileNameExtensionFilter fileFilter =new FileNameExtensionFilter("*.Images","jpg","png","gif");
				file.addChoosableFileFilter(fileFilter);
				
				int fileState =file.showSaveDialog(null);
				
				//if a user select a file
				
				if(fileState == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = file.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					imagePth = path;
					
					//display the image in the jlabel using resize icone
					
					
					 Labelimg.setIcon(resizeIcon(path));
				}
				//if the user concel
				
				else if(fileState == JFileChooser.CANCEL_OPTION)
				{
					System.out.println("no image selected");
				}
			
			}
		});
		btnBrowse.setBackground(new Color(173, 216, 230));
		btnBrowse.setBounds(333, 283, 89, 23);
		panel_1.add(btnBrowse);
		
		JButton btnquiter = new JButton("Quiter");
		btnquiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnquiter.setBackground(new Color(255, 140, 0));
		btnquiter.setBounds(79, 392, 89, 23);
		panel_1.add(btnquiter);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verifData())
				{
					Connection con=BDconnection.getConnection();
					
					PreparedStatement ps;
				    try {
						ps=con.prepareStatement("INSERT INTO `user`(`Type_user`, `Nom`, `Prenom`, `Email`, `Mot_de_passe`, `Image`) VALUES (?,?,?,?,?,?)");
						String Nom=textFieldNom.getText();
						String Prenom=textFieldPrenom.getText();
						String Email=textFieldEmail.getText();
						String pass1=passwordField1.getText();
						//String pass2=RetypePasswordField.getText();
						String type=textFieldTypeUser.getText();
						InputStream img = new FileInputStream(new File(imagePth));
						
						ps.setString(1, type);
						ps.setString(2, Nom);
						ps.setString(3, Prenom);
						ps.setString(4, Email);
						ps.setString(5, pass1);
						ps.setBlob(6, img);
						
						//test if the user name is exist or not
						if(isUserNameExist(textFieldEmail.getText()))
						{
							JOptionPane.showMessageDialog(null, "user name already exists");
						}
						//if user name dosn't exist
						else 
						{
						     if(ps.executeUpdate() !=0)
						       {
							     JOptionPane.showMessageDialog(null, "account created");
						       }
						     else  
						       {
							JOptionPane.showMessageDialog(null, "somthing wrong");
						       }
						}
						
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						Logger.getLogger(inscrit.class.getName()).log(Level.SEVERE,null,e1);
					}
					
				}
			}
		});
		btnValider.setBackground(new Color(144, 238, 144));
		btnValider.setBounds(277, 392, 89, 23);
		panel_1.add(btnValider);
		
		JLabel LabelCon = new JLabel("Deja inscrit? connectez-vous");
		LabelCon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginForm logf=new LoginForm();
				logf.setVisible(true);
				
				logf.setLocationRelativeTo(null);
				logf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		LabelCon.setFont(new Font("Tahoma", Font.PLAIN, 10));
		LabelCon.setBounds(142, 443, 160, 14);
		panel_1.add(LabelCon);
	}

}
