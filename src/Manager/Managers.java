package Manager;

public class Managers {

    private static TaskManager aDefault;

    public static HistoryManager getDefaultHistory() {

        return new InMemoryHistoryManager();
    }

    public static TaskManager getDefaultTask() {

        return new InMemoryTaskManager();
    }

    public static TaskManager getaDefault() {
        return aDefault;
    }

    public static void setaDefault(TaskManager aDefault) {
        Managers.aDefault = aDefault;
    }

    public static TaskManager getDefault() {
        return getaDefault();
    }
}
