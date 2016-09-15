package controlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import dao.ProjetoDAO;
import entities.Projeto;
import views.MainForm;

public class MainControler {
	
	private static final MainForm frame = new MainForm();
	private static List<Projeto> bdProjs;
	
	public static void main(String[] args) {
		
		frame.setVisible(true);
		listProjects();
//		Projeto p = new Projeto();
//		p.setNome("DOIS");
//		p.setDuracao(20);
//		ProjetoDAO pdao = new ProjetoDAO();
//		pdao.criarProjeto(p);
		
		//pdao.deletaProjeto("DOIS");
		
		
		// Ações dos botões
        //botao criar Novo Projeto
        frame.novoProjBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	frame.projetosTable.setEnabled(false);
                frame.novoProjBt.setEnabled(false);
                frame.editarProjBt.setEnabled(false);
                frame.deleteProjBt.setEnabled(false);
                frame.consultarProjBt.setEnabled(false);
                
                frame.nomeLabel.setEnabled(true);
                frame.nomeProjTxt.setEnabled(true);
                frame.duracaoLabel.setEnabled(true);
                frame.durSpinner.setEnabled(true);
                
                frame.addProjBt.setEnabled(true);
                frame.cancelarBt.setEnabled(true);
            }
        });
        
        frame.editarProjBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        frame.deleteProjBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        frame.consultarProjBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        frame.addProjBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				frame.projetosTable.setEnabled(true);
		        frame.novoProjBt.setEnabled(true);
		        frame.editarProjBt.setEnabled(true);
		        frame.deleteProjBt.setEnabled(true);
		        frame.consultarProjBt.setEnabled(true);
		        
		        frame.nomeLabel.setEnabled(false);
		        frame.nomeProjTxt.setEnabled(false);
		        frame.duracaoLabel.setEnabled(false);
		        frame.durSpinner.setEnabled(false);
		        
		        frame.addProjBt.setEnabled(false);
		        frame.cancelarBt.setEnabled(false);
				
			}
		});
        
        frame.cancelarBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.projetosTable.setEnabled(true);
		        frame.novoProjBt.setEnabled(true);
		        frame.editarProjBt.setEnabled(true);
		        frame.deleteProjBt.setEnabled(true);
		        frame.consultarProjBt.setEnabled(true);
		        
		        frame.nomeLabel.setEnabled(false);
		        frame.nomeProjTxt.setEnabled(false);
		        frame.duracaoLabel.setEnabled(false);
		        frame.durSpinner.setEnabled(false);
		        
		        frame.addProjBt.setEnabled(false);
		        frame.cancelarBt.setEnabled(false);
				
			}
		});
        
	}
	
	 //Metodo para listar os Projetos cadastrados no banco e colocar na tabela do form.
    private static void listProjects() {
        DefaultTableModel tableModel = new DefaultTableModel(0, 2);
        tableModel.setColumnIdentifiers(new Object[]{"Nome", "Duração"});
        

        bdProjs = ProjetoDAO.getAllProjetos();
        if (!bdProjs.isEmpty()) {
            for (Projeto p : bdProjs) {
                tableModel.addRow(new Object[]{p.getNome(), p.getDuracao()});
            }
            frame.projetosTable.setModel(tableModel);
            frame.editarProjBt.setEnabled(true);
            frame.deleteProjBt.setEnabled(true);
            frame.consultarProjBt.setEnabled(true);
        } else {

            frame.consultarProjBt.setEnabled(false);
            frame.deleteProjBt.setEnabled(false);
            frame.editarProjBt.setEnabled(false);
        }
    }
    
	
	

}
