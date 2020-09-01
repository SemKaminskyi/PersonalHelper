package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;

import java.util.ArrayList;
import java.util.List;

public class UserPlanerProvider {
    private final static UserPlanerProvider instance =new UserPlanerProvider();
    private static List<PlanerDetails> planerList;

    public  static UserPlanerProvider getInstance(){
        return  instance;
    }

    private UserPlanerProvider (){
        planerList = new ArrayList<>();

        planerList.add(new PlanerDetails(0,"new task1",false));
        planerList.add(new PlanerDetails(1,"new task2",false));
    }

    List<PlanerDetails> getCardsDetails (){
        List<PlanerDetails>cardList = new ArrayList<>();
        cardList.add(new PlanerDetails(-1,"new task", false));

        return cardList;
    }


    public PlanerDetails getTaskById(long id){
        return  planerList.get((int) id);
    }


    public List<PlanerDetails> getPlanerList(){
        return  new ArrayList<>(planerList);
    }


    public void updateTaskWithID(long taskID, PlanerDetails task){
        planerList.remove((int)taskID);
        planerList.add ((int)taskID,task);
    }


    public long addNewTask(PlanerDetails task){
        long taskId = planerList.size();
        PlanerDetails newTask = new PlanerDetails(taskId, task.getTask(),false);
        planerList.add(newTask);
        return taskId;
    }

}
