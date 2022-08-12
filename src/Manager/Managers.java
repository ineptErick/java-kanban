package Manager;

public class Managers {
    // Managers должен сам подбирать нужную реализацию TaskManager
    // и возвращать объект правильного типа

    public static Manager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
