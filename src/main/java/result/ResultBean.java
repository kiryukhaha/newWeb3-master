package result;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import management.MXBean;
@ManagedBean(name = "resultBean")
@SessionScoped
public class ResultBean extends NotificationBroadcasterSupport implements Serializable , MXBean{
    private long sequenceNumber = 0;
    private long resultAmount = 0;
    private long missAmount = 0;
    private long svgAmount = 0;
    private double missPercentage = 0;

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

        checkPointAmountDivisor();
        return "update";
    }

    public boolean validate(Result result){
        return result.getX() >= -3 && result.getX() <= 5 && result.getY() > -3 && result.getY() < 3 &&
                result.getR() >= 1 && result.getR() <= 3;
    }

    public ArrayList<Result> getList(){
        return list;
    }



    @Override
    public long getResultAmount() {

        return list.size();
    }




    @Override
    public long getMissAmount() {

        return list.stream().filter(result -> result.getResult() == false).count();
    }

    @Override
    public long checkPointAmountDivisor() {
        long amount = getResultAmount();
        long misses = getMissAmount();
        resultAmount = amount;
        missAmount = misses;

            sendNotification(new Notification("Result amount can be divided by 15", this.getClass().getName(), sequenceNumber++, "Overall number of results: " + list.size() + "\n Missed results: " + misses));

        return amount;
    }

    @Override
    public double getMissPercentage() {


        return 0;
    }
}
