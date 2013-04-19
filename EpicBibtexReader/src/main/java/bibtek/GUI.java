package bibtek;

import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame {
            //GUI implements Observer ja sit Bibtex extends Observable ja implements ActionListener?
    
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
        private JPanel buttonPanel = new JPanel();
       
        private JLabel searchL = new JLabel("Search:");
        private JLabel authorL = new JLabel("Author:");
        private JLabel titleL = new JLabel("Title:");
        private JLabel yearL = new JLabel("Year:");
        private JLabel pagesL = new JLabel("Pages:");
        private JLabel publisherL = new JLabel("Publisher:");
        private JLabel volumeL = new JLabel("Volume:");
        private JLabel deleteL = new JLabel("Delete:");
        
        private JTextField searchF = new JTextField(30);
        private JTextField authorF = new JTextField(25);
        private JTextField titleF = new JTextField(25);
        private JTextField yearF = new JTextField(10);
        private JTextField pagesF = new JTextField(10);
        private JTextField publisherF = new JTextField(25);
        private JTextField volumeF = new JTextField(10);
        private JTextField deleteID = new JTextField(10);
        
        private JButton searchB = new JButton("Search");
        private JButton addB = new JButton("Add");
        private JButton removeB = new JButton("Remove");
        private JButton saveB = new JButton("Save");
        private JButton deleteB = new JButton("Delete");
        private boolean buttonsActive = true;
        
        public GUI(){
        
            super("EpicBibtexReader");
            
            //this.OLIO = new OLIO();
            //this.OLIO.addObserver(this);
            
            //asetetaan paneelit
            //mainPanel.setLayout(new GridLayout(4, 0, 5, 5));
            this.getContentPane().add(BorderLayout.NORTH, this.topPanel);
            this.getContentPane().add(BorderLayout.CENTER, this.middlePanel);
            this.getContentPane().add(BorderLayout.SOUTH, this.bottomPanel);

            //määritellään paneelien yksityiskohdat
            this.topPanel.setLayout(new BoxLayout(this.topPanel, BoxLayout.X_AXIS));
            this.topPanel.setPreferredSize(new Dimension(700, 50));
            this.topPanel.add(this.searchB);
            this.topPanel.add(this.addB);
            this.topPanel.add(this.removeB);
                                                        
            this.middlePanel.setLayout(new FlowLayout());
            //this.middlePanel.setLayout(new BoxLayout(this.middlePanel, FlowLayout.LEFT));
            this.middlePanel.setPreferredSize(new Dimension(700, 150));
            this.middlePanel.add(Box.createHorizontalStrut(20));
            this.middlePanel.add(this.searchL);
            this.middlePanel.add(this.searchF); 
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
            this.middlePanel.add(this.pagesL);
            this.middlePanel.add(this.pagesF);
            this.middlePanel.add(Box.createHorizontalStrut(15));
            this.middlePanel.add(this.publisherL);
            this.middlePanel.add(this.publisherF);
            this.middlePanel.add(Box.createHorizontalStrut(15));
            this.middlePanel.add(this.volumeL);
            this.middlePanel.add(this.volumeF);
            this.middlePanel.add(Box.createHorizontalStrut(15));
            this.middlePanel.add(this.deleteB);
            this.middlePanel.add(this.saveB);
            

            this.bottomPanel.setLayout(new BoxLayout(this.bottomPanel, BoxLayout.X_AXIS));
            this.bottomPanel.setPreferredSize(new Dimension(700, 100));
            this.bottomPanel.add(this.scroll);
            this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            
            //luodaan tekstikenttä
            this.textArea.setLineWrap(true);
            this.textArea.setWrapStyleWord(true);
            this.textArea.setEditable(false);
            this.textArea.setText("  Welcome to EpicBibtexReader! \n");
            
            //asetetaan komponentit
            this.topPanel.add(Box.createHorizontalStrut(20));
            this.topPanel.add(this.searchB);
            this.topPanel.add(Box.createHorizontalStrut(15));
            this.topPanel.add(this.addB);
            this.topPanel.add(Box.createHorizontalStrut(15));
            this.topPanel.add(this.removeB);
            this.topPanel.add(Box.createHorizontalStrut(15));
            //tähän sit keskipaneelin jutut:

            
            //nappien ActionListenerien alustus
            //this.searchB.addActionListener();
            
        }            
//            public void introDialogue() {
//		
//		JDialog intro = new JDialog(this, "joku viesti tai ohje", true);
//		intro.setSize(new Dimension(530, 330));
//		intro.setLocationRelativeTo(null);
//		intro.add(new Intro(intro));
//		intro.setVisible(true);
//            }

            
            private void configureButtons(JButton button) {         
		
                button.setAlignmentX(Component.LEFT_ALIGNMENT);  
            }
            
            public void deactivateButtons() {
		
		this.searchB.setEnabled(false);
		this.addB.setEnabled(false);
		this.removeB.setEnabled(false);
		this.saveB.setEnabled(false);
		this.deleteB.setEnabled(false);
		this.buttonsActive = false;
            }
            
            public void activateButtons() {
		
		this.searchB.setEnabled(true);
		this.addB.setEnabled(true);
		this.removeB.setEnabled(true);
		this.saveB.setEnabled(true);
		this.deleteB.setEnabled(true);
		this.buttonsActive = true;
            }
            
            /*public void updateTextArea(String text) {
		
		//teksti uudelta riviltä?
		String lineStart = "\n   ";
		
		this.textArea.append(text + lineStart);
		
		//textArea näyttää uusimman päivityksen
		
		this.textArea.setText(this.textArea.getText());
            }*/

        
            public static void main(String[] args) {

                GUI gui = new GUI();
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.pack();
                gui.setLocationRelativeTo(null);
                gui.setVisible(true);
                gui.setResizable(false);
        
            }
}
