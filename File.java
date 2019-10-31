package fileSystem;

public class File extends Entry {
    int size;
    String context;

    File(String name, Directory parent) {
        super(name, parent);
        this.size = 0;
        this.context = "";
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public String getContext() {
        return this.context;
    }

    public String updateContext(String contexts) {
        size = contexts.length();
        this.context = contexts;
        return context;
    }


}
