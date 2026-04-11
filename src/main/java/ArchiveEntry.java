public class ArchiveEntry {
    private String date;
    private String fileName;

    public ArchiveEntry(String date, String fileName) {
        this.date = date;
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}