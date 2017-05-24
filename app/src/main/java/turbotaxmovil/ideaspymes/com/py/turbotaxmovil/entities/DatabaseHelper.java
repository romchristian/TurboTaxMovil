package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.entities;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.R;

/**
 * Created by christian.romero on 18/04/2017.
 */

public class DatabaseHelper  extends OrmLiteSqliteOpenHelper {
    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "turbotax.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    private Dao<Impuesto, Integer> impuestoDao = null;
    private RuntimeExceptionDao<Impuesto, Integer> impuestoRuntimeDao = null;

    private Dao<Libro, Integer> libroDao = null;
    private RuntimeExceptionDao<Libro, Integer> libroRuntimeDao = null;

    private Dao<ClasificacionUsuario, Integer> clasificacionUsuarioDao = null;
    private RuntimeExceptionDao<ClasificacionUsuario, Integer> clasificacionUsuarioRuntimeDao = null;

    private Dao<RegistroParam, Integer> registroParamDao = null;
    private RuntimeExceptionDao<RegistroParam, Integer> registroParamRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Impuesto.class);
            TableUtils.createTable(connectionSource, Libro.class);
            TableUtils.createTable(connectionSource, ClasificacionUsuario.class);
            TableUtils.createTable(connectionSource, RegistroParam.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Impuesto.class, true);
            TableUtils.dropTable(connectionSource, Libro.class, true);
            TableUtils.dropTable(connectionSource, ClasificacionUsuario.class, true);
            TableUtils.dropTable(connectionSource, RegistroParam.class,true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<Impuesto, Integer> getImpuestoDao() throws SQLException {
        if (impuestoDao == null) {
            impuestoDao = getDao(Impuesto.class);
        }
        return impuestoDao;
    }

    public Dao<Libro, Integer> getLibroDao() throws SQLException {
        if (libroDao == null) {
            libroDao = getDao(Libro.class);
        }
        return libroDao;
    }

    public Dao<ClasificacionUsuario, Integer> getClasificacionUsuarioDao() throws SQLException {
        if (clasificacionUsuarioDao == null) {
            clasificacionUsuarioDao = getDao(ClasificacionUsuario.class);
        }
        return clasificacionUsuarioDao;
    }

    public Dao<RegistroParam, Integer> getRegistroParamDao() throws SQLException {
        if (registroParamDao == null) {
            registroParamDao = getDao(RegistroParam.class);
        }
        return registroParamDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */

    public RuntimeExceptionDao<Impuesto, Integer> getImpuestoDataDao() {
        if (impuestoRuntimeDao == null) {
            impuestoRuntimeDao = getRuntimeExceptionDao(Impuesto.class);
        }
        return impuestoRuntimeDao;
    }

    public RuntimeExceptionDao<Libro, Integer> getLibroDataDao() {
        if (libroRuntimeDao == null) {
            libroRuntimeDao = getRuntimeExceptionDao(Libro.class);
        }
        return libroRuntimeDao;
    }

    public RuntimeExceptionDao<ClasificacionUsuario, Integer> getClasificacionUsuarioDataDao() {
        if (clasificacionUsuarioRuntimeDao == null) {
            clasificacionUsuarioRuntimeDao = getRuntimeExceptionDao(ClasificacionUsuario.class);
        }
        return clasificacionUsuarioRuntimeDao;
    }

    public RuntimeExceptionDao<RegistroParam, Integer> getRegistroParamDataDao() {
        if (registroParamRuntimeDao == null) {
            registroParamRuntimeDao = getRuntimeExceptionDao(RegistroParam.class);
        }
        return registroParamRuntimeDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        impuestoDao = null;
        impuestoRuntimeDao = null;
        libroDao = null;
        impuestoRuntimeDao = null;
        clasificacionUsuarioDao = null;
        clasificacionUsuarioRuntimeDao = null;
        registroParamDao = null;
        registroParamRuntimeDao = null;
    }

}
