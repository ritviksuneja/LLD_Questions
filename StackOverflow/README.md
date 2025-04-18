## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Database Schema

### Users Table
| Column Name   | Data Type | Constraints                     |
|---------------|-----------|---------------------------------|
| `id`          | INT       | PRIMARY KEY, AUTO_INCREMENT     |
| `userName`    | VARCHAR   | NOT NULL                       |
| `emailId`     | VARCHAR   | NOT NULL, UNIQUE               |
| `reputation`  | INT       | DEFAULT 0                      |

---

### Questions Table
| Column Name    | Data Type | Constraints                     |
|----------------|-----------|---------------------------------|
| `id`           | INT       | PRIMARY KEY, AUTO_INCREMENT     |
| `authorId`     | INT       | FOREIGN KEY REFERENCES `Users(id)` |
| `title`        | VARCHAR   | NOT NULL                       |
| `content`      | TEXT      | NOT NULL                       |
| `creationTime` | DATETIME  | DEFAULT CURRENT_TIMESTAMP       |
| `tags`         | VARCHAR   | (Comma-separated tags)          |

---

### Answers Table
| Column Name    | Data Type | Constraints                     |
|----------------|-----------|---------------------------------|
| `id`           | INT       | PRIMARY KEY, AUTO_INCREMENT     |
| `questionId`   | INT       | FOREIGN KEY REFERENCES `Questions(id)` |
| `authorId`     | INT       | FOREIGN KEY REFERENCES `Users(id)` |
| `content`      | TEXT      | NOT NULL                       |
| `creationTime` | DATETIME  | DEFAULT CURRENT_TIMESTAMP       |
| `isAccepted`   | BOOLEAN   | DEFAULT FALSE                  |

---

### Comments Table
| Column Name    | Data Type | Constraints                     |
|----------------|-----------|---------------------------------|
| `id`           | INT       | PRIMARY KEY, AUTO_INCREMENT     |
| `authorId`     | INT       | FOREIGN KEY REFERENCES `Users(id)` |
| `entityType`   | ENUM      | ('Question', 'Answer')          |
| `entityId`     | INT       | (ID of the Question or Answer being commented on) |
| `content`      | TEXT      | NOT NULL                       |
| `creationTime` | DATETIME  | DEFAULT CURRENT_TIMESTAMP       |

---

### Votes Table
| Column Name    | Data Type | Constraints                     |
|----------------|-----------|---------------------------------|
| `id`           | INT       | PRIMARY KEY, AUTO_INCREMENT     |
| `userId`       | INT       | FOREIGN KEY REFERENCES `Users(id)` |
| `entityType`   | ENUM      | ('Question', 'Answer')          |
| `entityId`     | INT       | (ID of the Question or Answer being voted on) |
| `value`        | INT       | CHECK (`value` IN (-1, 1))      |

---

### Relationships
- **Users → Questions**: One-to-Many (A user can post many questions).
- **Users → Answers**: One-to-Many (A user can post many answers).
- **Users → Comments**: One-to-Many (A user can post many comments).
- **Users → Votes**: One-to-Many (A user can vote on many entities).
- **Questions → Answers**: One-to-Many (A question can have many answers).
- **Questions/Answers → Comments**: One-to-Many (A question or answer can have many comments).
- **Questions/Answers → Votes**: One-to-Many (A question or answer can have many votes).

## Refactored Code with Interfaces

To improve the design of the `StackOverflow` application, we have refactored the code to introduce interfaces for better adherence to the **Dependency Inversion Principle (DIP)**. This makes the code more flexible, testable, and easier to mock during unit testing.

### Key Changes
1. **Interfaces Introduced**:
   - `IUserManager`: Manages user-related operations.
   - `IQuestionManager`: Manages question-related operations.
   - `IAnswerManager`: Manages answer-related operations.
   - `ICommentManager`: Manages comment-related operations.
   - `IVoteManager`: Manages voting-related operations.
   - `ISearchManager`: Manages search-related operations.

