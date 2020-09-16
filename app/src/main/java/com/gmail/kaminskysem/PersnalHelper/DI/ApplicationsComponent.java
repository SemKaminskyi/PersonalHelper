package com.gmail.kaminskysem.PersnalHelper.DI;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.PlanerActivity;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetailsAdapter;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetailsViewHolder;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, DBModule.class})
@Singleton
public interface ApplicationsComponent {
    void inject(PlanerActivity activity);

    void inject(PlanerDetailsAdapter planerDetailsAdapter);

    void inject(PlanerDetailsViewHolder planerDetailsViewHolder);
}
