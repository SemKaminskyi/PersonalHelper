package com.gmail.kaminskysem.PersnalHelper.planer;

import com.gmail.kaminskysem.PersnalHelper.planer.forRecyclerView.model.CardDetails;

import java.util.ArrayList;
import java.util.List;

public class UserCardsProvider {
    List<CardDetails> getCardsDetails (){
        List<CardDetails>cardList = new ArrayList<>();
        cardList.add(new CardDetails("new task"));
        cardList.add(new CardDetails("new task1"));
        cardList.add(new CardDetails("new task2"));
        cardList.add(new CardDetails("new task3"));
        cardList.add(new CardDetails("new task4"));
        cardList.add(new CardDetails("new task5"));
        cardList.add(new CardDetails("new task6"));
        cardList.add(new CardDetails("new task7"));
        cardList.add(new CardDetails("new task8"));
        cardList.add(new CardDetails("new task9"));
        cardList.add(new CardDetails("new task10"));
        cardList.add(new CardDetails("new task11"));
        cardList.add(new CardDetails("new task12"));
        cardList.add(new CardDetails("new task13"));
        cardList.add(new CardDetails("new task14"));
        cardList.add(new CardDetails("new task15"));
        cardList.add(new CardDetails("new task16"));
        cardList.add(new CardDetails("new task17"));
        cardList.add(new CardDetails("new task18"));
        cardList.add(new CardDetails("new task19"));
        cardList.add(new CardDetails("new task20"));
        return cardList;
    }
}
