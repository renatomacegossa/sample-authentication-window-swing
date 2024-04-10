import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.concurrent.CountDownLatch;

public class AuthenticationWindow {

    private CountDownLatch latch;

    private final JPasswordField password;
    private final JTextField otp;

    private final JFrame frame;

    public AuthenticationWindow() {

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setPreferredSize(new Dimension(60, 35));

        JLabel otpLabel = new JLabel("OTP:");
        otpLabel.setPreferredSize(new Dimension(60, 35));

        password = new JPasswordField();
        password.setPreferredSize(new Dimension(200,35));

        otp = new JTextField();
        otp.setPreferredSize(new Dimension(200,35));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JButton xxx = new JButton("XXX");
        xxx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                latch.countDown();

                frame.dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel.add(passwordLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel.add(password, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        panel.add(otpLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        panel.add(otp, gridBagConstraints);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panel);
        mainPanel.add(xxx);

        frame = new JFrame("TITLE");
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);

                password.requestFocus();
            }
        });
    }

    public void show() {

        latch = new CountDownLatch(1);

        password.setText("");

        otp.setText("");

        frame.setVisible(true);
    }

    public String[] data() throws InterruptedException {

        latch.await();

        return new String[] { password.getText(), otp.getText() };
    }
}
