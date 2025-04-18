import java.util.Date;

public class Comment {
    private final int id;
    private final String content;
    private final User author;
    private final Date creationDate;

    public Comment(String content, User author){
        this.id = GetUniqueId.getUniqueId();
        this.content = content;
        this.author = author;
        this.creationDate = new Date();
    }

    public int getId() { return id; }
    public User getAuthor() { return author; }
    public String getContent() { return content; }
    public Date getCreationDate() { return creationDate; }
}
