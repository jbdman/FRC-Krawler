package com.team2052.frckrawler.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "ROBOT_EVENT".
 */
public class RobotEventDao extends AbstractDao<RobotEvent, Long> {

    public static final String TABLENAME = "ROBOT_EVENT";

    /**
     * Properties of entity RobotEvent.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Robot_id = new Property(1, long.class, "robot_id", false, "ROBOT_ID");
        public final static Property Event_id = new Property(2, long.class, "event_id", false, "EVENT_ID");
        public final static Property Data = new Property(3, String.class, "data", false, "DATA");
    }

    ;

    private DaoSession daoSession;

    private Query<RobotEvent> event_RobotEventListQuery;
    private Query<RobotEvent> robot_RobotEventListQuery;

    public RobotEventDao(DaoConfig config) {
        super(config);
    }

    public RobotEventDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"ROBOT_EVENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE ," + // 0: id
                "\"ROBOT_ID\" INTEGER NOT NULL ," + // 1: robot_id
                "\"EVENT_ID\" INTEGER NOT NULL ," + // 2: event_id
                "\"DATA\" TEXT);"); // 3: data
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ROBOT_EVENT\"";
        db.execSQL(sql);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected void bindValues(SQLiteStatement stmt, RobotEvent entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getRobot_id());
        stmt.bindLong(3, entity.getEvent_id());

        String data = entity.getData();
        if (data != null) {
            stmt.bindString(4, data);
        }
    }

    @Override
    protected void attachEntity(RobotEvent entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /**
     * @inheritdoc
     */
    @Override
    public RobotEvent readEntity(Cursor cursor, int offset) {
        RobotEvent entity = new RobotEvent( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getLong(offset + 1), // robot_id
                cursor.getLong(offset + 2), // event_id
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // data
        );
        return entity;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void readEntity(Cursor cursor, RobotEvent entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRobot_id(cursor.getLong(offset + 1));
        entity.setEvent_id(cursor.getLong(offset + 2));
        entity.setData(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Long updateKeyAfterInsert(RobotEvent entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long getKey(RobotEvent entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

    /**
     * Internal query to resolve the "robotEventList" to-many relationship of Event.
     */
    public List<RobotEvent> _queryEvent_RobotEventList(long event_id) {
        synchronized (this) {
            if (event_RobotEventListQuery == null) {
                QueryBuilder<RobotEvent> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Event_id.eq(null));
                event_RobotEventListQuery = queryBuilder.build();
            }
        }
        Query<RobotEvent> query = event_RobotEventListQuery.forCurrentThread();
        query.setParameter(0, event_id);
        return query.list();
    }

    /**
     * Internal query to resolve the "robotEventList" to-many relationship of Robot.
     */
    public List<RobotEvent> _queryRobot_RobotEventList(long robot_id) {
        synchronized (this) {
            if (robot_RobotEventListQuery == null) {
                QueryBuilder<RobotEvent> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Robot_id.eq(null));
                robot_RobotEventListQuery = queryBuilder.build();
            }
        }
        Query<RobotEvent> query = robot_RobotEventListQuery.forCurrentThread();
        query.setParameter(0, robot_id);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getRobotDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getEventDao().getAllColumns());
            builder.append(" FROM ROBOT_EVENT T");
            builder.append(" LEFT JOIN ROBOT T0 ON T.\"ROBOT_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN EVENT T1 ON T.\"EVENT_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }

    protected RobotEvent loadCurrentDeep(Cursor cursor, boolean lock) {
        RobotEvent entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Robot robot = loadCurrentOther(daoSession.getRobotDao(), cursor, offset);
        if (robot != null) {
            entity.setRobot(robot);
        }
        offset += daoSession.getRobotDao().getAllColumns().length;

        Event event = loadCurrentOther(daoSession.getEventDao(), cursor, offset);
        if (event != null) {
            entity.setEvent(event);
        }

        return entity;
    }

    public RobotEvent loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();

        String[] keyArray = new String[]{key.toString()};
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

    /**
     * Reads all available rows from the given cursor and returns a list of new ImageTO objects.
     */
    public List<RobotEvent> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<RobotEvent> list = new ArrayList<RobotEvent>(count);

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

    protected List<RobotEvent> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }


    /**
     * A raw-style query where you can pass any WHERE clause and arguments.
     */
    public List<RobotEvent> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }

}
