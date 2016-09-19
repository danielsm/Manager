/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import dao.*;
import entities.*;
import views.ConsultaForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author daniel
 */
public class ConsultaControler {
    	
    ConsultaForm consulta;
    Projeto projeto;
    List<Tarefa> tarefas;
    List<Material> materiais;
    List<Pessoa> pessoas;
    
    int semana;
    
    public ConsultaControler(Projeto p){
        consulta = new ConsultaForm();
        this.projeto = p;
        
        consulta.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        consulta.setTitle("Consulta do Projeto: "+ this.projeto.getNome());
        consulta.nomeProjeto.setText(this.projeto.getNome());
        consulta.durProjeto.setText((this.projeto.getDuracao()) + " Semana(s)");
        
        tarefas = TarefaDAO.getTarefasByNomeProjeto(this.projeto.getNome());
               
    }
    
    public void start(){
    	consulta.setVisible(true);
    	
    	for (int i=1; i <= this.projeto.getDuracao(); i++){
    		consulta.semanaConsulta.addItem(Integer.toString(i));
    	}
    	
    	for (Tarefa t: tarefas){
			consulta.tarefasComboBox.addItem(t.getNome());
		}
		
    	
    	consulta.semanaConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				semana = Integer.parseInt(consulta.semanaConsulta.getSelectedItem().toString());
				consulta.tarefasComboBox.setEnabled(true);
				consulta.semanaConsulta.setEnabled(false);
		
				
			}
		});
    	
    	
    	consulta.tarefasComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				consulta.showMateriais.setEnabled(true);
				consulta.showPessoas.setEnabled(true);
				consulta.mudarSemana.setEnabled(true);
				
				String tarName = consulta.tarefasComboBox.getSelectedItem().toString();
				Tarefa chosen = new Tarefa();
				for (Tarefa t: tarefas){
					if(tarName.equals(t.getNome())){
						chosen = t;
					}
				}
				
				consulta.inicioTar.setText(Integer.toString(chosen.getSemanaInicio()));
				consulta.duracaoTar.setText(Integer.toString(chosen.getDuracao()));
				
				materiais = MaterialDAO.getMateriaisByIdTarefa(chosen.getId());
				
				float custo_total = 0;
				for (Material m: materiais){
					custo_total+= m.getCusto();
				}
				consulta.custoTar.setText(Float.toString(custo_total));
				
				if(semana < chosen.getSemanaInicio())
					consulta.statusTar.setText("Tarefa Futura");
				else{
					if(semana >= chosen.getSemanaInicio() && semana < chosen.getSemanaInicio()+chosen.getDuracao()){
						consulta.statusTar.setText("Em andamento");
					}
					else if(semana >= chosen.getDuracao()+chosen.getSemanaInicio()){
						consulta.statusTar.setText("Tarefa Finalizada");
					}
						
				}
				
			}
		});
    	
    	
    	consulta.mudarSemana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	consulta.tarefasComboBox.setEnabled(false);
            	consulta.showMateriais.setEnabled(false);
				consulta.showPessoas.setEnabled(false);
				consulta.mudarSemana.setEnabled(false);
				consulta.inicioTar.setText("");
				consulta.duracaoTar.setText("");
				consulta.custoTar.setText("");
				consulta.statusTar.setText("");
				
				consulta.semanaConsulta.setEnabled(true);
            }
      	});
    	
    	consulta.voltarButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              consulta.dispose();
          }
    	});
    }
//    
//    public void start(){
//        
//        listar();
//        consulta.setVisible(true);
//        consulta.voltarButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                consulta.dispose();
//            }
//        });
//    }
//     //Lista as Tarefas e Materiais do Projeto selecionado
//    private void listar(){
//       
//        tarefas = TarefaDAO.getTarefasByNomeProjeto(projeto.getNome());
//        String situacaoMat,situacaTar;
//        DefaultTableModel tableModel = new DefaultTableModel(0, 4);
//        tableModel.setColumnIdentifiers(new Object[]{"Material","Situação (Material)", "Tarefa", "Situação (Tarefa)"});
//        
//        for (Tarefa t:tarefas){
//            materiais = MaterialDAO.getMateriaisByIdTarefa(t.getId());
//            for (Material m:materiais){
//                
//                if(semanaConsulta < t.getSemanaInicio()){
//                    situacaTar = "Tarefa Futura";
//                    situacaoMat = "Disponivel";
//                }
//                else if (semanaConsulta >= t.getSemanaInicio() && semanaConsulta <= t.getSemanaInicio()+(t.getDuracao()-1)){
//                    situacaTar = "Em andamento";
//                    situacaoMat = "Em uso";
//                }
//                else {
//                    situacaTar = "Tarefa Finalizada";
//                    situacaoMat = "Usado";
//                }
//                
//                tableModel.addRow(new Object[]{m.getNome(),situacaoMat,t.getNome(), situacaTar});
//                consulta.tarefasTable.setModel(tableModel);
//            }
//
//        }
//
//        consulta.voltarButton.setEnabled(true);
//        
//    }
}
