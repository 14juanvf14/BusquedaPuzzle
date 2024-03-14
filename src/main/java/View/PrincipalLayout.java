package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PrincipalLayout extends JFrame {
    private JPanel panel1;
    private JLabel lbl_Tittle;
    private JPanel panel00;
    private JPanel panel10;
    private JPanel panel20;
    private JLabel label00;
    private JLabel label10;
    private JLabel label20;
    private JPanel panel01;
    private JPanel panel02;
    private JPanel panel11;
    private JPanel panel12;
    private JPanel panel21;
    private JPanel panel22;
    private JLabel label01;
    private JLabel label11;
    private JLabel label21;
    private JLabel label02;
    private JLabel label12;
    private JLabel label22;
    private JButton btnLeerArchivo;
    private JButton btnHc;
    private JButton btnBpp;
    private JButton btnAA;
    private JButton btn_gen;
    private JTextField textField1;

    public PrincipalLayout( ){
        super("Puzzle 8 Juan Vasquez - Sebastian Avila - Juan Bolivar");

        setContentPane(panel1);
        pack();

        btnLeerArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int seleccion = fileChooser.showOpenDialog(PrincipalLayout.this);

                // Si el usuario selecciona un archivo y hace clic en "Aceptar"
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        // Lee el contenido del archivo
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        reader.close();


                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                };
            }

        });
    }
}
