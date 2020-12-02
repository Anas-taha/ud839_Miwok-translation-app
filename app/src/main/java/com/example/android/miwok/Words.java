package com.example.android.miwok;

import android.widget.ImageButton;
import android.widget.ImageView;



public class Words {

    private String mDefaultTranslation;

    private String mMiwokTranslation;
    private int mImgRscId=NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mAudioResourceId;




    public Words(String defaultTranslation, String miwokTranslation,int AudioResourceId){
        mDefaultTranslation = defaultTranslation ;
        mMiwokTranslation   = miwokTranslation;
        mAudioResourceId    = AudioResourceId;

    }
    public Words(String defaultTranslation, String miwokTranslation,int ImgRsId,int AudioResourceId){
        mDefaultTranslation = defaultTranslation ;
        mMiwokTranslation   = miwokTranslation;
        mImgRscId           = ImgRsId;
        mAudioResourceId    = AudioResourceId;

    }

    public int    getmImgRscId(){ return mImgRscId; }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public  int getmAudioResourceId(){
        return mAudioResourceId;
    }

    public boolean hasImage(){
        return mImgRscId != NO_IMAGE_PROVIDED;

    }


}
