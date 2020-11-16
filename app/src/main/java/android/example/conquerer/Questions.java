package android.example.conquerer;

public class Questions {

    private String mQuestions[] = {
            "Who is this actor or character ?",
            "Who is this ?",
            "Who is this actor or character ?",
            "Who is this ?",
            "Who is this actor or character ?",
            "Who is this ?",
            "Who is this actor or character?"
    };

    private String mChoices[] [] = {
            {"Chris Evans", "Chris Hemsworth", "Mark Ruffalo"},
            {"Chris Evans", "Tom Holland", "Robert Downey, Jr"},
            {"Thor", "Chris Hemsworth", "MIB"},
            {"Chris Evans", "spider-man", "Chadwick Boseman"},
            {"Thor", "Hulk", "Strongest Avenger"},
            {"Widow", "Assassin", "Black Widow"},
            {"spider-man", "Iron Man", "Captain America"},
    };

    private String mImages[] = {
            "q_1", // Chris Evans
            "q_2", // Robert Downey, Jr.
            "q_3", // Chris Hemsworth
            "q_4", // Chadwick Aaron Boseman
            "q_5", // Hulk
            "q_6", // Black Widow
            "q_7" // spider-man
    };

    private String mQuestionsType[] = {
           "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
            "radiobutton",
    };

    private String mCorrectAnswer[] = {
            "Chris Evans",
            "Robert Downey, Jr",
            "Chris Hemsworth",
            "Chadwick Boseman",
            "Hulk",
            "Black Widow",
            "spider-man"
    };

    public String getQuestions(int q) {
        String questions = mQuestions[q];
        return questions;
    }

    public String[] getChoices(int q) {
        String[] choice = mChoices[q];
        return choice;
    }

    public String getImages(int q) {
        String image = mImages[q];
        return image;
    }

    public String getType(int q) {
        String type = mQuestionsType[q];
        return type;
    }

    public int getLength() {
        return mQuestions.length;
    }

    public String getCorrectAnswer(int q) {

        String correct = mCorrectAnswer[q];

        return correct;
    }
}
