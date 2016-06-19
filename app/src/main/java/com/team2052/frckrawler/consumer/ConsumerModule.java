package com.team2052.frckrawler.consumer;

import dagger.Module;
import dagger.Provides;

@Module
public class ConsumerModule {
    @Provides
    public ListViewConsumer provideListViewConsumer() {
        return new ListViewConsumer();
    }

    @Provides
    public SpinnerConsumer provideSpinnerConsumer() {
        return new SpinnerConsumer();
    }

    @Provides
    public BaseScoutDataConsumer provideBaseScoutDataConsumer() {
        return new BaseScoutDataConsumer();
    }

    @Provides
    public ServerFragmentConsumer provideServerFragmentConsumer(){
        return new ServerFragmentConsumer();
    }
}