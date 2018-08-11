package cresla.interfaces;

public interface Identifiable { //should be implemented by reactors and modules or an abstract class, which contains field id
    int getId();
}
//also the factory for reactors and modules should probably be the one giving out the unique ids.
// Maybe just have a private field id, which increments every time something is created.