/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;
import entities.*;
import views.PessoaForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.PessoaDAO;

/**
 *
 * @author daniel
 */
public class PessoaControler {
    List<Pessoa> bdPessoas;
    Tarefa localTarefa; // tarefa atual
    PessoaForm pesForm;
    
    public PessoaControler(Tarefa t){
        this.localTarefa = t;
        
        pesForm = new PessoaForm();
        pesForm.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        pesForm.setTitle("Tarefa: "+ t.getId() +" - Pessoas");
        
    } 
    
    //cria as acoes dos botoes
    public void start(){
        pesForm.setVisible(true);
        
        listPessoas();
        clear();
        //botao adicionar material
        pesForm.addPessoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesForm.nomePesText.setText(" ");
                pesForm.funcaoText.setText(" ");
                pesForm.custoText.setText(" ");
                pesForm.addPessoaButton.setEnabled(false);
                pesForm.nomePesText.setEnabled(true);
                pesForm.funcaoText.setEnabled(true);
                pesForm.custoText.setEnabled(true);
                pesForm.cancelButton.setEnabled(true);
                pesForm.addNewPesButton.setEnabled(true);
            }
        });
        
        //botao editar Pessoa
        pesForm.editarPesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = pesForm.pessoasTable.getSelectedRow();
                if (index >= 0){
                    pesForm.addPessoaButton.setEnabled(false);
                    pesForm.deleteButton.setEnabled(false);
                    pesForm.editarPesButton.setEnabled(false);
                    pesForm.nomePesText.setEnabled(true);
                    pesForm.nomePesText.setText(pesForm.pessoasTable.getModel().getValueAt(index, 1).toString());
                    pesForm.funcaoText.setEnabled(true);
                    pesForm.funcaoText.setText(pesForm.pessoasTable.getModel().getValueAt(index, 2).toString());
                    pesForm.custoText.setEnabled(true);
                    pesForm.custoText.setText(pesForm.pessoasTable.getModel().getValueAt(index, 3).toString());

                    pesForm.cancelButton.setEnabled(true);
                    pesForm.atualizarButton.setEnabled(true);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Nenhum Material foi selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
        
        //botao Remover Material
        pesForm.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = pesForm.pessoasTable.getSelectedRow();
        
                bdPessoas = PessoaDAO.getPessoasByIdTarefa(localTarefa.getId());

                if (index >= 0){
                    int id = Integer.parseInt(pesForm.pessoasTable.getModel().getValueAt(index, 0).toString());
                    for (Pessoa m:bdPessoas){
                        if (id == m.getId()){
                            PessoaDAO.deletaPessoa(id);
                        }
                    }
                    DefaultTableModel tableModel = (DefaultTableModel)pesForm.pessoasTable.getModel();
                    tableModel.setNumRows(0);
                    JOptionPane.showMessageDialog(null, "Material excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    listPessoas();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Nenhum Material foi selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
        
        //botao criar novo Material
        pesForm.addNewPesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (pesForm.nomePesText.getText().isEmpty() || pesForm.nomePesText.getText().equals(" ")){
                    JOptionPane.showMessageDialog(null, "Insira o nome da Pessoa", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else if (pesForm.funcaoText.getText().isEmpty() || pesForm.funcaoText.getText().equals(" ")){
                    JOptionPane.showMessageDialog(null, "A Função não foi informada", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else if (pesForm.custoText.getText().isEmpty() || pesForm.custoText.getText().equals(" ")){
                    JOptionPane.showMessageDialog(null, "O valor do custo não foi informado", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else{
                     
                     Pessoa m = new Pessoa();
                     m.setId_tarefa(localTarefa.getId());
                     m.setNome(pesForm.nomePesText.getText().trim());
                     m.setFuncao(pesForm.funcaoText.getText().trim());
                     m.setCusto(Float.parseFloat(pesForm.custoText.getText().trim()));

                     PessoaDAO.criaPessoa(m);

                     pesForm.deleteButton.setEnabled(true);
                     pesForm.confirmButton.setEnabled(true);
                     clear();
                     listPessoas();
                     
                }
            }
        });
        
        //botao Atualizar Material
        pesForm.atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = pesForm.pessoasTable.getSelectedRow();
                if (index >= 0){
                    int id = Integer.parseInt(pesForm.pessoasTable.getModel().getValueAt(index, 0).toString());
                    String nome = pesForm.pessoasTable.getModel().getValueAt(index, 1).toString();
                    String funcao = pesForm.pessoasTable.getModel().getValueAt(index, 2).toString();
                    

                    String selNome = pesForm.nomePesText.getText();
                    String selFunc = pesForm.funcaoText.getText();
                    float selCusto = Float.parseFloat(pesForm.custoText.getText());
                    
                    if (!nome.equals(selNome) || !funcao.equals(selFunc)){
                        
                            Pessoa t = PessoaDAO.getPessoaById(id);
                            t.setNome(selNome);
                            t.setFuncao(selFunc);
                            t.setCusto(selCusto);
                            PessoaDAO.updatePessoa(t);
                            listPessoas();
                            clear();
                            
                            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso", "Aviso", JOptionPane.OK_OPTION);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Nenhuma Pessoa foi selecionada", "Erro", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
        
        //botao cancelar
        pesForm.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        
        //botao Voltar
        pesForm.confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesForm.dispose();
            }
        });
    }
    
     //Listar as Pessoas adicionados
    private void listPessoas(){
        DefaultTableModel tableModel = new DefaultTableModel(0,4); 
        tableModel.setColumnIdentifiers(new Object[] {"Id","Nome", "Função", "Custo"});
        bdPessoas = PessoaDAO.getPessoasByIdTarefa(localTarefa.getId());
        
        if(!bdPessoas.isEmpty()){
            for (Pessoa m:bdPessoas){
                tableModel.addRow(new Object[]{m.getId(),m.getNome(),m.getFuncao(),m.getCusto()});
                pesForm.pessoasTable.setModel(tableModel);
                pesForm.deleteButton.setEnabled(true);
                pesForm.editarPesButton.setEnabled(true);
            }
        }
        else{
            pesForm.deleteButton.setEnabled(false);
            pesForm.editarPesButton.setEnabled(false);
        }
    }
    
    // desabilitar a area de criacao de pessoas
    private void clear(){
        pesForm.nomePesText.setText(" ");
        pesForm.funcaoText.setText(" ");
        pesForm.custoText.setText(" ");
        if (!bdPessoas.isEmpty()){
            pesForm.deleteButton.setEnabled(true);
            pesForm.editarPesButton.setEnabled(true);
        }
        pesForm.nomePesText.setEnabled(false);
        pesForm.funcaoText.setEnabled(false);
        pesForm.custoText.setEnabled(false);
        pesForm.cancelButton.setEnabled(false);
        pesForm.addNewPesButton.setEnabled(false);
        pesForm.atualizarButton.setEnabled(false);
        pesForm.addPessoaButton.setEnabled(true);
    }
}
