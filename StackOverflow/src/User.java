import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private final String userName;
    private final String emailId;
    private int reputation;
    private final List<Question> questions;
    private final List<Answer> answers;
    private final List<Comment> comments;

    private static final int QUESTION_REPUTATION = 5;
    private static final int ANSWER_REPUTATION = 10;
    private static final int COMMENT_REPUTATION = 2;

    public User(String userName, String emailId){
        this.id = GetUniqueId.getUniqueId();
        this.userName = userName;
        this.emailId = emailId;
        this.reputation = 0;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getUserName() {
        return userName;
    }

    public int getReputation() {
        return reputation;
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public List<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public Answer writeAnswer(Question question, String content){
        Answer ans = new Answer(this, content, question);
        answers.add(ans);
        question.addAnswer(ans);
        updateReputation(ANSWER_REPUTATION);
        return ans;
    }

    public Comment writeComment(ICommentable entity, String content){
        Comment comment = new Comment(content, this);
        comments.add(comment);
        entity.addComment(comment);
        updateReputation(COMMENT_REPUTATION);
        return comment;
    }

    public Question addQuestion(String title, String content, List<String> tags){
        Question ques = new Question(title, this, content, tags);
        this.questions.add(ques);
        updateReputation(QUESTION_REPUTATION);
        return ques;
    }

    public void addVote(IVotable entity, int value){
        Vote vote = new Vote(this, value);
        try
        {
            entity.addVote(vote);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Exception thrown: " + e.getMessage());
            throw new IllegalArgumentException(e);
        }

        System.out.println("Vote added successfuly!");
    }

    public void acceptAnswer(Answer ans){
        try
        {
            ans.markAsAccepted(this);
        }
        catch (Exception e)
        {
            System.out.println("Exception thrown: " + e.getMessage());
        }
    }

    public synchronized void updateReputation(int reputation){
        this.reputation += reputation;
        if (this.reputation < 0) {
            this.reputation = 0;
        }
    }
}
