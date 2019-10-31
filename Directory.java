package fileSystem;
import java.util.*;
public class Directory extends Entry {
    private int size;
    private List<Entry> contexts;
    private Set<String> names;


    Directory(String name, Directory parent) {
        super(name, parent);
        this.size = 0;
        this.names = new HashSet<>();
        contexts = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public void addEntry(Entry file) {
        size += file.getSize();
        contexts.add(file);
        if (names.contains(file.getName())) {
            throw new IllegalArgumentException("this file is already exist!");
        }
        names.add(file.getName());

    }

    public void deleteEntry(Entry file) {
        if (!contexts.contains(file)) throw new IllegalArgumentException("This file is not exist in current directory");
        size -= file.getSize();
        contexts.remove(file);
    }

    public List<Entry> getContexts() {
        return contexts;
    }

}
