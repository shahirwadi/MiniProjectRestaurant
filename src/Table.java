public class Table {
    private int tableId;
    private int capacity;
    private boolean available;

    public Table(int tableId, int capacity, boolean available){
        this.tableId = tableId;
        this.capacity = capacity;
        this.available = available;
    }

    public void reserveTable(){
        this.available = false;
    }

    public void releaseTable(){
        this.available = true;
    }

    

    public int getTableId() { return tableId; }
    public int getCapacity() { return capacity; }
    public boolean getAvailable() { return available; }
}
