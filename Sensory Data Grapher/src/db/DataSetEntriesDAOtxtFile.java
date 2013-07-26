package db;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.DataSetEntry;

public class DataSetEntriesDAOtxtFile implements DataSetEntriesDAO {
	@Override
	@SuppressWarnings("resource")
	public List<DataSetEntry> getDataSetEntries(String fileName) throws ParseException, IOException {

		List<DataSetEntry> entries = Collections.synchronizedList(new ArrayList<DataSetEntry>());
		LineNumberReader lr = new LineNumberReader(new FileReader(fileName));

		while (lr.ready()) {
			String str = lr.readLine();
			if (lr.getLineNumber() > 1){
				String[] strA = str.split(",");
				Double x = null;
				Double y = null;
				try { 
					x = Double.parseDouble(strA[0]);
					y = Double.parseDouble(strA[1]);
				} catch(NumberFormatException e) {
					throw new ParseException("Could not parse data at line " + lr.getLineNumber(), lr.getLineNumber());
				}
				DataSetEntry entry = new DataSetEntry(x, y);
				entries.add(entry);
			}
		}
		lr.close();
		return entries;
	}
	
}
