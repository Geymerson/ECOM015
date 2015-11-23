package gui.sim.multiple;

import automata.Automaton;
import grammar.Grammar;

public class TableModelFactory {

	public TableModel getTableModel(String tableModel, Automaton automaton, int add){

		if(tableModel == null){
			return null;
		}
		if(tableModel.equalsIgnoreCase("INPUTTABLEMODEL")){
			return new InputTableModel(automaton, add);

		} else if(tableModel.equalsIgnoreCase("MODEL2")){
			//TODO

		} else if(tableModel.equalsIgnoreCase("MODEL3")){
			//TODO
		}

		return null;
	}

	public TableModel getTableModel(String tableModel, InputTableModel model){

		if(tableModel == null){
			return null;
		}
		if(tableModel.equalsIgnoreCase("INPUTTABLEMODEL")){
			return new InputTableModel(model);

		} else if(tableModel.equalsIgnoreCase("MODEL2")){
			//TODO

		} else if(tableModel.equalsIgnoreCase("MODEL3")){
			//TODO
		}

		return null;
	}


	public TableModel getTableModel(String tableModel, Grammar gram, int add){

		if(tableModel == null){
			return null;
		}
		if(tableModel.equalsIgnoreCase("INPUTTABLEMODEL")){
			return new InputTableModel(gram, add);

		} else if(tableModel.equalsIgnoreCase("MODEL2")){
			//TODO

		} else if(tableModel.equalsIgnoreCase("MODEL3")){
			//TODO
		}

		return null;
	}

	public TableModel getTableModel(String tableModel, int columns){

		if(tableModel == null){
			return null;
		}
		if(tableModel.equalsIgnoreCase("INPUTTABLEMODEL")){
			return new InputTableModel(columns);

		} else if(tableModel.equalsIgnoreCase("MODEL2")){
			//TODO

		} else if(tableModel.equalsIgnoreCase("MODEL3")){
			//TODO
		}

		return null;
	}
}
