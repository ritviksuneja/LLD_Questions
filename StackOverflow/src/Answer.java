import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer implements ICommentable, IVotable{
    private final int id;
    private final Date creationTime;
    private final User author;
    private final String content;
    private final List<Comment> comments;
    private final Question question;
    private final List<Vote> votes;
    private boolean isAccepted;

    public Answer(User author, String content, Question question){
        this.id = GetUniqueId.getUniqueId();
        this.author = author;
        this.content = content;
        this.creationTime = new Date();
        this.question = question;
        this.votes = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.isAccepted = false;
    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public String getContent() {
        return content;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public List<Vote> getVotes() {
        return new ArrayList<>(votes);
    }

    public Question getQuestion() {
        return question;
    }

    public boolean isAccepted(){
        return isAccepted;
    }

    public void acceptAnswer(){
        isAccepted = true;
    }

    @Override
    public void addComment(Comment comment){
        comments.add(comment);
    }

    @Override
    public void addVote(Vote vote){
        if(vote.getValue() != 1 && vote.getValue() != -1){
            throw new IllegalArgumentException("Vote value must be either 1 or -1");
        }

        if(this.author == vote.getUser()){
            throw new IllegalArgumentException("User can't vote its own question.");
        }

        votes.removeIf(v -> v.getUser().equals(vote.getUser()));
        votes.add(vote);
        author.updateReputation(vote.getValue() * 5);
    }

    public void markAsAccepted(User user){
        if(this.question.getAuthor() != user){
            throw new IllegalArgumentException("Unauthorized user is trying to accept the answer.");
        }
        if(this.question.getAuthor() == user){
            if(this.isAccepted){
                throw new IllegalStateException("This answer is already accepted.");
            }

            isAccepted = true;
            author.updateReputation(15);
        }
    }
}
