package bibtek;

import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame {
             //sit Bibtex extends Observable ja implements ActionListener?
    
        //private Bibtex bibtex;
        
        //jos käytetään editable drop boxia...
        //String currentBox1;
        
        //TARVIIKO?
        //private static final long serialVersionUID = 1L;
    	private JTextArea textArea = new JTextArea();
	private JScrollPane scroll = new JScrollPane(this.textArea); 
    
       // private JPanel mainPanel = new JPanel();
        private JPanel topPanel = new JPanel();
	private JPanel middlePanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
        private JPanel leftPanel = new JPanel();
        private JPanel rightPanel = new JPanel();
        
        private JButton searchB = new JButton("Search >>");
        private JButton addB = new JButton("Add Reference");
        private JButton removeB = new JButton("Delete ID >>");
        //private JButton saveB = new JButton("Save");
        //private JButton deleteB = new JButton("Delete");
        private JButton listallB = new JButton("List All");
        private JButton bibtexB = new JButton("List Bibtex");
        //private boolean buttonsActive = true;
       
        private JLabel typeL = new JLabel("Type:");
        private JLabel authorL = new JLabel("Author:");
        private JLabel titleL = new JLabel("Title:");
        private JLabel booktitleL = new JLabel("Booktitle:");
        private JLabel journalL = new JLabel("Journal:");
        private JLabel yearL = new JLabel("Year:");
        private JLabel pagesL = new JLabel("Pages:");
        private JLabel publisherL = new JLabel("Publisher:");
        private JLabel volumeL = new JLabel("Volume:");
        private JLabel addressL = new JLabel("Address:");
        
        private JTextField searchF = new JTextField(20);
        private JTextField typeF = new JTextField(7);
        private JTextField authorF = new JTextField(19);
        private JTextField titleF = new JTextField(20);
        private JTextField booktitleF = new JTextField(18);
        private JTextField journalF = new JTextField(18);
        private JTextField yearF = new JTextField(6);
        private JTextField pagesF = new JTextField(4);
        private JTextField publisherF = new JTextField(16);
        private JTextField volumeF = new JTextField(6);
        private JTextField addressF = new JTextField(9);
        private JTextField deleteIDF = new JTextField(5);
        
        public GUI(){
        
            super("EpicBibtexReader");
            
            //this.OLIO = new OLIO();
            //this.OLIO.addObserver(this);
            
            //asetetaan paneelit
            //mainPanel.setLayout(new GridLayout(4, 0, 5, 5));
            this.getContentPane().add(BorderLayout.NORTH, this.topPanel);
            this.getContentPane().add(BorderLayout.CENTER, this.middlePanel);
            this.getContentPane().add(BorderLayout.SOUTH, this.bottomPanel);
            this.getContentPane().add(BorderLayout.EAST, this.rightPanel);
            this.getContentPane().add(BorderLayout.WEST, this.leftPanel);

            //määritellään paneelien yksityiskohdat
            this.topPanel.setLayout(new BoxLayout(this.topPanel, BoxLayout.X_AXIS));
            this.topPanel.setPreferredSize(new Dimension(700, 50));
            this.topPanel.add(Box.createHorizontalStrut(210));
            this.topPanel.add(this.removeB);
            this.topPanel.add(Box.createHorizontalStrut(2));
            this.topPanel.add(this.deleteIDF);
            this.deleteIDF.setMaximumSize(deleteIDF.getPreferredSize());
            this.topPanel.add(Box.createHorizontalStrut(30));
            this.topPanel.add(this.searchB);
            this.topPanel.add(Box.createHorizontalStrut(2));
            this.topPanel.add(this.searchF);
            this.searchF.setMaximumSize(searchF.getPreferredSize());

                                                        
            this.middlePanel.setLayout(new FlowLayout());
            //this.middlePanel.setLayout(new BoxLayout(this.middlePanel, FlowLayout.LEFT));
            this.middlePanel.setPreferredSize(new Dimension(700, 150));
            this.middlePanel.add(Box.createHorizontalStrut(5));
            this.middlePanel.add(this.typeL);
            this.middlePanel.add(this.typeF);
            this.middlePanel.add(Box.createHorizontalStrut(15));
            this.middlePanel.add(this.authorL);
            this.middlePanel.add(this.authorF);
            this.middlePanel.add(Box.createHorizontalStrut(15));
            this.middlePanel.add(this.titleL);
            this.middlePanel.add(this.titleF);
            this.middlePanel.add(Box.createHorizontalStrut(15));
            this.middlePanel.add(this.yearL);
            this.middlePanel.add(this.yearF);
            this.middlePanel.add(Box.createHorizontalStrut(15));
            this.middlePanel.add(this.booktitleL);
            this.middlePanel.add(this.booktitleF);
            this.middlePanel.add(Box.createHorizontalStrut(15));
            this.middlePanel.add(this.journalL);
            this.middlePanel.add(this.journalF);
            this.middlePanel.add(Box.createHorizontalStrut(15));
            this.middlePanel.add(this.pagesL);
            this.middlePanel.add(this.pagesF);
            this.middlePanel.add(Box.createHorizontalStrut(10));
            this.middlePanel.add(this.publisherL);
            this.middlePanel.add(this.publisherF);
            this.middlePanel.add(Box.createHorizontalStrut(10));
            this.middlePanel.add(this.volumeL);
            this.middlePanel.add(this.volumeF);
            this.middlePanel.add(Box.createHorizontalStrut(10));
            this.middlePanel.add(this.addressL);
            this.middlePanel.add(this.addressF);
            this.middlePanel.add(Box.createHorizontalStrut(10));
            this.middlePanel.add(this.addB);
            this.middlePanel.add(Box.createHorizontalStrut(350));
            this.middlePanel.add(this.listallB);
            this.middlePanel.add(this.bibtexB);
            

            this.bottomPanel.setLayout(new BoxLayout(this.bottomPanel, BoxLayout.X_AXIS));
            this.bottomPanel.setPreferredSize(new Dimension(700, 500));
            this.bottomPanel.add(this.scroll);
            this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            
            //luodaan tekstikenttä
            this.textArea.setLineWrap(true);
            this.textArea.setWrapStyleWord(true);
            this.textArea.setEditable(false);
            this.textArea.setText("  Welcome to EpicBibtexReader! \n");
            
//            this.searchB.addActionListener(new ActionListener() {
//           
//		public void actionPerformed(ActionEvent e) {
//            	-MITÄ NAPPI TEKEE-
//                }
//            });
            
//            this.addB.addActionListener(new ActionListener() {
//           
//		public void actionPerformed(ActionEvent e) {
//            	-MITÄ NAPPI TEKEE-
//                }
//            });
            
//            this.removeB.addActionListener(new ActionListener() {
//           
//		public void actionPerformed(ActionEvent e) {
//            	-MITÄ NAPPI TEKEE-
//                }
//            });
            
//            this.saveB.addActionListener(new ActionListener() {
//           
//		public void actionPerformed(ActionEvent e) {
//            	-MITÄ NAPPI TEKEE-
//                }
//            });
            
//            this.listallB.addActionListener(new ActionListener() {
//           
//		public void actionPerformed(ActionEvent e) {
//            	-MITÄ NAPPI TEKEE-
//                }
//            });
            
//            this.bibtexB.addActionListener(new ActionListener() {
//           
//		public void actionPerformed(ActionEvent e) {
//            	-MITÄ NAPPI TEKEE-
//                }
//            });
            
        }       
//            Jos halutaan joku intro viesti, esim. ohjeita sun muuta
//            public void introDialogue() {
//		
//		JDialog intro = new JDialog(this, "joku viesti tai ohje", true);
//		intro.setSize(new Dimension(530, 330));
//		intro.setLocationRelativeTo(null);
//		intro.add(new Intro(intro));
//		intro.setVisible(true);
//            }

            
//            private void configureButtons(JButton button) {         
//		
//                button.setAlignmentX(Component.LEFT_ALIGNMENT);  
//            }
 
                //Tarviiko näitä nappien aktivointeja mihinkään?
//            public void deactivateButtons() {
//		
//		this.searchB.setEnabled(false);
//		this.addB.setEnabled(false);
//		this.removeB.setEnabled(false);
//		this.saveB.setEnabled(false);
//		this.deleteB.setEnabled(false);
//		this.buttonsActive = false;
//            }
//            
//            public void activateButtons() {
//		
//		this.searchB.setEnabled(true);
//		this.addB.setEnabled(true);
//		this.removeB.setEnabled(true);
//		this.saveB.setEnabled(true);
//		this.deleteB.setEnabled(true);
//		this.buttonsActive = true;
//            }
            
            public void updateTextArea(String text) {
		
                //tyhjentää tekstikentän kun jotain uutta tulee
                textArea.setText("");
		
		this.textArea.append(text);
		
		//textArea näyttää uusimman päivityksen
		this.textArea.setText(this.textArea.getText());
            }

        
            public static void main(String[] args) {

                GUI gui = new GUI();
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.pack();
                gui.setLocationRelativeTo(null);
                gui.setVisible(true);
                gui.setResizable(false);
        
            }
}
