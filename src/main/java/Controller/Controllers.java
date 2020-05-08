package Controller;

import com.mycompany.maven.App;

public abstract class Controllers {
    App app;
    public void setMainApp(App app){
        this.app=app;
        this.onLoad();
    }
    //To be ovewritten
    void onLoad(){};
    //To be ovewritten
    void doOnCloseModal(Object response){}
}
