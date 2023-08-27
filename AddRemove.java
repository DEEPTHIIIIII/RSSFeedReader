import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class AddRemove extends JFrame {
    private ArrayList<String> urls;
    private JPanel contentPanel;
    private JTextField urlInput;
    private JPanel urlsPanel;

    public AddRemove() {
        urls = new ArrayList<>();
        setTitle("Add and Remove URLs");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create a gradient background using JPanel
        contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(200, 230, 255); // Light blue
                Color endColor = new Color(255, 210, 220);   // Light pink
                GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                g2d.setPaint(gradientPaint);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPanel.setLayout(new GridBagLayout());
        setContentPane(contentPanel);

        // Label "Welcome to RSS Feed Reader" at the center on the top
        JLabel welcomeLabel = new JLabel("WELCOME TO RSS FEED READER", SwingConstants.CENTER);
        GridBagConstraints welcomeLabelConstraints = new GridBagConstraints();
        welcomeLabelConstraints.gridx = 0;
        welcomeLabelConstraints.gridy = 0;
        welcomeLabelConstraints.gridwidth = 2;
        welcomeLabelConstraints.gridheight = 1;
        welcomeLabelConstraints.weightx = 1.0;
        welcomeLabelConstraints.weighty = 0.5;
        welcomeLabelConstraints.anchor = GridBagConstraints.PAGE_START;
        contentPanel.add(welcomeLabel, welcomeLabelConstraints);

        // Additional label with specified content
        JLabel descriptionLabel = new JLabel("<html>Want to catch up with the latest news but no time? " +
                "No worries, We got you covered.<br>Just enter the URL and we will give you the latest updates " +
                "in just 2 or 3 lines.</html>", SwingConstants.CENTER);
        GridBagConstraints descriptionLabelConstraints = new GridBagConstraints();
        descriptionLabelConstraints.gridx = 0;
        descriptionLabelConstraints.gridy = 1;
        descriptionLabelConstraints.gridwidth = 2;
        descriptionLabelConstraints.gridheight = 1;
        descriptionLabelConstraints.weightx = 1.0;
        descriptionLabelConstraints.weighty = 0.5;
        descriptionLabelConstraints.anchor = GridBagConstraints.CENTER;
        contentPanel.add(descriptionLabel, descriptionLabelConstraints);

        // Label "ENTER THE URL" in the center of the screen
        JLabel centerLabel = new JLabel("ENTER THE URL", SwingConstants.CENTER);
        GridBagConstraints centerLabelConstraints = new GridBagConstraints();
        centerLabelConstraints.gridx = 0;
        centerLabelConstraints.gridy = 2;
        centerLabelConstraints.gridwidth = 2;
        centerLabelConstraints.gridheight = 1;
        centerLabelConstraints.weightx = 1.0;
        centerLabelConstraints.weighty = 0.5;
        centerLabelConstraints.anchor = GridBagConstraints.PAGE_START;
        contentPanel.add(centerLabel, centerLabelConstraints);

        // Input textarea for URL
        urlInput = new JTextField("Type here", 30);
        // Rounded border for the input box
        Border roundedBorder = BorderFactory.createLineBorder(Color.GRAY, 1, true);
        urlInput.setBorder(roundedBorder);
        urlInput.setPreferredSize(new Dimension(150, 50));

        // Adding FocusListener to remove the "Type here" placeholder text when the text field is clicked
        urlInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (urlInput.getText().equals("Type here")) {
                    urlInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (urlInput.getText().isEmpty()) {
                    urlInput.setText("Type here");
                }
            }
        });

        // Adding ActionListener to the text field to add the URL when the Enter key is pressed
        urlInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addURL();
            }
        });

        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 0;
        inputConstraints.gridy = 3;
        inputConstraints.gridwidth = 2;
        inputConstraints.gridheight = 1;
        inputConstraints.weightx = 1.0;
        inputConstraints.weighty = 0.5;
        inputConstraints.anchor = GridBagConstraints.PAGE_START;
        inputConstraints.insets = new Insets(0, 0, 5, 0); // Add space between input and button
        contentPanel.add(urlInput, inputConstraints);

        // Add button to add the URL
        JButton addButton = new JButton("ADD");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addURL();
            }
        });
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 4;
        buttonConstraints.gridwidth = 2;
        buttonConstraints.gridheight = 1;
        buttonConstraints.weightx = 1.0;
        buttonConstraints.weighty = 0.5;
        buttonConstraints.anchor = GridBagConstraints.PAGE_START;
        buttonConstraints.insets = new Insets(0, 0, 10, 0); // Add space between input and button
        contentPanel.add(addButton, buttonConstraints);

        // Panel to display the entered URLs
        urlsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(200, 230, 255); // Light blue
                Color endColor = new Color(255, 210, 220);   // Light pink
                GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                g2d.setPaint(gradientPaint);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        urlsPanel.setOpaque(false); // Make the panel transparent to show the gradient background
        urlsPanel.setLayout(new BoxLayout(urlsPanel, BoxLayout.Y_AXIS));
        GridBagConstraints urlsConstraints = new GridBagConstraints();
        urlsConstraints.gridx = 0;
        urlsConstraints.gridy = 5;
        urlsConstraints.gridwidth = 2;
        urlsConstraints.gridheight = 1;
        urlsConstraints.weightx = 1.0;
        urlsConstraints.weighty = 1.0;
        urlsConstraints.fill = GridBagConstraints.BOTH;
        contentPanel.add(urlsPanel, urlsConstraints);

        setVisible(true);
    }

    private void addURL() {
        String url = urlInput.getText().trim();
        if (!url.isEmpty() && !url.equals("Type here")) {
            urls.add(url);
            displayUrls();
            urlInput.setText("");
        }
    }

    private void displayUrls() {
        urlsPanel.removeAll(); // Clear the panel before re-populating
        for (String url : urls) {
            JPanel urlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            rss rss =new rss();

            urlPanel.add(new JLabel(rss.rssfeed(url)));
            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeURL(url);
                }
            });
            urlPanel.add(removeButton);
            urlsPanel.add(urlPanel);
            urlsPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing between adjacent links
        }
        contentPanel.revalidate();
    }

    private void removeURL(String urlToRemove) {
        urls.remove(urlToRemove);
        displayUrls();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddRemove();
            }
        });
    }
}
