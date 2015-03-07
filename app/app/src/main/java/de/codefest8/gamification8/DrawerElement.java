package de.codefest8.gamification8;

/**
 * Created by koerfer on 07.03.2015.
 */
public class DrawerElement {

    private String Label;
    private int ImageResourceId;

    public DrawerElement(String label, int imgResId)
    {
        this.Label = label;
        this.ImageResourceId = imgResId;
    }

    public int getImageResourceID()
    {
        return this.ImageResourceId;
    }

    public String getLabel()
    {
        return this.Label;
    }
}
