package result;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

@ManagedBean(name = "resultBean")
@SessionScoped
public class ResultBean extends NotificationBroadcasterSupport implements Serializable {


    private Result newResult;

    private ArrayList<Result> list = new ArrayList<Result>();


    public ResultBean(){

        this.newResult = new Result();



    }


    public ResultBean(Result result){
        this.newResult = result;

    }



    public Result getNewResult(){
        return newResult;
    }

    public void setNewResult(Result newResult){
        this.newResult = newResult;
    }

    public ArrayList<Result> getResults() {
        return list;
    }

    public void clear(){
        list.clear();
    }

    public String addResult(){

        newResult.checkHit();
        if (validate(newResult)){
        Result finalResult = new Result(newResult.getX(), newResult.getY(), newResult.getR(), newResult.getResult());

        list.add(finalResult);}
//        Result resultForTable = new Result(newResult.getX(), newResult.getY(), newResult.getR(), newResult.getResult());
//        newResult = new Result(0,0,1,false);
        sendNotification(new Notification("Result amount can be divided by 15", this.getClass().getName(), 15, "Overall number of results: " + 15 + "\n Missed results: " + 15));
        return "update";
    }

    public boolean validate(Result result){
        return result.getX() >= -3 && result.getX() <= 5 && result.getY() > -3 && result.getY() < 3 &&
                result.getR() >= 1 && result.getR() <= 3;
    }

    public ArrayList<Result> getList(){
        return list;
    }

}
