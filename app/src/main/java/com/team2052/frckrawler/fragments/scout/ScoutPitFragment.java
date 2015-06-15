package com.team2052.frckrawler.fragments.scout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.team2052.frckrawler.R;
import com.team2052.frckrawler.background.scout.PopulatePitMetricsTask;
import com.team2052.frckrawler.background.scout.PopulatePitRobotsTask;
import com.team2052.frckrawler.background.scout.SavePitMetricsTask;
import com.team2052.frckrawler.bluetooth.client.LoginHandler;
import com.team2052.frckrawler.bluetooth.client.events.ScoutSyncSuccessEvent;
import com.team2052.frckrawler.database.MetricValue;
import com.team2052.frckrawler.db.Event;
import com.team2052.frckrawler.db.Robot;
import com.team2052.frckrawler.db.RobotEvent;
import com.team2052.frckrawler.fragments.BaseFragment;
import com.team2052.frckrawler.views.metric.MetricWidget;
import com.team2052.frckrawler.util.ScoutUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * @author Adam
 */
public class ScoutPitFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
    @InjectView(R.id.metricWidgetList)
    public LinearLayout mLinearLayout;
    public Event mEvent;
    public Spinner mTeamSpinner;
    public List<RobotEvent> mRobots;
    @InjectView(R.id.comments)
    public EditText mComments;
    SavePitMetricsTask mSaveTask;
    private PopulatePitRobotsTask mTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scouting_pit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        mTeamSpinner = (Spinner) view.findViewById(R.id.robot);
        mTeamSpinner.setOnItemSelectedListener(this);
        loadAllData(ScoutUtil.getScoutEvent(getActivity(), mDbManager));
    }

    private void loadAllData(Event event) {
        if (event == null) {
            setErrorVisible(true);
            return;
        }

        if (mTask != null) {
            mTask.cancel(false);
        }

        mEvent = event;
        mTask = new PopulatePitRobotsTask(this, mEvent);
        mTask.execute();
    }

    public void setErrorVisible(boolean visible) {
        if (getView() != null) {
            if (visible) {
                getView().findViewById(R.id.error_message).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.scroll_view).setVisibility(View.GONE);
            } else {
                getView().findViewById(R.id.error_message).setVisibility(View.GONE);
                getView().findViewById(R.id.scroll_view).setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.scout, menu);
        menu.removeItem(R.id.action_view_match);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            if (mTeamSpinner.getSelectedItem() != null) {
                //Get data from view
                LoginHandler loginHandler = LoginHandler.getInstance(getActivity(), mDbManager);
                if (!loginHandler.isLoggedOn() && !loginHandler.loggedOnUserStillExists()) {
                    loginHandler.login(getActivity());
                    //TODO AUTO AFTER RELOG
                    Toast.makeText(getActivity(), "Try to save again", Toast.LENGTH_LONG).show();
                } else {
                    save();
                }
            }
        }
        return false;
    }

    public void save() {
        if (mRobots != null && !mRobots.isEmpty()) {
            Robot robot = mDbManager.mRobotEvents.getRobot(mRobots.get(mTeamSpinner.getSelectedItemPosition()));
            List<MetricValue> widgets = new ArrayList<>();

            for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
                widgets.add(((MetricWidget) mLinearLayout.getChildAt(i)).getValue());
            }

            mSaveTask = new SavePitMetricsTask(getActivity(), mEvent, robot, widgets, mComments.getText().toString());
            mSaveTask.execute();
        }
    }

    @SuppressWarnings("unused")
    public void onEvent(ScoutSyncSuccessEvent event) {
        loadAllData(ScoutUtil.getScoutEvent(getActivity(), mDbManager));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Robot robot = mDbManager.mRobotEvents.getRobot(mRobots.get(mTeamSpinner.getSelectedItemPosition()));
        new PopulatePitMetricsTask(this, mEvent, robot).execute();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
