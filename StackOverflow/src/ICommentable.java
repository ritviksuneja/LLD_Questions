import java.util.List;

public interface ICommentable {
    public List<Comment> getComments();
    public void addComment(Comment comment);
}
