package intervall_timer_backend.DataTransferModel;

public class ExerciseDT {
    private long id;
    private String name;

    private int repCount;
    private int lapTime;
    private int lapBreakTime;
    private int startCountdown;
    // private String ringTone;

    public ExerciseDT() {
    }

    public ExerciseDT(String name, int repCount, int lapTime, int lapBreakTime, int startCountdown) {
        this.name = name;
        this.repCount = repCount;
        this.lapTime = lapTime;
        this.lapBreakTime = lapBreakTime;
        this.startCountdown = startCountdown;
    }

    public ExerciseDT(long id, String name, int repCount, int lapTime, int lapBreakTime, int startCountdown) {
        this.id = id;
        this.name = name;
        this.repCount = repCount;
        this.lapTime = lapTime;
        this.lapBreakTime = lapBreakTime;
        this.startCountdown = startCountdown;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepCount() {
        return repCount;
    }

    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }

    public int getLapTime() {
        return lapTime;
    }

    public void setLapTime(int lapTime) {
        this.lapTime = lapTime;
    }

    public int getLapBreakTime() {
        return lapBreakTime;
    }

    public void setLapBreakTime(int lapBreakTime) {
        this.lapBreakTime = lapBreakTime;
    }

    public int getStartCountdown() {
        return startCountdown;
    }

    public void setStartCountdown(int startCountdown) {
        this.startCountdown = startCountdown;
    }
}

