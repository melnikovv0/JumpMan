package lab;

public class Score {

    private String name;
    private int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.score;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Score s) {
            return this.name.equals(s.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

