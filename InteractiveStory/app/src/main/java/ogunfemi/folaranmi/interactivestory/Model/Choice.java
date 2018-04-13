package ogunfemi.folaranmi.interactivestory.Model;

/**
 * Choices Consist of text descriptions and a page number destination
 */

public class Choice {
    private int textId;
    private int nextPage;

    public Choice(int textId, int nextPageInt) {
        this.textId = textId;
        nextPage = nextPageInt;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
