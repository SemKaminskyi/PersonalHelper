package com.gmail.kaminskysem.PersnalHelper.Data;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;

import java.util.ArrayList;
import java.util.List;
// old name this class UserPlanerProvider
public class InMemoryUserPlanerProvider implements IUserPlanerProvider {

    private final List<PlanerDetails> planerList;
    private final static InMemoryUserPlanerProvider instance = new InMemoryUserPlanerProvider();

 public InMemoryUserPlanerProvider (){
     planerList = new ArrayList<>();

     planerList.add(new PlanerDetails(0,"new task1",false));
     planerList.add(new PlanerDetails(1,"new task2",false));
 }

    public static InMemoryUserPlanerProvider getInstance(){
        return instance;
    }


    @Override
    public PlanerDetails getTaskById(long id){
        return  planerList.get((int) id);
    }

    @Override
    public List<PlanerDetails> getPlanerList(){
        return  new ArrayList<>(planerList);
    }

    @Override
    public void updateTaskWithID(long taskID, PlanerDetails task){
        planerList.remove((int)taskID);
        planerList.add ((int)taskID,task);
    }


    @Override
    public long addNewTask(PlanerDetails task){
        long taskId = planerList.size();
        PlanerDetails newTask = new PlanerDetails(taskId, task.getTask(),false);
        planerList.add(newTask);
        return taskId;
    }


    //TODO find class to this method

    public List<PlanerDetails> getPlanerDetails(){
        List<PlanerDetails>planerList = new ArrayList<>();

        return planerList;
    }


}
