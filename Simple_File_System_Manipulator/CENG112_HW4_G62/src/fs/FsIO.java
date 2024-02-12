package fs;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FsIO {
	public static Iterable<FsEntry> getAllEntries(String fsDir) throws FileNotFoundException {
		File dir = new File(fsDir); 
		List<FsEntry> entries = new ArrayList<>();
		
		for(File file: dir.listFiles()) {
			Scanner sc = new Scanner(file);
			String line = sc.nextLine();
			String fields[] = line.split(",");
			List<String> content = new ArrayList<>();
			while(sc.hasNextLine()) {
				content.add(sc.nextLine());
			}
			entries.add(new FsEntry(Integer.parseInt(file.getName()), 
									FsEntryType.valueOf(fields[0]),
									fields.length == 1 ? "" : fields[1],
									content.toArray(new String[content.size()])));
			sc.close();
		}
		return entries;
	}
}
