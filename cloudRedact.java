package Forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import ConnectionBase.BDconnection;
import classe.myFunction;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class cloudRedact extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	
	String charset="UTF-8";
	String imagePth=null;
	private JLabel Labelimg;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cloudRedact frame = new cloudRedact();
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
	

	//code pour imprimer les information de textarea(je vient le copier au form article pour le client lecteur
	public void importer()
	{
		try
		{
			JFileChooser chooser=new JFileChooser();
			chooser.showOpenDialog(null);
			File fich = chooser.getSelectedFile();
			String nomFich=fich.getAbsolutePath();
			
			try
			{			
				BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(nomFich), charset));
				textArea.read(br, null);
				br.close();
				textArea.requestFocus();
			}
			catch(IOException e){
				
			}
			
		}catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "operation annulee!!", "Information",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void imprimer()
	{
		try
		{
			boolean complete = textArea.print();
			if(complete)
			{
				JOptionPane.showMessageDialog(null, "c'est imprimer!!", "Information",JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "erreur d'ompression!!", "Information",JOptionPane.ERROR_MESSAGE);
			}
		}catch(PrinterException p) {
			JOptionPane.showMessageDialog(null,p);
		}
	}
	
	public cloudRedact() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(54, 118, 51));
		panel.setBounds(0, 0, 450, 32);
		contentPane.add(panel);
		
		JLabel lblCloud = new JLabel("Cloud");
		lblCloud.setHorizontalAlignment(SwingConstants.CENTER);
		lblCloud.setForeground(new Color(255, 255, 240));
		lblCloud.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCloud.setBounds(0, 1, 70, 21);
		panel.add(lblCloud);
		
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
		panel_1.setBounds(0, 31, 450, 269);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Retour");
		lblNewLabel.setBounds(0, 0, 46, 14);
		panel_1.add(lblNewLabel);
		
		JLabel Labelphoto = new JLabel("Photo");
		Labelphoto.setBounds(43, 36, 46, 14);
		panel_1.add(Labelphoto);
		
		Labelimg = new JLabel("");
		Labelimg.setBackground(Color.LIGHT_GRAY);
		Labelimg.setOpaque(true);
		Labelimg.setBounds(30, 61, 99, 111);
		panel_1.add(Labelimg);
		
		JButton btnNewButton = new JButton("Explorer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myFunction fun=new myFunction();
				imagePth = fun.BrowseIlage(Labelimg);
				System.out.println(imagePth);
			
			}
		});
		btnNewButton.setBounds(30, 183, 99, 23);
		panel_1.add(btnNewButton);
		
		JLabel lbltext = new JLabel("Taper text ici:");
		lbltext.setBounds(278, 36, 90, 14);
		panel_1.add(lbltext);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(206, 61, 234, 111);
		panel_1.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		//pour encadrer le text de textarea
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JButton ButtonImporter = new JButton("Importer");
		ButtonImporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importer();
			}
		});
		ButtonImporter.setBounds(206, 183, 89, 23);
		panel_1.add(ButtonImporter);
		// boutton pur vider textrea
		JButton ButtonVider = new JButton("Vider");
		ButtonVider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
			}
		});
		ButtonVider.setBounds(351, 183, 89, 23);
		panel_1.add(ButtonVider);
		
		JButton ButtonImprimer = new JButton("Imprimer");
		ButtonImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimer();
			}
		});
		ButtonImprimer.setBounds(206, 217, 89, 23);
		panel_1.add(ButtonImprimer);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=BDconnection.getConnection();
				PreparedStatement ps;
				
				try
				{
					ps=con.prepareStatement("INSERT INTO `cloud`(`image`, `text`) VALUES (?,?)");
					InputStream img = new FileInputStream(new File(imagePth));
					ps.setBlob(1, img);
					ps.setString(2,textArea.getText() );
					
					
					
					if(ps.executeUpdate() !=0)
				       {
					     JOptionPane.showMessageDialog(null, "categorie ajouter avec succes");
				       }else {
				    	   JOptionPane.showMessageDialog(null, "l'ajout de cet categorie est echouee");
				       }
					
					
				}catch(Exception e1)
				{
					Logger.getLogger(inscrit.class.getName()).log(Level.SEVERE,null,e1);
				}
			}
		});
		
		btnAjouter.setBounds(351, 217, 89, 23);
		panel_1.add(btnAjouter);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PageLecteur lec=new PageLecteur();
				lec.setVisible(true);
				
			}
		});
		}
}

