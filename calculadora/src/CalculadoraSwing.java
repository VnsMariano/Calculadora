import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraSwing extends JFrame implements ActionListener {
    private JTextField campoTexto;
    private double numero1, numero2, resultado;
    private String operacao;

    public CalculadoraSwing() {
        // Janela
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Campo de texto
        campoTexto = new JTextField();
        campoTexto.setEditable(false);
        campoTexto.setFont(new Font("Arial", Font.BOLD, 24));
        add(campoTexto, BorderLayout.NORTH);

        // Painel de botões
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 18));
            botao.addActionListener(this);
            painel.add(botao);
        }

        add(painel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.matches("[0-9\\.]")) {
            campoTexto.setText(campoTexto.getText() + comando);
        } else if (comando.matches("[+\\-*/]")) {
            try {
                numero1 = Double.parseDouble(campoTexto.getText());
                operacao = comando;
                campoTexto.setText("");
            } catch (NumberFormatException ex) {
                campoTexto.setText("Erro");
            }
        } else if (comando.equals("=")) {
            try {
                numero2 = Double.parseDouble(campoTexto.getText());
                switch (operacao) {
                    case "+": resultado = numero1 + numero2; break;
                    case "-": resultado = numero1 - numero2; break;
                    case "*": resultado = numero1 * numero2; break;
                    case "/": resultado = numero1 / numero2; break;
                }
                campoTexto.setText(String.valueOf(resultado));
            } catch (Exception ex) {
                campoTexto.setText("Erro");
            }
        } else if (comando.equals("C")) {
            campoTexto.setText("");
            numero1 = 0;
            numero2 = 0;
            resultado = 0;
            operacao = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraSwing().setVisible(true);
        });
    }
}