2. **Concrete Implementations**:
   - `UserManager` implements `IUserManager`.
   - `QuestionManager` implements `IQuestionManager`.
   - `AnswerManager` implements `IAnswerManager`.
   - `CommentManager` implements `ICommentManager`.
   - `VoteManager` implements `IVoteManager`.
   - `SearchManager` implements `ISearchManager`.

3. **Dependency Injection**:
   - The `StackOverflow` class now depends on the interfaces (`IUserManager`, `IQuestionManager`, etc.) instead of concrete implementations. This allows for easier testing and flexibility in swapping implementations.

### Example Code

#### **Interface Example**
```java
public interface IUserManager {
    User createUser(String username, String emailId);
    User getUserById(int userId);
}

public interface IQuestionManager {
    Question askQuestion(String title, String content, User author, List<String> tagNames);
    Question getQuestionById(int questionId);
}

public class UserManager implements IUserManager {
    private final Map<Integer, User> users;

    public UserManager() {
        this.users = new ConcurrentHashMap<>();
    }

    @Override
    public User createUser(String username, String emailId) {
        User user = new User(username, emailId);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User getUserById(int userId) {
        return users.get(userId);
    }
}

public class QuestionManager implements IQuestionManager {
    private final Map<Integer, Question> questions;
    private final TagManager tagManager;

    public QuestionManager(TagManager tagManager) {
        this.questions = new ConcurrentHashMap<>();
        this.tagManager = tagManager;
    }

    @Override
    public Question askQuestion(String title, String content, User author, List<String> tagNames) {
        Question question = author.addQuestion(title, content, tagNames);
        questions.put(question.getId(), question);

        // Add tags to the TagManager
        List<Tag> questionTags = question.getTags();
        for (Tag tag : questionTags) {
            tagManager.addTag(tag);
        }

        return question;
    }

    @Override
    public Question getQuestionById(int questionId) {
        return questions.get(questionId);
    }
}

public class StackOverflow {
    private final IUserManager userManager;
    private final IQuestionManager questionManager;
    private final IAnswerManager answerManager;
    private final ICommentManager commentManager;
    private final IVoteManager voteManager;
    private final ISearchManager searchManager;

    public StackOverflow(IUserManager userManager, IQuestionManager questionManager, 
                         IAnswerManager answerManager, ICommentManager commentManager, 
                         IVoteManager voteManager, ISearchManager searchManager) {
        this.userManager = userManager;
        this.questionManager = questionManager;
        this.answerManager = answerManager;
        this.commentManager = commentManager;
        this.voteManager = voteManager;
        this.searchManager = searchManager;
    }

    public User createUser(String username, String emailId) {
        return userManager.createUser(username, emailId);
    }

    public Question askQuestion(String title, String content, User author, List<String> tagNames) {
        return questionManager.askQuestion(title, content, author, tagNames);
    }

    public Answer answerQuestion(User author, Question question, String content) {
        return answerManager.answerQuestion(author, question, content);
    }

    public Comment addComment(User author, ICommentable entity, String content) {
        return commentManager.addComment(author, entity, content);
    }

    public void voteEntity(User voter, IVotable entity, int value) {
        voteManager.voteEntity(voter, entity, value);
    }

    public List<Question> searchQuestions(String query) {
        return searchManager.searchQuestions(query);
    }
}

public class Main {
    public static void main(String[] args) {
        IUserManager userManager = new UserManager();
        IQuestionManager questionManager = new QuestionManager(new TagManager());
        IAnswerManager answerManager = new AnswerManager();
        ICommentManager commentManager = new CommentManager();
        IVoteManager voteManager = new VoteManager();
        ISearchManager searchManager = new SearchManager();

        StackOverflow stackOverflow = new StackOverflow(userManager, questionManager, 
                                                        answerManager, commentManager, 
                                                        voteManager, searchManager);

        // Use stackOverflow object here
    }
}
