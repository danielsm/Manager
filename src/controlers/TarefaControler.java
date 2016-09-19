/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import dao.TarefaDAO;
import entities.*;
import views.TarefaForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author daniel
 */
public class TarefaControler {
    
    List<Tarefa> bdTarefas;
    Projeto projetoLocal; // Projeto que esta sendo criado, vindo do form anterior, ProjectForm.
    TarefaForm tarForm;
    Tarefa local;
    
    public TarefaControler(Projeto p){
        this.projetoLocal = p;
        tarForm = new TarefaForm();
        //tarForm.setVisible(true);
        tarForm.setTitle("Projeto "+this.projetoLocal.getNome() +" - Tarefas");
        tarForm.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
       
        
    }
   
    //cria as acoes dos botoes
    public void start(){
        tarForm.setVisible(true);
        
        
        listTarefas();
        clear();
        //Botao adicionar tarefa
        tarForm.addTarefaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarForm.descricaoTextField.setText(" ");
                tarForm.inicioSpinner.setValue((Object)0);
                tarForm.durSpinner.setValue((Object)0);
                tarForm.addTarefaButton.setEnabled(false);
                tarForm.editarTarButton.setEnabled(false);
                tarForm.deleteButton.setEnabled(false);
                tarForm.descricaoTextField.setEnabled(true);
                tarForm.inicioSpinner.setEnabled(true);
                tarForm.durSpinner.setEnabled(true);
                tarForm.addNewTarButton.setEnabled(true);
                tarForm.cancelButton.setEnabled(true);
            }
        });
        
        //Botao editar tarefa
        tarForm.editarTarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tarForm.tarefasTable.getSelectedRow();

                if (index >= 0){
                    tarForm.addTarefaButton.setEnabled(false);
                    tarForm.editarTarButton.setEnabled(false);
                    tarForm.deleteButton.setEnabled(false);
                    tarForm.descricaoTextField.setEnabled(true);
                    tarForm.descricaoTextField.setText(tarForm.tarefasTable.getModel().getValueAt(index, 1).toString());
                    tarForm.inicioSpinner.setEnabled(true);
                    tarForm.inicioSpinner.setValue((Object)Integer.parseInt(tarForm.tarefasTable.getModel().getValueAt(index, 2).toString()));
                    tarForm.durSpinner.setEnabled(true);
                    tarForm.durSpinner.setValue((Object)Integer.parseInt(tarForm.tarefasTable.getModel().getValueAt(index, 3).toString()));
                    tarForm.cancelButton.setEnabled(true);
                    tarForm.inserirMatButton.setEnabled(true);
                    tarForm.inserirPesButton.setEnabled(true);
                    tarForm.updateTarButton.setEnabled(true);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Nenhuma Tarefa foi selecionada", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //botao Deletar Tarefa
        tarForm.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tarForm.tarefasTable.getSelectedRow();
                bdTarefas = TarefaDAO.getTarefasByNomeProjeto(projetoLocal.getNome());

                if (index >= 0){
                    int id = Integer.parseInt(tarForm.tarefasTable.getModel().getValueAt(index, 0).toString());
                    for (Tarefa t:bdTarefas){
                        if (id == t.getId()){
                            TarefaDAO.deletaTarefa(id);
                        }
                    }
                    DefaultTableModel tableModel = (DefaultTableModel)tarForm.tarefasTable.getModel();
                    tableModel.setNumRows(0);
                    listTarefas();
                    JOptionPane.showMessageDialog(null, "Tarefa excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                }
                else {
                    JOptionPane.showMessageDialog(null,"Nenhuma Tarefa foi selecionada", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //botao criar nova tarefa
        tarForm.addNewTarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome;
                int dur, inicio;
                nome = tarForm.descricaoTextField.getText().trim();

                inicio = (Integer)tarForm.inicioSpinner.getValue();
                dur = (Integer)tarForm.durSpinner.getValue();

                if(nome.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Por favor insira um nome", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                else if (inicio <= 0){
                    JOptionPane.showMessageDialog(null, "Tarefa deve iniciar pelo menos na 1ª semana", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                else if (dur <= 0){
                    JOptionPane.showMessageDialog(null, "Tarefa deve ter no minimo 1 semana de duração", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                else if (!nome.isEmpty() && inicio > 0 && dur > 0){
                    if (inicio+(dur-1) <= projetoLocal.getDuracao()){
                        local = new Tarefa();
                        local.setNome(nome);
                        local.setNomeProjeto(projetoLocal.getNome());
                        local.setSemanaInicio(inicio);
                        local.setDuracao(dur);                
                        TarefaDAO.criaTarefa(local);
                        listTarefas();

                        tarForm.deleteButton.setEnabled(false);
                        tarForm.editarTarButton.setEnabled(false);
                        tarForm.addNewTarButton.setEnabled(false);
                        tarForm.voltarButton.setEnabled(true);
                        tarForm.inserirMatButton.setEnabled(true);
                        tarForm.inserirPesButton.setEnabled(true);
                        tarForm.addTarefaButton.setEnabled(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Duração da TAREFA excede a duração do PROJETO", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        //botao Atualizar tarefa
        tarForm.updateTarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tarForm.tarefasTable.getSelectedRow();
                if (index >=0){
                    int id = Integer.parseInt(tarForm.tarefasTable.getModel().getValueAt(index, 0).toString());
                    String nome = tarForm.tarefasTable.getModel().getValueAt(index, 1).toString();
                    int ini = Integer.parseInt(tarForm.tarefasTable.getModel().getValueAt(index, 2).toString());
                    int dur = Integer.parseInt(tarForm.tarefasTable.getModel().getValueAt(index, 3).toString());

                    String selDesc = tarForm.descricaoTextField.getText();
                    int selIni = Integer.parseInt(tarForm.inicioSpinner.getValue().toString());
                    int selDur = Integer.parseInt(tarForm.durSpinner.getValue().toString());
                    if(!selDesc.equals(nome) || selIni != ini || selDur != dur){
                        if (selIni > 0){
                            if (selDur > 0){
                                Tarefa t = TarefaDAO.getTarefaById(id);
                                t.setNome(selDesc);
                                t.setSemanaInicio(selIni);
                                t.setDuracao(selDur);
                                TarefaDAO.updateTarefa(t);
                                listTarefas();
                                clear();
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Tarefa deve ter no minimo 1 semana de duração", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Tarefa deve iniciar pelo menos na 1ª semana", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Nenhuma Tarefa foi selecionada", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //botao adicionar Material
        tarForm.inserirMatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = tarForm.descricaoTextField.getText().trim();
                int inicio = (Integer)tarForm.inicioSpinner.getValue();
                int dur = (Integer)tarForm.durSpinner.getValue();


                if (nome.isEmpty() || nome.equals(" ")){
                    JOptionPane.showMessageDialog(null, "Nenhuma Tarefa selecionada","Erro",JOptionPane.ERROR_MESSAGE);
                }
                else if(inicio <=0){
                    JOptionPane.showMessageDialog(null, "Tarefa deve iniciar pelo menos na 1ª semana", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else if(dur <=0){
                    JOptionPane.showMessageDialog(null, "Tarefa deve ter no minimo 1 semana de duração", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    bdTarefas = TarefaDAO.getTarefasByNomeProjeto(projetoLocal.getNome());

                    for (Tarefa t:bdTarefas){
                        if (t.getNome().equals(nome)){
                           MaterialControler matControler = new MaterialControler(t);
                           matControler.start();
//                           MateriaisForm mat = new MateriaisForm(t);
//                           mat.setVisible(true);
                        }
                    }
                }
            }
        });
        
      //botao adicionar Pessoa
        tarForm.inserirPesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = tarForm.descricaoTextField.getText().trim();
                int inicio = (Integer)tarForm.inicioSpinner.getValue();
                int dur = (Integer)tarForm.durSpinner.getValue();


                if (nome.isEmpty() || nome.equals(" ")){
                    JOptionPane.showMessageDialog(null, "Nenhuma Tarefa selecionada","Erro",JOptionPane.ERROR_MESSAGE);
                }
                else if(inicio <=0){
                    JOptionPane.showMessageDialog(null, "Tarefa deve iniciar pelo menos na 1ª semana", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else if(dur <=0){
                    JOptionPane.showMessageDialog(null, "Tarefa deve ter no minimo 1 semana de duração", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    bdTarefas = TarefaDAO.getTarefasByNomeProjeto(projetoLocal.getNome());

                    for (Tarefa t:bdTarefas){
                        if (t.getNome().equals(nome)){
                        	PessoaControler pesControler = new PessoaControler(t);
                        	pesControler.start();
                           //MaterialControler matControler = new MaterialControler(t);
                           //matControler.start();
//                           MateriaisForm mat = new MateriaisForm(t);
//                           mat.setVisible(true);
                        }
                    }
                }
            }
        });
        
        //botao cancelar
        tarForm.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        
        //botao cancelar
        tarForm.voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarForm.dispose();
            }
        });
    }
    
    // Lista as tarefas existentes ou ja criadas para o projeto atual.
    private void listTarefas(){
         DefaultTableModel tableModel = new DefaultTableModel(0,4); 
         tableModel.setColumnIdentifiers(new Object[]{"Id","Nome","Ínicio(semana)","Duração"});
         bdTarefas = TarefaDAO.getTarefasByNomeProjeto(projetoLocal.getNome());
         
         if (!bdTarefas.isEmpty()){
            for (Tarefa t:bdTarefas){
               tableModel.addRow(new Object[]{t.getId(),t.getNome(),t.getSemanaInicio(),t.getDuracao()});
               tarForm.tarefasTable.setModel(tableModel);
               tarForm.deleteButton.setEnabled(true);
               tarForm.editarTarButton.setEnabled(true);
            }
         }
         else{
             
           tarForm.deleteButton.setEnabled(false);
           tarForm.editarTarButton.setEnabled(false);  
         }
    }
    
     //Metodo para desabilitar a area de edicao e criacao de nova tarefa
    private void clear(){
    	 bdTarefas = TarefaDAO.getTarefasByNomeProjeto(projetoLocal.getNome());
        Object o = (Object)0;
        tarForm.descricaoTextField.setText("");
        tarForm.inicioSpinner.setValue(o);
        tarForm.durSpinner.setValue(o);
        if (!bdTarefas.isEmpty()){
            tarForm.deleteButton.setEnabled(true);
            tarForm.editarTarButton.setEnabled(true);
        }
        tarForm.descricaoTextField.setEnabled(false);
        tarForm.inicioSpinner.setEnabled(false);
        tarForm.durSpinner.setEnabled(false);
        tarForm.addNewTarButton.setEnabled(false); 
        tarForm.cancelButton.setEnabled(false);
        tarForm.updateTarButton.setEnabled(false);
        tarForm.inserirMatButton.setEnabled(false);
        tarForm.inserirPesButton.setEnabled(false);
        tarForm.inserirPesButton.setEnabled(false);
        tarForm.addTarefaButton.setEnabled(true);
    }
}

