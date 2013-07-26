package db;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.DataSetEntry;

/**
 * 
 * This is a dummy implementation for testing.
 *
 */
public class DataSetEntriesDAOdummy implements DataSetEntriesDAO {
	
	private List<DataSetEntry> entries;
	
	public DataSetEntriesDAOdummy() {
		entries = Collections.synchronizedList(new ArrayList<DataSetEntry>());
	}
	

	@Override
	public List<DataSetEntry> getDataSetEntries(String fileName)
			throws ParseException, IOException {
		entries.clear();
		int n = 200;
		for(int i = 0; i<n; i++) {
			DataSetEntry entry = new DataSetEntry((double) i, Math.sin(i*2*Math.PI/n));
			entries.add(entry);
		}
		return entries;
	}

}
