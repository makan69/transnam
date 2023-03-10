package transnam;

import java.lang.String;

public class PersianWord implements java.lang.Comparable {
    private String word, comparable;
    public final static String ZERO_WIDTH_JOIN = "‌";
    public PersianWord(String word) {
        setWord(word);
    }

    public void setWord(String word) {
        word = word.replace("ة", "ه").replace("ي", "ی")
                .replace("(", ZERO_WIDTH_JOIN).replace(")", ZERO_WIDTH_JOIN)
                .replace(" ", ZERO_WIDTH_JOIN).replace("ۀ", "ه")
                .replace("ؤ", "و").replace("إ", "ا").replace(" ّ", "")
                .replace("ً", "").replace("ٌ", "").replace("ٍ", "") // neglect An, En, On
                .replace("َ", "").replace("ُ", "").replace("ِ", "") // neglect A, E, O
                .replace("أ", "ا").replace("ء", "").replaceAll("[" + ZERO_WIDTH_JOIN + "]+", ZERO_WIDTH_JOIN);
        this.word = word;
        setComparable(word);
    }

    protected String getComparable() {
        return comparable;
    }

    private void setComparable(String word) {
        this.comparable = word.replace(ZERO_WIDTH_JOIN, "");
    }

    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(Object o) {
        PersianWord other = (PersianWord) o;
        // Use the normal string comparison of the comparable form.
        return getComparable().compareTo(other.getComparable());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comparable == null) ? 0 : comparable.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        }
        PersianWord that;
        if (o instanceof PersianWord) {
            that = (PersianWord) o;
        } else if (o instanceof String) {
            that = new PersianWord((String) o);
        } else {
            return false;
        }
        if (this.comparable.equals(that.getComparable())) {
            return true;
        }
        return false;
}}
