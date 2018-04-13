package ogunfemi.folaranmi.interactivestory.Model;

/**
 * Each page will contain two choices
 */

public class Page {

    private int imageId;
    private int textId; //will be a string from the xml so that it can be easily changed if need be.

    private Choice choice1;
    private Choice choice2;

    private Boolean isFinalPage = false;




    // Constructor for final pages that do not include any choices
    public Page(int imageId, int textId) {
        this.imageId = imageId;
        this.textId = textId;
        this.isFinalPage = true;
    }
    //Main Constructor
    public Page(int imageId, int textId, Choice choice1, Choice choice2) {
        this.imageId = imageId;
        this.textId = textId;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.isFinalPage = false;
    }

    public Boolean getFinalPage() {
        return isFinalPage;
    }

    public void setFinalPage(Boolean finalPage) {
        isFinalPage = finalPage;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public Choice getChoice1() {
        return choice1;
    }

    public void setChoice1(Choice choice1) {
        this.choice1 = choice1;
    }

    public Choice getChoice2() {
        return choice2;
    }

    public void setChoice2(Choice choice2) {
        this.choice2 = choice2;
    }

    public boolean isFinalPage() {
        return isFinalPage;
    }
}
