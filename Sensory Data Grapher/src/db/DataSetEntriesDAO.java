package db;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import model.DataSetEntry;

public interface DataSetEntriesDAO {
	public List<DataSetEntry> getDataSetEntries(String fileName) throws ParseException, IOException;
}
