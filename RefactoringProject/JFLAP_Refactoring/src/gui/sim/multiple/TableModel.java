package gui.sim.multiple;

import java.util.ArrayList;

import automata.Configuration;

public interface TableModel {
	
	public String getColumnName(int column);

	public String[][] getInputs();

	public boolean isCellEditable(int row, int column);

	public int getInputCount();

	public void setResult(int row, String result, Configuration config, ArrayList comparison, int index);
	
	public void clear();

	public Configuration getAssociatedConfigurationForRow(int row);
}
