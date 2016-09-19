/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;
import dao.MaterialDAO;
import entities.*;
import views.MateriaisForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author daniel
 */
public class MaterialControler {
    List<Material> bdMateriais;
    Tarefa localTarefa; // tarefa atual
    MateriaisForm matForm;
    
    public MaterialControler(Tarefa t){
        this.localTarefa = t;
        
        matForm = new MateriaisForm();
        matForm.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        matForm.setTitle("Tarefa: "+ t.getId() +" - Materiais");
        
    } 
    
    //cria as acoes dos botoes
    public void start(){
        matForm.setVisible(true);
        
        listMateriais();
        clear();
        //botao adicionar material
        matForm.addMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matForm.nomeMatTxt.setText(" ");
                matForm.qntdText.setText(" ");
                matForm.custoText.setText(" ");
                matForm.addMaterialButton.setEnabled(false);
                matForm.nomeMatTxt.setEnabled(true);
                matForm.qntdText.setEnabled(true);
                matForm.custoText.setEnabled(true);
                matForm.cancelButton.setEnabled(true);
                matForm.addNewMatButton.setEnabled(true);
            }
        });
        
        //botao editar Material
        matForm.editarMatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = matForm.materiaisTable.getSelectedRow();
                if (index >= 0){
                    matForm.addMaterialButton.setEnabled(false);
                    matForm.deleteButton.setEnabled(false);
                    matForm.editarMatButton.setEnabled(false);
                    matForm.nomeMatTxt.setEnabled(true);
                    matForm.nomeMatTxt.setText(matForm.materiaisTable.getModel().getValueAt(index, 1).toString());
                    matForm.qntdText.setEnabled(true);
                    matForm.qntdText.setText(matForm.materiaisTable.getModel().getValueAt(index, 2).toString());
                    matForm.custoText.setEnabled(true);
                    matForm.custoText.setText(matForm.materiaisTable.getModel().getValueAt(index, 3).toString());

                    matForm.cancelButton.setEnabled(true);
                    matForm.atualizarButton.setEnabled(true);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Nenhum Material foi selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
        
        //botao Remover Material
        matForm.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = matForm.materiaisTable.getSelectedRow();
        
                bdMateriais = MaterialDAO.getMateriaisByIdTarefa(localTarefa.getId());

                if (index >= 0){
                    int id = Integer.parseInt(matForm.materiaisTable.getModel().getValueAt(index, 0).toString());
                    for (Material m:bdMateriais){
                        if (id == m.getId()){
                            MaterialDAO.deletaMaterial(id);
                        }
                    }
                    DefaultTableModel tableModel = (DefaultTableModel)matForm.materiaisTable.getModel();
                    tableModel.setNumRows(0);
                    JOptionPane.showMessageDialog(null, "Material excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    listMateriais();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Nenhum Material foi selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
        
        //botao criar novo Material
        matForm.addNewMatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int qntd = 0;
                if (matForm.nomeMatTxt.getText().isEmpty() || matForm.nomeMatTxt.getText().equals(" ")){
                    JOptionPane.showMessageDialog(null, "Insira o nome do Material", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else if (matForm.qntdText.getText().isEmpty() || matForm.qntdText.getText().equals(" ")){
                    JOptionPane.showMessageDialog(null, "A Quantidade não foi informada", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else if (matForm.custoText.getText().isEmpty() || matForm.custoText.getText().equals(" ")){
                    JOptionPane.showMessageDialog(null, "O valor do custo não foi informado", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else{
                     qntd = Integer.parseInt(matForm.qntdText.getText().trim());
                     if (qntd <= 0){
                         JOptionPane.showMessageDialog(null, "A Quantidade deve ser maior que 0", "Erro", JOptionPane.ERROR_MESSAGE); 
                     }
                     else {

                         Material m = new Material();
                         m.setId_tarefa(localTarefa.getId());
                         m.setNome(matForm.nomeMatTxt.getText().trim());
                         m.setQuantidade(qntd);
                         m.setCusto(Float.parseFloat(matForm.custoText.getText().trim()));

                         MaterialDAO.criaMaterial(m);

                         matForm.deleteButton.setEnabled(true);
                         matForm.confirmButton.setEnabled(true);
                         clear();
                         listMateriais();
                     }
                }
            }
        });
        
        //botao Atualizar Material
        matForm.atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = matForm.materiaisTable.getSelectedRow();
                if (index >= 0){
                    int id = Integer.parseInt(matForm.materiaisTable.getModel().getValueAt(index, 0).toString());
                    String nome = matForm.materiaisTable.getModel().getValueAt(index, 1).toString();
                    int qntd = Integer.parseInt(matForm.materiaisTable.getModel().getValueAt(index, 2).toString());
                    float custo = Float.parseFloat(matForm.materiaisTable.getModel().getValueAt(index, 3).toString());

                    String selNome = matForm.nomeMatTxt.getText();
                    int selQntd = Integer.parseInt(matForm.qntdText.getText());
                    float selCusto = Float.parseFloat(matForm.custoText.getText());
                    
                    if (!nome.equals(selNome) || qntd != selQntd){
                        if (selQntd > 0){
                            Material t = MaterialDAO.getMaterialById(id);
                            t.setNome(selNome);
                            t.setQuantidade(selQntd);
                            t.setCusto(selCusto);
                            MaterialDAO.updateMaterial(t);
                            listMateriais();
                            clear();
                            
                            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso", "Aviso", JOptionPane.OK_OPTION);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "A Quantidade deve ser maior que 0", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Nenhum Material foi selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
        
        //botao cancelar
        matForm.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        
        //botao Voltar
        matForm.confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matForm.dispose();
            }
        });
    }
    
     //Listar as os Materias ja adicionados
    private void listMateriais(){
        DefaultTableModel tableModel = new DefaultTableModel(0,4); 
        tableModel.setColumnIdentifiers(new Object[] {"Id","Nome", "Quantidade", "Custo"});
        bdMateriais = MaterialDAO.getMateriaisByIdTarefa(localTarefa.getId());
        
        if(!bdMateriais.isEmpty()){
            for (Material m:bdMateriais){
                tableModel.addRow(new Object[]{m.getId(),m.getNome(),m.getQuantidade(),m.getCusto()});
                matForm.materiaisTable.setModel(tableModel);
                matForm.deleteButton.setEnabled(true);
                matForm.editarMatButton.setEnabled(true);
            }
        }
        else{
            matForm.deleteButton.setEnabled(false);
            matForm.editarMatButton.setEnabled(false);
        }
    }
    
    // desabilitar a area de criacao de materiais
    private void clear(){
        matForm.nomeMatTxt.setText(" ");
        matForm.qntdText.setText(" ");
        matForm.custoText.setText(" ");
        if (!bdMateriais.isEmpty()){
            matForm.deleteButton.setEnabled(true);
            matForm.editarMatButton.setEnabled(true);
        }
        matForm.nomeMatTxt.setEnabled(false);
        matForm.qntdText.setEnabled(false);
        matForm.custoText.setEnabled(false);
        matForm.cancelButton.setEnabled(false);
        matForm.addNewMatButton.setEnabled(false);
        matForm.atualizarButton.setEnabled(false);
        matForm.addMaterialButton.setEnabled(true);
    }
}
