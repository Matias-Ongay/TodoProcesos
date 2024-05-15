package Controller;

public interface UpdatingDatabase {
    void updateInDatabase(String id, String description, String endDate, String priority, String userId, String status);
}