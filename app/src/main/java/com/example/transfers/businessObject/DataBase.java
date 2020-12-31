package com.example.transfers.businessObject;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.transfers.dataObject.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/*
public class DataBase  extends SQLiteOpenHelper {
    public static final String TAG = DataBase.class.getSimpleName();
    public static int flag;
    // Exact Name of you db file that you put in assets folder with extension.
    static String DB_NAME = "dbname.sqlite";
    private final Context myContext;
    String outFileName = "";
    private String DB_PATH;
    private SQLiteDatabase db;

    public DataBase(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        ContextWrapper cw = new ContextWrapper(context);
        DB_PATH = cw.getFilesDir().getAbsolutePath() + "/databases/";
        Log.e(TAG, "Databasehelper: DB_PATH " + DB_PATH);
        outFileName = DB_PATH + DB_NAME;
        File file = new File(DB_PATH);
        Log.e(TAG, "Databasehelper: " + file.exists());
        if (!file.exists()) {
            file.mkdir();
        }
    }

    */
/**
     * Creates a empty database on the system and rewrites it with your own database.
     *//*

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            //do nothing - database already exist
        } else {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    */
/**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     *//*

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(outFileName, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            try {
                copyDataBase();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    */
/**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     *//*


    private void copyDataBase() throws IOException {

        Log.i("Database",
                "New database is being copied to device!");
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        // Open your local db as the input stream
        InputStream myInput = null;
        try {
            myInput = myContext.getAssets().open(DB_NAME);
            // transfer bytes from the inputfile to the
            // outputfile
            myOutput = new FileOutputStream(DB_PATH + DB_NAME);
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
            Log.i("Database",
                    "New database has been copied to device!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        Log.e(TAG, "openDataBase: Open " + db.isOpen());
    }

    @Override
    public synchronized void close() {
        if (db != null)
            db.close();
        super.close();
    }


    public void onCreate(SQLiteDatabase arg0) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }


}*/

