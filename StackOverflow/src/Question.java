import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question implements ICommentable, IVotable{
    private final int id;
    private final String title;
    private final Date creationTime;
    private final User author;
    private final String content;
    private final List<Comment> comments;
    private final List<Answer> answers;
    private final List<Vote> votes;
    private final List<Tag> tags;

    public Question(String title, User author, String content, List<String> tagNames){
        this.id = GetUniqueId.getUniqueId();
        this.title = title;
        this.author = author;
        this.content = content;
        this.creationTime = new Date();
        this.comments = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.tags = new ArrayList<>();
        for(String name : tagNames){
            this.tags.add(new Tag(name));
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public List<Tag> getTags() {
        return new ArrayList<>(tags);
    }

    public List<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }
    
    public void addAnswer(Answer answer){
        answers.add(answer);
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
}
