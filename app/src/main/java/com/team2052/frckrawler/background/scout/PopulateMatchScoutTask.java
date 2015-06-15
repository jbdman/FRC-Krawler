package com.team2052.frckrawler.background.scout;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.team2052.frckrawler.database.DBManager;
import com.team2052.frckrawler.db.Event;
import com.team2052.frckrawler.db.Robot;
import com.team2052.frckrawler.fragments.scout.ScoutMatchFragment;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam
 * @since 12/28/2014.
 */
public class PopulateMatchScoutTask extends AsyncTask<Void, Void, Void> {
    private final DBManager mDaoSession;
    private final Context context;
    private Event mEvent;
    private ScoutMatchFragment mFragment;
    private List<Robot> mRobots;

    public PopulateMatchScoutTask(@NotNull ScoutMatchFragment fragment, @NotNull Event event) {
        mFragment = fragment;
        this.context = fragment.getActivity();
        this.mDaoSession = DBManager.getInstance(context);
        this.mEvent = event;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.d("PopulateMatchScoutTask", "running PopulateMatchScoutTask");
        mRobots = mDaoSession.mGames.getRobots(mDaoSession.mEvents.getGame(mEvent));
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        List<String> robotListItems = new ArrayList<>();
        mFragment.setRobots(mRobots);

        for (Robot robot : mRobots) {
            robotListItems.add(String.format("%d, %s", robot.getTeam_id(), mDaoSession.mRobots.getTeam(robot).getName()));
        }

        mFragment.mRobotNames = robotListItems;
        mFragment.mRobotAutoComplete.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, robotListItems));
        mFragment.selectedRobot = mFragment.getRobots().get(0);
        mFragment.mRobotAutoComplete.setText(String.format("%d, %s", mFragment.getRobots().get(0).getTeam_id(), mDaoSession.mRobots.getTeam(mFragment.getRobots().get(0)).getName()));
        mFragment.updateMetricValues();
    }


}
