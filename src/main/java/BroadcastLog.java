public class BroadcastLog {
    private String time;
    private String program;
    private String status;

    public BroadcastLog(String time, String program, String status) {
        this.time = time;
        this.program = program;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}