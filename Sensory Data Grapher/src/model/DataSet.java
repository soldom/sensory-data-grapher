package model;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
	
	private List<DataSetEntry> entries;
	private DataSetConfig config;
	
	public DataSet() {
		entries = new ArrayList<DataSetEntry>();
		config = new DataSetConfig();
	}
	
	public DataSet(DataSetConfig config, List<DataSetEntry> entries) {
		this.config = config;
		this.entries = entries;
	}
	
	public List<DataSetEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<DataSetEntry> entries) {
		this.entries = entries;
	}

	public DataSetConfig getConfig() {
		return config;
	}

	public void setConfig(DataSetConfig config) {
		this.config = config;
	}

	/**
	 * 
	 * Appends the specified entry to the this list entries in the data set. 
	 * 
	 * @param e entry to add to this data set
	 */

	public void addEntry(DataSetEntry e) {
		entries.add(e);
	}
	
	public void clear() {
		entries.clear();
	}
	
}
