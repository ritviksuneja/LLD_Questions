public class Tag {
    private final int id;
    private final String name;

    public Tag(String name){
        this.id = GetUniqueId.getUniqueId();
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
