package it.unicam.cs.ids.localplatform.model;

public abstract class Content implements Info {
    private InfoData infoData;

    public Content() {
        this.infoData = null; // Da completare
    }

    public InfoData getInfoData() {
        return infoData;
    }
}
