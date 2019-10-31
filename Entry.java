package fileSystem;

import javax.xml.crypto.Data;
import java.util.*;

public abstract class Entry {
    protected String name;
    protected Entry parent;
    protected Date createTime;
    protected Date updateTime;
    protected Date lastAccess;


    Entry(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.createTime = new Date();
        this.updateTime = new Date();
        this.lastAccess = new Date();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entry getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public Date getCreateTime() {
        return createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }


    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public abstract int getSize();

    public String getPath() {
        if (parent == null) {
            return "/" + this.name;
        }
        return parent.getPath() + "/" +  this.name;
    }

}
