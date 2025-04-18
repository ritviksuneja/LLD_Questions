import java.util.List;

public interface IVotable {
    public List<Vote> getVotes();
    public void addVote(Vote vote);
}
