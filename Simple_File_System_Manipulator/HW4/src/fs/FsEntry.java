package fs;
public class FsEntry {
	private int id;
	private String name;
	private String[] content;
	private FsEntryType type;
	
	public FsEntry(int id, FsEntryType type, String name, String[] content) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getContent() {
		return content;
	}

	public void setContent(String[] content) {
		this.content = content;
	}

	public FsEntryType getType() {
		return type;
	}

	public void setType(FsEntryType type) {
		this.type = type;
	}
}
