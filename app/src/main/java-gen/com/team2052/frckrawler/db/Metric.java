package com.team2052.frckrawler.db;

import java.util.List;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table METRIC.
 */
public class Metric implements java.io.Serializable {

    private Long id;
    private String name;
    private Integer category;
    private Integer type;
    private String data;
    private long game_id;

    /**
     * Used to resolve relations
     */
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    private transient MetricDao myDao;

    private List<MatchData> matchDataList;
    private List<PitData> pitDataList;

    public Metric() {
    }

    public Metric(Long id) {
        this.id = id;
    }

    public Metric(Long id, String name, Integer category, Integer type, String data, long game_id) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.type = type;
        this.data = data;
        this.game_id = game_id;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMetricDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getGame_id() {
        return game_id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }

    /**
     * To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity.
     */
    public List<MatchData> getMatchDataList() {
        if (matchDataList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MatchDataDao targetDao = daoSession.getMatchDataDao();
            List<MatchData> matchDataListNew = targetDao._queryMetric_MatchDataList(id);
            synchronized (this) {
                if (matchDataList == null) {
                    matchDataList = matchDataListNew;
                }
            }
        }
        return matchDataList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    public synchronized void resetMatchDataList() {
        matchDataList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity.
     */
    public List<PitData> getPitDataList() {
        if (pitDataList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PitDataDao targetDao = daoSession.getPitDataDao();
            List<PitData> pitDataListNew = targetDao._queryMetric_PitDataList(id);
            synchronized (this) {
                if (pitDataList == null) {
                    pitDataList = pitDataListNew;
                }
            }
        }
        return pitDataList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    public synchronized void resetPitDataList() {
        pitDataList = null;
    }

    /**
     * Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context.
     */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context.
     */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context.
     */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

}
