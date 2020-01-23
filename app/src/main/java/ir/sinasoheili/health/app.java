package ir.sinasoheili.health;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import model.DB_Illness;
import model.DB_Medicine;

public class app extends Application
{
    public static final String pref_name    = "DB_COPY";
    public static final String key_illness  = DB_Illness.getDbName();
    public static final String key_medicins = DB_Medicine.getDbName();

    @Override
    public void onCreate()
    {
        super.onCreate();

        Thread t_illness = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    boolean result_Illness = copy_database(DB_Illness.getDbName() , getApplicationContext().getAssets().open(DB_Illness.getDbName()));
                    String slog = result_Illness ? "Illness DB copied" : "Illness DB don't copied";
                    Log.i("tag" , slog);
                    SharedPreferences pref = getApplicationContext().getSharedPreferences(pref_name , MODE_PRIVATE);
                    pref.edit().putBoolean(key_illness , true).commit();

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        Thread t_medicine = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    boolean result_Medicine = copy_database(DB_Medicine.getDbName() , getApplicationContext().getAssets().open(DB_Medicine.getDbName()));
                    String slog = result_Medicine ? "Medicine DB copied" : "Medicine DB don't copied";
                    Log.i("tag" , slog);
                    SharedPreferences pref = getApplicationContext().getSharedPreferences(pref_name , MODE_PRIVATE);
                    pref.edit().putBoolean(key_medicins , true).commit();

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        t_illness.start();
        t_medicine.start();
    }

    private boolean copy_database(String db_name , InputStream is)
    {
        String path = "/data/data/"+getPackageName()+"/databases";

        try
        {
            File file  =  new File(path);
            if(! file.exists())
            {
                file.mkdir();
            }
            file = new File(path+"/"+db_name);
            if(! file.exists())
            {
                file.createNewFile();

                BufferedInputStream bis = new BufferedInputStream(is);
                FileOutputStream fos = new FileOutputStream(file);

                int r;
                while((r=bis.read()) != -1)
                {
                    fos.write(r);
                }

                bis.close();
                fos.flush();
                fos.close();

                return true;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            Log.i("tag" , "FileNotFoundException"+e.getMessage());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.i("tag" , "IOException"+e.getMessage());
        }
        return false;
    }
}