public class DataBase  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dataBase.db";

    public static final String TABLA_NOMBRE = "Nits";

    private static final String COD_EMPRESA = "C_EMP";
    private static final String IDENTIFICACION = "N_IDE";
    private static final String NOMBRE_PERSONA_JURIDICA = "NOM";
    private static final String TIPO_IDENTIFICACION = "IDE";
    private static final String DIRECCION = "DIR";
    private static final String TELEFONO = "TEL";
    private static final String APARTADO_AEREO = "AA";
    private static final String ESTADO = "EST";
    private static final String PRIMER_NOMBRE = "NOM1";
    private static final String SEGUNDO_NOMBRE = "NOM2";
    private static final String PRIMER_APELLIDO = "APE1";
    private static final String SEGUNDO_APELLIDO ="APE2";
    private static final String CORREO = "DIR_ELECT";
    private static final String DIGITO_VERIFICACION = "DIG";
    private static final String TIPO_NIT = "TIPO_NIT";
    private static final String TIPO_CONTRIBUYENTE = "TIP_CONTRIB";
    private static final String TIPO_TERCERO = "TIP_TERC";
    private static final String FECHA_CREACION = "FECHA";

    private static final String sentencia = "CREATE TABLE IF NOT EXISTS "
            + TABLA_NOMBRE + " (" + IDENTIFICACION + " INT PRIMARY KEY, "
            +COD_EMPRESA + " TEXT, " + NOMBRE_PERSONA_JURIDICA +" TEXT,  "
            +TIPO_IDENTIFICACION + " TEXT, "
            +DIRECCION + " TEXT, "
            +TELEFONO + " TEXT, "
            +APARTADO_AEREO + " TEXT, "
            +ESTADO + " TEXT, "
            +PRIMER_NOMBRE + " TEXT, "
            +SEGUNDO_NOMBRE + " TEXT, "
            +PRIMER_APELLIDO + " TEXT, "
            +SEGUNDO_APELLIDO + " TEXT, "
            +CORREO + " TEXT, "
            +DIGITO_VERIFICACION + " TEXT, "
            +TIPO_NIT + " TEXT, "
            +TIPO_CONTRIBUYENTE + " TEXT, "
            +TIPO_TERCERO + " TEXT, "
            +FECHA_CREACION + " TEXT "
            +")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sentencia);
    }

    public DataBase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void editarUsuario(User u, User user){
        SQLiteDatabase db = getWritableDatabase();
        try {
            if(db!=null) {
                db.execSQL("update Nits set N_IDE = '" + u.getId() + "', C_EMP = '" + u.getCodEmpresa() + "', " +
                        "NOM = '" + u.getNombrePersonaJuridica() + "', IDE = '" + u.getTipoId() + "', DIR = '" + u.getDireccion() + "', " +
                        "TEL = '" + u.getTelefono() + "', AA = '" + u.getApartadoAereo() + "'," +
                        " EST = '" + u.getEstado() + "' , NOM1 = '" + u.getPrimerNombre() + "', NOM2 = '" + u.getSegundoNombre() + "'," +
                        " APE1 = '" + u.getPriemrApellido() + "', APE2 = '" + u.getSegundoApellido() + "', " +
                        "DIR_ELECT = '" + u.getCorreo() + "', DIG = '" + u.getDigitoVerificacion() + "'," +
                        "  TIPO_NIT = '" + u.getTipoNit() + "', TIP_CONTRIB = '" + u.getTipoContribuyente() + "' , " +
                        "TIP_TERC = '" + u.getTipoTercero() + "', FECHA = '" + u.getFecha() + "' where N_IDE = '" + user.getId() + "'");
            }
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }

    }
    public void addNits (User u){
        SQLiteDatabase db = getWritableDatabase();
        try{
        if(db!=null){
            db.execSQL("INSERT INTO "+TABLA_NOMBRE+" VALUES ('"+u.getId()+"' , " +
                    "' "+u.getCodEmpresa()+"'," +
                    "' "+u.getNombrePersonaJuridica()+"'," +
                    "' "+u.getTipoId()+"'," +
                    "' "+u.getDireccion()+"'," +
                    "' "+u.getTelefono()+"'," +
                    "' "+u.getApartadoAereo()+"'," +
                    "' "+u.getEstado()+"'," +
                    "' "+u.getPrimerNombre()+"'," +
                    "' "+u.getSegundoNombre()+"'," +
                    "' "+u.getPriemrApellido()+"'," +
                    "' "+u.getSegundoApellido()+"'," +
                    "' "+u.getCorreo()+"'," +
                    "' "+u.getDigitoVerificacion()+"'," +
                    "' "+u.getTipoNit()+"'," +
                    "' "+u.getTipoContribuyente()+"'," +
                    "' "+u.getTipoTercero()+"'," +
                    "' "+u.getFecha()+"')");
            db.close();
        }
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
    }

    public List<User> mostrarUsuarios(){
        SQLiteDatabase db = getReadableDatabase();
        List<User> usuarios = new ArrayList<>();
        try{
        Cursor cursor =db.rawQuery("SELECT N_IDE, NOM, IDE , DIG FROM Nits ", null);

        User u = new User();
        if(cursor.moveToFirst()){
            do{
                u = new User();
                u.setId(cursor.getInt(cursor.getColumnIndex("N_IDE")));
                u.setNombrePersonaJuridica(cursor.getString(cursor.getColumnIndex("NOM")));
                u.setTipoNit(cursor.getString(cursor.getColumnIndex("IDE")));
                u.setDigitoVerificacion(cursor.getString(cursor.getColumnIndex("DIG")));
                usuarios.add(u);
            }while (cursor.moveToNext());
        }
        if (cursor != null) cursor.close();
        if (db != null) db.close();
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
        return usuarios;
    }
    public User cargarUsuario(String codigo){
        SQLiteDatabase db = getReadableDatabase();
        List<User> usuarios = new ArrayList<>();
        User u = new User();
        try{
        Cursor cursor =db.rawQuery("SELECT * FROM Nits where N_IDE ='"+codigo+"'", null);

        if(cursor.moveToFirst()){
            do{
                u = new User();
                u.setId(cursor.getInt(cursor.getColumnIndex("N_IDE")));
                u.setNombrePersonaJuridica(cursor.getString(cursor.getColumnIndex("NOM")));
                u.setTipoNit(cursor.getString(cursor.getColumnIndex("IDE")));
                u.setDigitoVerificacion(cursor.getString(cursor.getColumnIndex("DIG")));
                u.setDireccion(cursor.getString(cursor.getColumnIndex("DIR")));
                u.setTelefono(cursor.getString(cursor.getColumnIndex("TEL")));
                u.setApartadoAereo(cursor.getString(cursor.getColumnIndex("AA")));
                u.setEstado(cursor.getString(cursor.getColumnIndex("EST")));
                u.setPrimerNombre(cursor.getString(cursor.getColumnIndex("NOM1")));
                u.setSegundoNombre(cursor.getString(cursor.getColumnIndex("NOM2")));
                u.setPriemrApellido(cursor.getString(cursor.getColumnIndex("APE1")));
                u.setSegundoApellido(cursor.getString(cursor.getColumnIndex("APE2")));
                u.setTipoId(cursor.getString(cursor.getColumnIndex("TIPO_NIT")));
                u.setTipoContribuyente(cursor.getString(cursor.getColumnIndex("TIP_CONTRIB")));
                u.setTipoTercero(cursor.getString(cursor.getColumnIndex("TIP_TERC")));
                u.setFecha(cursor.getString(cursor.getColumnIndex("FECHA")));

            }while (cursor.moveToNext());

        }
        if (cursor != null) cursor.close();
        if (db != null) db.close();
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
        return u;
    }

    public void eliminarUsuario(String codigo){
        SQLiteDatabase db = getWritableDatabase();
        try{
          db.execSQL("DELETE FROM "+TABLA_NOMBRE+ " WHERE N_IDE = '"+codigo+"' ");
        }catch (Exception e) {
        } finally {
            if (db != null) db.close();
        }
    }

}
