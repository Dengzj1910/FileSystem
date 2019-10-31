package fileSystem;

import java.util.*;

public class FileSystem {

    private List<Entry> entries;

    FileSystem() {
        entries = new ArrayList<>();
        Entry root = new Directory("root", null);
        entries.add(root);
    }

    public Entry search(String entryName) {
        for (Entry item : entries) {
            if (item.getName().equals(entryName)) {
                return item;
            }
        }
        return null;
    }

    public String create(String entryName, String parentName, boolean isDirectory) {
        Entry p =  search(parentName);
        if (!(p instanceof Directory) || p == null) {
            throw new IllegalArgumentException("Parent directory does not exist or illegal!");
        }
        Directory parent = (Directory) p;
        Entry newEntry = new File(entryName, parent);
        if (isDirectory) {
             newEntry = new Directory(entryName, parent);
        }

        parent.addEntry(newEntry);
        entries.add(newEntry);

        return newEntry.getPath();
    }

    public String delete(String entryName, String parentName) {
        Entry p =  search(parentName);
        if (!(p instanceof Directory) || p == null) {
            throw new IllegalArgumentException("Parent directory does not exist or illegal!");
        }
        Directory parent = (Directory) p;
        Entry entry = search(entryName);
        parent.deleteEntry(entry);
        entries.remove(entry);

        return entry.getPath();
    }

    public void updateContext(String entryName, String context) {
        Entry entry = search(entryName);
        if (!(entry instanceof File)) {
            throw new IllegalArgumentException("Only file could update context");
        }
        ((File) entry).updateContext(context);

    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        fs.create("file1", "root", false);
        fs.create("Directory1", "root", true);
        fs.create("D1file2", "Directory1", false);
        fs.updateContext("D1file2", "this is file2 in directory1");
        System.out.println(fs.search("D1file2").getPath());
        System.out.println(fs.search("file1").getCreateTime());
        try{
            Thread.currentThread().sleep(5000);
        } catch(InterruptedException ie){
            ie.printStackTrace();
        }
        System.out.println(fs.search("file1").getCreateTime());
        fs.delete("file1", "root");
        try{
            System.out.println(fs.search("file1").getPath());
        } catch (NullPointerException e) {
            System.out.println("name: " + e.getMessage());
        }



    }
}
