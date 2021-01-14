package A2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

//java http://server.com/login.cgi
//cgi, Common Gateway Interface, can be written in Shell, Perl, C and Java

public class Post extends JPanel implements ActionListener{
    JTextField nameField;
    JPasswordField passwordField;
    String postURL;

    GridBagConstraints constraints = new GridBagConstraints();

    //Add new Component
    void addGB(Component component, int x, int y){
        constraints.gridx = x; constraints.gridy = y;
        add(component, constraints);
    }

    public Post (String postURL){
        this.postURL = postURL;

        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
        JButton postButton = new JButton("Post");
        postButton.addActionListener(this);
        setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addGB(new JLabel("Name", JLabel.TRAILING), 0, 0);
        addGB(nameField = new JTextField(20), 1, 0);
        addGB(new JLabel("Password", JLabel.TRAILING), 0, 1);
        addGB(nameField = new JTextField(20), 1, 1);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridwidth=2;
        constraints.anchor = GridBagConstraints.EAST;
        addGB(postButton, 1, 2);
    }

    protected void postData(){
        StringBuilder sb = new StringBuilder();
        String pw = new String(passwordField.getPassword());
        try {
            sb.append(URLEncoder.encode("Name", "UTF-8")).append("=");
            sb.append(URLEncoder.encode(nameField.getText(), "UTF-8"));
            sb.append("&").append(URLEncoder.encode(nameField.getText(), "UTF-8")).append("=");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        postData();
    }


    public static void main(String[] args) {
        if (args.length != 1){
            System.err.println("Must specify URL on command line");
            System.exit(-1);
        }

        JFrame frame = new JFrame("Simple Post");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Post(args[0]), "Center");
        frame.pack();
        frame.setVisible(true);
    }
}
