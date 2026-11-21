import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends JFrame {

    private JTable listaVendas;
    private DefaultTableModel modelo;
    private JButton btnVoltar;

    public vendasVIEW() {
        setTitle("Cenaflix - Produtos Vendidos");
        setSize(550, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Lista de Produtos Vendidos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Lucida Fax", Font.BOLD, 18));
        lblTitulo.setBounds(30, 20, 480, 30);
        add(lblTitulo);

        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Valor", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        listaVendas = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(listaVendas);
        scroll.setBounds(30, 70, 480, 230);
        add(scroll);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(400, 325, 110, 32);
        add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        listarProdutosVendidos();
    }

    private void listarProdutosVendidos() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();
            modelo.setNumRows(0);
            
            ArrayList<ProdutosDTO> listagem = produtosdao.listarProdutosVendidos();
            
            for (int i = 0; i < listagem.size(); i++) {
                modelo.addRow(new Object[]{
                    listagem.get(i).getId(),
                    listagem.get(i).getNome(),
                    listagem.get(i).getValor(),
                    listagem.get(i).getStatus()
                });
            }
        } catch (Exception e) {
        }
    }
}