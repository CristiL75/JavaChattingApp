package JavaChattingApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client extends JFrame implements ActionListener{
    JTextField  text;
    static JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();

    static DataOutputStream dout;
    Client(){
        f.setLayout(null);
        
        JPanel p1 = new JPanel();
        p1.setBackground((Color.BLUE));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/sageataMess.PNG"));
        Image i2 = i1.getImage().getScaledInstance(25, 25 , Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                    System.exit(0);//cand apas pe sageata din stanga, iese din program


            }


        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/ronaldo.JPG"));
        Image i5 = i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 5, 70, 65);
        p1.add(profile);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.PNG"));
        Image i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel call = new JLabel(i9);
        call.setBounds(290, 20, 30, 30);
        p1.add(call);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/video.PNG"));
        Image i11 = i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel video = new JLabel(i12);
        video.setBounds(350, 20, 35, 30);
        p1.add(video);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.PNG"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel more = new JLabel(i15);
        more.setBounds(410, 20, 10, 25);
        p1.add(more);

        JLabel name = new JLabel("Cristiano Ronaldo");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 110, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);

         a1 = new JPanel();
        a1.setBounds(5, 75, 425, 570);
        f.add(a1);

         text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(Color.BLUE);
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);

        f.setSize(450, 700);
        f.setLocation(800, 50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);

        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            String out = text.getText();

            JLabel output = new JLabel(out);

            JPanel p2 = formatLabel(out);

            a1.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            a1.add(vertical, BorderLayout.PAGE_START);
            if(dout!=null){
            dout.writeUTF(out);}

            text.setText("");

            f.repaint();
            f.invalidate();
            f.validate();
        }catch(Exception e){
            e.printStackTrace();

        }

    
    }

    public static JPanel formatLabel(String out){
            JPanel Panel = new JPanel();
            Panel.setLayout(new BoxLayout(Panel,  BoxLayout.Y_AXIS));

            JLabel output = new JLabel("<html><p style=\"width: 150px\">"+ out + "</p></html>");
            output.setFont(new Font("Tahoma", Font.PLAIN, 16));
            output.setBackground(Color.BLUE);
            output.setOpaque(true);
            output.setBorder(new EmptyBorder(15, 15, 15, 50));

            Panel.add(output);

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            JLabel time = new JLabel();
            time.setText(sdf.format(cal.getTime()));

            Panel.add(time);

            return Panel;
    }

    public static void main(String[] args){
        new Client();

        try{
                Socket s =new Socket("127.0.0.1", 6001);
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());

                while(true){
                    a1.setLayout(new BorderLayout());
                    String msg = din.readUTF();
                    JPanel Panel = formatLabel(msg);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(Panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    vertical.add(Box.createVerticalStrut(15));
                    a1.add(vertical, BorderLayout.PAGE_START);

                    f.validate();

                }

        }catch(Exception e){
            
            e.printStackTrace();
        
        }


    }

    
}