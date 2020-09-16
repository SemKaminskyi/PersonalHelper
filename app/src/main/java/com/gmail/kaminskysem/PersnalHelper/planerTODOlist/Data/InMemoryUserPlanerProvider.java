package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.Data;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.IUserPlanerDao;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;

import java.util.ArrayList;
import java.util.List;

// old name this class UserPlanerProvider
public class InMemoryUserPlanerProvider implements IUserPlanerDao {

    private final List<PlanerDetails> planerList=new ArrayList<>();;
    private final static InMemoryUserPlanerProvider instance = new InMemoryUserPlanerProvider();

   public InMemoryUserPlanerProvider() {
   }

    public static InMemoryUserPlanerProvider getInstance(){
        return instance;
    }


    public List<PlanerDetails> getPlanerDetails(){

        return planerList;
    }



    @Override
    public PlanerDetails getTaskById(long id) {
        return planerList.get(Math.toIntExact(id));
    }

    @Override
    public List<PlanerDetails> getTaskList() {
        return planerList;
    }

    @Override
    public void updateTaskWithId(PlanerDetails task) {
        planerList.remove(Math.toIntExact(task.getTaskID()));
        planerList.add (Math.toIntExact(task.getTaskID()), task);
    }

    @Override
    public void addNewTask(PlanerDetails planerDetails){
        planerList.add(planerDetails);
    }

    @Override
    public void delete(PlanerDetails task) {
       planerList.remove(task);
    }


}
