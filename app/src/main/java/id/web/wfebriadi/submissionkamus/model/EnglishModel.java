package id.web.wfebriadi.submissionkamus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EnglishModel {
    private int id;
    private String word;
    private String translate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //------------
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    //------------
    public String getTranslate() {
        return translate;
    }
    public void setTranslate(String translate) {
        this.translate = translate;
    }

    protected EnglishModel(Parcel in) {
        id = in.readInt();
        word = in.readString();
        translate = in.readString();
    }

    public EnglishModel(){

    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(word);
        dest.writeString(translate);
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<EnglishModel> CREATOR = new Parcelable.Creator<EnglishModel>() {
        @Override
        public EnglishModel createFromParcel(Parcel in) {
            return new EnglishModel(in);
        }

        @Override
        public EnglishModel[] newArray(int size) {
            return new EnglishModel[size];
        }
    };
    public EnglishModel(int id, String word, String translate){
        this.id = id;
        this.word= word;
        this.translate = translate;
    }
    public EnglishModel(String word, String translate){
        this.word = word;
        this.translate = translate;
    }
}
