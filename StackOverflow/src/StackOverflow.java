import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StackOverflow {
    private final Map<Integer, User> users;
    private final Map<Integer, Question> questions;
    private final Map<Integer, Answer> answers;
    private final Map<Integer, Tag> tags;

    public StackOverflow(){
        this.users = new ConcurrentHashMap<>();
        this.questions = new ConcurrentHashMap<>();
        this.answers = new ConcurrentHashMap<>();
        this.tags = new ConcurrentHashMap<>();
    }

    public User createUser(String username, String emailId){
        User user = new User(username, emailId);
        users.put(user.getId(), user);
        return user;
    }

    public Question askQuestion(String title, String content, User author, List<String> tagNames){
        Question ques = author.addQuestion(title, content, tagNames);
        questions.put(ques.getId(), ques);
        List<Tag> questionTags = ques.getTags();

        for(Tag tag : questionTags){
            this.tags.putIfAbsent(tag.getId(), tag);
        }

        return ques;
    }

    public Answer answerQuestion(User author, Question ques, String content){
        Answer ans = author.writeAnswer(ques, content);
        this.answers.put(ans.getId(), ans);
        return ans;
    }

    public Comment addComment(User author, ICommentable entity, String content){
        Comment comment = author.writeComment(entity, content);
        return comment;
    }

    public void voteEntity(User voter, IVotable entity, int value){
        try {
            voter.addVote(entity, value);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid vote: " + e.getMessage());
        }
    }

    public List<Question> searchQuestions(String query){
        // List<Integer> questionIds = questions.keySet().stream().collect(Collectors.toList());
        return questions.values().stream()
                .filter(q -> q.getTitle().toLowerCase().contains(query.toLowerCase()) || 
                        q.getContent().toLowerCase().contains(query.toLowerCase()) ||
                        q.getTags().stream().anyMatch(t -> t.getName().equalsIgnoreCase(query)))
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionsByUser(User user) {
        return user.getQuestions();
    }

    public void acceptAnswer(User user, Answer ans){
        user.acceptAnswer(ans);
    }


    public User getUser(int id) { return users.get(id); }
    public Question getQuestion(int id) { return questions.get(id); }
    public Answer getAnswer(int id) { return answers.get(id); }
    public Tag getTag(int id) { return tags.get(id); }
}
