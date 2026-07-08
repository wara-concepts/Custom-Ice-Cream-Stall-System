// A customer's rating out of 5 and an optional comment left after the order.
public class K2521133Feedback {
    private final int rating;
    private final String comment;

    public K2521133Feedback(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return rating + "/5 - " + comment;
    }
}
