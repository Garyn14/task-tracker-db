package roadmap.tast_tracker.cli.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {
    TO_DO("to-do"),
    IN_PROGRESS("in-progress"),
    DONE("done");

    private final String status;

    TaskStatus(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
