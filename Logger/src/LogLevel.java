public enum LogLevel {
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4);

    private final int severity;

    private LogLevel(int severity){
        this.severity = severity;
    }

    public int getSeverity(){
        return this.severity;
    }

    public boolean shouldLog(LogLevel configuredSeverity){
        return this.getSeverity() >= configuredSeverity.getSeverity();
    }
}
