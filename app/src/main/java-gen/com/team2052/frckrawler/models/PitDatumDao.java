package com.team2052.frckrawler.models;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "PIT_DATUM".
 */
public class PitDatumDao extends AbstractDao<PitDatum, Long> {

    public static final String TABLENAME = "PIT_DATUM";
    private DaoSession daoSession;
    private Query<PitDatum> event_PitDatumListQuery;
    private Query<PitDatum> robot_PitDatumListQuery;
    private Query<PitDatum> metric_PitDatumListQuery;
    private String selectDeep;

    public PitDatumDao(DaoConfig config) {
        super(config);
    }

    public PitDatumDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PIT_DATUM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ROBOT_ID\" INTEGER NOT NULL ," + // 1: robot_id
                "\"METRIC_ID\" INTEGER NOT NULL ," + // 2: metric_id
                "\"EVENT_ID\" INTEGER NOT NULL ," + // 3: event_id
                "\"DATA\" TEXT," + // 4: data
                "\"LAST_UPDATED\" INTEGER);"); // 5: last_updated
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PIT_DATUM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PitDatum entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getRobot_id());
        stmt.bindLong(3, entity.getMetric_id());
        stmt.bindLong(4, entity.getEvent_id());
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(5, data);
        }
 
        java.util.Date last_updated = entity.getLast_updated();
        if (last_updated != null) {
            stmt.bindLong(6, last_updated.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PitDatum entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getRobot_id());
        stmt.bindLong(3, entity.getMetric_id());
        stmt.bindLong(4, entity.getEvent_id());
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(5, data);
        }
 
        java.util.Date last_updated = entity.getLast_updated();
        if (last_updated != null) {
            stmt.bindLong(6, last_updated.getTime());
        }
    }

    @Override
    protected final void attachEntity(PitDatum entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PitDatum readEntity(Cursor cursor, int offset) {
        PitDatum entity = new PitDatum( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getLong(offset + 1), // robot_id
                cursor.getLong(offset + 2), // metric_id
                cursor.getLong(offset + 3), // event_id
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // data
                cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)) // last_updated
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PitDatum entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRobot_id(cursor.getLong(offset + 1));
        entity.setMetric_id(cursor.getLong(offset + 2));
        entity.setEvent_id(cursor.getLong(offset + 3));
        entity.setData(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLast_updated(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PitDatum entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PitDatum entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PitDatum entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    /** Internal query to resolve the "pitDatumList" to-many relationship of Event. */
    public List<PitDatum> _queryEvent_PitDatumList(long event_id) {
        synchronized (this) {
            if (event_PitDatumListQuery == null) {
                QueryBuilder<PitDatum> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Event_id.eq(null));
                event_PitDatumListQuery = queryBuilder.build();
            }
        }
        Query<PitDatum> query = event_PitDatumListQuery.forCurrentThread();
        query.setParameter(0, event_id);
        return query.list();
    }

    /** Internal query to resolve the "pitDatumList" to-many relationship of Robot. */
    public List<PitDatum> _queryRobot_PitDatumList(long robot_id) {
        synchronized (this) {
            if (robot_PitDatumListQuery == null) {
                QueryBuilder<PitDatum> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Robot_id.eq(null));
                robot_PitDatumListQuery = queryBuilder.build();
            }
        }
        Query<PitDatum> query = robot_PitDatumListQuery.forCurrentThread();
        query.setParameter(0, robot_id);
        return query.list();
    }

    /** Internal query to resolve the "pitDatumList" to-many relationship of Metric. */
    public List<PitDatum> _queryMetric_PitDatumList(long metric_id) {
        synchronized (this) {
            if (metric_PitDatumListQuery == null) {
                QueryBuilder<PitDatum> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Metric_id.eq(null));
                metric_PitDatumListQuery = queryBuilder.build();
            }
        }
        Query<PitDatum> query = metric_PitDatumListQuery.forCurrentThread();
        query.setParameter(0, metric_id);
        return query.list();
    }

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getRobotDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getMetricDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T2", daoSession.getEventDao().getAllColumns());
            builder.append(" FROM PIT_DATUM T");
            builder.append(" LEFT JOIN ROBOT T0 ON T.\"ROBOT_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN METRIC T1 ON T.\"METRIC_ID\"=T1.\"_id\"");
            builder.append(" LEFT JOIN EVENT T2 ON T.\"EVENT_ID\"=T2.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }

    protected PitDatum loadCurrentDeep(Cursor cursor, boolean lock) {
        PitDatum entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Robot robot = loadCurrentOther(daoSession.getRobotDao(), cursor, offset);
        if (robot != null) {
            entity.setRobot(robot);
        }
        offset += daoSession.getRobotDao().getAllColumns().length;

        Metric metric = loadCurrentOther(daoSession.getMetricDao(), cursor, offset);
        if (metric != null) {
            entity.setMetric(metric);
        }
        offset += daoSession.getMetricDao().getAllColumns().length;

        Event event = loadCurrentOther(daoSession.getEventDao(), cursor, offset);
        if (event != null) {
            entity.setEvent(event);
        }

        return entity;
    }
    
    public PitDatum loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();

        String[] keyArray = new String[]{key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);

        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }

    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<PitDatum> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<PitDatum> list = new ArrayList<PitDatum>(count);

        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<PitDatum> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<PitDatum> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
    
    /**
     * Properties of entity PitDatum.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Robot_id = new Property(1, long.class, "robot_id", false, "ROBOT_ID");
        public final static Property Metric_id = new Property(2, long.class, "metric_id", false, "METRIC_ID");
        public final static Property Event_id = new Property(3, long.class, "event_id", false, "EVENT_ID");
        public final static Property Data = new Property(4, String.class, "data", false, "DATA");
        public final static Property Last_updated = new Property(5, java.util.Date.class, "last_updated", false, "LAST_UPDATED");
    }
 
}