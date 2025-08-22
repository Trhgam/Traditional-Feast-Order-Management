
package Interf;



public interface Workable<T> {
    //Generic
    //add Customer Order SetMenu 
    public abstract boolean addNew(T x);
    //update Customer Order SetMenu 
    public abstract boolean update(T x);
    //search Customer Order SetMenu by Id
    public abstract T searchById(String id);
    
    public abstract void showAll();
    
    public abstract boolean readFromFile();
    
    public abstract boolean saveToFile();

    
}
