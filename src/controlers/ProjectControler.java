/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import dao.ProjetoDAO;
import entities.Projeto;
import views.ProjectForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author daniel
 */
public class ProjectControler {
    
    static final ProjectForm form = new ProjectForm();
    static List<Projeto> bdProjetos; // lista de projetos contidos no banco de dados
    static Projeto local;
    
    public static void main(String args []){
        
        form.setVisible(true);
        form.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
     
        form.projectNameTxtField.setEnabled(false);
        form.duracaoSpinner.setEnabled(false);
        form.addNewButton.setEnabled(false);
        form.cancelarButton.setEnabled(false);
        //form.updateProjButton.setEnabled(false);
        form.inserirTarButton.setEnabled(false);
        form.setTitle("Controle de Materiais");
        
        listProjects();
        
        // Ações dos botões
        //botao criar Novo Projeto
        form.criarProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.projectNameTxtField.setText(" ");
                form.duracaoSpinner.setValue((Object)0);
                form.criarProjectButton.setEnabled(false);
                form.consultarButton.setEnabled(false);
                form.deleteButton.setEnabled(false);
                form.modificarButton.setEnabled(false);
                form.inserirTarButton.setEnabled(false);
                form.projectNameTxtField.setEnabled(true);
                form.duracaoSpinner.setEnabled(true);
                form.addNewButton.setEnabled(true);
                form.cancelarButton.setEnabled(true);
            }
        });
        
        //botao remover projeto
        form.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = form.projetosTable.getSelectedRow();
                if (index >= 0) {
                    String nome = form.projetosTable.getModel().getValueAt(index, 0).toString();

                    for (Projeto p : bdProjetos) {
                        if (nome.equals(p.getNome())) {
                            ProjetoDAO.deletaProjeto(nome);
                            DefaultTableModel tableModel = (DefaultTableModel) form.projetosTable.getModel();
                            tableModel.setNumRows(0);
                            listProjects();
                            JOptionPane.showMessageDialog(null, "Projeto excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum Projeto foi selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //botao Editar
        form.modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = form.projetosTable.getSelectedRow();
                if (index >= 0) {
                    form.criarProjectButton.setEnabled(false);
                    form.consultarButton.setEnabled(false);
                    form.deleteButton.setEnabled(false);
                    form.modificarButton.setEnabled(false);
                    form.projetosTable.setEnabled(false);

                    form.projectNameTxtField.setEnabled(true);
                    form.duracaoSpinner.setEnabled(true);
                    form.cancelarButton.setEnabled(true);
                    form.inserirTarButton.setEnabled(true);
                    //form.updateProjButton.setEnabled(true);

                    String nome = form.projetosTable.getModel().getValueAt(index, 0).toString();
                    form.projectNameTxtField.setText(nome);
                    form.duracaoSpinner.setValue((Object)Integer.parseInt(form.projetosTable.getModel().getValueAt(index, 1).toString()));

                    //listProjects();
                }
                else {
                JOptionPane.showMessageDialog(null, "Nenhum Projeto foi selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
                }   
            }
        });
        
        //botao Consultar
        form.consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                int index = form.projetosTable.getSelectedRow();
                if (index >= 0) {
                String nome = form.projetosTable.getModel().getValueAt(index, 0).toString();

                for (Projeto p : bdProjetos) {
                    if (nome.equals(p.getNome())) {
                                                        
                        ConsultaControler consultControl = new ConsultaControler(ProjetoDAO.getProjetoByNome(nome));
                        consultControl.start();
//                        ConsultaForm consulta = new ConsultaForm(ProjetoDAO.getProjetoByNome(nome),semaCons);
//                        consulta.setVisible(true);
                    }
                }
                } else {
                	JOptionPane.showMessageDialog(null, "Nenhum Projeto foi selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //Botao Adicionar novo Projeto
        form.addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dur = (Integer) form.duracaoSpinner.getValue();
                // se o campo de nome estiver vazio, mostra erro e nao faz nada
                if (form.projectNameTxtField.getText().isEmpty() || form.projectNameTxtField.getText().equals(" ")) {
                    JOptionPane.showMessageDialog(null, "Por favor insira um nome para o projeto", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                // se o campo de duracao for menor que 0, mostra erro e nao faz nada
                if (dur <= 0) {
                    JOptionPane.showMessageDialog(null, "Projeto deve ter no minimo 1 semana", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                //se o nome nao esta vazio e a duracao for maior que 0, adiciona novo projeto ao banco
                // e vai para o proximo passo, criar as Tarefas.
                if (!form.projectNameTxtField.getText().isEmpty() && dur > 0) {
                    local = new Projeto();
                    local.setNome(form.projectNameTxtField.getText().trim());
                    local.setDuracao(dur);
                    //localProjetos.add(p);
                    ProjetoDAO.criarProjeto(local);
                    //dispose();

                    listProjects();

                    form.consultarButton.setEnabled(false);
                    form.deleteButton.setEnabled(false);
                    form.modificarButton.setEnabled(false);
                    form.addNewButton.setEnabled(false);
                    form.inserirTarButton.setEnabled(true);
                    form.criarProjectButton.setEnabled(true);
                    // setTempProj;
                }
            }
        });
        
        //Botao Inserir Tarefa
        form.inserirTarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = form.projectNameTxtField.getText().trim();
                int dur = (Integer) form.duracaoSpinner.getValue();
                if (nome.equals(" ") || nome.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nenhum projeto selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else if (dur <= 0){
                    JOptionPane.showMessageDialog(null, "Projeto deve ter no minimo 1 semana", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                else if(!nome.equals(" ") && !nome.isEmpty() && dur > 0){
                	local = new Projeto();
                	local.setNome(nome);
                	local.setDuracao(dur);
                    TarefaControler tarControler = new TarefaControler(local);
                    tarControler.start();
//                    TarefaForm tarForm = new TarefaForm(ProjetoDAO.getProjetoByNome(nome));
//                    tarForm.setVisible(true);
                }
            }
        });
        
        //Botao cancelar
        form.cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        
        form.sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.dispose();
                System.exit(1);
            }
        });
        
    }
    
    //Metodo para listar os Projetos cadastrados no banco e colocar na tabela do form.
    private static void listProjects() {
        DefaultTableModel tableModel = new DefaultTableModel(0, 2);
        tableModel.setColumnIdentifiers(new Object[]{"Nome", "Duração"});
        

        bdProjetos = ProjetoDAO.getAllProjetos();
        if (!bdProjetos.isEmpty()) {
            for (Projeto p : bdProjetos) {
                tableModel.addRow(new Object[]{p.getNome(), p.getDuracao()});
                
            }
            form.projetosTable.setModel(tableModel);
            form.modificarButton.setEnabled(true);
            form.consultarButton.setEnabled(true);
            form.deleteButton.setEnabled(true);
        } else {

            form.consultarButton.setEnabled(false);
            form.deleteButton.setEnabled(false);
            form.modificarButton.setEnabled(false);
        }
    }
    
     //Metodo para desativar a area de Criacao de Projeto caso botao cancele é pressionado.
    private static void clear() {
        Object o = (Object) 0;

        form.projectNameTxtField.setText(" ");
        form.duracaoSpinner.setValue(o);
        form.projetosTable.setEnabled(true);
        form.criarProjectButton.setEnabled(true);
        if (!bdProjetos.isEmpty()){
            form.consultarButton.setEnabled(true);
            form.deleteButton.setEnabled(true);
            form.modificarButton.setEnabled(true);
        }
        
        
        form.projectNameTxtField.setEnabled(false);
        form.duracaoSpinner.setEnabled(false);
        form.addNewButton.setEnabled(false);
        form.inserirTarButton.setEnabled(false);
        form.cancelarButton.setEnabled(false);
        //form.updateProjButton.setEnabled(false);
    }
}
