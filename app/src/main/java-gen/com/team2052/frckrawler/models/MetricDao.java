package com.team2052.frckrawler.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "METRIC".
 */
public class MetricDao extends AbstractDao<Metric, Long> {

    public static final String TABLENAME = "METRIC";
    private DaoSession daoSession;
    private Query<Metric> season_MetricListQuery;
    private String selectDeep;

    public MetricDao(DaoConfig config) {
        super(config);
    }

    public MetricDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"METRIC\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"CATEGORY\" INTEGER," + // 2: category
                "\"TYPE\" INTEGER," + // 3: type
                "\"DATA\" TEXT," + // 4: data
                "\"SEASON_ID\" INTEGER NOT NULL ," + // 5: season_id
                "\"ENABLED\" INTEGER NOT NULL ," + // 6: enabled
                "\"PRIORITY\" INTEGER NOT NULL );"); // 7: priority
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"METRIC\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Metric entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }

        Integer category = entity.getCategory();
        if (category != null) {
            stmt.bindLong(3, category);
        }

        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(4, type);
        }

        String data = entity.getData();
        if (data != null) {
            stmt.bindString(5, data);
        }
        stmt.bindLong(6, entity.getSeason_id());
        stmt.bindLong(7, entity.getEnabled() ? 1L : 0L);
        stmt.bindLong(8, entity.getPriority());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Metric entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }

        Integer category = entity.getCategory();
        if (category != null) {
            stmt.bindLong(3, category);
        }

        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(4, type);
        }

        String data = entity.getData();
        if (data != null) {
            stmt.bindString(5, data);
        }
        stmt.bindLong(6, entity.getSeason_id());
        stmt.bindLong(7, entity.getEnabled() ? 1L : 0L);
        stmt.bindLong(8, entity.getPriority());
    }

    @Override
    protected final void attachEntity(Metric entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public Metric readEntity(Cursor cursor, int offset) {
        Metric entity = new Metric( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
                cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // category
                cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // type
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // data
                cursor.getLong(offset + 5), // season_id
                cursor.getShort(offset + 6) != 0, // enabled
                cursor.getInt(offset + 7) // priority
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, Metric entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCategory(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setData(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSeason_id(cursor.getLong(offset + 5));
        entity.setEnabled(cursor.getShort(offset + 6) != 0);
        entity.setPriority(cursor.getInt(offset + 7));
    }

    @Override
    protected final Long updateKeyAfterInsert(Metric entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(Metric entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Metric entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    /**
     * Internal query to resolve the "metricList" to-many relationship of Season.
     */
    public List<Metric> _querySeason_MetricList(long season_id) {
        synchronized (this) {
            if (season_MetricListQuery == null) {
                QueryBuilder<Metric> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Season_id.eq(null));
                season_MetricListQuery = queryBuilder.build();
            }
        }
        Query<Metric> query = season_MetricListQuery.forCurrentThread();
        query.setParameter(0, season_id);
        return query.list();
    }

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getSeasonDao().getAllColumns());
            builder.append(" FROM METRIC T");
            builder.append(" LEFT JOIN SEASON T0 ON T.\"SEASON_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }

    protected Metric loadCurrentDeep(Cursor cursor, boolean lock) {
        Metric entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Season season = loadCurrentOther(daoSession.getSeasonDao(), cursor, offset);
        if (season != null) {
            entity.setSeason(season);
        }

        return entity;
    }

    public Metric loadDeep(Long key) {
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
    public List<Metric> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Metric> list = new ArrayList<Metric>(count);

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

    protected List<Metric> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    /**
     * A raw-style query where you can pass any WHERE clause and arguments.
     */
    public List<Metric> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }

    /**
     * Properties of entity Metric.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Category = new Property(2, Integer.class, "category", false, "CATEGORY");
        public final static Property Type = new Property(3, Integer.class, "type", false, "TYPE");
        public final static Property Data = new Property(4, String.class, "data", false, "DATA");
        public final static Property Season_id = new Property(5, long.class, "season_id", false, "SEASON_ID");
        public final static Property Enabled = new Property(6, boolean.class, "enabled", false, "ENABLED");
        public final static Property Priority = new Property(7, int.class, "priority", false, "PRIORITY");
    }

}
