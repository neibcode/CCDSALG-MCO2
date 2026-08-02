public class SimulationMetrics {
    private int totalSteps;
    private int pathLength;
    private long executionTimeMs;

    public SimulationMetrics() {
        this.totalSteps = 0;
        this.pathLength = 0;
        this.executionTimeMs = 0;
    }

    // Void methods: No return statements allowed[cite: 1]
    public void incrementSteps() {
        this.totalSteps++;
    }

    public void setPathLength(int length) {
        this.pathLength = length;
    }

    public void setExecutionTime(long timeMs) {
        this.executionTimeMs = timeMs;
    }

    // Non-void methods: Single return at the end[cite: 1]
    public int getTotalSteps() {
        return this.totalSteps;
    }

    public int getPathLength() {
        return this.pathLength;
    }

    public long getExecutionTimeMs() {
        return this.executionTimeMs;
    }
}