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
       planerList.add(new PlanerDetails(0L, "0",false));
       planerList.add(new PlanerDetails(1L, "1",false));
       planerList.add(new PlanerDetails(2L, "2",false));
       planerList.add(new PlanerDetails(3L, "3",false));
       planerList.add(new PlanerDetails(4L, "4",false));
       planerList.add(new PlanerDetails(5L, "5",false));
       planerList.add(new PlanerDetails(6L, "6",false));
       planerList.add(new PlanerDetails(7L, "7",false));
       planerList.add(new PlanerDetails(8L, "8",false));
       planerList.add(new PlanerDetails(9L, "9",false));
       planerList.add(new PlanerDetails(10L, "10",false));
       planerList.add(new PlanerDetails(11L, "11",false));
       planerList.add(new PlanerDetails(12L, "12",false));
       planerList.add(new PlanerDetails(13L, "13",false));
       planerList.add(new PlanerDetails(14L, "14",false));
       planerList.add(new PlanerDetails(15L, "15",false));
       planerList.add(new PlanerDetails(16L, "16",false));
       planerList.add(new PlanerDetails(17L, "17",false));
       planerList.add(new PlanerDetails(18L, "18",false));
       planerList.add(new PlanerDetails(19L, "19",false));
       planerList.add(new PlanerDetails(20L, "20",false));
       planerList.add(new PlanerDetails(21L, "21",false));
       planerList.add(new PlanerDetails(22L, "22",false));
       planerList.add(new PlanerDetails(23L, "23",false));
       planerList.add(new PlanerDetails(24L, "24",false));
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
