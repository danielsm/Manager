/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author daniel
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        novoProjBt = new javax.swing.JButton();
        editarProjBt = new javax.swing.JButton();
        deleteProjBt = new javax.swing.JButton();
        consultarProjBt = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        nomeLabel = new javax.swing.JLabel();
        nomeProjTxt = new javax.swing.JTextField();
        duracaoLabel = new javax.swing.JLabel();
        durSpinner = new javax.swing.JSpinner();
        addProjBt = new javax.swing.JButton();
        cancelarBt = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        projetosTable = new javax.swing.JTable();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Projetos");

        jLabel1.setText("Projetos");

        novoProjBt.setText("Criar Novo");
        novoProjBt.setToolTipText("");
        

        editarProjBt.setText("Editar");
        editarProjBt.setEnabled(false);
        
        deleteProjBt.setText("Deletar");
        deleteProjBt.setEnabled(false);
        

        consultarProjBt.setText("Consultar");
        consultarProjBt.setEnabled(false);

        nomeLabel.setText("Nome:");
        nomeLabel.setEnabled(false);

        nomeProjTxt.setEnabled(false);

        duracaoLabel.setText("Duração:");
        duracaoLabel.setEnabled(false);

        durSpinner.setEnabled(false);

        addProjBt.setText("Adicionar");
        addProjBt.setEnabled(false);
       

        cancelarBt.setText("Cancelar");
        cancelarBt.setEnabled(false);
        

        projetosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Duração"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(projetosTable);
        if (projetosTable.getColumnModel().getColumnCount() > 0) {
            projetosTable.getColumnModel().getColumn(1).setResizable(false);
            projetosTable.getColumnModel().getColumn(1).setPreferredWidth(6);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(cancelarBt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)
                            .addComponent(addProjBt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(novoProjBt, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                        .addComponent(editarProjBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(deleteProjBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(consultarProjBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(duracaoLabel)
                                    .addGap(1, 1, 1)
                                    .addComponent(durSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(nomeLabel)
                                    .addGap(2, 2, 2)
                                    .addComponent(nomeProjTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(novoProjBt)
                        .addGap(18, 18, 18)
                        .addComponent(editarProjBt)
                        .addGap(18, 18, 18)
                        .addComponent(deleteProjBt)
                        .addGap(18, 18, 18)
                        .addComponent(consultarProjBt))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 84, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeLabel)
                    .addComponent(nomeProjTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(duracaoLabel)
                    .addComponent(durSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProjBt)
                    .addComponent(cancelarBt))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>                        

    // Variables declaration - do not modify                     
    public javax.swing.JButton addProjBt;
    public javax.swing.JButton cancelarBt;
    public javax.swing.JButton consultarProjBt;
    public javax.swing.JButton deleteProjBt;
    public javax.swing.JSpinner durSpinner;
    public javax.swing.JLabel duracaoLabel;
    public javax.swing.JButton editarProjBt;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JLabel nomeLabel;
    public javax.swing.JTextField nomeProjTxt;
    public javax.swing.JButton novoProjBt;
    public javax.swing.JTable projetosTable;
    // End of variables declaration                   
}