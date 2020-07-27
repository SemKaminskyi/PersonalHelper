package com.gmail.kaminskysem.PersnalHelper.Data;

import android.util.Log;
import android.widget.EditText;

import com.gmail.kaminskysem.PersnalHelper.Data.IUserPlanerProvider;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.planer.forRecyclerView.model.PlanerDetails;

import java.util.ArrayList;
import java.util.List;
// old name this class UserPlanerProvider
public class InMemoryPlanerProvider implements IUserPlanerProvider {
    private List<PlanerDetails> planerList = null;
    private final static InMemoryPlanerProvider instance = new InMemoryPlanerProvider();

    public InMemoryPlanerProvider() {

    }

    public static InMemoryPlanerProvider getInstance(){
        return instance;
    }

    public InMemoryPlanerProvider(List<PlanerDetails> planerList) {
        this.planerList = planerList;
    }
    @Override
    public PlanerDetails getTaskById(long id){
        return planerList.get((int) id);
    }

    @Override
    public List<PlanerDetails> getPlanerList(){
        return new ArrayList<>(planerList);
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
//        planerList.add(new PlanerDetails(-1,"new task",false));

        return planerList;
    }


}
