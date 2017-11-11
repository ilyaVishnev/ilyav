package ru.job4j.incapsulation;

public class Item {
    private String Name;
    private String desc;
    private long create;
    private String[] comments;
    private String id;
    public void setId(String id){
       this.id=id;
    }
    public Item(){}
    public Item(String name, String desc, long create){
this.Name=name;
this.desc=desc;
this.create=create;
    }
    public String getName(){
        return this.Name;
    }
    public String getDesc(){
        return this.desc;
    }
    public long getCreate(){
        return this.create;
    }
    public String getId(){
        return id;
    }

    public String[] getComments() {
        return comments;
    }

    public void setName(String name){
        this.Name=name;
    }
    public void setDesc(String desc){
        this.desc=desc;
    }
    public void setCreate(Long create){
        this.create=create;
    }
    public void setComments(String[]com){
        this.comments=com;
    }

}
