import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SchedulerRealization implements Scheduler {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void execute(Runnable task) {
        executor.execute(task);
    }
}

