public class Program {
    private String name;
    private String time;
    private String duration;
    private String category;
    private String assignedRJ;

    public Program(String name, String time, String duration, String category, String assignedRJ) {
        this.name = name;
        this.time = time;
        this.duration = duration;
        this.category = category;
        this.assignedRJ = assignedRJ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAssignedRJ() {
        return assignedRJ;
    }

    public void setAssignedRJ(String assignedRJ) {
        this.assignedRJ = assignedRJ;
    }
}





