package id.web.wfebriadi.submissionkamus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class IndonesiaModel {
    private int id;
    private String word;
    private String translate;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id= id;
    }
    //---------------
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    //--------------
    public String getTranslate() {
        return translate;
    }
    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public IndonesiaModel(){

    }

    protected IndonesiaModel(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.translate = in.readString();
    }

    public static final Parcelable.Creator<IndonesiaModel> CREATOR = new Parcelable.Creator<IndonesiaModel>() {
        @Override
        public IndonesiaModel createFromParcel(Parcel in) {
            return new IndonesiaModel(in);
        }

        @Override
        public IndonesiaModel[] newArray(int size) {
            return new IndonesiaModel[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.translate);
    }
    public IndonesiaModel(int id, String word, String translate){
        this.id = id;
        this.word = word;
        this.translate = translate;
    }
    public IndonesiaModel(String word, String translate){
        this.word = word;
        this.translate = translate;
    }
}
